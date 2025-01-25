package com.svastha.service;

import java.awt.desktop.QuitEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.svastha.entity.QueueJob;
import com.svastha.logs.LogServiceFactory;
import com.svastha.repository.ActivityTrackersRepository;
import com.svastha.repository.QueueJobRepository;
import com.svastha.util.ScheduleServiceUtil;

@Service
public class PredictionService {

	@Autowired
	private QueueJobRepository queueDao;

	@Async
	public void predictDisease(String filePath, Long jobId) {
		QueueJob job = queueDao.findById(jobId).get();
		try {
			ProcessBuilder processBuilder = new ProcessBuilder("python3", "main.py", filePath);
			processBuilder.directory(new File("/home/svastha/Python/padDet"));
			processBuilder.redirectErrorStream(true);
			Process process = processBuilder.start();
			int status = 0;
			StringBuilder output = new StringBuilder();
			StringBuilder result = new StringBuilder();
			// Capture and log the output (optional)
			try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
				String line;
				while ((line = reader.readLine()) != null) {
					output.append(line);
					if (line.contains("Predicted Disease:")) {
						status = status + 1;
						result.append(line);
					}
					if (line.contains("Confidence")) {
						status = status + 1;
						result.append(line);
					}
				}
				System.out.println(output.toString());
			}
			String response = "";

			// Wait for the process to complete
			int exitCode = process.waitFor();

			if (exitCode != 0) {
				job.setResult("Error in executing Python script");
				job.setStatus("FAILED");
			}
			if (output == null) {
				job.setResult("No output generated");
				job.setStatus("FAILED");
			} else if (status != 0) {
				job.setResult(result.toString());
				job.setStatus("COMPLETED");
			} else {
				LogServiceFactory.getService().logError(
						"Script did not generate a response for jobid :" + jobId + " response = " + output, null);
				job.setResult("Script did not generate a response");
				job.setStatus("FAILED");
			}
		} catch (Exception e) {
			LogServiceFactory.getService().logError("Error in Python script execution :", e);
			job.setResult("Error executing Python Script");
			job.setStatus("FAILED");
		}
		queueDao.save(job);
	}

}
