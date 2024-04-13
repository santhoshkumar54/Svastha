package com.svastha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.svastha.entity.FarmPlots;
import com.svastha.entity.FarmProjects;
import com.svastha.entity.OrganicAnnualProgram;
import com.svastha.model.OrganicProjectModel;
import com.svastha.repository.FarmPlotsRepository;
import com.svastha.repository.FarmProjectRepository;
import com.svastha.repository.MasterProjectTypeRepository;
import com.svastha.repository.OrganicAnnualProgramRepository;
import com.svastha.repository.UserRepository;
import com.svastha.service.ExcelWriter;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class OrganicProjectController {

	@Autowired
	private MasterProjectTypeRepository projectTypeDao;

	@Autowired
	private FarmProjectRepository projectDao;

	@Autowired
	private FarmPlotsRepository plotsDao;

	@Autowired
	private UserRepository userDao;

	@Autowired
	private OrganicAnnualProgramRepository annualDao;

	@Autowired
	private ExcelWriter excel;

	private static String PROJECT_TYPE = "Organic";

	@GetMapping("/organicProjects")
	public @ResponseBody Page<FarmProjects> getAllProjects(@RequestParam(required = false) Long yearId,
			@RequestParam(required = false) Long seasonId, @RequestParam(required = false) Long cropId,
			@RequestParam(required = false) String key, @RequestParam(required = false) Long userId,
			Pageable pageable) {
		Long projectTypePk1 = projectTypeDao.findByProjectType(PROJECT_TYPE).getPk1();
		Page<FarmProjects> projects = projectDao.findWithFilters(yearId, seasonId, cropId, key, userId, projectTypePk1,
				pageable);
		return projects;
	}

	@GetMapping("/exportOrganicProjects")
	public @ResponseBody String exportProjects(@RequestParam(required = false) Long yearId,
			@RequestParam(required = false) Long seasonId, @RequestParam(required = false) Long cropId,
			@RequestParam(required = false) String key, @RequestParam(required = false) Long userId,
			@RequestParam String email) {
		try {
			System.out.println("year-" + yearId + " season-" + seasonId + " crop-" + cropId + " key-" + key + " user-"
					+ userId + " email-" + email);
			Long projectTypePk1 = projectTypeDao.findByProjectType(PROJECT_TYPE).getPk1();
			excel.startProjectExport(yearId, seasonId, cropId, key, userId, email, projectTypePk1);
			return "The exported data will be sent to your email.";
		} catch (Exception e) {
			return "Failed to trigger batch job: " + e.getMessage();
		}
	}

	@GetMapping("/listOrganicProjects")
	public @ResponseBody Iterable<FarmProjects> getProjectsUserId(@RequestParam Long userId) {
		Long projectTypePk1 = projectTypeDao.findByProjectType(PROJECT_TYPE).getPk1();
		return projectDao.findWithFilters(null, null, null, null, userId, projectTypePk1);
	}

	@GetMapping("/getOrganicProject")
	public @ResponseBody OrganicProjectModel getFarmById(@RequestParam Long projectId) {
		OrganicProjectModel projectModel = new OrganicProjectModel();
		FarmProjects f = projectDao.findById(projectId).get();
		projectModel.setFarm(f);
		List<OrganicAnnualProgram> annual = annualDao.findByProjects(f);
		projectModel.setAnnualProgram(annual);
		return projectModel;
	}

	@PostMapping("/saveAnnualProgram")
	public @ResponseBody List<OrganicAnnualProgram> saveAnnualProgram(
			@RequestBody List<OrganicAnnualProgram> annualProgram) {
		return annualDao.saveAll(annualProgram);
	}

	@PostMapping("/getAnnualProgram")
	public @ResponseBody List<OrganicAnnualProgram> getAnnualProgram(@RequestParam Long projectId,
			@RequestParam Long plotId) {
		FarmProjects project = projectDao.findById(projectId).get();
		FarmPlots plot = plotsDao.findById(plotId).get();
		return annualDao.findByProjectsAndPlots(project, plot);
	}
}
