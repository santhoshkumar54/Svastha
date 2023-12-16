package com.svastha.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.svastha.dto.ProjectSeedTreatmentDTO;
import com.svastha.entity.FarmProjects;
import com.svastha.entity.ProjectDSRMethod;
import com.svastha.entity.ProjectIrrigation;
import com.svastha.entity.ProjectNurseryWater;
import com.svastha.entity.ProjectSeedTreatment;
import com.svastha.entity.ProjectSeedTreatmentChemical;
import com.svastha.entity.ProjectSowingData;
import com.svastha.repository.FarmProjectRepository;
import com.svastha.repository.ProjectsDsrRepository;
import com.svastha.repository.ProjectsIrrigationRepository;
import com.svastha.repository.ProjectsSeedTreatmentChemicalRepository;
import com.svastha.repository.ProjectsSeedTreatmentRepository;
import com.svastha.repository.ProjectsSowingDataRepository;
import com.svastha.repository.ProjectsWaterRepository;

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

}
