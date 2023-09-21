package com.svastha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.svastha.entity.FarmGrainMarket;
import com.svastha.entity.FarmImages;
import com.svastha.entity.FarmLiveStock;
import com.svastha.entity.FarmTools;
import com.svastha.entity.FarmWaterSource;
import com.svastha.entity.FarmWorkers;
import com.svastha.entity.Farms;
import com.svastha.entity.LandDetails;
import com.svastha.entity.Users;
import com.svastha.model.FarmModel;
import com.svastha.repository.FarmGrainMarketRepository;
import com.svastha.repository.FarmImagesRepository;
import com.svastha.repository.FarmLiveStockRepository;
import com.svastha.repository.FarmRepository;
import com.svastha.repository.FarmToolsRepository;
import com.svastha.repository.FarmWaterSourceRepository;
import com.svastha.repository.FarmWorkersRepository;
import com.svastha.repository.LandDetailsRepository;
import com.svastha.repository.UserRepository;
import com.svastha.service.FilesStorageService;

@RestController
public class FarmController {

	@Autowired
	private FarmRepository farmDao;

	@Autowired
	private LandDetailsRepository landDetailsDao;

	@Autowired
	private FarmWaterSourceRepository waterSourceDao;

	@Autowired
	private FarmLiveStockRepository liveStockDao;

	@Autowired
	private FarmToolsRepository toolsDao;

	@Autowired
	private FarmWorkersRepository workerDao;

	@Autowired
	private FarmGrainMarketRepository grainMarketDao;

	@Autowired
	private UserRepository userDao;

	@Autowired
	FilesStorageService storageService;

	@Autowired
	private FarmImagesRepository imageDao;

	@GetMapping("/farms")
	public @ResponseBody Iterable<Farms> getAllFarms() {
		return farmDao.findAll();
	}

	@GetMapping("/listFarms")
	public @ResponseBody List<Farms> getFarmByUserId(@RequestParam Long userId) {
		Users u = userDao.findByPk1(userId);
		List<Farms> l = farmDao.findByCreatedBy(u);
		return l;
	}

	@GetMapping("/getFarm")
	public @ResponseBody FarmModel getFarmById(@RequestParam Long farmId) {
		FarmModel farmModel = new FarmModel();
		Farms f = farmDao.findById(farmId).get();
		farmModel.setFarm(f);
		farmModel.setLand(landDetailsDao.findLandDetailsByFarm(f));
		farmModel.setLiveStocks(liveStockDao.findAllLiveStocksByFarm(f));
		farmModel.setFarmWorker(workerDao.findAllFarmWorkersByFarm(f));
		farmModel.setGrainMarket(grainMarketDao.findAllGrainMarketByFarm(f));
		farmModel.setTools(toolsDao.findAllToolsByFarm(f));
		farmModel.setWaterSources(waterSourceDao.findAllWaterSourceByFarm(f));
		return farmModel;
	}

	@PostMapping("addFarm")
	public @ResponseBody Farms saveFarm(@RequestBody Farms farm) {
		try {
			Farms f = farmDao.save(farm);
			return f;

		} catch (Exception e) {
			throw e;
		}
	}

	@PostMapping("addLandDetails")
	public @ResponseBody LandDetails saveLandDetails(@RequestBody LandDetails landDetails) {
		try {
			LandDetails land = landDetailsDao.save(landDetails);
			return land;

		} catch (Exception e) {
			throw e;
		}
	}

	@PostMapping("addWaterSource")
	public @ResponseBody String saveWaterSource(@RequestBody Iterable<FarmWaterSource> farmWaterSources) {
		try {
			waterSourceDao.saveAll(farmWaterSources);
			return "Success";
		} catch (Exception e) {
			throw e;
		}
	}

	@PostMapping("addLiveStock")
	public @ResponseBody String saveLiveStock(@RequestBody Iterable<FarmLiveStock> farmLiveStocks) {
		try {
			liveStockDao.saveAll(farmLiveStocks);
			return "Success";

		} catch (Exception e) {
			throw e;
		}
	}

	@PostMapping("addTools")
	public @ResponseBody String saveTools(@RequestBody Iterable<FarmTools> farmTools) {
		try {
			toolsDao.saveAll(farmTools);
			return "Success";

		} catch (Exception e) {
			throw e;
		}
	}

	@PostMapping("addGrainMarket")
	public @ResponseBody String saveGrainMarket(@RequestBody FarmGrainMarket farmGrainsMarkets) {
		try {
			grainMarketDao.save(farmGrainsMarkets);
			return "Success";

		} catch (Exception e) {
			throw e;
		}
	}

	@PostMapping("addWorkers")
	public @ResponseBody String saveWorkers(@RequestBody FarmWorkers workers) {
		try {
			workerDao.save(workers);
			return "success";

		} catch (Exception e) {
			throw e;
		}
	}

	@PostMapping("/upload")
	public @ResponseBody String uploadFile(@RequestBody MultipartFile[] file, @RequestParam Long farmId,
			@RequestParam Long userId) {
		try {
			Farms f = farmDao.findById(farmId).get();
			Users u = userDao.findById(userId).get();
	    	for (MultipartFile multipartFile : file) {
			FarmImages i = new FarmImages();
			i.setFileName(multipartFile.getOriginalFilename());
			i.setCreatedBy(u);
			i.setFarm(f);
			storageService.save(multipartFile);
			imageDao.save(i);
			}

			return "Success";
		} catch (Exception e) {
			return "Failed";
		}
	}
}