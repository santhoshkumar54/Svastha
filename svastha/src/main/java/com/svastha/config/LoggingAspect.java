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
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
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

		Object result = joinPoint.proceed();
		LogServiceFactory.getService().logInfo("Response: {}" + result.toString());

		return result;
	}
}
