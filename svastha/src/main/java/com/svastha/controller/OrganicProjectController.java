package com.svastha.controller;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.svastha.entity.FarmPlots;
import com.svastha.entity.FarmProjects;
import com.svastha.entity.OrganicAnnualProgram;
import com.svastha.entity.OrganicFieldMap;
import com.svastha.entity.OrganicSeedData;
import com.svastha.entity.OrganicSoilAnalysis;
import com.svastha.entity.OrganicSowingData;
import com.svastha.entity.OrganicTransplantData;
import com.svastha.entity.OrganicWaterAnalysis;
import com.svastha.model.OrganicProjectModel;
import com.svastha.model.OrganicProjectPlotModel;
import com.svastha.repository.FarmPlotsRepository;
import com.svastha.repository.FarmProjectRepository;
import com.svastha.repository.MasterProjectTypeRepository;
import com.svastha.repository.OrganicAnnualProgramRepository;
import com.svastha.repository.OrganicCropVarietyRepository;
import com.svastha.repository.OrganicFieldMapRepository;
import com.svastha.repository.OrganicPlotBoundaryRepository;
import com.svastha.repository.OrganicSeedDataRepository;
import com.svastha.repository.OrganicSoilAnalysisRepository;
import com.svastha.repository.OrganicSowingDataRepository;
import com.svastha.repository.OrganicTransplantDataRepository;
import com.svastha.repository.OrganicWaterAnalysisRepository;
import com.svastha.service.ExcelWriter;
import com.svastha.service.FilesStorageService;

@RestController
public class OrganicProjectController {

	@Autowired
	private MasterProjectTypeRepository projectTypeDao;

	@Autowired
	private FarmProjectRepository projectDao;

	@Autowired
	private FarmPlotsRepository plotsDao;

	@Autowired
	private OrganicSeedDataRepository seedDao;

	@Autowired
	private OrganicSowingDataRepository sowingDao;

	@Autowired
	private OrganicTransplantDataRepository transplantDao;

	@Autowired
	private OrganicAnnualProgramRepository annualDao;

	@Autowired
	private OrganicPlotBoundaryRepository boundaryDao;

	@Autowired
	private OrganicCropVarietyRepository varietyDao;

	@Autowired
	private OrganicFieldMapRepository fieldDao;

	@Autowired
	private OrganicSoilAnalysisRepository soilDao;

	@Autowired
	private OrganicWaterAnalysisRepository waterDao;

	@Autowired
	private ExcelWriter excel;

	@Autowired
	FilesStorageService storageService;

	private static String PROJECT_TYPE = "Organic";
	public static final String SEPARATOR = FileSystems.getDefault().getSeparator();

