package com.svastha.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Sort;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.svastha.entity.FarmPlots;
import com.svastha.entity.FarmProjects;
import com.svastha.entity.OrganicAnnualProgram;
import com.svastha.entity.OrganicBioFertilizer;
import com.svastha.entity.OrganicCropVariety;
import com.svastha.entity.OrganicGrowthPromoter;
import com.svastha.entity.OrganicHarvest;
import com.svastha.entity.OrganicOrganicManure;
import com.svastha.entity.OrganicPlotBoundary;
import com.svastha.entity.OrganicProtection;
import com.svastha.entity.OrganicSeedData;
import com.svastha.entity.OrganicSoilAnalysis;
import com.svastha.entity.OrganicSowingData;
import com.svastha.entity.OrganicTransplantData;
import com.svastha.entity.OrganicWaterAnalysis;
import com.svastha.entity.ProjectBioFertilizers;
import com.svastha.entity.ProjectDSRMethod;
import com.svastha.entity.ProjectDispatch;
import com.svastha.entity.ProjectHarvest;
import com.svastha.entity.ProjectIrrigation;
import com.svastha.entity.ProjectLandPreparation;
import com.svastha.entity.ProjectMicroNutrient;
import com.svastha.entity.ProjectNurseryNutrient;
import com.svastha.entity.ProjectNurseryPests;
import com.svastha.entity.ProjectNurseryWater;
import com.svastha.entity.ProjectNurseryWeed;
import com.svastha.entity.ProjectOrganicManure;
import com.svastha.entity.ProjectPacking;
import com.svastha.entity.ProjectPlots;
import com.svastha.entity.ProjectPostPurchase;
import com.svastha.entity.ProjectPrePurchase;
import com.svastha.entity.ProjectProcurement;
import com.svastha.entity.ProjectProtection;
import com.svastha.entity.ProjectSeedTreatment;
import com.svastha.entity.ProjectSowingData;
import com.svastha.entity.ProjectStorage;
import com.svastha.entity.ProjectSyntheticFertilizers;
import com.svastha.entity.ProjectTransplantManagement;
import com.svastha.entity.ProjectWeedManagement;
import com.svastha.repository.FarmProjectRepository;
import com.svastha.repository.OrganicAnnualProgramRepository;
import com.svastha.repository.OrganicBioFertilizerRepository;
import com.svastha.repository.OrganicCropVarietyRepository;
import com.svastha.repository.OrganicGrowthPromoterRepository;
import com.svastha.repository.OrganicHarvestRepository;
import com.svastha.repository.OrganicOrganicManureRepository;
import com.svastha.repository.OrganicPlotBoundaryRepository;
import com.svastha.repository.OrganicProtectionRepository;
import com.svastha.repository.OrganicSeedDataRepository;
import com.svastha.repository.OrganicSoilAnalysisRepository;
import com.svastha.repository.OrganicSowingDataRepository;
import com.svastha.repository.OrganicTransplantDataRepository;
import com.svastha.repository.OrganicWaterAnalysisRepository;
import com.svastha.repository.ProjectsBioFertilizerRepository;
import com.svastha.repository.ProjectsDispatchRepository;
import com.svastha.repository.ProjectsDsrRepository;
import com.svastha.repository.ProjectsHarvestRepository;
import com.svastha.repository.ProjectsIrrigationRepository;
import com.svastha.repository.ProjectsLandPreparationRepository;
import com.svastha.repository.ProjectsManureRepository;
import com.svastha.repository.ProjectsMicroNutrientRepository;
import com.svastha.repository.ProjectsNutrientRepository;
import com.svastha.repository.ProjectsPackingRepository;
import com.svastha.repository.ProjectsPestsRepository;
import com.svastha.repository.ProjectsPlotsRepository;
import com.svastha.repository.ProjectsPostPurchaseRepository;
import com.svastha.repository.ProjectsPrePurchaseRepository;
import com.svastha.repository.ProjectsProcurementRepository;
import com.svastha.repository.ProjectsProtectionRepository;
import com.svastha.repository.ProjectsSeedTreatmentChemicalRepository;
import com.svastha.repository.ProjectsSeedTreatmentRepository;
import com.svastha.repository.ProjectsSowingDataRepository;
import com.svastha.repository.ProjectsStorageRepository;
import com.svastha.repository.ProjectsSyntheticFertilizerRepository;
import com.svastha.repository.ProjectsTransplantManagementRepository;
import com.svastha.repository.ProjectsWaterRepository;
import com.svastha.repository.ProjectsWeedManagementRepository;
import com.svastha.repository.ProjectsWeedRepository;

