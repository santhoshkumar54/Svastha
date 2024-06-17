package com.svastha.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.svastha.entity.FarmProjects;
import com.svastha.entity.ProjectBioFertilizers;
import com.svastha.entity.ProjectDSRMethod;
import com.svastha.entity.ProjectIrrigation;
import com.svastha.entity.ProjectLandPreparation;
import com.svastha.entity.ProjectNurseryNutrient;
import com.svastha.entity.ProjectNurseryPests;
import com.svastha.entity.ProjectNurseryWater;
import com.svastha.entity.ProjectNurseryWeed;
import com.svastha.entity.ProjectOrganicManure;
import com.svastha.entity.ProjectSeedTreatment;
import com.svastha.entity.ProjectSeedTreatmentChemical;
import com.svastha.entity.ProjectSowingData;
import com.svastha.entity.ProjectSyntheticFertilizers;
import com.svastha.entity.ProjectTransplantManagement;
import com.svastha.entity.ProjectWeedManagement;
import com.svastha.repository.FarmProjectRepository;
import com.svastha.repository.ProjectsBioFertilizerRepository;
import com.svastha.repository.ProjectsDsrRepository;
import com.svastha.repository.ProjectsIrrigationRepository;
import com.svastha.repository.ProjectsLandPreparationRepository;
import com.svastha.repository.ProjectsManureRepository;
import com.svastha.repository.ProjectsNutrientRepository;
import com.svastha.repository.ProjectsPestsRepository;
import com.svastha.repository.ProjectsSeedTreatmentChemicalRepository;
import com.svastha.repository.ProjectsSeedTreatmentRepository;
import com.svastha.repository.ProjectsSowingDataRepository;
import com.svastha.repository.ProjectsSyntheticFertilizerRepository;
import com.svastha.repository.ProjectsTransplantManagementRepository;
import com.svastha.repository.ProjectsWaterRepository;
import com.svastha.repository.ProjectsWeedManagementRepository;
import com.svastha.repository.ProjectsWeedRepository;
import com.svastha.util.ProjectSeedTreatmentDTO;

@RestController
public class ProductionController {

	@Autowired
	private ProjectsSeedTreatmentRepository seedTreatmentDao;

	@Autowired
	private ProjectsSeedTreatmentChemicalRepository seedTreatmentChemicalDao;

	@Autowired
	private ProjectsDsrRepository dsrDao;

	@Autowired
	private ProjectsSowingDataRepository sowingDao;

	@Autowired
	private FarmProjectRepository projectDao;

	@Autowired
	private ProjectsIrrigationRepository irrigationDao;

	@Autowired
	private ProjectsWaterRepository waterDao;

	@Autowired
	private ProjectsWeedRepository weedDao;

	@Autowired
	private ProjectsNutrientRepository nutrientDao;

	@Autowired
	private ProjectsPestsRepository pestDao;

	@Autowired
	private ProjectsLandPreparationRepository landDao;

	@Autowired
	private ProjectsTransplantManagementRepository transplantDao;

	@Autowired
	private ProjectsWeedManagementRepository weedMgtDao;

	@Autowired
	private ProjectsManureRepository manureDao;

	@Autowired
	private ProjectsBioFertilizerRepository bioDao;

	@Autowired
	private ProjectsSyntheticFertilizerRepository syntheticDao;

	@GetMapping("/getSeedTreatment")
	public ProjectSeedTreatmentDTO getSeedTReatment(@RequestParam Long projectId) {
		FarmProjects project = projectDao.findById(projectId).get();
		ProjectSeedTreatment seedTreatment = seedTreatmentDao.findSeedTreatmentByProjects(project);
		List<ProjectSeedTreatmentChemical> chemicals = seedTreatmentChemicalDao
				.findAllChemicalBySeedTreatment(seedTreatment);

		ProjectSeedTreatmentDTO dto = new ProjectSeedTreatmentDTO();
		dto.setSeedTreatment(seedTreatment);
		dto.setChemicals(chemicals);
		return dto;
	}

	@PostMapping("saveSeedTreatment")
	public ProjectSeedTreatmentDTO saveSeedTreatment(@RequestBody ProjectSeedTreatmentDTO seedTreatment) {
		ProjectSeedTreatment seed = seedTreatmentDao.save(seedTreatment.getSeedTreatment());
		List<ProjectSeedTreatmentChemical> chemicalEntity = new ArrayList<>();
		for (ProjectSeedTreatmentChemical chemical : seedTreatment.getChemicals()) {
			chemical.setSeedTreatment(seed);
			chemicalEntity.add(chemical);
		}
		seedTreatmentChemicalDao.saveAll(chemicalEntity);
		seedTreatment.setChemicals(chemicalEntity);
		seedTreatment.setSeedTreatment(seed);
		return seedTreatment;
	}

	@GetMapping("/getDsrMethod")
	public ProjectDSRMethod getDsrMethod(@RequestParam Long projectId) {
		FarmProjects project = projectDao.findById(projectId).get();
		return dsrDao.findDsrByProjects(project);
	}

	@PostMapping("/saveDsrMethod")
	public ProjectDSRMethod saveDsrMethod(@RequestBody ProjectDSRMethod dsrMethod) {

		return dsrDao.save(dsrMethod);
	}

	@GetMapping("/getSowingData")
	public List<ProjectSowingData> getSowingData(@RequestParam Long projectId) {

		FarmProjects project = projectDao.findById(projectId).get();
		return sowingDao.findAllSowingDataByProjects(project);
	}

	@PostMapping("/saveSowingData")
	public void saveSowingData(@RequestBody List<ProjectSowingData> sowings) {

		sowingDao.saveAll(sowings);
	}

