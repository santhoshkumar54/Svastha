package com.svastha.controller;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.MultipartConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
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
import com.svastha.logs.LogServiceFactory;
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
import com.svastha.service.ExcelWriter;
import com.svastha.service.FarmExcelWriter;
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

	@Autowired
	private FarmExcelWriter excel;

	public static final String SEPARATOR = FileSystems.getDefault().getSeparator();

	@GetMapping("/farms")
	public @ResponseBody Page<Farms> getAllFarms(@RequestParam(required = false) Long districtId,
			@RequestParam(required = false) Long thalukId, @RequestParam(required = false) Long villageId,
			@RequestParam(required = false) String key, @RequestParam(required = false) Long userId,
			@RequestParam(required = false) String type, Pageable pageable) {
		Page<Farms> farms = farmDao.findWithFilters(thalukId, districtId, villageId, key, userId, type, pageable);
		return farms;
	}

	@GetMapping("/exportFarms")
	public @ResponseBody String exportFarms(@RequestParam(required = false) Long districtId,
			@RequestParam(required = false) Long thalukId, @RequestParam(required = false) Long villageId,
			@RequestParam(required = false) String key, @RequestParam(required = false) Long userId,
			@RequestParam String email, @RequestParam(required = false) String type) {
		try {
			excel.startFarmExportV2(districtId, thalukId, villageId, key, userId, type, email);
			return "The exported data will be sent to your email.";
		} catch (Exception e) {
			return "Failed to trigger batch job: " + e.getMessage();
		}
	}

	@GetMapping("/listFarms")
	public @ResponseBody List<Farms> getFarmByUserId(@RequestParam Long userId) {
		Users u = userDao.findByPk1(userId);
		List<Farms> l = farmDao.findByCreatedBy(u, Sort.by(Sort.Direction.DESC, "pk1"));
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

	@PostMapping("addFarm")
	public @ResponseBody Farms saveFarm(@RequestParam String farmObj,
			@RequestParam(required = false) MultipartFile file) {

		try {
			Farms farm = new Gson().fromJson(farmObj, Farms.class);
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			farm.setDateOfReg(timestamp.toString());
			Farms f = farmDao.save(farm);
			f.setRegNumber(String.format("%04d", f.getPk1()));
			if (file != null && !file.isEmpty()) {
				FarmImages i = new FarmImages();
				System.out.println(" Into for loop : 00");
				i.setCreatedBy(f.getCreatedBy());
				System.out.println(" Into for loop 1: ");

				i.setFarm(f);
				System.out.println(" Into for loop 2: ");
				String folderPath = SEPARATOR + "farmerImage" + SEPARATOR + f.getPk1();
				Path p = storageService.createFolder(folderPath);
				String filePath = storageService.save(file, p);
				i.setFileName(filePath);
				i.setPath(p.toString());
				System.out.println(" Into for loop : 3");
				imageDao.save(i);
				f.setFarmerImage("/farmer/images" + folderPath + SEPARATOR + filePath);
			}
			f = farmDao.save(f);
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

	@GetMapping("surveylist")
	public @ResponseBody List<String> getSurveyNumber(@RequestParam Long farmId) {
		Farms f = farmDao.findById(farmId).get();
		LandDetails landDetails = landDetailsDao.findLandDetailsByFarm(f);
		List<String> surveyNumbers = new ArrayList<>();
		if (landDetails != null) {
			String surveyNumber = landDetails.getSurveyNumber();
			if (surveyNumber != null) {
				String[] surveys = surveyNumber.split(",");
				for (String string : surveys) {
					surveyNumbers.add(string.trim());
				}
			}
		}
		return surveyNumbers;
	}

	@PostMapping("addWaterSource")
	public @ResponseBody String saveWaterSource(@RequestBody List<FarmWaterSource> farmWaterSources) {
		try {
			waterSourceDao.saveAll(farmWaterSources);
			return "Success";
		} catch (Exception e) {
			throw e;
		}
	}

	@PostMapping("addPlots")
	public @ResponseBody String savePlots(@RequestBody List<FarmPlots> farmPlots) {
		try {
			plotsDao.saveAll(farmPlots);
			return "Success";
		} catch (Exception e) {
			throw e;
		}
	}

	@PostMapping("addLiveStock")
	public @ResponseBody String saveLiveStock(@RequestBody List<FarmLiveStock> farmLiveStocks) {

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
	public @ResponseBody String saveTools(@RequestBody List<FarmTools> farmTools) {
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
	public @ResponseBody String uploadPhoto(@RequestParam MultipartFile[] file, @RequestParam String farmId,
			@RequestParam String userId) {
		Long fid = Long.parseLong(farmId);
		Long uid = Long.parseLong(userId);
		System.out.println("req" + farmId + "   " + userId);
		Farms f = farmDao.findById(fid).get();
		Users u = userDao.findById(uid).get();

		Path p = storageService.createFolder(SEPARATOR + "farmerImage" + SEPARATOR + farmId);

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
	public ResponseEntity<Resource> getFarmerImage(@RequestParam String farmId) throws IOException {
		Long fid = Long.parseLong(farmId);
		Farms f = farmDao.findById(fid).get();
		List<FarmImages> images = imageDao.findAllImagesByFarm(f);
		Path p = Paths.get(images.get(0).getPath());
		Resource resource = null;
		MediaType mediaType = null;
		try {
			resource = storageService.load(p, images.get(0).getFileName());
			mediaType = storageService.getContentType(p, images.get(0).getFileName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok().contentLength(resource.contentLength()).contentType(mediaType).body(resource);
	}

	@Scheduled(cron = "0 30 2 * * *")
	public void updateCompletion() {
		farmDao.updateFarmCompletionPercentage();
	}
}