@Component
public class OrganicExcelWriter {

	@Autowired
	private FarmProjectRepository projectDao;

	@Autowired
	private ProjectsPlotsRepository projectPlotsDao;

	@Autowired
	private OrganicAnnualProgramRepository annualDao;

	@Autowired
	private OrganicCropVarietyRepository varietyDao;

	@Autowired
	private OrganicPlotBoundaryRepository boundaryDao;

	@Autowired
	private OrganicSoilAnalysisRepository soilDao;

	@Autowired
	private OrganicWaterAnalysisRepository waterDao;

	@Autowired
	private OrganicSeedDataRepository seedDao;

	@Autowired
	private OrganicSowingDataRepository sowingDao;

	@Autowired
	private OrganicTransplantDataRepository transDao;

	@Autowired
	private OrganicBioFertilizerRepository bioDao;

	@Autowired
	private OrganicOrganicManureRepository manureDao;

	@Autowired
	private OrganicGrowthPromoterRepository growthDao;

	@Autowired
	private OrganicProtectionRepository protDao;
	
	@Autowired
	private OrganicHarvestRepository harvestDao;

	@Autowired
	private JavaMailSender mailSender;

	// For windows
	// static String excelFilePath = "C:\\Users\\smsan\\work\\svastha
	// project\\Svastha\\svastha\\";

	@Value("${upload.directory}")
	private String uploadDirectory;

	private String excelFilePath;

	@PostConstruct
	public void init() {
		System.out.println("Upload Directory: " + uploadDirectory);
		excelFilePath = uploadDirectory + "Excel/";
		System.out.println("EXCEL Directory: " + excelFilePath);
	}

