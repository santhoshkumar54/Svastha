package com.svastha.logs;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.annotation.PostConstruct;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy;

@Service
public class LogServiceImpl implements LogService {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(LogServiceImpl.class);

    @Value("${LOG_PATH:/home/svastha/logs}")
    private String logPath;

    @Value("${LOG_LEVEL:INFO}")
    private String logLevel;

    @PostConstruct
    private void init() {
    	System.out.println(" Log path : "+logPath);
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();

        // Encoder configuration
        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
        encoder.setContext(context);
        encoder.setPattern("%d{yyyy-MM-dd HH:mm:ss} %-5level %logger{36} - %msg%n");
        encoder.start();

        // Create date folder format
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String currentDateFolder = LocalDate.now().format(dateFormatter);

        // Rolling file appender for error logs
        RollingFileAppender<ILoggingEvent> fileAppender = new RollingFileAppender<>();
        fileAppender.setContext(context);
        fileAppender.setEncoder(encoder);
        fileAppender.setFile(logPath + "/logFile.log");

        TimeBasedRollingPolicy<ILoggingEvent> errorRollingPolicy = new TimeBasedRollingPolicy<>();
        errorRollingPolicy.setContext(context);
        errorRollingPolicy.setFileNamePattern(logPath + "/" + currentDateFolder + "/logFile-%d{yyyy-MM-dd}.log");
        errorRollingPolicy.setMaxHistory(30);
        errorRollingPolicy.setParent(fileAppender);
        errorRollingPolicy.start();

        fileAppender.setRollingPolicy(errorRollingPolicy);
        fileAppender.start();

        // Adding the appenders to the root logger
        Logger rootLogger = context.getLogger(Logger.ROOT_LOGGER_NAME);
        rootLogger.setLevel(Level.toLevel(logLevel));
        rootLogger.addAppender(fileAppender);
        //rootLogger.detachAppender("console");
        
        // Set specific logging level for Hibernate
        Logger hibernateLogger = context.getLogger("org.hibernate");
        hibernateLogger.setLevel(Level.toLevel(logLevel));
        hibernateLogger.addAppender(fileAppender);  // Adding the error appender to Hibernate logger
       // hibernateLogger.detachAppender("console");
    }

    @Override
    public void logDebug(String message) {
        logger.debug(message);
    }

    @Override
    public void logError(String message, Throwable throwable) {
        logger.error(message, throwable);
    }

    @Override
    public void logInfo(String message) {
        logger.info(message);
    }
}
