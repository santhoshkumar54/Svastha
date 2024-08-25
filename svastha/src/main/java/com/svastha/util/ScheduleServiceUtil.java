package com.svastha.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.svastha.logs.LogServiceFactory;

@Service
public class ScheduleServiceUtil {

	@Autowired
	private JavaMailSender mailSender;

	public void executeDBDump(String upload, String dbuser, String dbpass) {
		String date = new SimpleDateFormat("dd-MMM-yyyy").format(new Date());
		String outputFilePath = String.format(upload.concat("Backups/DB_dump/backup_%s.sql"), date);
		String command = String.format("mysqldump -u%s -p%s %s -r %s", dbuser, dbpass, "svastha", outputFilePath);

		try {
			// Execute the command
			Process process = Runtime.getRuntime().exec(command);

			// Check for any errors
			BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			String s;
			while ((s = stdError.readLine()) != null) {
				LogServiceFactory.getService().logInfo(s);
			}

			int processComplete = process.waitFor();
			if (processComplete == 0) {
				LogServiceFactory.getService().logInfo("Backup taken successfully.");
			} else {
				LogServiceFactory.getService().logInfo("Could not take mysql backup.");
			}
		} catch (Exception ex) {
			LogServiceFactory.getService().logError("Exception caught in Scheduling MySQL Dump : ", ex);
		}
	}

	public void executeStorageAndImageReport(String upload) {
		try {
			// Fetch disk space details
			File file = new File("/");
			long totalSpace = file.getTotalSpace() / (1024 * 1024 * 1024);
			long freeSpace = file.getFreeSpace() / (1024 * 1024 * 1024);
			long usableSpace = file.getUsableSpace() / (1024 * 1024 * 1024);
			long usedSpace = totalSpace - freeSpace;

			// Fetch the number of files
			String directoryPath = upload.concat("/images");
			long fileCount = countFiles(directoryPath);

			// Format the date
			String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
			LogServiceFactory.getService().logInfo(date);
			LogServiceFactory.getService().logInfo(fileCount + "");
			LogServiceFactory.getService().logInfo(totalSpace + "");
			LogServiceFactory.getService().logInfo(usedSpace + "");
			LogServiceFactory.getService().logInfo(usableSpace + "");
			LogServiceFactory.getService().logInfo(freeSpace + "");
			// Create email content
			String subject = "Svastha App - Disk Space and Images Count Report - " + date;
			String htmlContent = String.format(
					"<h2>Disk Space Report as of %s</h2>" + "<ul>" + "<li><b>Total Disk Space:</b> %d GB</li>"
							+ "<li><b>Used Disk Space:</b> %d GB</li>" + "<li><b>Free Disk Space:</b> %d GB</li>"
							+ "<li><b>Usable Disk Space:</b> %d GB</li>"
							+ "<li><b>Number of Images Uploaded:</b> %d</li>" + "</ul>",
					date, totalSpace, usedSpace, freeSpace, usableSpace, fileCount);
			LogServiceFactory.getService().logInfo(htmlContent);
			// Send email
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true); // true indicates multipart

			helper.setTo("soundaryakasiraman@gmail.com");
			helper.setCc("smsanthoshkumar@ymail.com");

			helper.setSubject(subject);
			helper.setText(htmlContent, true); // true indicates that the content is HTML

			mailSender.send(message);

		} catch (Exception ex) {
			LogServiceFactory.getService().logError("Exception caught in generating storage report : ", ex);
		}
	}

	private long countFiles(String directoryPath) {
		File directory = new File(directoryPath);
		long fileCount = 0;

		if (directory.exists() && directory.isDirectory()) {
			fileCount = countFilesInDirectory(directory);
		}

		return fileCount;
	}

	private long countFilesInDirectory(File directory) {
		long count = 0;
		File[] files = directory.listFiles();

		if (files != null) {
			for (File file : files) {
				if (file.isFile()) {
					count++;
				} else if (file.isDirectory()) {
					count += countFilesInDirectory(file);
				}
			}
		}

		return count;
	}
}