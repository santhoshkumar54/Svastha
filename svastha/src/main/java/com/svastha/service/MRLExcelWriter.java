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
import com.svastha.entity.HarvestDryingProcess;
import com.svastha.entity.HarvestEstimates;
import com.svastha.entity.HarvestEwayBill;
import com.svastha.entity.HarvestGstInvoice;
import com.svastha.entity.HarvestInvoice;
import com.svastha.entity.HarvestLabelling;
import com.svastha.entity.HarvestLoadingSlip;
import com.svastha.entity.HarvestMillEquipmentUsage;
import com.svastha.entity.HarvestMillingProcess;
import com.svastha.entity.HarvestPackagingDetails;
import com.svastha.entity.HarvestPaymentDetails;
import com.svastha.entity.HarvestPriceConfirmation;
import com.svastha.entity.HarvestPurchasePointEntry;
import com.svastha.entity.HarvestQualityAssessment;
import com.svastha.entity.HarvestQualityStandards;
import com.svastha.entity.HarvestSampleTest;
import com.svastha.entity.HarvestStocking;
import com.svastha.entity.HarvestUnloadingSlip;
import com.svastha.entity.HarvestWeighmentDetails;
import com.svastha.entity.ProjectBioFertilizers;
import com.svastha.entity.ProjectDSRMethod;
import com.svastha.entity.ProjectHarvest;
import com.svastha.entity.ProjectIrrigation;
import com.svastha.entity.ProjectLandPreparation;
import com.svastha.entity.ProjectMicroNutrient;
import com.svastha.entity.ProjectNurseryNutrient;
import com.svastha.entity.ProjectNurseryPests;
import com.svastha.entity.ProjectNurseryWater;
import com.svastha.entity.ProjectNurseryWeed;
import com.svastha.entity.ProjectOrganicManure;
import com.svastha.entity.ProjectPlots;
import com.svastha.entity.ProjectProtection;
import com.svastha.entity.ProjectSeedTreatment;
import com.svastha.entity.ProjectSowingData;
import com.svastha.entity.ProjectSyntheticFertilizers;
import com.svastha.entity.ProjectTransplantManagement;
import com.svastha.entity.ProjectWeedManagement;
import com.svastha.repository.FarmProjectRepository;
import com.svastha.repository.HarvestDryingProcessRepository;
import com.svastha.repository.HarvestEstimatesRepository;
import com.svastha.repository.HarvestEwayBillRepository;
import com.svastha.repository.HarvestGstInvoiceRepository;
import com.svastha.repository.HarvestInvoiceRepository;
import com.svastha.repository.HarvestLabellingRepository;
import com.svastha.repository.HarvestLoadingSlipRepository;
import com.svastha.repository.HarvestMillEquipmentUsageRepository;
import com.svastha.repository.HarvestMillingProcessRepository;
import com.svastha.repository.HarvestPackagingDetailsRepository;
import com.svastha.repository.HarvestPaymentDetailsRepository;
import com.svastha.repository.HarvestPriceConfirmationRepository;
import com.svastha.repository.HarvestPurchasePointEntryRepository;
import com.svastha.repository.HarvestQualityAssessmentRepository;
import com.svastha.repository.HarvestQualityStandardRepository;
import com.svastha.repository.HarvestSampleTestRepository;
import com.svastha.repository.HarvestStockingRepository;
import com.svastha.repository.HarvestUnloadingSlipRepository;
import com.svastha.repository.HarvestWeighmentDetailsRepository;
import com.svastha.repository.ProjectsBioFertilizerRepository;
import com.svastha.repository.ProjectsDsrRepository;
import com.svastha.repository.ProjectsHarvestRepository;
import com.svastha.repository.ProjectsIrrigationRepository;
import com.svastha.repository.ProjectsLandPreparationRepository;
import com.svastha.repository.ProjectsManureRepository;
import com.svastha.repository.ProjectsMicroNutrientRepository;
import com.svastha.repository.ProjectsNutrientRepository;
import com.svastha.repository.ProjectsPestsRepository;
import com.svastha.repository.ProjectsPlotsRepository;
import com.svastha.repository.ProjectsProtectionRepository;
import com.svastha.repository.ProjectsSeedTreatmentChemicalRepository;
import com.svastha.repository.ProjectsSeedTreatmentRepository;
import com.svastha.repository.ProjectsSowingDataRepository;
import com.svastha.repository.ProjectsSyntheticFertilizerRepository;
import com.svastha.repository.ProjectsTransplantManagementRepository;
import com.svastha.repository.ProjectsWaterRepository;
import com.svastha.repository.ProjectsWeedManagementRepository;
import com.svastha.repository.ProjectsWeedRepository;

@Component
public class MRLExcelWriter {

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
	private ProjectsMicroNutrientRepository microDao;

	@Autowired
	private ProjectsProtectionRepository protectionDao;

	@Autowired
	private ProjectsHarvestRepository harvestDao;
	
	@Autowired
	private HarvestQualityAssessmentRepository harvestQADao;
	
	@Autowired
	private HarvestPurchasePointEntryRepository harvestPurchaseEntryDao;
	
	@Autowired
	private HarvestPackagingDetailsRepository harvestPackingDao;
	
	@Autowired
	private HarvestLabellingRepository harvestLabelDao;
	
	@Autowired
	private HarvestWeighmentDetailsRepository harvestWeighmentDao;
	
	@Autowired
	private HarvestQualityStandardRepository harvestQualityStdDao;
	
	@Autowired
	private HarvestInvoiceRepository harvestInvoiceDao;
	
	@Autowired
	private HarvestPriceConfirmationRepository harvestPriceConfirmDao;
	
	@Autowired
	private HarvestGstInvoiceRepository harvestGstInvoiceDao;
	
	@Autowired
	private HarvestLoadingSlipRepository harvestLoadingSlipDao;
	
	@Autowired
	private HarvestUnloadingSlipRepository harvestUnLoadingSlipDao;
	
	@Autowired
	private HarvestPaymentDetailsRepository harvestPaymentDetailsDao;
	
	@Autowired
	private HarvestEwayBillRepository harvestEwayBillDao;
	
	@Autowired
	private HarvestStockingRepository harvestStockingDao;
	
	
	@Autowired
	private HarvestDryingProcessRepository harvestDryingProcessDao;
	
	@Autowired
	private HarvestMillingProcessRepository harvestMillingProcessDao;
	
	@Autowired
	private HarvestMillEquipmentUsageRepository harvestMillEquipmentUsageDao;
	
	@Autowired
	private HarvestSampleTestRepository harvestSampleTestDao;
	
	@Autowired
	private HarvestEstimatesRepository harvestEstimatesDao;


	@Autowired
	private JavaMailSender mailSender;

	// For windows
	// static String excelFilePath = "C:\\Users\\smsan\\work\\svastha
	// project\\Svastha\\svastha\\";

	@Value("${upload.directory}")
	private String uploadDirectory;

	private String excelFilePath;

	private Row sourceRow = null;

	@PostConstruct
	public void init() {
		System.out.println("Upload Directory: " + uploadDirectory);
		excelFilePath = uploadDirectory + "Excel/";
		System.out.println("EXCEL Directory: " + excelFilePath);
	}

