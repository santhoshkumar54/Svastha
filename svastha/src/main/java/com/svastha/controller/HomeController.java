package com.svastha.controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.svastha.dto.Daily;
import com.svastha.dto.GeoMapDTO;
import com.svastha.dto.WeatherDTO;
import com.svastha.entity.District;
import com.svastha.entity.Manual;
import com.svastha.entity.Thaluk;
import com.svastha.entity.Village;
import com.svastha.entity.Weather;
import com.svastha.repository.DistrictRepository;
import com.svastha.repository.MasterManualRepository;
import com.svastha.repository.ThalukRepository;
import com.svastha.repository.VillageRepository;
import com.svastha.repository.WeatherRepository;

@RestController
public class HomeController {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private MasterManualRepository manualDao;

	@Autowired
	private VillageRepository villageDao;

	@Autowired
	private DistrictRepository districtDao;

	@Autowired
	private ThalukRepository thalukDao;

	@Autowired
	private WeatherRepository weatherDao;

	@Scheduled(cron = "0 9 1 * * ?") // Execute at 10:00 AM every day
	public void callAPI() {
		System.out.println("Weather");
		fetchWeather();
	}

	@GetMapping("/hello")
	public String index() throws IllegalArgumentException, IllegalAccessException {
		Field[] fields = Thaluk.class.getDeclaredFields();
		List<Thaluk> thaluk = thalukDao.findAll();
		for (Thaluk th : thaluk) {
			 for (Field field : fields) {
				 field.setAccessible(true);
				 Object value = field.get(th);
				 if (value instanceof District) {
                     District district = (District) value;
                     if(district != null)
                     {
                     System.out.println(district.getName()); 
                     }
                 } else {
                	 if(value != null)
                	 System.out.println(value.toString());
                 }
			 }
		}
		return "Hello Deepan!";
	}

	@GetMapping("/downloadapk")
	public ResponseEntity downloadapk() throws IOException {
		File apk = new File("/dev/svastha/APK/svastha.apk");
		Path path = Paths.get(apk.getAbsolutePath());
		ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentDisposition(ContentDisposition.builder("attachment").filename(apk.getName()).build());
		return ResponseEntity.ok().contentLength(apk.length()).contentType(MediaType.APPLICATION_OCTET_STREAM)
				.headers(httpHeaders).body(resource);
	}

	@GetMapping("/getManual")
	public List<Manual> getManual() {
		return manualDao.findAll();
	}

	@GetMapping("/getVillages")
	public List<Village> getVillages() {
		return villageDao.findAll();
	}

	@GetMapping("/getThaluk")
	public List<Thaluk> getThaluk() {
		return thalukDao.findAll();
	}

	@GetMapping("/getDistrict")
	public List<District> getDistrict() {
		return districtDao.findAll();
	}
	
	@GetMapping("/getWeather")
	public List<Weather> getWeather(@RequestParam(required = false) String thaluk,@RequestParam(required = false) Date capturedDateStart,@RequestParam(required = false) Date capturedDateEnd)
	{
		return weatherDao.findByDateAndLocation(thaluk,capturedDateStart,capturedDateEnd,5);
	}

	@GetMapping("/fetchWeather")
	public void fetchWeather() {

		Map<String, String> locations = getThaluks();
		String timesteps = "1d";
		String apikey = "04sfV0yFAOZ5EQyTQ7jrexo9dTXt7SIx";

		for (Map.Entry<String, String> entry : locations.entrySet()) {
			try {
				String apiUrl = "https://api.tomorrow.io/v4/weather/forecast";
				String key = entry.getKey();
				String val = entry.getValue();
				System.out.println(key + "   " + val);

				apiUrl = UriComponentsBuilder.fromHttpUrl(apiUrl).queryParam("location", val)
						.queryParam("timesteps", timesteps).queryParam("apikey", apikey).build().toUriString();
				RestTemplate restTemplate = new RestTemplate();
				WeatherDTO wd = restTemplate.getForObject(apiUrl, WeatherDTO.class);
				List<Daily> daily = wd.getTimelines().getDaily();
				Daily d = daily.get(0);
				Weather w = new Weather();
				w.setCapturedDate(d.getTime());
				w.setThaluk(key);
				w.setMinTemp(d.getValues().getTemperatureMin());
				w.setMaxTemp(d.getValues().getTemperatureMax());
				w.setMinHumidity(d.getValues().getHumidityMin());
				w.setMaxHumidity(d.getValues().getHumidityMax());
				w.setMinPrec(d.getValues().getPrecipitationProbabilityMin());
				w.setMaxPrec(d.getValues().getPrecipitationProbabilityMax());
				w.setMinUvIndex(d.getValues().getUvIndexMin());
				w.setMaxUvIndex(d.getValues().getUvIndexMax());
				weatherDao.save(w);

			} catch (Exception e) {
				System.out.println("Error in fetching weather " + e);
			}
		}
	}

	public Map<String, String> getThaluks() {
		Map<String, String> locations = new HashMap<>();

		String apiKey = "65e4896cbe6a7019036672grs89364e";
		List<Thaluk> thaluks = thalukDao.findAll();
		for (Thaluk thaluk : thaluks) {
			try {
				String apiURL = "https://geocode.maps.co/search";
				apiURL = UriComponentsBuilder.fromHttpUrl(apiURL).queryParam("q", thaluk.getName())
						.queryParam("api_key", apiKey).build().toUriString();
				System.out.println(apiURL);

				RestTemplate restTemplate = new RestTemplate();
				GeoMapDTO[] w = restTemplate.getForObject(apiURL, GeoMapDTO[].class);
				System.out.println(w[0].getLat());
				locations.put(thaluk.getName(), w[0].getLat() + "," + w[0].getLon());
				Thread.sleep(1000);

			} catch (Exception e) {
				System.out.println("error in fetching geo location " + e);
			}
		}
		return locations;
	}

	// under construction
	public void sendEmailToMultipleRecepients(String[] to, String subject, String body) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(body);

		mailSender.send(message);
	}
}