	@GetMapping("/organicProjects")
	public @ResponseBody Page<FarmProjects> getAllProjects(@RequestParam(required = false) Long yearId,
			@RequestParam(required = false) Long seasonId, @RequestParam(required = false) Long cropId,
			@RequestParam(required = false) String key, @RequestParam(required = false) Long userId,
			Pageable pageable) {
		Long projectTypePk1 = projectTypeDao.findByProjectType(PROJECT_TYPE).getPk1();
		Page<FarmProjects> projects = projectDao.findWithFilters(yearId, seasonId, cropId, key, userId, projectTypePk1,
				"APPROVED", pageable);
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

	@PostMapping("/savePlotDetails")
	public @ResponseBody OrganicProjectPlotModel savePlotDetails(@RequestParam MultipartFile file,
			@RequestBody OrganicProjectPlotModel model) {
		model.setAnnualProgram(annualDao.save(model.getAnnualProgram()));
		model.setBoundary(boundaryDao.saveAll(model.getBoundary()));
		model.setCropVariety(varietyDao.save(model.getCropVariety()));
		OrganicFieldMap field = model.getFieldMap();
		Long plotId = field.getPlots().getPk1();
		Long projectId = field.getProjects().getPk1();
		String folderPath = SEPARATOR + "fieldMap" + SEPARATOR + projectId + SEPARATOR + plotId;
		Path p = storageService.createFolder(folderPath);

		String filePath = storageService.save(file, p);
		field.setFileName(filePath);
		field.setFilePath(p.toString());
		field.setImageUrl("/farmer/images" + folderPath + SEPARATOR + filePath);
		model.setFieldMap(fieldDao.save(model.getFieldMap()));

		return model;
	}

	@GetMapping("/getPlotDetails")
	public @ResponseBody OrganicProjectPlotModel getPlotDetails(@RequestParam Long projectId,
			@RequestParam Long plotId) {
		FarmProjects project = projectDao.findById(projectId).get();
		FarmPlots plots = plotsDao.findById(plotId).get();
		OrganicProjectPlotModel model = new OrganicProjectPlotModel();
		model.setAnnualProgram(annualDao.findByProjectsAndPlots(project, plots));
		model.setCropVariety(varietyDao.findByProjectsAndPlots(project, plots));
		model.setBoundary(boundaryDao.findByProjectsAndPlots(project, plots));
		model.setFieldMap(fieldDao.findByProjectsAndPlots(project, plots));
		model.setFarm(project);
		return model;
	}

	@PostMapping("/saveAnnualProgram")
	public @ResponseBody OrganicAnnualProgram saveAnnualProgram(@RequestBody OrganicAnnualProgram annualProgram) {
		return annualDao.save(annualProgram);
	}

	@GetMapping("/getAnnualProgram")
	public @ResponseBody OrganicAnnualProgram getAnnualProgram(@RequestParam Long projectId,
			@RequestParam Long plotId) {
		FarmProjects project = projectDao.findById(projectId).get();
		FarmPlots plot = plotsDao.findById(plotId).get();
		return annualDao.findByProjectsAndPlots(project, plot);
	}

	@PostMapping("/saveWaterAnalysis")
	public @ResponseBody OrganicWaterAnalysis saveWaterAnalysis(@RequestBody OrganicWaterAnalysis waterAnalysis) {
		return waterDao.save(waterAnalysis);
	}

	@GetMapping("/getWaterAnalysis")
	public @ResponseBody OrganicWaterAnalysis getWaterAnalysis(@RequestParam Long projectId,
			@RequestParam Long plotId) {
		FarmProjects project = projectDao.findById(projectId).get();
		FarmPlots plot = plotsDao.findById(plotId).get();
		return waterDao.findByProjectsAndPlots(project, plot);
	}

	@PostMapping("/saveSoilAnalysis")
	public @ResponseBody OrganicSoilAnalysis saveSoilAnalysis(@RequestBody OrganicSoilAnalysis soilAnalysis) {
		return soilDao.save(soilAnalysis);
	}

	@GetMapping("/getSoilAnalysis")
	public @ResponseBody OrganicSoilAnalysis getSoilAnalysis(@RequestParam Long projectId, @RequestParam Long plotId) {
		FarmProjects project = projectDao.findById(projectId).get();
		FarmPlots plot = plotsDao.findById(plotId).get();
		return soilDao.findByProjectsAndPlots(project, plot);
	}

	@PostMapping("/saveOrganicSeedData")
	public @ResponseBody OrganicSeedData saveOrganicSeedData(@RequestParam MultipartFile file,
			@RequestBody OrganicSeedData seedData) {

		Long plotId = seedData.getPlots().getPk1();
		Long projectId = seedData.getProjects().getPk1();

		String folderPath = SEPARATOR + "seedData" + SEPARATOR + projectId + SEPARATOR + plotId;
		Path p = storageService.createFolder(folderPath);

		String filePath = storageService.save(file, p);
		seedData.setImageUrl("/farmer/images" + folderPath + SEPARATOR + filePath);
		return seedDao.save(seedData);
	}

	@GetMapping("/getOrganicSeedData")
	public @ResponseBody OrganicSeedData getOrganicSeedData(@RequestParam Long projectId, @RequestParam Long plotId) {
		FarmProjects project = projectDao.findById(projectId).get();
		FarmPlots plot = plotsDao.findById(plotId).get();
		return seedDao.findByProjectsAndPlots(project, plot);
	}

	@PostMapping("/saveOrganicSowing")
	public @ResponseBody OrganicSowingData saveOrganicSowing(@RequestBody OrganicSowingData sowing) {
		return sowingDao.save(sowing);
	}

	@GetMapping("/getOrganicSowing")
	public @ResponseBody OrganicSowingData getOrganicSowing(@RequestParam Long projectId, @RequestParam Long plotId) {
		FarmProjects project = projectDao.findById(projectId).get();
		FarmPlots plot = plotsDao.findById(plotId).get();
		return sowingDao.findByProjectsAndPlots(project, plot);
	}

	@PostMapping("/saveOrganicTransplant")
	public @ResponseBody OrganicTransplantData saveOrganicTransplant(@RequestBody OrganicTransplantData transplant) {
		return transplantDao.save(transplant);
	}

	@GetMapping("/getOrganicTransplant")
	public @ResponseBody OrganicTransplantData getOrganicTransplant(@RequestParam Long projectId,
			@RequestParam Long plotId) {
		FarmProjects project = projectDao.findById(projectId).get();
		FarmPlots plot = plotsDao.findById(plotId).get();
		return transplantDao.findByProjectsAndPlots(project, plot);
	}
}