	public void createSingleRows(FarmProjects project, Row row, int rowNum) {
		if (row.getCell(0) == null) {
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
			row.createCell(15).setCellValue(project.getCreatedBy().getUsername());
			row.createCell(16).setCellValue(project.getCreatedDt());
			row.createCell(17).setCellValue(project.getAssignedTo().getUsername());

			generateSeedData(project, row);
			generateDSRData(project, row);
			generateHarvestData(project, row);
			sourceRow = row;
		}
	}

	private void generateSeedData(FarmProjects project, Row row) {
		ProjectSeedTreatment seed = seedTreatmentDao.findSeedTreatmentByProjects(project);
		if (seed != null) {
			row.createCell(21).setCellValue(seed.getSeedRate());
			row.createCell(22).setCellValue(seed.getSaltSolution());
			row.createCell(23).setCellValue(seed.getSeedTreatment());
			row.createCell(24).setCellValue(seed.getSeedSource());
			row.createCell(25).setCellValue(seed.getBrand());
			row.createCell(26).setCellValue(seed.getCompany());
			row.createCell(27).setCellValue(seed.getSeedClass());

			Object[] result = seedTreatmentChemicalDao.findBySeedTreatmentPk1(seed.getPk1());
			if (result != null && result.length > 1) {
				row.createCell(28).setCellValue((String) result[1]);
				row.createCell(29).setCellValue((String) result[2]);
				row.createCell(30).setCellValue((String) result[3]);
			}
			row.createCell(31).setCellValue(seed.getCost());
		}
	}

	private void generateDSRData(FarmProjects project, Row row) {
		ProjectDSRMethod obj = dsrDao.findDsrByProjects(project);
		if (obj != null) {
			row.createCell(32).setCellValue(obj.getSeedRate());
			row.createCell(33).setCellValue(obj.getAcres());
			row.createCell(34).setCellValue(obj.getSoakingTime());
			row.createCell(35).setCellValue(obj.getCost());
		}
	}

	private int populateSowingData(FarmProjects project, FarmPlots farmPlot, int start, int end, int rowNum,
			Sheet sheet, Row sourceRow) {
		List<ProjectSowingData> objs = sowingDao.findAllByProjectsAndPlots(project, farmPlot,
				Sort.by(Sort.Direction.DESC, "pk1"));

		for (ProjectSowingData obj : objs) {
			if (start > end) {
				end = start;
			}
			Row row = sheet.getRow(start);
			if (row == null) {
				row = sheet.createRow(start);
				createSingleRows(project, row, rowNum);
			}
			if (row.getCell(18) == null) {
				row.createCell(18).setCellValue(obj.getPlots().getPk1());
				row.createCell(19).setCellValue(obj.getPlots().getPlotNumber());
				row.createCell(20).setCellValue(obj.getPlots().getLocation() == null ? "No" : "Yes");
			}
			row.createCell(36).setCellValue(obj.getVariety() == null ? "" : obj.getVariety().getVariety());
			row.createCell(37).setCellValue(obj.getSowingDate());
			row.createCell(38).setCellValue(obj.getRemarks());
			row.createCell(39).setCellValue(obj.getCost());
			start = start + 1;
		}
		return end;
	}

	private int populateNurseryWater(FarmProjects project, FarmPlots farmPlot, int start, int end, int rowNum,
			Sheet sheet, Row sourceRow) {
		List<ProjectNurseryWater> objs = waterDao.findAllByProjectsAndPlot(project, farmPlot);

		for (ProjectNurseryWater obj : objs) {
			if (start > end) {
				end = start;
			}
			Row row = sheet.getRow(start);
			if (row == null) {
				row = sheet.createRow(start);
				createSingleRows(project, row, rowNum);
			}
			if (row.getCell(18) == null) {
				row.createCell(18).setCellValue(obj.getPlot().getPk1());
				row.createCell(19).setCellValue(obj.getPlot().getPlotNumber());
				row.createCell(20).setCellValue(obj.getPlot().getLocation() == null ? "No" : "Yes");
			}
			row.createCell(40).setCellValue(obj.getVariety() == null ? "" : obj.getVariety().getVariety());
			row.createCell(41).setCellValue(obj.getSeedlingAge());
			row.createCell(42).setCellValue(obj.getIrrigationDate());
			row.createCell(43).setCellValue(obj.getCost());
			start = start + 1;
		}
		return end;
	}

	private int populateNurseryWeed(FarmProjects project, FarmPlots farmPlot, int start, int end, int rowNum,
			Sheet sheet, Row sourceRow) {
		List<ProjectNurseryWeed> objs = weedDao.findAllByProjectsAndPlot(project, farmPlot);

		for (ProjectNurseryWeed obj : objs) {
			if (start > end) {
				end = start;
			}
			Row row = sheet.getRow(start);
			if (row == null) {
				row = sheet.createRow(start);
				createSingleRows(project, row, rowNum);
			}
			if (row.getCell(18) == null) {
				row.createCell(18).setCellValue(obj.getPlot().getPk1());
				row.createCell(19).setCellValue(obj.getPlot().getPlotNumber());
				row.createCell(20).setCellValue(obj.getPlot().getLocation() == null ? "No" : "Yes");
			}
			row.createCell(44).setCellValue(obj.getWeedicide() == null ? "" : obj.getWeedicide().getName());
			row.createCell(45).setCellValue(obj.getManagement());
			row.createCell(46).setCellValue(obj.getDose());
			row.createCell(47).setCellValue(obj.getWeedingDate());
			row.createCell(48).setCellValue(obj.getMethod());
			row.createCell(49).setCellValue(obj.getCost());

			start = start + 1;
		}
		return end;
	}

	private int populateNurseryNutrient(FarmProjects project, FarmPlots farmPlot, int start, int end, int rowNum,
			Sheet sheet, Row sourceRow) {
		List<ProjectNurseryNutrient> objs = nutrientDao.findAllNutrientByProjectsAndPlot(project, farmPlot);

		for (ProjectNurseryNutrient obj : objs) {
			if (start > end) {
				end = start;
			}
			Row row = sheet.getRow(start);
			if (row == null) {
				row = sheet.createRow(start);
				createSingleRows(project, row, rowNum);
			}
			if (row.getCell(18) == null) {
				row.createCell(18).setCellValue(obj.getPlot().getPk1());
				row.createCell(19).setCellValue(obj.getPlot().getPlotNumber());
				row.createCell(20).setCellValue(obj.getPlot().getLocation() == null ? "No" : "Yes");
			}
			row.createCell(50).setCellValue(obj.getFertilizer() == null ? "" : obj.getFertilizer().getName());
			row.createCell(51).setCellValue(obj.getDose());
			row.createCell(52).setCellValue(obj.getAppliedDate());
			row.createCell(53).setCellValue(obj.getCost());

			start = start + 1;
		}
		return end;
	}

