package com.svastha.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("/hello")
	public String index() {
		return "Hello Deepan!";
	}

	@GetMapping("/downloadapk")
	public ResponseEntity downloadapk() throws IOException {
		File apk = new File("/dev/svastha/APK/svastha.apk");
		Path path = Paths.get(apk.getAbsolutePath());
		ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

		return ResponseEntity.ok().contentLength(apk.length()).contentType(MediaType.APPLICATION_OCTET_STREAM)
				.body(resource);
	}

}
