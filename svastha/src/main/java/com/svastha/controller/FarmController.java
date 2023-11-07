package com.svastha.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.annotation.MultipartConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import com.svastha.entity.FarmPlots;
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
import com.svastha.repository.FarmPlotsRepository;
import com.svastha.repository.FarmRepository;
import com.svastha.repository.FarmToolsRepository;
import com.svastha.repository.FarmWaterSourceRepository;
import com.svastha.repository.FarmWorkersRepository;
import com.svastha.repository.LandDetailsRepository;
import com.svastha.repository.UserRepository;
import com.svastha.service.FilesStorageService;
import com.svastha.service.MasterService;

@RestController
@MultipartConfig
public class FarmController {

	@Autowired
	private FarmRepository farmDao;

	@Autowired
	private LandDetailsRepository landDetailsDao;

	@Autowired
	private FarmPlotsRepository plotsDao;

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
	private MasterService masterService;

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
		farmModel.setPlots(plotsDao.findAllPlotsByFarm(f));
		farmModel.setLiveStocks(liveStockDao.findAllLiveStocksByFarm(f));
		farmModel.setFarmWorker(workerDao.findAllFarmWorkersByFarm(f));
		farmModel.setGrainMarket(grainMarketDao.findAllGrainMarketByFarm(f));
		farmModel.setTools(toolsDao.findAllToolsByFarm(f));
		farmModel.setWaterSources(waterSourceDao.findAllWaterSourceByFarm(f));
		return farmModel;
	}

	@SuppressWarnings("deprecation")
	@PostMapping("addFarm")
	public @ResponseBody String saveFarm(@RequestBody Farms farm, @RequestParam(required = false) MultipartFile file) {
		try {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			StringBuilder code = new StringBuilder();
			code.append(farm.getFarmerName());
			code.append("-");
			code.append(farm.getVillage());
			code.append("-");
			code.append(timestamp.getYear());
			code.append("-");
			code.append(timestamp.getTime());
			farm.setRegNumber(code.toString());
			farm.setDateOfReg(timestamp.toString());
			Farms f = farmDao.save(farm);
			if (file != null && !file.isEmpty()) {
				FarmImages i = new FarmImages();
				System.out.println(" Into for loop : 00");
				i.setCreatedBy(f.getCreatedBy());
				System.out.println(" Into for loop 1: ");

				i.setFarm(f);
				System.out.println(" Into for loop 2: ");
				Path p = storageService.createFolder("\\farmerImage\\" + f.getPk1());
				String filePath = storageService.save(file, p);
				i.setFileName(filePath);
				i.setPath(p.toString());
				System.out.println(" Into for loop : 3");

				imageDao.save(i);
			}
			return f.getRegNumber();

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

	@PostMapping("addPlots")
	public @ResponseBody String savePlots(@RequestBody Iterable<FarmPlots> farmPlots) {
		try {
			plotsDao.saveAll(farmPlots);
			return "Success";
		} catch (Exception e) {
			throw e;
		}
	}

	@PostMapping("addLiveStock")
	public @ResponseBody String saveLiveStock(@RequestBody Iterable<FarmLiveStock> farmLiveStocks) {
		try {
			for (FarmLiveStock farmLiveStock : farmLiveStocks) {
				if (farmLiveStock.getLivestock().equals("others")) {
					masterService.addLiveStocksFromOthers(farmLiveStock.getOthers(), farmLiveStock.getCreatedBy());
					farmLiveStock.setLivestock(farmLiveStock.getOthers());
					farmLiveStock.setOthers(null);
				}
				liveStockDao.save(farmLiveStock);

			}
			return "Success";

		} catch (Exception e) {
			throw e;
		}
	}

	@PostMapping("addTools")
	public @ResponseBody String saveTools(@RequestBody Iterable<FarmTools> farmTools) {
		try {
			for (FarmTools tools : farmTools) {
				if (tools.getTool().equals("others")) {
					masterService.addToolsFromOthers(tools.getOthers(), tools.getCreatedBy());
					tools.setTool(tools.getOthers());
					tools.setOthers(null);
				}
				toolsDao.save(tools);
			}
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
	public @ResponseBody String uploadPhoto(@RequestBody MultipartFile[] file, @RequestParam String farmId,
			@RequestParam String userId) {
		Long fid = Long.parseLong(farmId);
		Long uid = Long.parseLong(userId);
		System.out.println("req" + farmId + "   " + userId);
		Farms f = farmDao.findById(fid).get();
		Users u = userDao.findById(uid).get();

		Path p = storageService.createFolder("\\farmerImage\\" + farmId);

		for (MultipartFile multipartFile : file) {
			FarmImages i = new FarmImages();
			i.setCreatedBy(u);
			i.setFarm(f);
			String filePath = storageService.save(multipartFile, p);
			i.setFileName(filePath);
			i.setPath(p.toString());
			imageDao.save(i);
		}
		return "Success";
	}

	@GetMapping("/getfarmerimage")
	public ResponseEntity getFarmerImage(@RequestParam String farmId) throws IOException {
		Long fid = Long.parseLong(farmId);
		Farms f = farmDao.findById(fid).get();
		List<FarmImages> images = imageDao.findAllImagesByFarm(f);
		Path p = Paths.get(images.get(0).getPath());
		Resource resource = null;
		try {
			resource = storageService.load(p, images.get(0).getFileName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok().contentLength(resource.contentLength()).contentType(MediaType.ALL).body(resource);
	}
}