	private int populateNurseryPests(FarmProjects project, FarmPlots farmPlot, int start, int end, int rowNum,
			Sheet sheet, Row sourceRow) {
		List<ProjectNurseryPests> objs = pestDao.findAllPestsByProjectsAndPlots(project, farmPlot);

		for (ProjectNurseryPests obj : objs) {
			if (start > end) {
				end = start;
			}
			Row row = sheet.getRow(start);
			if (row == null) {
				row = sheet.createRow(start);
				createSingleRows(project, row, rowNum);
			}
			if (row.getCell(18) == null) {
				row.createCell(18).setCellValue(obj.getPlots().getPk1());
				row.createCell(19).setCellValue(obj.getPlots().getPlotNumber());
				row.createCell(20).setCellValue(obj.getPlots().getLocation() == null ? "No" : "Yes");
			}
			row.createCell(54).setCellValue(obj.getPests() == null ? "" : obj.getPests().getName());
			row.createCell(55).setCellValue(obj.getChemicals() == null ? "" : obj.getChemicals().getChemicalName());
			row.createCell(56).setCellValue(obj.getBrands() == null ? "" : obj.getBrands().getTradeName());
			row.createCell(57).setCellValue(obj.getBrands() == null ? "" : obj.getBrands().getCompanyName());
			row.createCell(58).setCellValue(obj.getAppliedDose());
			row.createCell(59).setCellValue(obj.getAppliedMethod());
			row.createCell(60).setCellValue(obj.getCost());

			start = start + 1;
		}
		return end;
	}

	private int populateLandPreparation(FarmProjects project, int rowNum, Sheet sheet) {
		List<ProjectLandPreparation> objs = landDao.findAllByProject(project);

		for (ProjectLandPreparation obj : objs) {

			Row row = sheet.getRow(rowNum);
			if (row == null) {
				row = sheet.createRow(rowNum);
				createSingleRows(project, row, rowNum);
			}
			row.createCell(61).setCellValue(obj.getVariety() == null ? "" : obj.getVariety().getVariety());
			row.createCell(62).setCellValue(obj.getAcresPrepared());
			row.createCell(63).setCellValue(obj.getDateOfPreparation());
			row.createCell(64).setCellValue(obj.getMethod());
			row.createCell(65).setCellValue(obj.getFieldPreparation());
			row.createCell(66).setCellValue(obj.getNoOfTimes());
			row.createCell(67).setCellValue(obj.getCost());

			rowNum = rowNum + 1;
		}
		return rowNum;
	}

	private int populateStandEstablishment(FarmProjects project, FarmPlots farmPlot, int start, int end, int rowNum,
			Sheet sheet, Row sourceRow) {
		List<ProjectTransplantManagement> objs = transplantDao.findAllByProjectAndPlots(project, farmPlot);

		for (ProjectTransplantManagement obj : objs) {
			if (start > end) {
				end = start;
			}
			Row row = sheet.getRow(start);
			if (row == null) {
				row = sheet.createRow(start);
				createSingleRows(project, row, rowNum);
			}
			if (row.getCell(18) == null) {
				row.createCell(18).setCellValue(obj.getPlots().getPk1());
				row.createCell(19).setCellValue(obj.getPlots().getPlotNumber());
				row.createCell(20).setCellValue(obj.getPlots().getLocation() == null ? "No" : "Yes");
			}
			row.createCell(68).setCellValue(obj.getVariety() == null ? "" : obj.getVariety().getVariety());
			row.createCell(69).setCellValue(obj.getDuration());
			row.createCell(70).setCellValue(obj.getDateOfTransplanting());
			row.createCell(71).setCellValue(obj.getAcres());
			row.createCell(72).setCellValue(obj.getSeedlingAge());
			row.createCell(73).setCellValue(obj.getSpacing());
			row.createCell(74).setCellValue(obj.getHill());
			row.createCell(75).setCellValue(obj.getCost());

			start = start + 1;
		}
		return end;
	}

	private int populateIrrigationManagement(FarmProjects project, int rowNum, Sheet sheet) {
		List<ProjectIrrigation> objs = irrigationDao.findAllIrrigationsByProjects(project);

		for (ProjectIrrigation obj : objs) {
			Row row = sheet.getRow(rowNum);
			if (row == null) {
				row = sheet.createRow(rowNum);
				createSingleRows(project, row, rowNum);
			}

			row.createCell(76).setCellValue(obj.getNumber());
			row.createCell(77).setCellValue(obj.getCropStage() == null ? "" : obj.getCropStage().getCropStage());
			row.createCell(78)
					.setCellValue(obj.getIrrigationSource() == null ? "" : obj.getIrrigationSource().getName());
			row.createCell(79).setCellValue(obj.getIrrigationDate());
			row.createCell(80).setCellValue(obj.getTechniques());
			row.createCell(81).setCellValue(obj.getRemarks());
			row.createCell(82).setCellValue(obj.getCost());

			rowNum = rowNum + 1;
		}
		return rowNum;
	}

	private int populateWeedManagement(FarmProjects project, int rowNum, Sheet sheet) {
		List<ProjectWeedManagement> objs = weedMgtDao.findAllByProjects(project);

		for (ProjectWeedManagement obj : objs) {
			Row row = sheet.getRow(rowNum);
			if (row == null) {
				row = sheet.createRow(rowNum);
				createSingleRows(project, row, rowNum);
			}

			row.createCell(83).setCellValue(obj.getWeedicide() == null ? "" : obj.getWeedicide().getName());
			row.createCell(84).setCellValue(obj.getWeedicide() == null ? "" : obj.getWeedicide().getBrand());
			row.createCell(85).setCellValue(obj.getEmergence());
			row.createCell(86).setCellValue(obj.getAcres());
			row.createCell(87).setCellValue(obj.getWeedingDate());
			row.createCell(88).setCellValue(obj.getCost());

			rowNum = rowNum + 1;
		}
		return rowNum;
	}

	private int populateOrganicManure(FarmProjects project, FarmPlots farmPlot, int start, int end, int rowNum,
			Sheet sheet, Row sourceRow) {
		List<ProjectOrganicManure> objs = manureDao.findAllByProjectsAndPlot(project, farmPlot);

		for (ProjectOrganicManure obj : objs) {
			if (start > end) {
				end = start;
			}
			Row row = sheet.getRow(start);
			if (row == null) {
				row = sheet.createRow(start);
				createSingleRows(project, row, rowNum);
			}
			if (row.getCell(18) == null) {
				row.createCell(18).setCellValue(obj.getPlot().getPk1());
				row.createCell(19).setCellValue(obj.getPlot().getPlotNumber());
				row.createCell(20).setCellValue(obj.getPlot().getLocation() == null ? "No" : "Yes");
			}
			row.createCell(89).setCellValue(obj.getManure() == null ? "" : obj.getManure().getName());
			row.createCell(90).setCellValue(obj.getDose());
			row.createCell(91).setCellValue(obj.getMethod());
			row.createCell(92).setCellValue(obj.getUsedDate());
			row.createCell(93).setCellValue(obj.getCost());
			start = start + 1;
		}
		return end;
	}

