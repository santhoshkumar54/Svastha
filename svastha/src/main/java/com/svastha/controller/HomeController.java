package com.svastha.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.svastha.dto.WeatherDTO;
import com.svastha.entity.District;
import com.svastha.entity.Manual;
import com.svastha.entity.Thaluk;
import com.svastha.entity.Village;
import com.svastha.repository.DistrictRepository;
import com.svastha.repository.MasterManualRepository;
import com.svastha.repository.ThalukRepository;
import com.svastha.repository.VillageRepository;

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

	@GetMapping("/hello")
	public String index() {
		fetchWeather();
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

	public void fetchWeather() {
		String apiUrl = "https://api.tomorrow.io/v4/weather/forecast";
		String location = "11.2304,79.5133";
		String timesteps = "1d";
		String apikey = "04sfV0yFAOZ5EQyTQ7jrexo9dTXt7SIx";

		// Build the URL with parameters
		apiUrl = UriComponentsBuilder.fromHttpUrl(apiUrl).queryParam("location", location)
				.queryParam("timesteps", timesteps).queryParam("apikey", apikey).build().toUriString();
		RestTemplate restTemplate = new RestTemplate();
		WeatherDTO w = restTemplate.getForObject(apiUrl, WeatherDTO.class);

		System.out.println(w.getTimelines().getDaily().get(0).getValues().getTemperatureMin());
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