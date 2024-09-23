package com.svastha.service;

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
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.svastha.logs.LogServiceFactory;
import com.svastha.repository.ActivityTrackersRepository;
import com.svastha.util.ScheduleServiceUtil;

@Service
public class ScheduledServices {

	@Value("${environment}")
	private String environment;

	@Value("${spring.datasource.username}")
	private String dbuser;

	@Value("${spring.datasource.password}")
	private String dbpass;

	@Value("${upload.directory}")
	private String upload;

	@Autowired
	private ScheduleServiceUtil util;
	
	@Autowired
	private ActivityTrackersRepository activityDao;

	private boolean isProd;

	@PostConstruct
	public void init() {

		isProd = (environment.equals("test")) ? false : true;
	}

	@Scheduled(cron = "0 45 23 * * *")
	public void runDbDump() {
		if (isProd) {
			LogServiceFactory.getService().logInfo("MySQL Dump Started");
			util.executeDBDump(upload, dbuser, dbpass);
		}
	}
	
	@Scheduled(cron = "0 30 23 * * *")
	@Transactional
	public void runActivityTracker() {
			LogServiceFactory.getService().logInfo("Activity Tracker Started");
			activityDao.populateactivitytracker(1);
	}

	@Scheduled(cron = "0 30 6 * * *")
	public void runStorageAndImageReport() {
		if (isProd) {
			LogServiceFactory.getService().logInfo("Storage and Image report Started");
			util.executeStorageAndImageReport(upload);
		}
	}

}