	private int populateBioFertilizers(FarmProjects project, FarmPlots farmPlot, int start, int end, int rowNum,
			Sheet sheet, Row sourceRow) {
		List<ProjectBioFertilizers> objs = bioDao.findByProjectsAndPlot(project, farmPlot);

		for (ProjectBioFertilizers obj : objs) {
			if (start > end) {
				end = start;
			}
			Row row = sheet.getRow(start);
			if (row == null) {
				row = sheet.createRow(start);
				createSingleRows(project, row, rowNum);
			}
			if (row.getCell(18) == null) {
				row.createCell(18).setCellValue(obj.getPlot().getPk1());
				row.createCell(19).setCellValue(obj.getPlot().getPlotNumber());
				row.createCell(20).setCellValue(obj.getPlot().getLocation() == null ? "No" : "Yes");
			}
			row.createCell(94).setCellValue(obj.getFertilizer() == null ? "" : obj.getFertilizer().getName());
			row.createCell(95).setCellValue(obj.getDose());
			row.createCell(96).setCellValue(obj.getMethod());
			row.createCell(97).setCellValue(obj.getStage() == null ? "" : obj.getStage().getCropStage());
			row.createCell(98).setCellValue(obj.getUsedDate());
			row.createCell(99).setCellValue(obj.getCost());
			start = start + 1;
		}
		return end;
	}

	private int populateSyntheticFertilizers(FarmProjects project, FarmPlots farmPlot, int start, int end, int rowNum,
			Sheet sheet, Row sourceRow) {
		List<ProjectSyntheticFertilizers> synthetics = syntheticDao.findByProjectsAndPlot(project, farmPlot);

		for (ProjectSyntheticFertilizers syn : synthetics) {
			if (start > end) {
				end = start;
			}
			Row row = sheet.getRow(start);
			if (row == null) {
				row = sheet.createRow(start);
				createSingleRows(project, row, rowNum);
			}
			if (row.getCell(18) == null) {
				row.createCell(18).setCellValue(syn.getPlot().getPk1());
				row.createCell(19).setCellValue(syn.getPlot().getPlotNumber());
				row.createCell(20).setCellValue(syn.getPlot().getLocation() == null ? "No" : "Yes");
			}
			row.createCell(100).setCellValue(syn.getFertilizer() == null ? "" : syn.getFertilizer().getName());
			row.createCell(101).setCellValue(syn.getDose());
			row.createCell(102).setCellValue(syn.getNumber());
			row.createCell(103).setCellValue(syn.getMethod());
			row.createCell(104).setCellValue(syn.getStage() == null ? "" : syn.getStage().getCropStage());
			row.createCell(105).setCellValue(syn.getUsedDate());
			row.createCell(106).setCellValue(syn.getPk1());
			start = start + 1;
		}
		return end;
	}

	private int populateMicroNutrient(FarmProjects project, FarmPlots farmPlot, int start, int end, int rowNum,
			Sheet sheet, Row sourceRow) {
		List<ProjectMicroNutrient> objs = microDao.findAllByProjectsAndPlots(project, farmPlot);

		for (ProjectMicroNutrient obj : objs) {
			if (start > end) {
				end = start;
			}
			Row row = sheet.getRow(start);
			if (row == null) {
				row = sheet.createRow(start);
				createSingleRows(project, row, rowNum);
			}
			if (row.getCell(18) == null) {
				row.createCell(18).setCellValue(obj.getPlots().getPk1());
				row.createCell(19).setCellValue(obj.getPlots().getPlotNumber());
				row.createCell(20).setCellValue(obj.getPlots().getLocation() == null ? "No" : "Yes");
			}
			row.createCell(107).setCellValue(obj.getMicro() == null ? "" : obj.getMicro().getName());
			row.createCell(108).setCellValue(obj.getDose());
			row.createCell(109).setCellValue(obj.getMethod());
			row.createCell(110).setCellValue(obj.getUsedDate());
			row.createCell(111).setCellValue(obj.getCost());
			start = start + 1;
		}
		return end;
	}

	private int populatePestManagement(FarmProjects project, FarmPlots farmPlot, int start, int end, int rowNum,
			Sheet sheet, Row sourceRow) {
		List<ProjectProtection> objs = protectionDao.findAllProtectionsByProjectsAndPlots(project, farmPlot);

		for (ProjectProtection obj : objs) {
			if (start > end) {
				end = start;
			}
			Row row = sheet.getRow(start);
			if (row == null) {
				row = sheet.createRow(start);
				createSingleRows(project, row, rowNum);
			}
			if (row.getCell(18) == null) {
				row.createCell(18).setCellValue(obj.getPlots().getPk1());
				row.createCell(19).setCellValue(obj.getPlots().getPlotNumber());
				row.createCell(20).setCellValue(obj.getPlots().getLocation() == null ? "No" : "Yes");
			}
			row.createCell(112).setCellValue(obj.getPests() == null ? "" : obj.getPests().getName());
			row.createCell(113).setCellValue(obj.getChemicals() == null ? "" : obj.getChemicals().getChemicalName());
			row.createCell(114).setCellValue(obj.getBrands() == null ? "" : obj.getBrands().getTradeName());
			row.createCell(115).setCellValue(obj.getBrands() == null ? "" : obj.getBrands().getCompanyName());
			row.createCell(116).setCellValue(obj.getStage() == null ? "" : obj.getStage().getCropStage());
			row.createCell(117).setCellValue(obj.getAppliedDate());
			row.createCell(118).setCellValue(obj.getAppliedDose());
			row.createCell(119).setCellValue(obj.getAppliedMethod());
			row.createCell(120).setCellValue(obj.getAppliedMode());
			row.createCell(121).setCellValue(obj.getCost());
			start = start + 1;
		}
		return end;
	}

	private int populatePaddyPriceConfirmation(FarmProjects project, int rowNum, Sheet sheet) {
		List<HarvestPriceConfirmation> objs = harvestPriceConfirmDao.findAllByProjects(project);
		int tempRowNum=rowNum;
		for (HarvestPriceConfirmation obj : objs) {
			Row row = sheet.getRow(rowNum);
			if (row == null) {
				row = sheet.createRow(rowNum);
				createSingleRows(project, row, rowNum);
			}

			row.createCell(122).setCellValue(obj.getQty());
			row.createCell(123).setCellValue(obj.getPurchaseOrder());
			row.createCell(124).setCellValue(obj.getPriceTrends());
			row.createCell(125).setCellValue(obj.getRate());
			row.createCell(126).setCellValue(obj.getRate());
			row.createCell(127).setCellValue(obj.getQualityVerifedBy());
			row.createCell(128).setCellValue(obj.getQualityStaff());
			row.createCell(129).setCellValue(obj.getSlipPreparedBy());
			row.createCell(130).setCellValue(obj.getApprovedBy());
			row.createCell(131).setCellValue(obj.getDateOfApproval());
			row.createCell(132).setCellValue(obj.getLaborCost());
			rowNum = rowNum + 1;
		}
		return tempRowNum;
}
		
