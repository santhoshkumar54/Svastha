package com.svastha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.svastha.entity.Crops;
import com.svastha.entity.Fertilizers;
import com.svastha.entity.LiveStock;
import com.svastha.entity.MasterCrop;
import com.svastha.entity.MasterSeason;
import com.svastha.entity.MasterYear;
import com.svastha.entity.TransplantMethods;
import com.svastha.entity.WaterSource;
import com.svastha.model.MasterProjectModel;
import com.svastha.repository.CropRepository;
import com.svastha.repository.FertilizerRepository;
import com.svastha.repository.LiveStockRepository;
import com.svastha.repository.MasterCropRepository;
import com.svastha.repository.MasterSeasonRepository;
import com.svastha.repository.MasterYearRepository;
import com.svastha.repository.TransplantMethodRepository;
import com.svastha.repository.WaterSourceRepository;

@RestController
public class MasterController {

	@Autowired
	private LiveStockRepository liveStockDao;

	@Autowired
	private WaterSourceRepository waterSourceDao;

	@Autowired
	private TransplantMethodRepository transplantMethodDao;

	@Autowired
	private CropRepository cropDao;

	@Autowired
	private FertilizerRepository fertilizerDao;
	
	@Autowired
	private MasterCropRepository masterCropDao;
	
	@Autowired
	private MasterYearRepository yearDao;
	
	@Autowired
	private MasterSeasonRepository seasonDao;

	@GetMapping(path = "/liveStocks")
	public @ResponseBody Iterable<LiveStock> getLiveStocks() {

		return liveStockDao.findAll();
	}

	@GetMapping(path = "/waterSources")
	public @ResponseBody Iterable<WaterSource> getWaterSources() {

		return waterSourceDao.findAll();
	}

	@GetMapping(path = "/fertilizers")
	public @ResponseBody Iterable<Fertilizers> getFertilizers() {

		return fertilizerDao.findAll();
	}

	@GetMapping(path = "/crops")
	public @ResponseBody Iterable<Crops> getCrops() {

		return cropDao.findAll();
	}

	@GetMapping(path = "/transplantMethod")
	public @ResponseBody Iterable<TransplantMethods> getTransplantMethods() {

		return transplantMethodDao.findAll();
	}
	
	@GetMapping(path = "/getProjectMaster")
	public @ResponseBody MasterProjectModel getMasterProject() {
		MasterProjectModel master = new MasterProjectModel();
		master.setYear(yearDao.findAll());
		master.setSeason(seasonDao.findAll());
		master.setCrop(masterCropDao.findAll());
		return master;
	}

}
