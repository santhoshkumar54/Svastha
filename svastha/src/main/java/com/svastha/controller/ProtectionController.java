package com.svastha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.svastha.entity.FarmProjects;
import com.svastha.entity.ProjectProtection;
import com.svastha.repository.FarmProjectRepository;
import com.svastha.repository.ProjectsProtectionRepository;

@RestController
public class ProtectionController {

	@Autowired
	private FarmProjectRepository projectDao;

	@Autowired
	private ProjectsProtectionRepository protectionDao;

	@GetMapping("/getProtection")
	public List<ProjectProtection> getProtection(@RequestParam Long projectId) {
		FarmProjects project = projectDao.findById(projectId).get();

		return protectionDao.findAllProtectionsByProjects(project);
	}

	@PostMapping("/saveProtection")
	public List<ProjectProtection> saveProtection(@RequestBody List<ProjectProtection> protection) {

		return protectionDao.saveAll(protection);
	}
}