		private int populateQualityAssessment(FarmProjects project, int rowNum, Sheet sheet) {
			List<HarvestQualityAssessment> objs = harvestQADao.findAllByProjects(project);
			int tempRowNum=rowNum;
			for (HarvestQualityAssessment obj : objs) {
				Row row = sheet.getRow(rowNum);
				if (row == null) {
					row = sheet.createRow(rowNum);
					createSingleRows(project, row, rowNum);
				}

				row.createCell(133).setCellValue(obj.getMoisturePercentage());
				row.createCell(134).setCellValue(obj.getMethodOfMoistureChecking());
				row.createCell(135).setCellValue(obj.getGreenGrainsPercentage());
				row.createCell(136).setCellValue(obj.getChaffyGrainsPercentage());
				row.createCell(137).setCellValue(obj.getImpurities());
				row.createCell(138).setCellValue(obj.getOtherQualityParameters());
				row.createCell(139).setCellValue(obj.getRemarks());
				rowNum = rowNum + 1;
			}
			return tempRowNum;
	}
		
		
		private int populatePurchasePointEntry(FarmProjects project, int rowNum, Sheet sheet) {
			List<HarvestPurchasePointEntry> objs = harvestPurchaseEntryDao.findAllByProjects(project);
			int tempRowNum=rowNum;
			for (HarvestPurchasePointEntry obj : objs) {
				Row row = sheet.getRow(rowNum);
				if (row == null) {
					row = sheet.createRow(rowNum);
					createSingleRows(project, row, rowNum);
				}

				row.createCell(140).setCellValue(obj.getPurchasePoint());
				row.createCell(141).setCellValue(obj.getFieldExecutiveName());
				row.createCell(142).setCellValue(obj.getPpcSlipNo());
				row.createCell(143).setCellValue(obj.getDateOfReceipt());
				row.createCell(144).setCellValue(obj.getQualityReportNo());
				row.createCell(145).setCellValue(obj.getDateOfTestReport());
				row.createCell(146).setCellValue(obj.getLabName());
				rowNum = rowNum + 1;
			}
			return tempRowNum;
	}
		
		private int populatePackingDetails(FarmProjects project, int rowNum, Sheet sheet) {
			List<HarvestPackagingDetails> objs = harvestPackingDao.findAllByProjects(project);
			int tempRowNum=rowNum;
			for (HarvestPackagingDetails obj : objs) {
				Row row = sheet.getRow(rowNum);
				if (row == null) {
					row = sheet.createRow(rowNum);
					createSingleRows(project, row, rowNum);
				}

				row.createCell(147).setCellValue(obj.getPackagingMaterial());
				row.createCell(148).setCellValue(obj.getNumberOfBagsRequired());
				row.createCell(149).setCellValue(obj.getPackingSize());
				row.createCell(150).setCellValue(obj.getCostOfBags());
				row.createCell(151).setCellValue(obj.getRopeCost());
				row.createCell(152).setCellValue(obj.getStitchingCost());
				row.createCell(153).setCellValue(obj.getNumberOfLoadMansInvolved());
				rowNum = rowNum + 1;
			}
			return tempRowNum;
	}
		
		private int populateLabelling(FarmProjects project, int rowNum, Sheet sheet) {
			List<HarvestLabelling> objs = harvestLabelDao.findAllByProjects(project);
			int tempRowNum=rowNum;
			for (HarvestLabelling obj : objs) {
				Row row = sheet.getRow(rowNum);
				if (row == null) {
					row = sheet.createRow(rowNum);
					createSingleRows(project, row, rowNum);
				}

				row.createCell(154).setCellValue(obj.getLotCode());
				row.createCell(155).setCellValue(obj.getBatchNumber());
				row.createCell(156).setCellValue(obj.getTagColour());
				row.createCell(157).setCellValue(obj.getQualityGrade());
				row.createCell(158).setCellValue(obj.getIdentificationMark());
				rowNum = rowNum + 1;
			}
			return tempRowNum;
	}
		
		private int populateWeighmentSlip(FarmProjects project, int rowNum, Sheet sheet) {
			List<HarvestWeighmentDetails> objs = harvestWeighmentDao.findAllByProjects(project);
			int tempRowNum=rowNum;
			for (HarvestWeighmentDetails obj : objs) {
				Row row = sheet.getRow(rowNum);
				if (row == null) {
					row = sheet.createRow(rowNum);
					createSingleRows(project, row, rowNum);
				}

				row.createCell(159).setCellValue(obj.getWeighmentSlipNumber());
				row.createCell(160).setCellValue(obj.getDateTimeOfWeighment());
				row.createCell(161).setCellValue(obj.getLoadingUnloadingStatus());
				row.createCell(162).setCellValue(obj.getVehicleNumber());
				row.createCell(163).setCellValue(obj.getDriverName());
				row.createCell(164).setCellValue(obj.getGrossWeight());
				row.createCell(165).setCellValue(obj.getTareWeight());
				row.createCell(166).setCellValue(obj.getNetWeight());
				row.createCell(167).setCellValue(obj.getWeightDifference());
				row.createCell(168).setCellValue(obj.getMoisturePercentage());
				row.createCell(169).setCellValue(obj.getTotalQuantity());
				row.createCell(170).setCellValue(obj.getNumberOfBags());
				row.createCell(171).setCellValue(obj.getChargesForWeighment());
				row.createCell(172).setCellValue(obj.getNameOfWeighBridge());
				row.createCell(173).setCellValue(obj.getSignatureOrSealOfInCharge());
				row.createCell(174).setCellValue(obj.getPlaceOfWeighment());
				rowNum = rowNum + 1;
			}
			return tempRowNum;
	}
		
		private int populatePhysicalQualityStandards(FarmProjects project, int rowNum, Sheet sheet) {
			List<HarvestQualityStandards> objs = harvestQualityStdDao.findAllByProjects(project);
			int tempRowNum=rowNum;
			for (HarvestQualityStandards obj : objs) {
				Row row = sheet.getRow(rowNum);
				if (row == null) {
					row = sheet.createRow(rowNum);
					createSingleRows(project, row, rowNum);
				}

				row.createCell(175).setCellValue(obj.getPurchaseDate());
				row.createCell(176).setCellValue(obj.getLotNumber());
				row.createCell(177).setCellValue(obj.getPurchaseCenter());
				row.createCell(178).setCellValue(obj.getQuantity());
				row.createCell(179).setCellValue(obj.getPackSize());
				row.createCell(180).setCellValue(obj.getLabelled());
				row.createCell(181).setCellValue(obj.getMoisture());
				row.createCell(182).setCellValue(obj.getHrPercentage());
				row.createCell(183).setCellValue(obj.getBrokenPercentage());
				row.createCell(184).setCellValue(obj.getLength());
				row.createCell(185).setCellValue(obj.getBreadth());
				row.createCell(186).setCellValue(obj.getDdPercentage());
				row.createCell(187).setCellValue(obj.getInertMatterPercentage());
				row.createCell(188).setCellValue(obj.getAdmixturePercentage());
				row.createCell(189).setCellValue(obj.getCheffPercentage());
				row.createCell(190).setCellValue(obj.getDispatchDate());
				rowNum = rowNum + 1;
			}
			return tempRowNum;
	}
		
