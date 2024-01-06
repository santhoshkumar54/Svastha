package com.svastha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.svastha.entity.FarmProjects;
import com.svastha.entity.ProjectDispatch;
import com.svastha.entity.ProjectPacking;
import com.svastha.entity.ProjectProcurement;
import com.svastha.repository.FarmProjectRepository;
import com.svastha.repository.ProjectsDispatchRepository;
import com.svastha.repository.ProjectsPackingRepository;
import com.svastha.repository.ProjectsProcurementRepository;

@RestController
public class HarvestController {

	@Autowired
	private FarmProjectRepository projectDao;

	@Autowired
	private ProjectsPackingRepository packingDao;

	@Autowired
	private ProjectsProcurementRepository procDao;

	@Autowired
	private ProjectsDispatchRepository dispatchDao;

	@GetMapping("/getPacking")
	public List<ProjectPacking> getPacking(@RequestParam Long projectId) {
		FarmProjects project = projectDao.findById(projectId).get();

		return packingDao.findAllByProjects(project);
	}

	@PostMapping("/savePacking")
	public List<ProjectPacking> savePacking(@RequestBody List<ProjectPacking> packing) {

		return packingDao.saveAll(packing);
	}

	@GetMapping("/getProcurement")
	public List<ProjectProcurement> getProcurement(@RequestParam Long projectId) {
		FarmProjects project = projectDao.findById(projectId).get();

		return procDao.findAllByProjects(project);
	}

	@PostMapping("/saveProcurement")
	public List<ProjectProcurement> saveProcurement(@RequestBody List<ProjectProcurement> procurement) {

		return procDao.saveAll(procurement);
	}

	@GetMapping("/getDispatch")
	public List<ProjectDispatch> getDispatch(@RequestParam Long projectId) {
		FarmProjects project = projectDao.findById(projectId).get();

		return dispatchDao.findAllByProjects(project);
	}

	@PostMapping("/saveDispatch")
	public List<ProjectDispatch> saveDispatch(@RequestBody List<ProjectDispatch> dispatch) {

		return dispatchDao.saveAll(dispatch);
	}
}
