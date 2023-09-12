package com.svastha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.svastha.entity.FarmProjects;
import com.svastha.entity.LandPreparation;
import com.svastha.entity.NurseryManagement;
import com.svastha.entity.TransplantManagement;
import com.svastha.entity.Users;
import com.svastha.model.ProjectModel;
import com.svastha.repository.FarmProjectRepository;
import com.svastha.repository.LandPreparationRepository;
import com.svastha.repository.NurseryManagementRepository;
import com.svastha.repository.TransplantManagementRepository;
import com.svastha.repository.UserRepository;

@RestController
public class FarmProjectController {

	@Autowired
	private FarmProjectRepository projectDao;

	@Autowired
	private LandPreparationRepository landDao;

	@Autowired
	private NurseryManagementRepository nurseryDao;

	@Autowired
	private TransplantManagementRepository transplantDao;

	@Autowired
	private UserRepository userDao;

	@GetMapping("/projects")
	public @ResponseBody Iterable<FarmProjects> getAllProjects() {
		return projectDao.findAll();
	}

	@GetMapping("/listProjects")
	public @ResponseBody Iterable<FarmProjects> getProjectsUserId(@RequestParam Long userId) {
		Users u = userDao.findByPk1(userId);
		return projectDao.findByCreatedBy(u);
	}

	@GetMapping("/getProject")
	public @ResponseBody ProjectModel getFarmById(@RequestParam Long projectId) {
		ProjectModel projectModel = new ProjectModel();
		FarmProjects f = projectDao.findById(projectId).get();
		projectModel.setFarm(f);
		List<LandPreparation> land = landDao.findAllByProject(f);
		List<NurseryManagement> nursery = nurseryDao.findAllByProject(f);
		List<TransplantManagement> transplant = transplantDao.findAllByProject(f);
		projectModel.setLandPreparation(land);
		projectModel.setNursery(nursery);
		projectModel.setTransplant(transplant);
		return projectModel;
	}

	@PostMapping("addProject")
	public @ResponseBody FarmProjects saveFarm(@RequestBody FarmProjects project) {
		try {
			FarmProjects f = projectDao.save(project);
			return f;

		} catch (Exception e) {
			throw e;
		}
	}

	@PostMapping("addLandPreparation")
	public @ResponseBody String saveLandPreparations(@RequestBody Iterable<LandPreparation> landPreparation) {
		try {
			landDao.saveAll(landPreparation);
			return "Success";

		} catch (Exception e) {
			throw e;
		}
	}

	@PostMapping("addNurseryManagement")
	public @ResponseBody String saveNurseryManagement(@RequestBody Iterable<NurseryManagement> nurseryManagement) {
		try {
			nurseryDao.saveAll(nurseryManagement);
			return "Success";

		} catch (Exception e) {
			throw e;
		}
	}

	@PostMapping("addTransplantManagement")
	public @ResponseBody String saveTransplantManagement(
			@RequestBody Iterable<TransplantManagement> transplantManagement) {
		try {
			transplantDao.saveAll(transplantManagement);
			return "Success";

		} catch (Exception e) {
			throw e;
		}
	}
}