		private int populateSalesInvoice(FarmProjects project, int rowNum, Sheet sheet) {
			
				HarvestInvoice obj = harvestInvoiceDao.findByProjects(project);
				Row row = sheet.getRow(rowNum);
				if (row == null) {
					row = sheet.createRow(rowNum);
					createSingleRows(project, row, rowNum);
				}

				row.createCell(191).setCellValue(obj.getInvoiceNumber());
				row.createCell(192).setCellValue(obj.getDateOfInvoice());
				row.createCell(193).setCellValue(obj.getPurchasePointAddress());
				row.createCell(194).setCellValue(obj.getTruckNo());
				row.createCell(195).setCellValue(obj.getEmptyWeight());
				row.createCell(196).setCellValue(obj.getGrossWeight());
				row.createCell(197).setCellValue(obj.getNetWeight());
				row.createCell(198).setCellValue(obj.getNumberOfBags());
				row.createCell(199).setCellValue(obj.getHsnCode());
				row.createCell(200).setCellValue(obj.getBuyerName());
				row.createCell(201).setCellValue(obj.getBillingAddress());
				row.createCell(202).setCellValue(obj.getBillingGSTIN());
				row.createCell(203).setCellValue(obj.getDeliveryAddress());
				row.createCell(204).setCellValue(obj.getDeliveryGSTIN());
			return rowNum;
	}
		
		private int populateGstInvoice(FarmProjects project, int rowNum, Sheet sheet) {
			
			HarvestGstInvoice obj = harvestGstInvoiceDao.findByProjects(project);
			Row row = sheet.getRow(rowNum);
			if (row == null) {
				row = sheet.createRow(rowNum);
				createSingleRows(project, row, rowNum);
			}

			row.createCell(205).setCellValue(obj.getInvoiceNumber());
			row.createCell(206).setCellValue(obj.getInvoiceDate());
			row.createCell(207).setCellValue(obj.getTransportMode());
			row.createCell(208).setCellValue(obj.getVehicleNumber());
			row.createCell(209).setCellValue(obj.getBillFromName());
			row.createCell(210).setCellValue(obj.getGstin());
			row.createCell(211).setCellValue(obj.getAddress());
			row.createCell(212).setCellValue(obj.getSupplierContactNumber());
			row.createCell(213).setCellValue(obj.getMillName());
			row.createCell(214).setCellValue(obj.getMillAddress());
			row.createCell(215).setCellValue(obj.getGstinOfMill());
			row.createCell(216).setCellValue(obj.getRecepientContactNumber());
			row.createCell(217).setCellValue(obj.getDispatchDateTime());
			row.createCell(218).setCellValue(obj.getPlaceOfDelivery());
			row.createCell(219).setCellValue(obj.getLocation());
		return rowNum;
}


		private int populateLoadingSlip(FarmProjects project, int rowNum, Sheet sheet) {
			List<HarvestLoadingSlip> objs = harvestLoadingSlipDao.findAllByProjects(project);
			int tempRowNum=rowNum;
			for (HarvestLoadingSlip obj : objs) {
				Row row = sheet.getRow(rowNum);
				if (row == null) {
					row = sheet.createRow(rowNum);
					createSingleRows(project, row, rowNum);
				}

				row.createCell(220).setCellValue(obj.getSlipNo());
				row.createCell(221).setCellValue(obj.getRatePerKg());
				row.createCell(222).setCellValue(obj.getWeighmentSlipNo());
				row.createCell(223).setCellValue(obj.getQuantityLoadedKg());
				row.createCell(224).setCellValue(obj.getNoOfBags());
				row.createCell(225).setCellValue(obj.getTotalValue());
				row.createCell(226).setCellValue(obj.getDateOfDispatch());
				row.createCell(227).setCellValue(obj.getDispatchTime());
				row.createCell(228).setCellValue(obj.getFromLocation());
				row.createCell(229).setCellValue(obj.getToLocation());
				row.createCell(230).setCellValue(obj.getVehicleNo());
				row.createCell(231).setCellValue(obj.getVerifierName());
				rowNum = rowNum + 1;
			}
			return tempRowNum;
	}
		
		private int populateUnLoadingSlip(FarmProjects project, int rowNum, Sheet sheet) {
			List<HarvestUnloadingSlip> objs = harvestUnLoadingSlipDao.findAllByProjects(project);
			int tempRowNum=rowNum;
			for (HarvestUnloadingSlip obj : objs) {
				Row row = sheet.getRow(rowNum);
				if (row == null) {
					row = sheet.createRow(rowNum);
					createSingleRows(project, row, rowNum);
				}

				row.createCell(232).setCellValue(obj.getUnloadingReceiptNo());
				row.createCell(233).setCellValue(obj.getVehicleNo());
				row.createCell(234).setCellValue(obj.getDateOfUnloading());
				row.createCell(235).setCellValue(obj.getTimeOfUnloading());
				row.createCell(236).setCellValue(obj.getDryerCode());
				row.createCell(237).setCellValue(obj.getUnloadingLocation());
				row.createCell(238).setCellValue(obj.getLoadingSlipNo());
				row.createCell(239).setCellValue(obj.getWeighmentSlipNo());
				row.createCell(240).setCellValue(obj.getTotalWeightKg());
				row.createCell(241).setCellValue(obj.getWeightDifference());
				row.createCell(242).setCellValue(obj.getNumberOfBags());
				row.createCell(243).setCellValue(obj.getMoistureContent());
				row.createCell(244).setCellValue(obj.getRemarksOfInCharge());
				row.createCell(245).setCellValue(obj.getNameOfInCharge());
				rowNum = rowNum + 1;
			}
			return tempRowNum;
	}
		
		private int populatePricePaymentDetails(FarmProjects project, int rowNum, Sheet sheet) {
			List<HarvestPaymentDetails> objs = harvestPaymentDetailsDao.findAllByProjects(project);
			int tempRowNum=rowNum;
			for (HarvestPaymentDetails obj : objs) {
				Row row = sheet.getRow(rowNum);
				if (row == null) {
					row = sheet.createRow(rowNum);
					createSingleRows(project, row, rowNum);
				}

				row.createCell(246).setCellValue(obj.getFarmerPrice());
				row.createCell(247).setCellValue(obj.getLabourCharges());
				row.createCell(248).setCellValue(obj.getPriceNegotiation());
				row.createCell(249).setCellValue(obj.getCurrentPriceTrends());
				row.createCell(250).setCellValue(obj.getPricePerMT());
				row.createCell(251).setCellValue(obj.getPaymentFollowUpPerson());
				row.createCell(252).setCellValue(obj.getApprovalStatus());
				row.createCell(253).setCellValue(obj.getNameOfSender());
				row.createCell(254).setCellValue(obj.getAmount());
				row.createCell(255).setCellValue(obj.getPaymentMode());
				row.createCell(256).setCellValue(obj.getUtrReferenceNumber());
				rowNum = rowNum + 1;
			}
			return tempRowNum;
	}
		
