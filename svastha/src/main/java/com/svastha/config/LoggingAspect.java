package com.svastha.config;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.svastha.logs.LogServiceFactory;

@Aspect
@Component
public class LoggingAspect {

	@Autowired
	private ObjectMapper objectMapper; // Spring Boot auto-configures ObjectMapper

	@Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
	public void controller() {
	}

	@Around("controller()")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		 try {
	            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
	            if (attributes != null) {
	                HttpServletRequest request = attributes.getRequest();
	                // Log request details
	                StringBuilder reqLog = new StringBuilder();
	        		reqLog.append("Request URL: ").append(request.getRequestURL()).append("  Request Method: ")
	        				.append(request.getMethod()).append("  Request Parameters:  ")
	        				.append(Arrays.toString(joinPoint.getArgs()));
	        		if (request.getMethod().equals("POST") || request.getMethod().equals("PUT")) {
	        			Object[] signatureArgs = joinPoint.getArgs();
	        			if (signatureArgs.length > 0) {
	        				Object requestBody = signatureArgs[0];
	        				try {
	        					// Convert request body to JSON string
	        					String requestBodyJson = objectMapper.writeValueAsString(requestBody);
	        					reqLog.append("Request Body: ").append(requestBodyJson);
	        				} catch (Exception e) {
	        					reqLog.append("Request Body: ").append(requestBody);
	        				}
	        			}
	        		}

	        		LogServiceFactory.getService().logInfo("Req: {}" + reqLog.toString());
	            }
	            else {
	                // Handle the absence of request context
	            	 LogServiceFactory.getService().logInfo("No request context available");
	            }
	        		Object result = joinPoint.proceed();
	        		if (result != null) {
	                    LogServiceFactory.getService().logInfo("Response: {}" + result.toString());
	                } else {
	                    LogServiceFactory.getService().logInfo("Response: {} void");
	                }

	        		return result;
	    
	        } catch (Exception e) {
	        	  // Check if the exception message contains "invalid_credentials"
	            if (e.getMessage() != null && e.getMessage().contains("INVALID_CREDENTIALS")) {
	                LogServiceFactory.getService().logError("Invalid credentials error", e);
	                throw e;  // Rethrow the exception specifically for invalid credentials
	            } else {
	                LogServiceFactory.getService().logError("Error occurred: " + e.getMessage(), e);
	                return joinPoint.proceed();  // Continue with normal flow for other exceptions
	            }
	        }
		

		
	}
}
