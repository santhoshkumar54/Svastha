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

import com.google.gson.Gson;
import com.svastha.entity.FarmPlots;
import com.svastha.entity.FarmProjects;
import com.svastha.entity.OrganicAnnualProgram;
import com.svastha.entity.OrganicBioFertilizer;
import com.svastha.entity.OrganicCropVariety;
import com.svastha.entity.OrganicFieldMap;
import com.svastha.entity.OrganicGrowthPromoter;
import com.svastha.entity.OrganicHarvest;
import com.svastha.entity.OrganicIcsType;
import com.svastha.entity.OrganicOrganicManure;
import com.svastha.entity.OrganicPlotBoundary;
import com.svastha.entity.OrganicProtection;
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
import com.svastha.repository.OrganicBioFertilizerRepository;
import com.svastha.repository.OrganicCropVarietyRepository;
import com.svastha.repository.OrganicFieldMapRepository;
import com.svastha.repository.OrganicGrowthPromoterRepository;
import com.svastha.repository.OrganicHarvestRepository;
import com.svastha.repository.OrganicIcsTypeRepository;
import com.svastha.repository.OrganicOrganicManureRepository;
import com.svastha.repository.OrganicPlotBoundaryRepository;
import com.svastha.repository.OrganicProtectionRepository;
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
	private OrganicOrganicManureRepository manureDao;

	@Autowired
	private OrganicBioFertilizerRepository bioDao;

	@Autowired
	private OrganicGrowthPromoterRepository growthDao;

	@Autowired
	private OrganicIcsTypeRepository icsDao;

	@Autowired
	private OrganicProtectionRepository pestDao;

	@Autowired
	private OrganicHarvestRepository harvestDao;

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
			@RequestParam(required = false) Long varietyId, @RequestParam(required = false) Long ics,
			Pageable pageable) {
		Long projectTypePk1 = projectTypeDao.findByProjectType(PROJECT_TYPE).getPk1();
		Page<FarmProjects> projects = projectDao.findWithFilters(yearId, seasonId, cropId, key, userId, projectTypePk1,
				varietyId, ics, "APPROVED", pageable);
		return projects;
	}

	@GetMapping("/exportOrganicProjects")
	public @ResponseBody String exportProjects(@RequestParam(required = false) Long yearId,
			@RequestParam(required = false) Long seasonId, @RequestParam(required = false) Long cropId,
			@RequestParam(required = false) String key, @RequestParam(required = false) Long userId,
			@RequestParam(required = false) Long varietyId, @RequestParam(required = false) Long ics,
			@RequestParam String email) {
		try {
			Long projectTypePk1 = projectTypeDao.findByProjectType(PROJECT_TYPE).getPk1();
			excel.startProjectExport(yearId, seasonId, cropId, key, userId, email, projectTypePk1, varietyId, ics);
			return "The exported data will be sent to your email.";
		} catch (Exception e) {
			return "Failed to trigger batch job: " + e.getMessage();
		}
	}

	@GetMapping("/listOrganicProjects")
	public @ResponseBody Iterable<FarmProjects> getProjectsUserId(@RequestParam Long userId) {
		Long projectTypePk1 = projectTypeDao.findByProjectType(PROJECT_TYPE).getPk1();
		return projectDao.findWithFilters(null, null, null, null, userId, projectTypePk1, null, null);
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
	public @ResponseBody OrganicProjectPlotModel savePlotDetails(@RequestParam(required = false) MultipartFile file,
			@RequestParam String model) {
		OrganicProjectPlotModel omodel = new Gson().fromJson(model, OrganicProjectPlotModel.class);
		omodel.setAnnualProgram(annualDao.save(omodel.getAnnualProgram()));
		omodel.setBoundary(boundaryDao.saveAll(omodel.getBoundary()));
		omodel.setCropVariety(varietyDao.save(omodel.getCropVariety()));
		omodel.setIcsType(icsDao.save(omodel.getIcsType()));
		if (file != null && !file.isEmpty()) {
			OrganicFieldMap field = omodel.getFieldMap();
			Long plotId = field.getPlots().getPk1();
			Long projectId = field.getProjects().getPk1();
			String folderPath = SEPARATOR + "fieldMap" + SEPARATOR + projectId + SEPARATOR + plotId;
			Path p = storageService.createFolder(folderPath);

			String filePath = storageService.save(file, p);
			field.setFileName(filePath);
			field.setFilePath(p.toString());
			field.setImageUrl("/farmer/images" + folderPath + SEPARATOR + filePath);
			omodel.setFieldMap(fieldDao.save(omodel.getFieldMap()));
		}
		return omodel;
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

	@PostMapping("/saveBoundary")
	public @ResponseBody OrganicPlotBoundary saveBoundary(@RequestBody OrganicPlotBoundary boundary) {
		return boundaryDao.save(boundary);
	}

	@GetMapping("/getBoundaryByProject")
	public @ResponseBody List<OrganicPlotBoundary> getBoundaryByProject(@RequestParam Long projectId) {
		FarmProjects project = projectDao.findById(projectId).get();
		return boundaryDao.findByProjects(project);
	}

	@PostMapping("/saveOrganicVariety")
	public @ResponseBody OrganicCropVariety saveVariety(@RequestBody OrganicCropVariety variety) {
		return varietyDao.save(variety);
	}

	@GetMapping("/getVarietyByProject")
	public @ResponseBody List<OrganicCropVariety> getVarietyByProject(@RequestParam Long projectId) {
		FarmProjects project = projectDao.findById(projectId).get();
		return varietyDao.findByProjects(project);
	}

	@PostMapping("/saveFieldMap")
	public @ResponseBody OrganicFieldMap saveFieldMap(@RequestBody OrganicFieldMap fieldMap) {
		return fieldDao.save(fieldMap);
	}

	@GetMapping("/getFieldMapByProject")
	public @ResponseBody List<OrganicFieldMap> getFieldMapByProject(@RequestParam Long projectId) {
		FarmProjects project = projectDao.findById(projectId).get();
		return fieldDao.findByProjects(project);
	}

	@PostMapping("/saveAnnualProgram")
	public @ResponseBody OrganicAnnualProgram saveAnnualProgram(@RequestBody OrganicAnnualProgram annualProgram) {
		return annualDao.save(annualProgram);
	}

	@GetMapping("/getAnnualProgramByProject")
	public @ResponseBody List<OrganicAnnualProgram> getAnnualProgram(@RequestParam Long projectId) {
		FarmProjects project = projectDao.findById(projectId).get();
		return annualDao.findByProjects(project);
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

	@GetMapping("/getWaterAnalysisByProject")
	public @ResponseBody List<OrganicWaterAnalysis> getWaterAnalysisByProject(@RequestParam Long projectId) {
		FarmProjects project = projectDao.findById(projectId).get();
		return waterDao.findByProjects(project);
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

	@GetMapping("/getSoilAnalysisByProject")
	public @ResponseBody List<OrganicSoilAnalysis> getSoilAnalysisByProject(@RequestParam Long projectId) {
		FarmProjects project = projectDao.findById(projectId).get();
		return soilDao.findByProjects(project);
	}

	@PostMapping("/saveOrganicSeedData")
	public @ResponseBody OrganicSeedData saveOrganicSeedData(@RequestParam(required = false) MultipartFile file,
			@RequestParam String seedData) {

		OrganicSeedData seed = new Gson().fromJson(seedData, OrganicSeedData.class);

		if (file != null && !file.isEmpty()) {
			Long plotId = seed.getPlots().getPk1();
			Long projectId = seed.getProjects().getPk1();

			String folderPath = SEPARATOR + "seedData" + SEPARATOR + projectId + SEPARATOR + plotId;
			Path p = storageService.createFolder(folderPath);

			String filePath = storageService.save(file, p);
			seed.setImageUrl("/farmer/images" + folderPath + SEPARATOR + filePath);
		}
		return seedDao.save(seed);
	}

	@GetMapping("/getOrganicSeedData")
	public @ResponseBody OrganicSeedData getOrganicSeedData(@RequestParam Long projectId, @RequestParam Long plotId) {
		FarmProjects project = projectDao.findById(projectId).get();
		FarmPlots plot = plotsDao.findById(plotId).get();
		return seedDao.findByProjectsAndPlots(project, plot);
	}

	@GetMapping("/getOrganicSeedDataByProject")
	public @ResponseBody List<OrganicSeedData> getOrganicSeedDataByProject(@RequestParam Long projectId) {
		FarmProjects project = projectDao.findById(projectId).get();
		return seedDao.findByProjects(project);
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

	@GetMapping("/getOrganicSowingByProject")
	public @ResponseBody List<OrganicSowingData> getOrganicSowing(@RequestParam Long projectId) {
		FarmProjects project = projectDao.findById(projectId).get();
		return sowingDao.findByProjects(project);
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

	@GetMapping("/getOrganicTransplantByProject")
	public @ResponseBody List<OrganicTransplantData> getOrganicTransplant(@RequestParam Long projectId) {
		FarmProjects project = projectDao.findById(projectId).get();
		return transplantDao.findByProjects(project);
	}

	@PostMapping("/saveOrganicOrganicManure")
	public @ResponseBody OrganicOrganicManure saveOrganicOrganicManure(
			@RequestParam(required = false) MultipartFile file, @RequestParam String manure) {
		OrganicOrganicManure man = new Gson().fromJson(manure, OrganicOrganicManure.class);

		if (file != null && !file.isEmpty()) {
			Long plotId = man.getPlots().getPk1();

			Long projectId = man.getProjects().getPk1();

			String folderPath = SEPARATOR + "manure" + SEPARATOR + projectId + SEPARATOR + plotId;
			Path p = storageService.createFolder(folderPath);

			String filePath = storageService.save(file, p);
			man.setImageUrl("/farmer/images" + folderPath + SEPARATOR + filePath);
		}
		return manureDao.save(man);
	}

	@GetMapping("/getOrganicOrganicManure")
	public @ResponseBody List<OrganicOrganicManure> getOrganicOrganicManure(@RequestParam Long projectId,
			@RequestParam Long plotId) {
		FarmProjects project = projectDao.findById(projectId).get();
		FarmPlots plot = plotsDao.findById(plotId).get();
		return manureDao.findByProjectsAndPlots(project, plot);
	}

	@GetMapping("/getOrganicOrganicManureByProject")
	public @ResponseBody List<OrganicOrganicManure> getOrganicOrganicManure(@RequestParam Long projectId) {
		FarmProjects project = projectDao.findById(projectId).get();
		return manureDao.findByProjects(project);
	}

	@PostMapping("/saveOrganicGrowthPromoter")
	public @ResponseBody OrganicGrowthPromoter saveOrganicGrowthPromoter(
			@RequestParam(required = false) MultipartFile file, @RequestParam String growthPromoter) {
		OrganicGrowthPromoter growth = new Gson().fromJson(growthPromoter, OrganicGrowthPromoter.class);
		if (file != null && !file.isEmpty()) {
			Long plotId = growth.getPlots().getPk1();
			Long projectId = growth.getProjects().getPk1();

			String folderPath = SEPARATOR + "growth promoter" + SEPARATOR + projectId + SEPARATOR + plotId;
			Path p = storageService.createFolder(folderPath);

			String filePath = storageService.save(file, p);
			growth.setImageUrl("/farmer/images" + folderPath + SEPARATOR + filePath);
		}
		return growthDao.save(growth);
	}

	@GetMapping("/getOrganicGrowthPromoter")
	public @ResponseBody List<OrganicGrowthPromoter> getOrganicGrowthPromoter(@RequestParam Long projectId,
			@RequestParam Long plotId) {
		FarmProjects project = projectDao.findById(projectId).get();
		FarmPlots plot = plotsDao.findById(plotId).get();
		return growthDao.findByProjectsAndPlots(project, plot);
	}

	@GetMapping("/getOrganicGrowthPromoterByProject")
	public @ResponseBody List<OrganicGrowthPromoter> getOrganicGrowthPromoter(@RequestParam Long projectId) {
		FarmProjects project = projectDao.findById(projectId).get();
		return growthDao.findByProjects(project);
	}

	@PostMapping("/saveOrganicBioFertilizer")
	public @ResponseBody OrganicBioFertilizer saveOrganicBioFertilizer(
			@RequestParam(required = false) MultipartFile file, @RequestParam String bioFertilizer) {
		OrganicBioFertilizer bio = new Gson().fromJson(bioFertilizer, OrganicBioFertilizer.class);

		if (file != null && !file.isEmpty()) {
			Long plotId = bio.getPlots().getPk1();
			Long projectId = bio.getProjects().getPk1();

			String folderPath = SEPARATOR + "Bio Fertilizer" + SEPARATOR + projectId + SEPARATOR + plotId;
			Path p = storageService.createFolder(folderPath);

			String filePath = storageService.save(file, p);
			bio.setImageUrl("/farmer/images" + folderPath + SEPARATOR + filePath);
		}
		return bioDao.save(bio);
	}

	@GetMapping("/getOrganicBioFertilizer")
	public @ResponseBody List<OrganicBioFertilizer> getOrganicBioFertilizer(@RequestParam Long projectId,
			@RequestParam Long plotId) {
		FarmProjects project = projectDao.findById(projectId).get();
		FarmPlots plot = plotsDao.findById(plotId).get();
		return bioDao.findByProjectsAndPlots(project, plot);
	}

	@GetMapping("/getOrganicBioFertilizerByProject")
	public @ResponseBody List<OrganicBioFertilizer> getOrganicBioFertilizer(@RequestParam Long projectId) {
		FarmProjects project = projectDao.findById(projectId).get();
		return bioDao.findByProjects(project);
	}

	@PostMapping("/saveOrganicIcs")
	public @ResponseBody OrganicIcsType saveOrganicIcs(@RequestBody OrganicIcsType ics) {
		return icsDao.save(ics);
	}

	@GetMapping("/getOrganicIcs")
	public @ResponseBody OrganicIcsType getOrganicIcs(@RequestParam Long projectId, @RequestParam Long plotId) {
		FarmProjects project = projectDao.findById(projectId).get();
		FarmPlots plot = plotsDao.findById(plotId).get();
		return icsDao.findByProjectsAndPlots(project, plot);
	}

	@GetMapping("/getOrganicIcsByProject")
	public @ResponseBody List<OrganicIcsType> getOrganicIcsByProject(@RequestParam Long projectId) {
		FarmProjects project = projectDao.findById(projectId).get();
		return icsDao.findByProjects(project);
	}

	@PostMapping("/saveOrganicProtection")
	public @ResponseBody OrganicProtection saveOrganicProtection(@RequestParam(required = false) MultipartFile file,
			@RequestParam String pest) {
		OrganicProtection man = new Gson().fromJson(pest, OrganicProtection.class);

		if (file != null && !file.isEmpty()) {
			Long plotId = man.getPlots().getPk1();

			Long projectId = man.getProjects().getPk1();

			String folderPath = SEPARATOR + "protection" + SEPARATOR + projectId + SEPARATOR + plotId;
			Path p = storageService.createFolder(folderPath);

			String filePath = storageService.save(file, p);
			man.setImageUrl("/farmer/images" + folderPath + SEPARATOR + filePath);
		}
		return pestDao.save(man);
	}

	@GetMapping("/getOrganicProtection")
	public @ResponseBody List<OrganicProtection> getOrganicProtection(@RequestParam Long projectId,
			@RequestParam Long plotId) {
		FarmProjects project = projectDao.findById(projectId).get();
		FarmPlots plot = plotsDao.findById(plotId).get();
		return pestDao.findByProjectsAndPlots(project, plot);
	}

	@GetMapping("/getOrganicProtectionByProject")
	public @ResponseBody List<OrganicProtection> getOrganicProtectionByProject(@RequestParam Long projectId) {
		FarmProjects project = projectDao.findById(projectId).get();
		return pestDao.findByProjects(project);
	}

	@PostMapping("/saveOrganicHarvest")
	public @ResponseBody OrganicHarvest saveOrganicHarvest(@RequestParam String harvest) {
		OrganicHarvest man = new Gson().fromJson(harvest, OrganicHarvest.class);
		return harvestDao.save(man);
	}

	@GetMapping("/getOrganicHarvest")
	public @ResponseBody OrganicHarvest getOrganicHarvest(@RequestParam Long projectId, @RequestParam Long plotId) {
		FarmProjects project = projectDao.findById(projectId).get();
		FarmPlots plot = plotsDao.findById(plotId).get();
		return harvestDao.findByProjectsAndPlots(project, plot);
	}

	@GetMapping("/getOrganicHarvestByProject")
	public @ResponseBody List<OrganicHarvest> getOrganicHarvestByProject(@RequestParam Long projectId) {
		FarmProjects project = projectDao.findById(projectId).get();
		return harvestDao.findByProjects(project);
	}
}
