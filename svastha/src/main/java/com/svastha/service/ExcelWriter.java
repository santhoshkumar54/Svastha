package com.svastha.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.svastha.entity.FarmGrainMarket;
import com.svastha.entity.FarmLiveStock;
import com.svastha.entity.FarmPlots;
import com.svastha.entity.FarmProjects;
import com.svastha.entity.FarmTools;
import com.svastha.entity.FarmWaterSource;
import com.svastha.entity.FarmWorkers;
import com.svastha.entity.Farms;
import com.svastha.entity.LandDetails;
import com.svastha.entity.ProjectDSRMethod;
import com.svastha.entity.ProjectNurseryNutrient;
import com.svastha.entity.ProjectNurseryPests;
import com.svastha.entity.ProjectNurseryWater;
import com.svastha.entity.ProjectNurseryWeed;
import com.svastha.entity.ProjectPlots;
import com.svastha.entity.ProjectSeedTreatment;
import com.svastha.entity.ProjectSeedTreatmentChemical;
import com.svastha.entity.ProjectSowingData;
import com.svastha.repository.FarmGrainMarketRepository;
import com.svastha.repository.FarmLiveStockRepository;
import com.svastha.repository.FarmPlotsRepository;
import com.svastha.repository.FarmProjectRepository;
import com.svastha.repository.FarmRepository;
import com.svastha.repository.FarmToolsRepository;
import com.svastha.repository.FarmWaterSourceRepository;
import com.svastha.repository.FarmWorkersRepository;
import com.svastha.repository.LandDetailsRepository;
import com.svastha.repository.ProjectsBioFertilizerRepository;
import com.svastha.repository.ProjectsDsrRepository;
import com.svastha.repository.ProjectsIrrigationRepository;
import com.svastha.repository.ProjectsLandPreparationRepository;
import com.svastha.repository.ProjectsManureRepository;
import com.svastha.repository.ProjectsNutrientRepository;
import com.svastha.repository.ProjectsPestsRepository;
import com.svastha.repository.ProjectsPlotsRepository;
import com.svastha.repository.ProjectsSeedTreatmentChemicalRepository;
import com.svastha.repository.ProjectsSeedTreatmentRepository;
import com.svastha.repository.ProjectsSowingDataRepository;
import com.svastha.repository.ProjectsSyntheticFertilizerRepository;
import com.svastha.repository.ProjectsTransplantManagementRepository;
import com.svastha.repository.ProjectsWaterRepository;
import com.svastha.repository.ProjectsWeedManagementRepository;
import com.svastha.repository.ProjectsWeedRepository;

@Component
public class ExcelWriter {

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
	private FarmProjectRepository projectDao;

	@Autowired
	private ProjectsPlotsRepository projectPlotsDao;

	@Autowired
	private ProjectsSeedTreatmentRepository seedTreatmentDao;

	@Autowired
	private ProjectsSeedTreatmentChemicalRepository seedTreatmentChemicalDao;

	@Autowired
	private ProjectsDsrRepository dsrDao;

	@Autowired
	private ProjectsSowingDataRepository sowingDao;

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

	@Autowired
	private JavaMailSender mailSender;

