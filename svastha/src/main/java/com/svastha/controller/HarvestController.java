package com.svastha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.svastha.entity.FarmProjects;
import com.svastha.entity.HarvestPriceConfirmation;
import com.svastha.entity.ProjectDispatch;
import com.svastha.entity.ProjectHarvest;
import com.svastha.entity.ProjectPacking;
import com.svastha.entity.ProjectPostPurchase;
import com.svastha.entity.ProjectPrePurchase;
import com.svastha.entity.ProjectProcurement;
import com.svastha.entity.ProjectStorage;
import com.svastha.repository.FarmProjectRepository;
import com.svastha.repository.HarvestPriceConfirmationRepository;
import com.svastha.repository.ProjectsDispatchRepository;
import com.svastha.repository.ProjectsHarvestRepository;
import com.svastha.repository.ProjectsPackingRepository;
import com.svastha.repository.ProjectsPostPurchaseRepository;
import com.svastha.repository.ProjectsPrePurchaseRepository;
import com.svastha.repository.ProjectsProcurementRepository;
import com.svastha.repository.ProjectsStorageRepository;

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

	@Autowired
	private ProjectsStorageRepository storageDao;

	@Autowired
	private ProjectsPrePurchaseRepository preDao;

	@Autowired
	private ProjectsPostPurchaseRepository postDao;

	@Autowired
	private ProjectsHarvestRepository harvestDao;

	@Autowired
	private HarvestPriceConfirmationRepository priceConfirmationDao;

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

	@GetMapping("/getStorage")
	public List<ProjectStorage> getStorage(@RequestParam Long projectId) {
		FarmProjects project = projectDao.findById(projectId).get();

		return storageDao.findAllByProjects(project);
	}

	@PostMapping("/saveStorage")
	public List<ProjectStorage> saveStorage(@RequestBody List<ProjectStorage> storage) {

		return storageDao.saveAll(storage);
	}

	@GetMapping("/getPrePurchase")
	public List<ProjectPrePurchase> getPrePurchase(@RequestParam Long projectId) {
		FarmProjects project = projectDao.findById(projectId).get();

		return preDao.findAllByProjects(project);
	}

	@PostMapping("/savePrePurchase")
	public List<ProjectPrePurchase> savePrePurchase(@RequestBody List<ProjectPrePurchase> prePurchase) {

		return preDao.saveAll(prePurchase);
	}

	@GetMapping("/getPostPurchase")
	public List<ProjectPostPurchase> getPostPurchase(@RequestParam Long projectId) {
		FarmProjects project = projectDao.findById(projectId).get();

		return postDao.findAllByProjects(project);
	}

	@PostMapping("/savePostPurchase")
	public List<ProjectPostPurchase> savePostPurchase(@RequestBody List<ProjectPostPurchase> postPurchase) {

		return postDao.saveAll(postPurchase);
	}

	@GetMapping("/getHarvestData")
	public ProjectHarvest getHarvestData(@RequestParam Long projectId) {
		FarmProjects project = projectDao.findById(projectId).get();

		return harvestDao.findByProjects(project);
	}

	@PostMapping("/saveHarvestData")
	public ProjectHarvest saveHarvestData(@RequestBody ProjectHarvest harvest) {

		return harvestDao.save(harvest);
	}

	@GetMapping("/getPriceConfirmation")
	public List<HarvestPriceConfirmation> getPriceConfirmation(@RequestParam Long projectId) {
		FarmProjects project = projectDao.findById(projectId).get();

		return priceConfirmationDao.findAllByProjects(project);
	}

	@PostMapping("/savePriceConfirmation")
	public HarvestPriceConfirmation savePriceConfirmation(@RequestBody HarvestPriceConfirmation price) {

		return priceConfirmationDao.save(price);
	}

}
