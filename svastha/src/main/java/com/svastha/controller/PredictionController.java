package com.svastha.controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.svastha.entity.AwdData;
import com.svastha.entity.District;
import com.svastha.entity.FarmProjects;
import com.svastha.entity.Manual;
import com.svastha.entity.ProjectImages;
import com.svastha.entity.QueueJob;
import com.svastha.entity.Thaluk;
import com.svastha.entity.Users;
import com.svastha.entity.Weather;
import com.svastha.entity.viewNotification;
import com.svastha.logs.LogServiceFactory;
import com.svastha.repository.AwdDataRepository;
import com.svastha.repository.MasterManualRepository;
import com.svastha.repository.QueueJobRepository;
import com.svastha.repository.ThalukRepository;
import com.svastha.repository.UserRepository;
import com.svastha.repository.ViewNotificationRepository;
import com.svastha.repository.WeatherRepository;
import com.svastha.service.FilesStorageService;
import com.svastha.service.PredictionService;
import com.svastha.util.Daily;
import com.svastha.util.GeoMapDTO;
import com.svastha.util.WeatherDTO;

@RestController
public class PredictionController {

	@Autowired
	private QueueJobRepository jobDao;

	@Autowired
	FilesStorageService storageService;

	@Autowired
	private UserRepository userDao;
	
	@Autowired
	private PredictionService predService;

	public static final String SEPARATOR = FileSystems.getDefault().getSeparator();

	public static List<String> jobTypes = new ArrayList<>();

	@PostConstruct
	public void init() {
		jobTypes.add("FindDisease");
	}

	@PostMapping("/addQueueJob")
	public @ResponseBody String uploadPhoto(@RequestParam MultipartFile file, @RequestParam String userId,
			@RequestParam String jobType) {
		
		  if (!jobTypes.contains(jobType)) {
	            return ResponseEntity.badRequest().body(Map.of("error", "Invalid job type")).toString();
	        }
		Long uid = Long.parseLong(userId);
		Users u = userDao.findById(uid).get();

		String folderPath = SEPARATOR + jobType;
		Path p = storageService.createFolder(folderPath);

		if (file == null) {
			LogServiceFactory.getService()
					.logError("No image recieved. Uploaded by " + userId + ". Uploaded in predictions", null);
		}

			QueueJob i = new QueueJob();
			i.setCreatedBy(u);
			i.setJobType(jobType);
			String filePath = storageService.save(file, p);
			i.setFileName(filePath);
			i.setPath(p.toString());
			i.setImageUrl("/farmer/images" + folderPath + SEPARATOR + filePath);
			i.setStatus("NEW");
			i = jobDao.save(i);

		 Long jobId = i.getPk1();
		 predService.predictDisease(i.getPath()+SEPARATOR+filePath, jobId);
		return i.getPk1().toString();
	}

	@GetMapping("/getResult")
	public @ResponseBody QueueJob getResult(@RequestParam Long jobId)
	{
		return jobDao.findById(jobId).get();
	}

	@GetMapping("/testPrediction")
	public void getResult(@RequestParam Long jobId,@RequestParam String filePath)
	{
		 predService.predictDisease(filePath, jobId);
	}	
}