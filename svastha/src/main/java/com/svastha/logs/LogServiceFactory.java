package com.svastha.logs;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LogServiceFactory {

    private static LogService logService;

    @Autowired
    public LogServiceFactory(LogService logService) {
        LogServiceFactory.logService = logService;
    }

    public static LogService getService() {
        return logService;
    }
}
