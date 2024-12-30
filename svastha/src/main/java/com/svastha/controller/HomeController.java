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

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.svastha.entity.AwdData;
import com.svastha.entity.District;
import com.svastha.entity.Manual;
import com.svastha.entity.Thaluk;
import com.svastha.entity.Weather;
import com.svastha.repository.AwdDataRepository;
import com.svastha.repository.MasterManualRepository;
import com.svastha.repository.ThalukRepository;
import com.svastha.repository.ViewNotificationRepository;
import com.svastha.repository.WeatherRepository;
import com.svastha.util.Daily;
import com.svastha.util.GeoMapDTO;
import com.svastha.util.WeatherDTO;

@RestController
public class HomeController {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private MasterManualRepository manualDao;

	@Autowired
	private ThalukRepository thalukDao;

	@Autowired
	private WeatherRepository weatherDao;

	@Autowired
	private AwdDataRepository awdDao;

	@Autowired
	private ViewNotificationRepository notificationDao;

	@Value("${upload.directory}")
	private String uploadDirectory;

	@Value("${environment}")
	private String environment;

	private String apkFilePath;

	private boolean shallFetchWeather;

	private static final String API_KEY_HEADER = "API-KEY";
	private static final String VALID_API_KEY = "93e4b7d5-f9a6-4c2e-b1fc-7a41cb5f4e85";

	@PostConstruct
	public void init() {
		System.out.println("Upload Directory: " + uploadDirectory);
		apkFilePath = uploadDirectory + "APK/svastha.apk";
		System.out.println("APK Directory: " + apkFilePath);
		shallFetchWeather = (environment.equals("test")) ? false : true;
		System.out.println("shallFetchWeather " + shallFetchWeather);
	}

	@Scheduled(cron = "0 30 6 * * *")
	public void fetchWeatherFirstIteration() {
		if (shallFetchWeather) {
			System.out.println("Weather Scheduler called");
			fetchWeather(1, 3);
		}
	}

	@Scheduled(cron = "0 45 7 * * *")
	public void fetchWeatherSecondIteration() {
		if (shallFetchWeather) {
			System.out.println("Weather Scheduler called");
			fetchWeather(4, 7);
		}
	}

	@GetMapping("/hello")
	public String index() throws IllegalArgumentException, IllegalAccessException {
		System.out.println("hello Scheduler called");

		Field[] fields = Thaluk.class.getDeclaredFields();
		List<Thaluk> thaluk = thalukDao.findAll();
		for (Thaluk th : thaluk) {
			for (Field field : fields) {
				field.setAccessible(true);
				Object value = field.get(th);
				if (value instanceof District) {
					District district = (District) value;
					if (district != null) {
						System.out.println(district.getName());
					}
				} else {
					if (value != null)
						System.out.println(value.toString());
				}
			}
		}
		return "Hello Deepan!";
	}

	@GetMapping("/downloadapk")
	public ResponseEntity downloadapk() throws IOException {
		File apk = new File(apkFilePath);
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

	@GetMapping("/getWeather")
	public List<Weather> getWeather(@RequestParam(required = false) String thaluk,
			@RequestParam(required = false) Date capturedDateStart,
			@RequestParam(required = false) Date capturedDateEnd) {
		return weatherDao.findByDateAndLocation(thaluk, capturedDateStart, capturedDateEnd, 5);
	}

	@GetMapping("/fetchWeather")
	public void fetchWeather(int minValue, int maxValue) {
		System.out.println("Fetching weather" + new java.util.Date());

		Map<String, String> locations = getThaluks(minValue, maxValue);
		System.out.println("Fetching weather" + locations.size());
		String timesteps = "1d";
		String apikey = "04sfV0yFAOZ5EQyTQ7jrexo9dTXt7SIx";

		for (Map.Entry<String, String> entry : locations.entrySet()) {
			try {
				Thread.sleep(2000);
				String apiUrl = "https://api.tomorrow.io/v4/weather/forecast";
				String key = entry.getKey();
				String val = entry.getValue();
				System.out.println("Fetching weather for" + key + "   " + val);

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

	public Map<String, String> getThaluks(int minValue, int maxValue) {
		Map<String, String> locations = new HashMap<>();

		String apiKey = "65e4896cbe6a7019036672grs89364e";
		List<Thaluk> thaluks = thalukDao.findByDistrictPk1Between(minValue, maxValue);
		for (Thaluk thaluk : thaluks) {
			try {
				Thread.sleep(2000);
				System.out.println("Fetching weather for" + thaluk.getName());
				String apiURL = "https://geocode.maps.co/search";
				apiURL = UriComponentsBuilder.fromHttpUrl(apiURL).queryParam("q", thaluk.getName())
						.queryParam("api_key", apiKey).build().toUriString();
				System.out.println(apiURL);

				RestTemplate restTemplate = new RestTemplate();
				GeoMapDTO[] w = restTemplate.getForObject(apiURL, GeoMapDTO[].class);
				System.out.println(w[0].getLat());
				locations.put(thaluk.getName(), w[0].getLat() + "," + w[0].getLon());

			} catch (Exception e) {
				System.out.println("error in fetching geo location " + e);
			}
		}
		return locations;
	}

	@PostMapping("/third-party/awddata")
	public ResponseEntity<String> saveAwdData(HttpServletRequest request, @RequestBody AwdData data) {
		String apiKey = request.getHeader(API_KEY_HEADER);
		System.out.println("in");
		if (VALID_API_KEY.equals(apiKey)) {
			try {
				awdDao.save(data);
				return ResponseEntity.ok("SUCCESS");

			} catch (Exception ex) {
				return ResponseEntity.badRequest().body("FAILED");
			}
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UNAUTHORIZED");
		}
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