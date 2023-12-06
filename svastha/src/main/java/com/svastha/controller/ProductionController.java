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
import com.svastha.dto.ProjectSowingDTO;
import com.svastha.entity.FarmProjects;
import com.svastha.entity.ProjectDSRMethod;
import com.svastha.entity.ProjectIrrigation;
import com.svastha.entity.ProjectSeedTreatment;
import com.svastha.entity.ProjectSeedTreatmentChemical;
import com.svastha.entity.ProjectSowingData;
import com.svastha.entity.ProjectSowingPlots;
import com.svastha.repository.FarmProjectRepository;
import com.svastha.repository.ProjectsDsrRepository;
import com.svastha.repository.ProjectsIrrigationRepository;
import com.svastha.repository.ProjectsSeedTreatmentChemicalRepository;
import com.svastha.repository.ProjectsSeedTreatmentRepository;
import com.svastha.repository.ProjectsSowingDataRepository;
import com.svastha.repository.ProjectsSowingPlotsRepository;

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
	private ProjectsSowingPlotsRepository sowingPlotsDao;

	@Autowired
	private FarmProjectRepository projectDao;

	@Autowired
	private ProjectsIrrigationRepository irrigationDao;

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
	public List<ProjectSowingDTO> getSowingData(@RequestParam Long projectId) {

		List<ProjectSowingDTO> dtos = new ArrayList<>();
		FarmProjects project = projectDao.findById(projectId).get();
		List<ProjectSowingData> sowings = sowingDao.findAllSowingDataByProjects(project);

		for (ProjectSowingData sowing : sowings) {
			ProjectSowingDTO dto = new ProjectSowingDTO();
			dto.setSowingData(sowing);
			List<ProjectSowingPlots> plots = sowingPlotsDao.findAllPlotsBySowing(sowing);
			dto.setPlots(plots);
			dtos.add(dto);
		}

		return dtos;
	}

	@PostMapping("/saveSowingData")
	public void saveSowingData(@RequestBody List<ProjectSowingDTO> dtos) {

		for (ProjectSowingDTO dto : dtos) {
			ProjectSowingData sowing = sowingDao.save(dto.getSowingData());
			List<ProjectSowingPlots> plotsEntity = new ArrayList<>();
			for (ProjectSowingPlots plots : dto.getPlots()) {
				plots.setSowing(sowing);
				plotsEntity.add(plots);
			}
			sowingPlotsDao.saveAll(plotsEntity);
		}
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
}