	@Async
	public void startFarmExport(Long districtId, Long thalukId, Long villageId, String key, Long userId, String type,
			String email) {

		List<Farms> farms = farmDao.findWithFilters(thalukId, districtId, villageId, key, userId, type);
		List<LandDetails> lands = landDetailsDao.findByFarmIn(farms);
		List<FarmPlots> plots = plotsDao.findByFarmIn(farms);
		List<FarmWaterSource> water = waterSourceDao.findByFarmIn(farms);
		List<FarmLiveStock> liveStock = liveStockDao.findByFarmIn(farms);
		List<FarmTools> tools = toolsDao.findByFarmIn(farms);
		List<FarmWorkers> worker = workerDao.findByFarmIn(farms);
		List<FarmGrainMarket> grainMarket = grainMarketDao.findByFarmIn(farms);

		String excelName = "farmers_"+ System.currentTimeMillis() + ".xlsx";
		String excelFilePath = "C:\\Users\\smsan\\work\\svastha project\\Svastha\\svastha\\"+excelName;
		System.out.println("excel writer called");

		try (Workbook workbook = new XSSFWorkbook()) {
			Thread.sleep(5000); // Sleep for 5 seconds

			generateFarmSheet(workbook, farms);
			generateLandSheet(workbook, lands);
			generatePlotSheet(workbook, plots);
			generateWaterSheet(workbook, water);
			generateLiveStockSheet(workbook, liveStock);
			generateToolsSheet(workbook, tools);
			generateWorkerSheet(workbook, worker);
			generateMarketSheet(workbook, grainMarket);
			try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
				workbook.write(outputStream);
				sendEmail(email, "Exported Farmer Data", "Please find the attched farmer data.",
						FileUtils.readFileToByteArray(new File(excelFilePath)), excelName);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void generateFarmSheet(Workbook workbook, List<Farms> farms) {
		try {
			Sheet sheet = workbook.createSheet("Farmer KYC");
			Row headerRow = sheet.createRow(0);

			headerRow.createCell(0).setCellValue("ID");
			headerRow.createCell(1).setCellValue("Farmer Name");
			headerRow.createCell(2).setCellValue("Father Name");
			headerRow.createCell(3).setCellValue("Gender");
			headerRow.createCell(4).setCellValue("Registration Number");
			headerRow.createCell(5).setCellValue("Farmer Type");
			headerRow.createCell(6).setCellValue("Contact 1");
			headerRow.createCell(7).setCellValue("Contact 2");
			headerRow.createCell(8).setCellValue("Mobile type");
			headerRow.createCell(9).setCellValue("Landline");
			headerRow.createCell(10).setCellValue("Street");
			headerRow.createCell(11).setCellValue("Block");
			headerRow.createCell(12).setCellValue("Village");
			headerRow.createCell(13).setCellValue("Thaluk");
			headerRow.createCell(14).setCellValue("District");
			headerRow.createCell(15).setCellValue("State");
			headerRow.createCell(16).setCellValue("Pin code");
			headerRow.createCell(17).setCellValue("Aadhar Number");
			headerRow.createCell(18).setCellValue("Bank Name");
			headerRow.createCell(19).setCellValue("Bank Account");
			headerRow.createCell(20).setCellValue("IFSC Code");
			headerRow.createCell(21).setCellValue("Created By");
			headerRow.createCell(22).setCellValue("Created Date");
			int rowNum = 1;
			for (Farms farm : farms) {
				Row row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(farm.getPk1());
				row.createCell(1).setCellValue(farm.getFarmerName());
				row.createCell(2).setCellValue(farm.getFatherName());
				row.createCell(3).setCellValue(farm.getGender());
				row.createCell(4).setCellValue(farm.getRegNumber());
				row.createCell(5).setCellValue(farm.getFarmerType());
				row.createCell(6).setCellValue(farm.getMobile1());
				row.createCell(7).setCellValue(farm.getMobile2());
				row.createCell(8).setCellValue(farm.getMobileType());
				row.createCell(9).setCellValue(farm.getLandline());
				row.createCell(10).setCellValue(farm.getStreet());
				row.createCell(11).setCellValue(farm.getDoorNumber());
				row.createCell(12).setCellValue(farm.getVillageId().getName());
				row.createCell(13).setCellValue(farm.getThalukId().getName());
				row.createCell(14).setCellValue(farm.getDistrictId().getName());
				row.createCell(15).setCellValue(farm.getState());
				row.createCell(16).setCellValue(farm.getPincode());
				row.createCell(17).setCellValue(farm.getAadhaar());
				row.createCell(18).setCellValue(farm.getBank());
				row.createCell(19).setCellValue(farm.getBankAccount());
				row.createCell(20).setCellValue(farm.getIfsc());
				row.createCell(21).setCellValue(farm.getCreatedBy().getUsername());
				row.createCell(22).setCellValue(farm.getCreatedDt());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void generateLandSheet(Workbook workbook, List<LandDetails> lands) {
		try {
			Sheet sheet = workbook.createSheet("Land Details");
			Row headerRow = sheet.createRow(0);
			headerRow.createCell(0).setCellValue("ID");
			headerRow.createCell(1).setCellValue("Farmer No.");
			headerRow.createCell(2).setCellValue("Owned Area");
			headerRow.createCell(3).setCellValue("Leased Area");
			headerRow.createCell(4).setCellValue("Cultivable Area");
			headerRow.createCell(5).setCellValue("Soil Type");
			headerRow.createCell(6).setCellValue("Survey Number");
			headerRow.createCell(7).setCellValue("Patta Number");
			headerRow.createCell(8).setCellValue("Created By");
			headerRow.createCell(9).setCellValue("Created Date");
			int rowNum = 1;
			for (LandDetails land : lands) {
				Row row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(land.getPk1());
				row.createCell(1).setCellValue(land.getFarm().getRegNumber());
				row.createCell(2).setCellValue(land.getAreaAcres());
				row.createCell(3).setCellValue(land.getAreaLeased());
				row.createCell(4).setCellValue(land.getAreaCultivable());
				row.createCell(5).setCellValue(land.getSoilData());
				row.createCell(6).setCellValue(land.getSurveyNumber());
				row.createCell(7).setCellValue(land.getPattaNumber());
				row.createCell(8).setCellValue(land.getCreatedBy().getUsername());
				row.createCell(9).setCellValue(land.getCreatedDt());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void generatePlotSheet(Workbook workbook, List<FarmPlots> plot) {
		try {
			Sheet sheet = workbook.createSheet("Plot Details");
			Row headerRow = sheet.createRow(0);
			headerRow.createCell(0).setCellValue("ID");
			headerRow.createCell(1).setCellValue("Farmer No.");
			headerRow.createCell(2).setCellValue("Plot type");
			headerRow.createCell(3).setCellValue("Plot Number");
			headerRow.createCell(4).setCellValue("Area in acres");
			headerRow.createCell(5).setCellValue("Survey Number");
			headerRow.createCell(6).setCellValue("Created By");
			headerRow.createCell(7).setCellValue("Created Date");
			int rowNum = 1;
			for (FarmPlots obj : plot) {
				Row row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(obj.getPk1());
				row.createCell(1).setCellValue(obj.getFarm().getRegNumber());
				row.createCell(2).setCellValue(obj.getPlotType());
				row.createCell(3).setCellValue(obj.getPlotNumber());
				row.createCell(4).setCellValue(obj.getAreaInAcres());
				row.createCell(5).setCellValue(obj.getSurveyNumber());
				row.createCell(6).setCellValue(obj.getCreatedBy().getUsername());
				row.createCell(7).setCellValue(obj.getCreatedDt());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void generateWaterSheet(Workbook workbook, List<FarmWaterSource> water) {
		try {
			Sheet sheet = workbook.createSheet("Water Source");
			Row headerRow = sheet.createRow(0);
			headerRow.createCell(0).setCellValue("ID");
			headerRow.createCell(1).setCellValue("Farmer No.");
			headerRow.createCell(2).setCellValue("Source");
			headerRow.createCell(3).setCellValue("Created By");
			headerRow.createCell(4).setCellValue("Created Date");
			int rowNum = 1;
			for (FarmWaterSource obj : water) {
				Row row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(obj.getPk1());
				row.createCell(1).setCellValue(obj.getFarm().getRegNumber());
				row.createCell(2).setCellValue(obj.getWaterSource());
				row.createCell(3).setCellValue(obj.getCreatedBy().getUsername());
				row.createCell(4).setCellValue(obj.getCreatedDt());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void generateLiveStockSheet(Workbook workbook, List<FarmLiveStock> liveStock) {
		try {
			Sheet sheet = workbook.createSheet("Livestock Details");
			Row headerRow = sheet.createRow(0);
			headerRow.createCell(0).setCellValue("ID");
			headerRow.createCell(1).setCellValue("Farmer No.");
			headerRow.createCell(2).setCellValue("Live stock");
			headerRow.createCell(3).setCellValue("Number");
			headerRow.createCell(4).setCellValue("Created By");
			headerRow.createCell(5).setCellValue("Created Date");
			int rowNum = 1;
			for (FarmLiveStock obj : liveStock) {
				Row row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(obj.getPk1());
				row.createCell(1).setCellValue(obj.getFarm().getRegNumber());
				row.createCell(2).setCellValue(obj.getLivestock());
				row.createCell(3).setCellValue(obj.getCount());
				row.createCell(4).setCellValue(obj.getCreatedBy().getUsername());
				row.createCell(5).setCellValue(obj.getCreatedDt());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void generateToolsSheet(Workbook workbook, List<FarmTools> tools) {
		try {
			Sheet sheet = workbook.createSheet("Agriculture Equipments");
			Row headerRow = sheet.createRow(0);
			headerRow.createCell(0).setCellValue("ID");
			headerRow.createCell(1).setCellValue("Farmer No.");
			headerRow.createCell(2).setCellValue("Tool type");
			headerRow.createCell(3).setCellValue("Number");
			headerRow.createCell(4).setCellValue("Created By");
			headerRow.createCell(5).setCellValue("Created Date");
			int rowNum = 1;
			for (FarmTools obj : tools) {
				Row row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(obj.getPk1());
				row.createCell(1).setCellValue(obj.getFarm().getRegNumber());
				row.createCell(2).setCellValue(obj.getTool());
				row.createCell(3).setCellValue(obj.getCount());
				row.createCell(4).setCellValue(obj.getCreatedBy().getUsername());
				row.createCell(5).setCellValue(obj.getCreatedDt());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void generateWorkerSheet(Workbook workbook, List<FarmWorkers> workers) {
		try {
			Sheet sheet = workbook.createSheet("Worker Details");
			Row headerRow = sheet.createRow(0);
			headerRow.createCell(0).setCellValue("ID");
			headerRow.createCell(1).setCellValue("Farmer No.");
			headerRow.createCell(2).setCellValue("Own Workers count");
			headerRow.createCell(3).setCellValue("Hired Workers count");
			headerRow.createCell(4).setCellValue("Created By");
			headerRow.createCell(5).setCellValue("Created Date");
			int rowNum = 1;
			for (FarmWorkers obj : workers) {
				Row row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(obj.getPk1());
				row.createCell(1).setCellValue(obj.getFarm().getRegNumber());
				row.createCell(2).setCellValue(obj.getOwnWorkers());
				row.createCell(3).setCellValue(obj.getHiredWorkers());
				row.createCell(4).setCellValue(obj.getCreatedBy().getUsername());
				row.createCell(5).setCellValue(obj.getCreatedDt());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void generateMarketSheet(Workbook workbook, List<FarmGrainMarket> market) {
		try {
			Sheet sheet = workbook.createSheet("Grain Market Details");
			Row headerRow = sheet.createRow(0);
			headerRow.createCell(0).setCellValue("ID");
			headerRow.createCell(1).setCellValue("Farmer No.");
			headerRow.createCell(2).setCellValue("Market type");
			headerRow.createCell(3).setCellValue("Market Name");
			headerRow.createCell(4).setCellValue("Firm Name");
			headerRow.createCell(5).setCellValue("Address");
			headerRow.createCell(6).setCellValue("Contact Number");
			headerRow.createCell(7).setCellValue("GST Number");
			headerRow.createCell(8).setCellValue("Bank Name");
			headerRow.createCell(9).setCellValue("IFSC Code");
			headerRow.createCell(10).setCellValue("Account Number");
			headerRow.createCell(11).setCellValue("Created By");
			headerRow.createCell(12).setCellValue("Created Date");
			int rowNum = 1;
			for (FarmGrainMarket obj : market) {
				Row row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(obj.getPk1());
				row.createCell(1).setCellValue(obj.getFarm().getRegNumber());
				row.createCell(2).setCellValue(obj.getAgentType());
				row.createCell(3).setCellValue(obj.getMarketName());
				row.createCell(4).setCellValue(obj.getFirmName());
				row.createCell(5).setCellValue(obj.getAddress());
				row.createCell(6).setCellValue(obj.getContactNumber());
				row.createCell(7).setCellValue(obj.getGstNumber());
				row.createCell(8).setCellValue(obj.getBank());
				row.createCell(9).setCellValue(obj.getIfsc());
				row.createCell(10).setCellValue(obj.getAccountNumber());
				row.createCell(11).setCellValue(obj.getCreatedBy().getUsername());
				row.createCell(12).setCellValue(obj.getCreatedDt());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Async
	public void startProjectExport(Long yearId, Long seasonId, Long cropId, String key, Long userId, String email) {
		List<FarmProjects> projects = projectDao.findWithFilters(yearId, seasonId, cropId, key, userId);
		List<ProjectPlots> plots = projectPlotsDao.findByProjectIn(projects);

		System.out.println("project size="+projects.size()+    "   plot size-"+plots.size());
		Map<FarmProjects, List<String>> projectsMap = generateProjectPlotMap(plots);

		List<ProjectSeedTreatment> seed = seedTreatmentDao.findByProjectsIn(projects);

		List<ProjectSeedTreatmentChemical> seedChem = seedTreatmentChemicalDao.findBySeedTreatmentIn(seed);

		List<ProjectDSRMethod> dsr = dsrDao.findByProjectsIn(projects);

		List<ProjectSowingData> sowing = sowingDao.findByProjectsIn(projects);

		List<ProjectNurseryWater> water = waterDao.findByProjectsIn(projects);

		List<ProjectNurseryWeed> weed = weedDao.findByProjectsIn(projects);

		List<ProjectNurseryNutrient> nutrient = nutrientDao.findByProjectsIn(projects);

		List<ProjectNurseryPests> pset = pestDao.findByProjectsIn(projects);

		String excelName = "projects_"+ System.currentTimeMillis() + ".xlsx";
		String excelFilePath = "C:\\Users\\smsan\\work\\svastha project\\Svastha\\svastha\\"+ excelName;
		System.out.println("excel writer called");
		try (Workbook workbook = new XSSFWorkbook()) {
			Thread.sleep(5000); // Sleep for 5 seconds

			generateProjectSheet(workbook, projectsMap);
			generateSeedTreatment(workbook, seed);
			generateSeedTreatmentChemicals(workbook, seedChem);
			generateDsrMethod(workbook, dsr);
			generateDsrSowingMethod(workbook, sowing);
			generateNurseryWaterMethod(workbook, water);
			generateNurseryWeed(workbook, weed);
			generateNurseryNutrient(workbook, nutrient);
			generateNurseryPest(workbook, pset);
			try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
				workbook.write(outputStream);
				sendEmail(email, "Exported Farmer Data", "Please find the attched farmer data.",
						FileUtils.readFileToByteArray(new File(excelFilePath)), excelName);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Map<FarmProjects, List<String>> generateProjectPlotMap(List<ProjectPlots> plots) {
		Map<FarmProjects, List<String>> projectsMap = new HashMap<>();
		for (ProjectPlots projectPlot : plots) {
			FarmProjects project = projectPlot.getProject();
			FarmPlots plot = projectPlot.getPlots();

			// Get or create a list of FarmPlots IDs for the current FarmProjects
			List<String> plotIds = projectsMap.computeIfAbsent(project, k -> new ArrayList<>());

			// Add the FarmPlots ID to the list
			plotIds.add(plot.getPk1() + " - " + plot.getPlotNumber());
		}
		return projectsMap;
	}

	public void generateProjectSheet(Workbook workbook, Map<FarmProjects, List<String>> projectsMap) {
		try {
			Sheet sheet = workbook.createSheet("Project Details");
			Row headerRow = sheet.createRow(0);
			headerRow.createCell(0).setCellValue("ID");
			headerRow.createCell(1).setCellValue("Farmer Name");
			headerRow.createCell(2).setCellValue("Village");
			headerRow.createCell(3).setCellValue("Thaluk");
			headerRow.createCell(4).setCellValue("District");
			headerRow.createCell(5).setCellValue("Farmer Reg No");
			headerRow.createCell(6).setCellValue("Year");
			headerRow.createCell(7).setCellValue("Crop");
			headerRow.createCell(8).setCellValue("Season");
			headerRow.createCell(9).setCellValue("Plot ID - Number");
			headerRow.createCell(10).setCellValue("Created By");
			headerRow.createCell(11).setCellValue("Created Date");

			int rowNum = 1;
			for (Map.Entry<FarmProjects, List<String>> entry : projectsMap.entrySet()) {
				FarmProjects obj = entry.getKey();
				List<String> plotIds = entry.getValue();

				Row row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(obj.getPk1());
				row.createCell(1).setCellValue(obj.getFarm().getFarmerName());
				row.createCell(2).setCellValue(obj.getFarm().getVillageId().getName());
				row.createCell(3).setCellValue(obj.getFarm().getThalukId().getName());
				row.createCell(4).setCellValue(obj.getFarm().getDistrictId().getName());
				row.createCell(5).setCellValue(obj.getFarm().getRegNumber());
				row.createCell(6).setCellValue(obj.getYear().getYear());
				row.createCell(7).setCellValue(obj.getCrop().getCrop());
				row.createCell(8).setCellValue(obj.getSeason().getSeason());
				String concatenatedIds = String.join("|",
						plotIds.stream().map(Object::toString).toArray(String[]::new));
				row.createCell(9).setCellValue(concatenatedIds);
				row.createCell(10).setCellValue(obj.getCreatedBy().getUsername());
				row.createCell(11).setCellValue(obj.getCreatedDt());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void generateSeedTreatment(Workbook workbook, List<ProjectSeedTreatment> objs) {
		try {
			Sheet sheet = workbook.createSheet("Seed Treatment");
			Row headerRow = sheet.createRow(0);
			headerRow.createCell(0).setCellValue("ID");
			headerRow.createCell(1).setCellValue("Project ID");
			headerRow.createCell(2).setCellValue("Farmer Reg No");
			headerRow.createCell(3).setCellValue("Seed Rate/Acre");
			headerRow.createCell(4).setCellValue("Salt Solution Applied?");
			headerRow.createCell(5).setCellValue("Seed Treated?");
			headerRow.createCell(6).setCellValue("Seed Source");
			headerRow.createCell(7).setCellValue("Brand");
			headerRow.createCell(8).setCellValue("Company");
			headerRow.createCell(9).setCellValue("Class");
			headerRow.createCell(10).setCellValue("cost");
			headerRow.createCell(11).setCellValue("Created By");
			headerRow.createCell(12).setCellValue("Created Date");
			int rowNum = 1;
			for (ProjectSeedTreatment obj : objs) {
				Row row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(obj.getPk1());
				row.createCell(1).setCellValue(obj.getProjects().getPk1());
				row.createCell(2).setCellValue(obj.getProjects().getFarm().getRegNumber());
				row.createCell(3).setCellValue(obj.getSeedRate());
				row.createCell(4).setCellValue(obj.getSaltSolution());
				row.createCell(5).setCellValue(obj.getSeedTreatment());
				row.createCell(6).setCellValue(obj.getSeedSource());
				row.createCell(7).setCellValue(obj.getBrand());
				row.createCell(8).setCellValue(obj.getCompany());
				row.createCell(9).setCellValue(obj.getSeedClass());
				row.createCell(10).setCellValue(obj.getCost());
				row.createCell(11).setCellValue(obj.getCreatedBy().getUsername());
				row.createCell(12).setCellValue(obj.getCreatedDt());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void generateSeedTreatmentChemicals(Workbook workbook, List<ProjectSeedTreatmentChemical> objs) {
		try {
			Sheet sheet = workbook.createSheet("Seed Treatment");
			Row headerRow = sheet.createRow(0);
			headerRow.createCell(0).setCellValue("ID");
			headerRow.createCell(1).setCellValue("Project ID");
			headerRow.createCell(2).setCellValue("Farmer Reg No");
			headerRow.createCell(3).setCellValue("Chemical Used");
			headerRow.createCell(4).setCellValue("Chemical Dose");
			headerRow.createCell(5).setCellValue("Bio Agent?");
			headerRow.createCell(6).setCellValue("BioAgent Dose");
			headerRow.createCell(7).setCellValue("Bio Fertilizer");
			headerRow.createCell(8).setCellValue("Biofertilizer Dose");
			headerRow.createCell(9).setCellValue("cost");
			headerRow.createCell(10).setCellValue("Created By");
			headerRow.createCell(11).setCellValue("Created Date");
			int rowNum = 1;
			for (ProjectSeedTreatmentChemical obj : objs) {
				Row row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(obj.getPk1());
				row.createCell(1).setCellValue(obj.getSeedTreatment().getProjects().getPk1());
				row.createCell(2).setCellValue(obj.getSeedTreatment().getProjects().getFarm().getRegNumber());
				row.createCell(3).setCellValue(obj.getChemicalName());
				row.createCell(4).setCellValue(obj.getChemicalDose());
				row.createCell(5).setCellValue(obj.getBioAgent());
				row.createCell(6).setCellValue(obj.getBioAgentDose());
				row.createCell(7).setCellValue(obj.getBioFertilizer());
				row.createCell(8).setCellValue(obj.getBioFertilizerDose());
				row.createCell(9).setCellValue(obj.getCost());
				row.createCell(10).setCellValue(obj.getCreatedBy().getUsername());
				row.createCell(11).setCellValue(obj.getCreatedDt());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void generateDsrMethod(Workbook workbook, List<ProjectDSRMethod> objs) {
		try {
			Sheet sheet = workbook.createSheet("DSR Method");
			Row headerRow = sheet.createRow(0);
			headerRow.createCell(0).setCellValue("ID");
			headerRow.createCell(1).setCellValue("Project ID");
			headerRow.createCell(2).setCellValue("Farmer Reg No");
			headerRow.createCell(3).setCellValue("Seed Rate/Acre");
			headerRow.createCell(4).setCellValue("Acres under DSR?");
			headerRow.createCell(5).setCellValue("Soaking Time");
			headerRow.createCell(6).setCellValue("Nursery Area");
			headerRow.createCell(7).setCellValue("cost");
			headerRow.createCell(8).setCellValue("Created By");
			headerRow.createCell(9).setCellValue("Created Date");
			int rowNum = 1;
			for (ProjectDSRMethod obj : objs) {
				Row row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(obj.getPk1());
				row.createCell(1).setCellValue(obj.getProjects().getPk1());
				row.createCell(2).setCellValue(obj.getProjects().getFarm().getRegNumber());
				row.createCell(3).setCellValue(obj.getSeedRate());
				row.createCell(4).setCellValue(obj.getAcres());
				row.createCell(5).setCellValue(obj.getSoakingTime());
				row.createCell(6).setCellValue(obj.getNurseryArea());
				row.createCell(7).setCellValue(obj.getCost());
				row.createCell(8).setCellValue(obj.getCreatedBy().getUsername());
				row.createCell(9).setCellValue(obj.getCreatedDt());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void generateDsrSowingMethod(Workbook workbook, List<ProjectSowingData> objs) {
		try {
			Sheet sheet = workbook.createSheet("Sowing Data");
			Row headerRow = sheet.createRow(0);
			headerRow.createCell(0).setCellValue("ID");
			headerRow.createCell(1).setCellValue("Project ID");
			headerRow.createCell(2).setCellValue("Farmer Reg No");
			headerRow.createCell(3).setCellValue("Variety");
			headerRow.createCell(4).setCellValue("Plots");
			headerRow.createCell(5).setCellValue("Sowing Date");
			headerRow.createCell(6).setCellValue("Remarks");
			headerRow.createCell(7).setCellValue("cost");
			headerRow.createCell(8).setCellValue("Created By");
			headerRow.createCell(9).setCellValue("Created Date");
			int rowNum = 1;
			for (ProjectSowingData obj : objs) {
				Row row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(obj.getPk1());
				row.createCell(1).setCellValue(obj.getProjects().getPk1());
				row.createCell(2).setCellValue(obj.getProjects().getFarm().getRegNumber());
				row.createCell(3).setCellValue(obj.getVariety().getVariety());
				row.createCell(4).setCellValue(obj.getPlots().getPlotNumber());
				row.createCell(5).setCellValue(obj.getSowingDate());
				row.createCell(6).setCellValue(obj.getRemarks());
				row.createCell(7).setCellValue(obj.getCost());
				row.createCell(8).setCellValue(obj.getCreatedBy().getUsername());
				row.createCell(9).setCellValue(obj.getCreatedDt());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void generateNurseryWaterMethod(Workbook workbook, List<ProjectNurseryWater> objs) {
		try {
			Sheet sheet = workbook.createSheet("Nursery Water Management");
			Row headerRow = sheet.createRow(0);
			headerRow.createCell(0).setCellValue("ID");
			headerRow.createCell(1).setCellValue("Project ID");
			headerRow.createCell(2).setCellValue("Farmer Reg No");
			headerRow.createCell(3).setCellValue("Variety");
			headerRow.createCell(4).setCellValue("Plots");
			headerRow.createCell(5).setCellValue("Age of Seedling");
			headerRow.createCell(6).setCellValue("Irrigation Date");
			headerRow.createCell(7).setCellValue("cost");
			headerRow.createCell(8).setCellValue("Created By");
			headerRow.createCell(9).setCellValue("Created Date");
			int rowNum = 1;
			for (ProjectNurseryWater obj : objs) {
				Row row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(obj.getPk1());
				row.createCell(1).setCellValue(obj.getProjects().getPk1());
				row.createCell(2).setCellValue(obj.getProjects().getFarm().getRegNumber());
				row.createCell(3).setCellValue(obj.getVariety().getVariety());
				row.createCell(4).setCellValue(obj.getPlot().getPlotNumber());
				row.createCell(5).setCellValue(obj.getSeedlingAge());
				row.createCell(6).setCellValue(obj.getIrrigationDate());
				row.createCell(7).setCellValue(obj.getCost());
				row.createCell(8).setCellValue(obj.getCreatedBy().getUsername());
				row.createCell(9).setCellValue(obj.getCreatedDt());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void generateNurseryWeed(Workbook workbook, List<ProjectNurseryWeed> objs) {
		try {
			Sheet sheet = workbook.createSheet("Nursery Weed Management");
			Row headerRow = sheet.createRow(0);
			headerRow.createCell(0).setCellValue("ID");
			headerRow.createCell(1).setCellValue("Project ID");
			headerRow.createCell(2).setCellValue("Farmer Reg No");
			headerRow.createCell(3).setCellValue("Plots");
			headerRow.createCell(4).setCellValue("Weedicide");
			headerRow.createCell(5).setCellValue("Type");
			headerRow.createCell(6).setCellValue("method");
			headerRow.createCell(7).setCellValue("Dose");
			headerRow.createCell(8).setCellValue("Applied Date");
			headerRow.createCell(9).setCellValue("cost");
			headerRow.createCell(10).setCellValue("Created By");
			headerRow.createCell(11).setCellValue("Created Date");
			int rowNum = 1;
			for (ProjectNurseryWeed obj : objs) {
				Row row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(obj.getPk1());
				row.createCell(1).setCellValue(obj.getProjects().getPk1());
				row.createCell(2).setCellValue(obj.getProjects().getFarm().getRegNumber());
				row.createCell(3).setCellValue(obj.getPlot().getPlotNumber());
				row.createCell(4).setCellValue(obj.getWeedicide().getName());
				row.createCell(5).setCellValue(obj.getManagement());
				row.createCell(6).setCellValue(obj.getMethod());
				row.createCell(7).setCellValue(obj.getDose());
				row.createCell(8).setCellValue(obj.getWeedingDate());
				row.createCell(9).setCellValue(obj.getCost());
				row.createCell(10).setCellValue(obj.getCreatedBy().getUsername());
				row.createCell(11).setCellValue(obj.getCreatedDt());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void generateNurseryNutrient(Workbook workbook, List<ProjectNurseryNutrient> objs) {
		try {
			Sheet sheet = workbook.createSheet("Sowing Data");
			Row headerRow = sheet.createRow(0);
			headerRow.createCell(0).setCellValue("ID");
			headerRow.createCell(1).setCellValue("Project ID");
			headerRow.createCell(2).setCellValue("Farmer Reg No");
			headerRow.createCell(3).setCellValue("Plots");
			headerRow.createCell(4).setCellValue("Fertilizer Name");
			headerRow.createCell(5).setCellValue("Applied Date");
			headerRow.createCell(6).setCellValue("Dose");
			headerRow.createCell(7).setCellValue("cost");
			headerRow.createCell(8).setCellValue("Created By");
			headerRow.createCell(9).setCellValue("Created Date");
			int rowNum = 1;
			for (ProjectNurseryNutrient obj : objs) {
				Row row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(obj.getPk1());
				row.createCell(1).setCellValue(obj.getProjects().getPk1());
				row.createCell(2).setCellValue(obj.getProjects().getFarm().getRegNumber());
				row.createCell(3).setCellValue(obj.getPlot().getPlotNumber());
				row.createCell(4).setCellValue(obj.getFertilizer().getName());
				row.createCell(5).setCellValue(obj.getAppliedDate());
				row.createCell(6).setCellValue(obj.getDose());
				row.createCell(7).setCellValue(obj.getCost());
				row.createCell(8).setCellValue(obj.getCreatedBy().getUsername());
				row.createCell(9).setCellValue(obj.getCreatedDt());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void generateNurseryPest(Workbook workbook, List<ProjectNurseryPests> objs) {
		try {
			Sheet sheet = workbook.createSheet("Nursery Pest Management");
			Row headerRow = sheet.createRow(0);
			headerRow.createCell(0).setCellValue("ID");
			headerRow.createCell(1).setCellValue("Project ID");
			headerRow.createCell(2).setCellValue("Farmer Reg No");
			headerRow.createCell(3).setCellValue("Plots");
			headerRow.createCell(4).setCellValue("Pest Type");
			headerRow.createCell(5).setCellValue("Chemical Used");
			headerRow.createCell(6).setCellValue("Brand Name");
			headerRow.createCell(7).setCellValue("PHI Recommended");
			headerRow.createCell(8).setCellValue("MRL Recommended");
			headerRow.createCell(9).setCellValue("Dose Recommended");
			headerRow.createCell(10).setCellValue("Dose Applied");
			headerRow.createCell(11).setCellValue("Age of Seedling");
			headerRow.createCell(12).setCellValue("Method Applied");
			headerRow.createCell(13).setCellValue("cost");
			headerRow.createCell(14).setCellValue("Created By");
			headerRow.createCell(15).setCellValue("Created Date");
			int rowNum = 1;
			for (ProjectNurseryPests obj : objs) {
				Row row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(obj.getPk1());
				row.createCell(1).setCellValue(obj.getProjects().getPk1());
				row.createCell(2).setCellValue(obj.getProjects().getFarm().getRegNumber());
				row.createCell(3).setCellValue(obj.getPlots().getPlotNumber());
				row.createCell(4).setCellValue(obj.getPests().getName());
				row.createCell(5).setCellValue(obj.getChemicals().getChemicalName());
				row.createCell(6).setCellValue(obj.getBrands().getTradeName() + " - " + obj.getBrands().getTradeName());
				row.createCell(7).setCellValue(obj.getChemicals().getPhi());
				row.createCell(8).setCellValue(obj.getChemicals().getMrl());
				row.createCell(9).setCellValue(obj.getChemicals().getRecommendedDosage());
				row.createCell(10).setCellValue(obj.getAppliedDose());
				row.createCell(11).setCellValue(obj.getAgeOfSeedling());
				row.createCell(12).setCellValue(obj.getAppliedMethod());
				row.createCell(13).setCellValue(obj.getCost());
				row.createCell(14).setCellValue(obj.getCreatedBy().getUsername());
				row.createCell(15).setCellValue(obj.getCreatedDt());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void sendEmail(String to, String subject, String body, byte[] attachmentData, String attachmentFile) {

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message, true);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body);

			// Attach the file
			helper.addAttachment(attachmentFile, new ByteArrayResource(attachmentData));

			mailSender.send(message);
		} catch (MessagingException e) {
			// Handle exception
		}
	}
}
