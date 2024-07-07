package com.svastha.logs;


public interface LogService {
    void logDebug(String message);
    void logError(String message, Throwable throwable);
    void logInfo(String message);
}