	@Async
	public void startProjectExportV2(Long yearId, Long seasonId, Long cropId, String key, Long userId, String email,
			Long projectTypePk1, Long varietyId, Long ics, Long districtId, Long thalukId, Long villageId) {
		List<FarmProjects> projects = projectDao.findWithFilters(yearId, seasonId, cropId, key, userId, projectTypePk1,
				varietyId, ics, districtId, thalukId, villageId);

		String excelName = "projects_" + System.currentTimeMillis() + ".xlsx";
		String excelPath = excelFilePath + excelName;
		File f = new File(excelFilePath);
		if (!f.exists()) {
			f.mkdirs();
		}
		System.out.println("excel writer called  " + excelPath);
		File template = new File(excelFilePath + "OrganicTemplate.xlsx");
		File file = new File(excelPath);
		System.out.println("excel writer called 1 ");
		try (InputStream inputStream = new FileInputStream(template);
				Workbook workbook = new XSSFWorkbook(inputStream);
				FileOutputStream outputStream = new FileOutputStream(file);) {
			Thread.sleep(5000); // Sleep for 5 seconds
			Sheet sheet = workbook.getSheetAt(0);

			int rowNum = 3;
			for (FarmProjects project : projects) {

				int tempRowNum = rowNum;
				List<ProjectPlots> plots = projectPlotsDao.findAllPlotsByProject(project);
				for (ProjectPlots plot : plots) {
					System.out.println("Into plot - " + plot.getPlots().getPk1() + "  " + rowNum);
					FarmPlots farmPlot = plot.getPlots();

					Row plotRow = sheet.getRow(rowNum);
					if (plotRow == null) {
						plotRow = sheet.createRow(rowNum);
						createSingleRows(project, plotRow, farmPlot, rowNum);
					}
					if (plotRow.getCell(19) == null) {
						plotRow.createCell(19).setCellValue(farmPlot.getPk1());
						plotRow.createCell(20).setCellValue(farmPlot.getPlotNumber());
					}
					int start = rowNum;
					int end = rowNum;
					end = Math.max(end, generateFertilizer(project, farmPlot, start, end, rowNum, sheet));
					end = Math.max(end, generateOrganicManure(project, farmPlot, start, end, rowNum, sheet));
					end = Math.max(end, generateGrowth(project, farmPlot, start, end, rowNum, sheet));
					end = Math.max(end, generatPests(project, farmPlot, start, end, rowNum, sheet));
					rowNum = end + 1;
				}
				rowNum = Math.max(tempRowNum, rowNum);
			}

			workbook.write(outputStream);
			sendEmail(email, "Exported Farmer Data", "Please find the attched farmer data.",
					FileUtils.readFileToByteArray(file), excelName);
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void createSingleRows(FarmProjects project, Row row, FarmPlots plot, int rowNum) {
		System.out.println("Create SIngle Row called: " + project.getPk1() + "  " + rowNum);
		if (row.getCell(0) == null) {
			System.out.println("Creating Single Row");
			row.createCell(0).setCellValue(rowNum - 2);
			row.createCell(1).setCellValue(project.getFarm().getFarmerName());
			row.createCell(2).setCellValue(project.getFarm().getFatherName());
			row.createCell(3).setCellValue(project.getFarm().getRegNumber());
			row.createCell(4).setCellValue(
					project.getFarm().getVillageId() == null ? "" : project.getFarm().getVillageId().getName());
			row.createCell(5).setCellValue(
					project.getFarm().getThalukId() == null ? "" : project.getFarm().getThalukId().getName());
			row.createCell(6).setCellValue(
					project.getFarm().getDistrictId() == null ? "" : project.getFarm().getDistrictId().getName());
			row.createCell(7).setCellValue(project.getFarm().getCreatedBy().getUsername());
			row.createCell(8).setCellValue(project.getFarm().getCreatedDt());
			row.createCell(9).setCellValue(project.getPk1());
			row.createCell(10).setCellValue(project.getYear().getYear());
			row.createCell(11).setCellValue(project.getSeason().getSeason());
			row.createCell(12).setCellValue(project.getCrop().getCrop());
			row.createCell(13).setCellValue(project.getVariety() == null ? "" : project.getVariety().getVariety());
			row.createCell(14).setCellValue(project.getSowingDate());
			row.createCell(15).setCellValue(project.getIcs() == null ? "" : project.getIcs().getIcs());
			row.createCell(16).setCellValue(project.getCreatedBy().getUsername());
			row.createCell(17).setCellValue(project.getCreatedDt());
			row.createCell(18).setCellValue(project.getAssignedTo().getUsername());

			generateAnnualCrop(project, plot, row);
			generateVariety(project, plot, row);
			generateBoundary(project, plot, row);
			generateWater(project, plot, row);
			generateSoil(project, plot, row);
			generateSeedData(project, plot, row);
			generateSowing(project, plot, row);
			generateTransplantData(project, plot, row);
			generateHarvestData(project, plot, row);
		}
	}

	private void generateAnnualCrop(FarmProjects project, FarmPlots farmPlot, Row row) {
		OrganicAnnualProgram obj = annualDao.findByProjectsAndPlots(project, farmPlot);
		if (obj != null) {
			row.createCell(21).setCellValue(obj.getAnnualProgram().getName());
			row.createCell(22).setCellValue(obj.getArea());
			row.createCell(23).setCellValue(obj.getCrop().getCrop());
		}
	}

	private void generateVariety(FarmProjects project, FarmPlots farmPlot, Row row) {
		OrganicCropVariety obj = varietyDao.findByProjectsAndPlots(project, farmPlot);
		if (obj != null) {
			row.createCell(24).setCellValue(obj.getCropVariety().getVariety());
		}
	}

	private void generateBoundary(FarmProjects project, FarmPlots farmPlot, Row row) {
		List<OrganicPlotBoundary> objs = boundaryDao.findByProjectsAndPlots(project, farmPlot);
		if (objs != null) {
			for (OrganicPlotBoundary obj : objs) {
				if (obj.getDirections().equals("North")) {
					row.createCell(25).setCellValue(obj.getDirections());
					row.createCell(26).setCellValue(obj.getFarmerName());
					row.createCell(27).setCellValue(obj.getPractice());
					row.createCell(28).setCellValue(obj.getArea());
					row.createCell(29).setCellValue(obj.getRisk().getName());
				} else if (obj.getDirections().equals("East")) {
					row.createCell(30).setCellValue(obj.getDirections());
					row.createCell(31).setCellValue(obj.getFarmerName());
					row.createCell(32).setCellValue(obj.getPractice());
					row.createCell(33).setCellValue(obj.getArea());
					row.createCell(34).setCellValue(obj.getRisk().getName());
				} else if (obj.getDirections().equals("South")) {
					row.createCell(35).setCellValue(obj.getDirections());
					row.createCell(36).setCellValue(obj.getFarmerName());
					row.createCell(37).setCellValue(obj.getPractice());
					row.createCell(38).setCellValue(obj.getArea());
					row.createCell(39).setCellValue(obj.getRisk().getName());
				} else if (obj.getDirections().equals("West")) {
					row.createCell(40).setCellValue(obj.getDirections());
					row.createCell(41).setCellValue(obj.getFarmerName());
					row.createCell(42).setCellValue(obj.getPractice());
					row.createCell(43).setCellValue(obj.getArea());
					row.createCell(44).setCellValue(obj.getRisk().getName());
				}
			}
		}
	}

	private void generateWater(FarmProjects project, FarmPlots farmPlot, Row row) {
		OrganicWaterAnalysis obj = waterDao.findByProjectsAndPlots(project, farmPlot);
		if (obj != null) {
			row.createCell(45).setCellValue(obj.getAlkalinity());
			row.createCell(46).setCellValue(obj.getPh());
			row.createCell(47).setCellValue(obj.getBicarbonates());
			row.createCell(48).setCellValue(obj.getDissolvedCalcium());
			row.createCell(49).setCellValue(obj.getCarbonates());
			row.createCell(50).setCellValue(obj.getRiversideClassification());
			row.createCell(51).setCellValue(obj.getChloride());
			row.createCell(52).setCellValue(obj.getConductivity());
			row.createCell(53).setCellValue(obj.getHardness());
			row.createCell(54).setCellValue(obj.getPhosphate());
		}
	}

	private void generateSoil(FarmProjects project, FarmPlots farmPlot, Row row) {
		OrganicSoilAnalysis obj = soilDao.findByProjectsAndPlots(project, farmPlot);
		if (obj != null) {
			row.createCell(55).setCellValue(obj.getSoiltype().getName());
			row.createCell(56).setCellValue(obj.getSoilEc());
			row.createCell(57).setCellValue(obj.getSoilPh());
			row.createCell(58).setCellValue(obj.getNitrogen());
			row.createCell(59).setCellValue(obj.getPhosporus());
			row.createCell(60).setCellValue(obj.getPotassium());
			row.createCell(61).setCellValue(obj.getSulphur());
			row.createCell(62).setCellValue(obj.getZinc());
			row.createCell(63).setCellValue(obj.getBoron());
			row.createCell(64).setCellValue(obj.getIron());
			row.createCell(65).setCellValue(obj.getManganese());
			row.createCell(66).setCellValue(obj.getCopper());
			row.createCell(67).setCellValue(obj.getOrganic_copper());
		}
	}

	private void generateSeedData(FarmProjects project, FarmPlots farmPlot, Row row) {
		OrganicSeedData obj = seedDao.findByProjectsAndPlots(project, farmPlot);
		if (obj != null) {
			row.createCell(68).setCellValue(obj.getSeedRate());
			row.createCell(69).setCellValue(obj.getSource());
		}
	}

	private void generateSowing(FarmProjects project, FarmPlots farmPlot, Row row) {
		OrganicSowingData obj = sowingDao.findByProjectsAndPlots(project, farmPlot);
		if (obj != null) {
			row.createCell(70).setCellValue(obj.getSowingDate());
		}
	}

	private void generateTransplantData(FarmProjects project, FarmPlots farmPlot, Row row) {
		OrganicTransplantData obj = transDao.findByProjectsAndPlots(project, farmPlot);
		if (obj != null) {
			row.createCell(71).setCellValue(obj.getTransplantDate());
		}
	}

	private int generateFertilizer(FarmProjects project, FarmPlots farmPlot, int start, int end, int rowNum,
			Sheet sheet) {
		List<OrganicBioFertilizer> objs = bioDao.findByProjectsAndPlots(project, farmPlot);

		for (OrganicBioFertilizer obj : objs) {
			if (start > end) {
				end = start;
			}
			Row row = sheet.getRow(start);
			if (row == null) {
				row = sheet.createRow(start);
				createSingleRows(project, row, farmPlot, rowNum);
			}
			if (row.getCell(19) == null) {
				row.createCell(19).setCellValue(obj.getPlots().getPk1());
				row.createCell(20).setCellValue(obj.getPlots().getPlotNumber());
			}
			row.createCell(72).setCellValue(obj.getBioFertilizer() == null ? "" : obj.getBioFertilizer().getName());
			row.createCell(73).setCellValue(obj.getDose());
			row.createCell(74).setCellValue(obj.getUsedDate());
			row.createCell(75).setCellValue(obj.getSource());

			start = start + 1;
		}
		return end;
	}

	private int generateOrganicManure(FarmProjects project, FarmPlots farmPlot, int start, int end, int rowNum,
			Sheet sheet) {
		List<OrganicOrganicManure> objs = manureDao.findByProjectsAndPlots(project, farmPlot);

		for (OrganicOrganicManure obj : objs) {
			if (start > end) {
				end = start;
			}
			Row row = sheet.getRow(start);
			if (row == null) {
				row = sheet.createRow(start);
				createSingleRows(project, row, farmPlot, rowNum);
			}
			if (row.getCell(19) == null) {
				row.createCell(19).setCellValue(obj.getPlots().getPk1());
				row.createCell(20).setCellValue(obj.getPlots().getPlotNumber());
			}
			row.createCell(76).setCellValue(obj.getManure() == null ? "" : obj.getManure().getName());
			row.createCell(77).setCellValue(obj.getDose());
			row.createCell(78).setCellValue(obj.getUsedDate());
			row.createCell(79).setCellValue(obj.getSource());

			start = start + 1;
		}
		return end;
	}

	private int generateGrowth(FarmProjects project, FarmPlots farmPlot, int start, int end, int rowNum, Sheet sheet) {
		List<OrganicGrowthPromoter> objs = growthDao.findByProjectsAndPlots(project, farmPlot);

		for (OrganicGrowthPromoter obj : objs) {
			if (start > end) {
				end = start;
			}
			Row row = sheet.getRow(start);
			if (row == null) {
				row = sheet.createRow(start);
				createSingleRows(project, row, farmPlot, rowNum);
			}
			if (row.getCell(19) == null) {
				row.createCell(19).setCellValue(obj.getPlots().getPk1());
				row.createCell(20).setCellValue(obj.getPlots().getPlotNumber());
			}
			row.createCell(80).setCellValue(obj.getGrowthPromoter() == null ? "" : obj.getGrowthPromoter().getName());
			row.createCell(81).setCellValue(obj.getDose());
			row.createCell(82).setCellValue(obj.getUsedDate());
			row.createCell(83).setCellValue(obj.getSource());

			start = start + 1;
		}
		return end;
	}

	private int generatPests(FarmProjects project, FarmPlots farmPlot, int start, int end, int rowNum, Sheet sheet) {
		List<OrganicProtection> objs = protDao.findByProjectsAndPlots(project, farmPlot);

		for (OrganicProtection obj : objs) {
			if (start > end) {
				end = start;
			}
			Row row = sheet.getRow(start);
			if (row == null) {
				row = sheet.createRow(start);
				createSingleRows(project, row, farmPlot, rowNum);
			}
			if (row.getCell(19) == null) {
				row.createCell(19).setCellValue(obj.getPlots().getPk1());
				row.createCell(20).setCellValue(obj.getPlots().getPlotNumber());
			}
			row.createCell(84).setCellValue(obj.getPests() == null ? "" : obj.getPests().getName());
			row.createCell(85).setCellValue(obj.getGrowth() == null ? "" : obj.getGrowth().getName());
			row.createCell(86).setCellValue(obj.getAppliedDose());
			row.createCell(87).setCellValue(obj.getAppliedDate());
			row.createCell(88).setCellValue(obj.getAppliedMethod());
			row.createCell(89).setCellValue(obj.getAppliedMode());
			row.createCell(90).setCellValue(obj.getStage().getCropStage());
			row.createCell(91).setCellValue(obj.getCompliant());
			row.createCell(92).setCellValue(obj.getCost());
			start = start + 1;
		}
		return end;
	}

	private void generateHarvestData(FarmProjects project, FarmPlots farmPlot, Row row) {
		OrganicHarvest obj = harvestDao.findByProjectsAndPlots(project, farmPlot);
		if (obj != null) {
			row.createCell(93).setCellValue(obj.getHarvestDate());
			row.createCell(94).setCellValue(obj.getHarvestYield());
		}
	}
	
	public void sendEmail(String to, String subject, String body, byte[] attachmentData, String attachmentFile) {

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message, true);
			helper.setFrom("ecoharvestsvastha@gmail.com");
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