	@GetMapping("/getIrrigation")
	public List<ProjectIrrigation> getIrrigation(@RequestParam Long projectId) {
		FarmProjects project = projectDao.findById(projectId).get();
		return irrigationDao.findAllIrrigationsByProjects(project);
	}

	@PostMapping("/saveIrrigation")
	public List<ProjectIrrigation> saveIrrigation(@RequestBody List<ProjectIrrigation> irrigation) {

		return irrigationDao.saveAll(irrigation);
	}

	@GetMapping("/getNurseryWater")
	public List<ProjectNurseryWater> getNurseryWater(@RequestParam Long projectId) {
		FarmProjects project = projectDao.findById(projectId).get();
		return waterDao.findAllWaterByProjects(project);
	}

	@PostMapping("/saveNurseryWater")
	public List<ProjectNurseryWater> saveNurseryWater(@RequestBody List<ProjectNurseryWater> water) {

		return waterDao.saveAll(water);
	}

	@GetMapping("/getNurseryWeed")
	public List<ProjectNurseryWeed> getNurseryWeed(@RequestParam Long projectId) {
		FarmProjects project = projectDao.findById(projectId).get();
		return weedDao.findAllWeedByProjects(project);
	}

	@PostMapping("/saveNurseryWeed")
	public List<ProjectNurseryWeed> saveNurseryWeed(@RequestBody List<ProjectNurseryWeed> weed) {

		return weedDao.saveAll(weed);
	}

	@GetMapping("/getNurseryNutrient")
	public List<ProjectNurseryNutrient> getNurseryNutrient(@RequestParam Long projectId) {
		FarmProjects project = projectDao.findById(projectId).get();
		return nutrientDao.findAllNutrientByProjects(project);
	}

	@PostMapping("/saveNurseryNutrient")
	public List<ProjectNurseryNutrient> saveNurseryNutrient(@RequestBody List<ProjectNurseryNutrient> nutrient) {

		return nutrientDao.saveAll(nutrient);
	}

	@GetMapping("/getNurseryPests")
	public List<ProjectNurseryPests> getNurseryPests(@RequestParam Long projectId) {
		FarmProjects project = projectDao.findById(projectId).get();

		return pestDao.findAllPestsByProjects(project);
	}

	@PostMapping("/saveNurseryPests")
	public List<ProjectNurseryPests> saveNurseryPests(@RequestBody List<ProjectNurseryPests> pests) {

		return pestDao.saveAll(pests);
	}

	@GetMapping("/getLandPreparation")
	public List<ProjectLandPreparation> getLandPreparation(@RequestParam Long projectId) {
		FarmProjects project = projectDao.findById(projectId).get();
		return landDao.findAllByProject(project);
	}

	@PostMapping("/saveLandPreparation")
	public @ResponseBody List<ProjectLandPreparation> saveLandPreparation(
			@RequestBody List<ProjectLandPreparation> landPreparation) {
		try {
			return landDao.saveAll(landPreparation);

		} catch (Exception e) {
			throw e;
		}
	}

	@GetMapping("/getTransplantManagement")
	public List<ProjectTransplantManagement> getTransplantManagement(@RequestParam Long projectId) {
		FarmProjects project = projectDao.findById(projectId).get();
		return transplantDao.findAllByProject(project);
	}

	@PostMapping("/saveTransplantManagement")
	public @ResponseBody List<ProjectTransplantManagement> saveTransplantManagement(
			@RequestBody List<ProjectTransplantManagement> transplantManagement) {
		try {
			return transplantDao.saveAll(transplantManagement);

		} catch (Exception e) {
			throw e;
		}
	}

	@GetMapping("/getNutrientManures")
	public List<ProjectOrganicManure> getNutrientManures(@RequestParam Long projectId) {
		FarmProjects project = projectDao.findById(projectId).get();

		return manureDao.findAllByProjects(project);
	}

	@PostMapping("/saveNutrientManures")
	public List<ProjectOrganicManure> saveNutrientManures(@RequestBody List<ProjectOrganicManure> manure) {

		return manureDao.saveAll(manure);
	}

	@GetMapping("/getNutrientBioFertilizers")
	public List<ProjectBioFertilizers> getNutrientBioFertilizers(@RequestParam Long projectId) {
		FarmProjects project = projectDao.findById(projectId).get();

		return bioDao.findAllByProjects(project);
	}

	@PostMapping("/saveNutrientBioFertilizers")
	public List<ProjectBioFertilizers> saveNutrientBioFertilizers(@RequestBody List<ProjectBioFertilizers> bio) {

		return bioDao.saveAll(bio);
	}

	@GetMapping("/getNutrientFertilizers")
	public List<ProjectSyntheticFertilizers> getNutrientFertilizers(@RequestParam Long projectId) {
		FarmProjects project = projectDao.findById(projectId).get();

		return syntheticDao.findAllByProjects(project);
	}

	@PostMapping("/saveNutrientFertilizers")
	public List<ProjectSyntheticFertilizers> saveNutrientFertilizers(
			@RequestBody List<ProjectSyntheticFertilizers> synthetic) {

		return syntheticDao.saveAll(synthetic);
	}

	@GetMapping("/getWeedManagement")
	public List<ProjectWeedManagement> getWeedManagement(@RequestParam Long projectId) {
		FarmProjects project = projectDao.findById(projectId).get();

		return weedMgtDao.findAllByProjects(project);
	}

	@PostMapping("/saveWeedManagement")
	public List<ProjectWeedManagement> saveWeedManagement(@RequestBody List<ProjectWeedManagement> weed) {

		return weedMgtDao.saveAll(weed);
	}
	
	
}