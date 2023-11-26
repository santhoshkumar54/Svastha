package com.svastha.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

@RestController
public class HomeController {

	@Autowired
	 private JavaMailSender mailSender;
	@GetMapping("/hello")
	public String index() {
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
	
	public void sendEmailToMultipleRecepients(String[] to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }
}