		private int populateEwayBillDetails(FarmProjects project, int rowNum, Sheet sheet) {
			List<HarvestEwayBill> objs = harvestEwayBillDao.findAllByProjects(project);
			int tempRowNum=rowNum;
			for (HarvestEwayBill obj : objs) {
				Row row = sheet.getRow(rowNum);
				if (row == null) {
					row = sheet.createRow(rowNum);
					createSingleRows(project, row, rowNum);
				}

				row.createCell(257).setCellValue(obj.geteWayBillSystem());
				row.createCell(258).setCellValue(obj.geteWayBillNo());
				row.createCell(259).setCellValue(obj.geteWayBillDate());
				row.createCell(260).setCellValue(obj.getGeneratedBy());
				row.createCell(261).setCellValue(obj.getValidFrom());
				row.createCell(262).setCellValue(obj.getValidUntil());
				row.createCell(263).setCellValue(obj.getGstinOfSupplier());
				row.createCell(264).setCellValue(obj.getGstinOfRecipient());
				row.createCell(265).setCellValue(obj.getPlaceOfDelivery());
				row.createCell(266).setCellValue(obj.getDocumentNo());
				row.createCell(267).setCellValue(obj.getDocumentDate());
				row.createCell(268).setCellValue(obj.getTransactionType());
				row.createCell(269).setCellValue(obj.getValueOfGoods());
				row.createCell(270).setCellValue(obj.getHsnCode());
				row.createCell(271).setCellValue(obj.getReasonForTransportation());
				row.createCell(272).setCellValue(obj.getTransporter());
				row.createCell(273).setCellValue(obj.getMode());
				row.createCell(274).setCellValue(obj.getVehicleNo());
				row.createCell(275).setCellValue(obj.getPlaceOfDispatchB());
				row.createCell(276).setCellValue(obj.getEnteredDate());
				row.createCell(277).setCellValue(obj.getEnteredBy());
				row.createCell(278).setCellValue(obj.getCewbNo());

				rowNum = rowNum + 1;
			}
			return tempRowNum;
	}
		private int populateStorageAndStocking(FarmProjects project, int rowNum, Sheet sheet) {
			List<HarvestStocking> objs = harvestStockingDao.findAllByProjects(project);
			int tempRowNum=rowNum;
			for (HarvestStocking obj : objs) {
				Row row = sheet.getRow(rowNum);
				if (row == null) {
					row = sheet.createRow(rowNum);
					createSingleRows(project, row, rowNum);
				}

				row.createCell(279).setCellValue(obj.getStackNumber());
				row.createCell(280).setCellValue(obj.getLotCode());
				row.createCell(281).setCellValue(obj.getNumberOfLot());
				row.createCell(282).setCellValue(obj.getNumberOfBagsInLot());
				row.createCell(283).setCellValue(obj.getIdentificationMark());
				row.createCell(284).setCellValue(obj.getTarpaulinUsed());
				row.createCell(285).setCellValue(obj.getFumigationChemical());
				row.createCell(286).setCellValue(obj.getDose());
				row.createCell(287).setCellValue(obj.getFumigationDate());
				row.createCell(288).setCellValue(obj.getExposureDate());
				row.createCell(289).setCellValue(obj.getAgencyName());
				row.createCell(290).setCellValue(obj.getRemarks());

				rowNum = rowNum + 1;
			}
			return tempRowNum;
	}
		
		private int populateDryingProcess(FarmProjects project, int rowNum, Sheet sheet) {
			List<HarvestDryingProcess> objs = harvestDryingProcessDao.findAllByProjects(project);
			int tempRowNum=rowNum;
			for (HarvestDryingProcess obj : objs) {
				Row row = sheet.getRow(rowNum);
				if (row == null) {
					row = sheet.createRow(rowNum);
					createSingleRows(project, row, rowNum);
				}

				row.createCell(291).setCellValue(obj.getInChargeName());
				row.createCell(292).setCellValue(obj.getBinCapacity());
				row.createCell(293).setCellValue(obj.getStartTime());
				row.createCell(294).setCellValue(obj.getEndTime());
				row.createCell(295).setCellValue(obj.getDryingTotalTime());
				row.createCell(296).setCellValue(obj.getMoistureBeforeDrying());
				row.createCell(297).setCellValue(obj.getMoistureAfterDrying());
				row.createCell(298).setCellValue(obj.getBinUnloadingDetails());
				row.createCell(299).setCellValue(obj.getNumberOfTrucksUnloaded());
				row.createCell(300).setCellValue(obj.getTruckCapacity());
				row.createCell(301).setCellValue(obj.getTotalUnloadedCapacity());

				rowNum = rowNum + 1;
			}
			return tempRowNum;
	}
		
		private int populateMillingProcess(FarmProjects project, int rowNum, Sheet sheet) {
			List<HarvestMillingProcess> objs = harvestMillingProcessDao.findAllByProjects(project);
			int tempRowNum=rowNum;
			for (HarvestMillingProcess obj : objs) {
				Row row = sheet.getRow(rowNum);
				if (row == null) {
					row = sheet.createRow(rowNum);
					createSingleRows(project, row, rowNum);
				}

				row.createCell(302).setCellValue(obj.getLoadArrivalDate());
				row.createCell(303).setCellValue(obj.getMillAndLocation());
				row.createCell(304).setCellValue(obj.getLoadNo());
				row.createCell(305).setCellValue(obj.getVehicleNo());
				row.createCell(306).setCellValue(obj.getPaddyMoistureContent());
				row.createCell(307).setCellValue(obj.getBatch());
				row.createCell(308).setCellValue(obj.getSoakingDate());
				row.createCell(309).setCellValue(obj.getMillingDate());
				row.createCell(310).setCellValue(obj.getInputQuantityKgs());
				row.createCell(311).setCellValue(obj.getFullRiceKgs());
				row.createCell(312).setCellValue(obj.getBrokenRiceKgs());
				row.createCell(313).setCellValue(obj.getBlackRiceKgs());
				row.createCell(314).setCellValue(obj.getDustRiceKgs());
				row.createCell(315).setCellValue(obj.getHeadRiceRecovery());
				row.createCell(316).setCellValue(obj.getTotalRiceRecovery());
				row.createCell(317).setCellValue(obj.getOutturnKgs());
				row.createCell(318).setCellValue(obj.getMillingRecoveryPercentage());

				rowNum = rowNum + 1;
			}
			return tempRowNum;
	}
		
		private int populateMillEquipmentUsage(FarmProjects project, int rowNum, Sheet sheet) {
			List<HarvestMillEquipmentUsage> objs = harvestMillEquipmentUsageDao.findAllByProjects(project);
			int tempRowNum=rowNum;
			for (HarvestMillEquipmentUsage obj : objs) {
				Row row = sheet.getRow(rowNum);
				if (row == null) {
					row = sheet.createRow(rowNum);
					createSingleRows(project, row, rowNum);
				}

				row.createCell(319).setCellValue(obj.getEquipment());
				row.createCell(320).setCellValue(obj.getDateOfOperation());
				row.createCell(321).setCellValue(obj.getQuantity());
				rowNum = rowNum + 1;
			}
			return tempRowNum;
	}
		
		private int populateSampleTest(FarmProjects project, int rowNum, Sheet sheet) {
			List<HarvestSampleTest> objs = harvestSampleTestDao.findAllByProjects(project);
			int tempRowNum=rowNum;
			for (HarvestSampleTest obj : objs) {
				Row row = sheet.getRow(rowNum);
				if (row == null) {
					row = sheet.createRow(rowNum);
					createSingleRows(project, row, rowNum);
				}

				row.createCell(322).setCellValue(obj.getStackNumber());
				row.createCell(323).setCellValue(obj.getLocation());
				row.createCell(324).setCellValue(obj.getProduct());
				row.createCell(325).setCellValue(obj.getQuantity());
				row.createCell(326).setCellValue(obj.getSampleCode());
				row.createCell(327).setCellValue(obj.getSamplingDate());
				row.createCell(328).setCellValue(obj.getSamplePrepDate());
				row.createCell(329).setCellValue(obj.getSampleTestDate());
				row.createCell(330).setCellValue(obj.getReportDate());
				row.createCell(331).setCellValue(obj.getMrl());
				row.createCell(332).setCellValue(obj.getResult());
				rowNum = rowNum + 1;
			}
			return tempRowNum;
	}
		
		private int populateHarvest(FarmProjects project, int rowNum, Sheet sheet) {
			List<HarvestEstimates> objs = harvestEstimatesDao.findAllByProjects(project);
			int tempRowNum=rowNum;
			for (HarvestEstimates obj : objs) {
				Row row = sheet.getRow(rowNum);
				if (row == null) {
					row = sheet.createRow(rowNum);
					createSingleRows(project, row, rowNum);
				}

				row.createCell(333).setCellValue(obj.getEstimatedDate());
				row.createCell(334).setCellValue(obj.getEstimatedYield());
				row.createCell(335).setCellValue(obj.getRemarks());
				rowNum = rowNum + 1;
			}
			return tempRowNum;
	}
		
	private void generateHarvestData(FarmProjects project, Row row) {
		ProjectHarvest obj = harvestDao.findByProjects(project);
		if (obj != null) {
			row.createCell(196).setCellValue(obj.getHarvestDate());
			row.createCell(197).setCellValue(obj.getYield());
		}
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
		File template = new File(excelFilePath + "MRLTemplate.xlsx");
		File file = new File(excelPath);
		try (InputStream inputStream = new FileInputStream(template);
				Workbook workbook = new XSSFWorkbook(inputStream);
				FileOutputStream outputStream = new FileOutputStream(file);) {
			Thread.sleep(5000); // Sleep for 5 seconds
			Sheet sheet = workbook.getSheetAt(0);

			int rowNum = 3;
			for (FarmProjects project : projects) {
				Row ProjectRow = sheet.createRow(rowNum);
				createSingleRows(project, ProjectRow, rowNum);

				// No plot multiple entires
				int tempRowNum = rowNum;
				tempRowNum = Math.max(tempRowNum, populateLandPreparation(project, tempRowNum, sheet));
				tempRowNum = Math.max(tempRowNum, populateIrrigationManagement(project, rowNum, sheet));
				tempRowNum = Math.max(tempRowNum, populateWeedManagement(project, rowNum, sheet));
				tempRowNum = Math.max(tempRowNum, populatePaddyPriceConfirmation(project, tempRowNum, sheet));
				tempRowNum = Math.max(tempRowNum, populateQualityAssessment(project, tempRowNum, sheet));
				tempRowNum = Math.max(tempRowNum, populatePurchasePointEntry(project, tempRowNum, sheet));
				tempRowNum = Math.max(tempRowNum, populatePackingDetails(project, tempRowNum, sheet));
				tempRowNum = Math.max(tempRowNum, populateLabelling(project, tempRowNum, sheet));
				tempRowNum = Math.max(tempRowNum, populateWeighmentSlip(project, tempRowNum, sheet));
				tempRowNum = Math.max(tempRowNum, populatePhysicalQualityStandards(project, tempRowNum, sheet));
				tempRowNum = Math.max(tempRowNum, populateSalesInvoice(project, tempRowNum, sheet));
				tempRowNum = Math.max(tempRowNum, populateGstInvoice(project, tempRowNum, sheet));
				tempRowNum = Math.max(tempRowNum, populateLoadingSlip(project, tempRowNum, sheet));
				tempRowNum = Math.max(tempRowNum, populateUnLoadingSlip(project, tempRowNum, sheet));
				tempRowNum = Math.max(tempRowNum, populatePricePaymentDetails(project, tempRowNum, sheet));
				tempRowNum = Math.max(tempRowNum, populateEwayBillDetails(project, tempRowNum, sheet));
				tempRowNum = Math.max(tempRowNum, populateStorageAndStocking(project, tempRowNum, sheet));
				tempRowNum = Math.max(tempRowNum, populateDryingProcess(project, tempRowNum, sheet));
				tempRowNum = Math.max(tempRowNum, populateMillingProcess(project, tempRowNum, sheet));
				tempRowNum = Math.max(tempRowNum, populateMillEquipmentUsage(project, tempRowNum, sheet));
				tempRowNum = Math.max(tempRowNum, populateSampleTest(project, tempRowNum, sheet));
				tempRowNum = Math.max(tempRowNum, populateHarvest(project, tempRowNum, sheet));

				List<ProjectPlots> plots = projectPlotsDao.findAllPlotsByProject(project);
				for (ProjectPlots plot : plots) {
					int start = rowNum;
					int end = rowNum;
					FarmPlots farmPlot = plot.getPlots();
					Row plotRow = sheet.getRow(rowNum);
					if (plotRow == null) {
						plotRow = sheet.createRow(rowNum);
						createSingleRows(project, plotRow, rowNum);
					}
					if (plotRow.getCell(18) == null) {
						plotRow.createCell(18).setCellValue(farmPlot.getPk1());
						plotRow.createCell(19).setCellValue(farmPlot.getPlotNumber());
						plotRow.createCell(20).setCellValue(farmPlot.getLocation() == null ? "No" : "Yes");
					}

					end = Math.max(end, populateSowingData(project, farmPlot, start, end, rowNum, sheet, plotRow));
					end = Math.max(end, populateNurseryWater(project, farmPlot, start, end, rowNum, sheet, plotRow));
					end = Math.max(end, populateNurseryWeed(project, farmPlot, start, end, rowNum, sheet, plotRow));
					end = Math.max(end, populateNurseryNutrient(project, farmPlot, start, end, rowNum, sheet, plotRow));
					end = Math.max(end, populateNurseryPests(project, farmPlot, start, end, rowNum, sheet, plotRow));
					end = Math.max(end,
							populateStandEstablishment(project, farmPlot, start, end, rowNum, sheet, plotRow));
					end = Math.max(end, populateOrganicManure(project, farmPlot, start, end, rowNum, sheet, plotRow));
					end = Math.max(end, populateBioFertilizers(project, farmPlot, start, end, rowNum, sheet, plotRow));
					end = Math.max(end,
							populateSyntheticFertilizers(project, farmPlot, start, end, rowNum, sheet, plotRow));
					end = Math.max(end, populateMicroNutrient(project, farmPlot, start, end, rowNum, sheet, plotRow));
					end = Math.max(end, populatePestManagement(project, farmPlot, start, end, rowNum, sheet, plotRow));

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
