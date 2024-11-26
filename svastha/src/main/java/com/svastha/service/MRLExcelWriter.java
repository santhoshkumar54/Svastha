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

	private int populatePrePurchase(FarmProjects project, int rowNum, Sheet sheet) {
		List<ProjectPrePurchase> objs = preDao.findAllByProjects(project);

		for (ProjectPrePurchase obj : objs) {
			Row row = sheet.getRow(rowNum);
			if (row == null) {
				row = sheet.createRow(rowNum);
				createSingleRows(project, row, rowNum);
			}

			row.createCell(122).setCellValue(obj.getVariety() == null ? "" : obj.getVariety().getVariety());
			row.createCell(123).setCellValue(obj.getLotQty());
			row.createCell(124).setCellValue(obj.getSamplingDate());
			row.createCell(125).setCellValue(obj.getSamplePrepDate());
			row.createCell(126).setCellValue(obj.getSampleTestDate());
			row.createCell(127).setCellValue(obj.getSampleCode());
			row.createCell(128).setCellValue(obj.getResult());
			rowNum = rowNum + 1;
		}
		return rowNum;
	}

	private int populatePrcurement(FarmProjects project, int rowNum, Sheet sheet) {
		List<ProjectProcurement> objs = procDao.findAllByProjects(project);

		for (ProjectProcurement obj : objs) {
			Row row = sheet.getRow(rowNum);
			if (row == null) {
				row = sheet.createRow(rowNum);
				createSingleRows(project, row, rowNum);
			}

			row.createCell(129).setCellValue(obj.getPurchaseDate());
			row.createCell(130).setCellValue(obj.getLotNumber());
			row.createCell(131).setCellValue(obj.getPurchaseCenter());
			row.createCell(132).setCellValue(obj.getVariety() == null ? "" : obj.getVariety().getVariety());
			row.createCell(133).setCellValue(obj.getNumberOfBags());
			row.createCell(134).setCellValue(obj.getPackSize());
			row.createCell(135).setCellValue(obj.getLabelled());
			row.createCell(136).setCellValue(obj.getMoisture());
			row.createCell(137).setCellValue(obj.getHr());
			row.createCell(138).setCellValue(obj.getBroken());
			row.createCell(139).setCellValue(obj.getLength());
			row.createCell(140).setCellValue(obj.getBreadth());
			row.createCell(141).setCellValue(obj.getDd());
			row.createCell(142).setCellValue(obj.getIntert());
			row.createCell(143).setCellValue(obj.getAdmixture());
			row.createCell(144).setCellValue(obj.getCheff());
			row.createCell(145).setCellValue(obj.getDispatchDate());

			rowNum = rowNum + 1;
		}
		return rowNum;
	}

	private int populatePacking(FarmProjects project, int rowNum, Sheet sheet) {
		List<ProjectPacking> objs = packingDao.findAllByProjects(project);

		for (ProjectPacking obj : objs) {
			Row row = sheet.getRow(rowNum);
			if (row == null) {
				row = sheet.createRow(rowNum);
				createSingleRows(project, row, rowNum);
			}
			row.createCell(146).setCellValue(obj.getVariety() == null ? "" : obj.getVariety().getVariety());
			row.createCell(147).setCellValue(obj.getHarvestDate());
			row.createCell(148).setCellValue(obj.getHarvestDate());
			row.createCell(149).setCellValue(obj.getThreshingDate());
			row.createCell(150).setCellValue(obj.getThreshingMethod());
			row.createCell(151).setCellValue(obj.getCleaningDate());
			row.createCell(152).setCellValue(obj.getMoisture());
			row.createCell(153).setCellValue(obj.getPackingMaterial());
			row.createCell(154).setCellValue(obj.getPackSize());
			row.createCell(155).setCellValue(obj.getNumberOfBags());
			row.createCell(156).setCellValue(obj.getGrossWeight());
			row.createCell(157).setCellValue(obj.getEmptyWeight());
			row.createCell(158).setCellValue(obj.getLabelled());

			rowNum = rowNum + 1;
		}
		return rowNum;
	}

	private int populateDispatch(FarmProjects project, int rowNum, Sheet sheet) {
		List<ProjectDispatch> objs = dispatchDao.findAllByProjects(project);

		for (ProjectDispatch obj : objs) {
			Row row = sheet.getRow(rowNum);
			if (row == null) {
				row = sheet.createRow(rowNum);
				createSingleRows(project, row, rowNum);
			}
			row.createCell(159).setCellValue(obj.getVariety() == null ? "" : obj.getVariety().getVariety());
			row.createCell(160).setCellValue(obj.getDispatchDate());
			row.createCell(161).setCellValue(obj.getInvoiceNumber());
			row.createCell(162).setCellValue(obj.getTruckNumber());
			row.createCell(163).setCellValue(obj.getTruckBiltyNumber());
			row.createCell(164).setCellValue(obj.getNumberOfBags());
			row.createCell(165).setCellValue(obj.getHsnCode());
			row.createCell(166).setCellValue(obj.getGrossWeight());
			row.createCell(167).setCellValue(obj.getEmptyWeight());
			row.createCell(168).setCellValue(obj.getRatePerKg());
			row.createCell(169).setCellValue(obj.getDispatchFrom());
			row.createCell(170).setCellValue(obj.getDispatchTo());
			row.createCell(171).setCellValue(obj.getDispatchBy());

			rowNum = rowNum + 1;
		}
		return rowNum;
	}

	private int populateStorage(FarmProjects project, int rowNum, Sheet sheet) {
		List<ProjectStorage> objs = storageDao.findAllByProjects(project);

		for (ProjectStorage obj : objs) {
			Row row = sheet.getRow(rowNum);
			if (row == null) {
				row = sheet.createRow(rowNum);
				createSingleRows(project, row, rowNum);
			}
			row.createCell(172).setCellValue(obj.getVariety() == null ? "" : obj.getVariety().getVariety());
			row.createCell(173).setCellValue(obj.getStorageDate());
			row.createCell(174).setCellValue(obj.getStackNumber());
			row.createCell(175).setCellValue(obj.getNumberOfBags());
			row.createCell(176).setCellValue(obj.getPackSize());
			row.createCell(177).setCellValue(obj.getGrossWeight());
			row.createCell(178).setCellValue(obj.getStorageLocation());
			row.createCell(179).setCellValue(obj.getGodownName());
			row.createCell(180).setCellValue(obj.getLabelled());
			row.createCell(181).setCellValue(obj.getSeparateStacks());
			row.createCell(182).setCellValue(obj.getChemicalName());
			row.createCell(183).setCellValue(obj.getDose());
			row.createCell(184).setCellValue(obj.getFumigationDate());
			row.createCell(185).setCellValue(obj.getExposureDate());
			row.createCell(186).setCellValue(obj.getAgencyName());

			rowNum = rowNum + 1;
		}
		return rowNum;
	}

	private int populatePostPurchase(FarmProjects project, int rowNum, Sheet sheet) {
		List<ProjectPostPurchase> objs = postDao.findAllByProjects(project);

		for (ProjectPostPurchase obj : objs) {
			Row row = sheet.getRow(rowNum);
			if (row == null) {
				row = sheet.createRow(rowNum);
				createSingleRows(project, row, rowNum);
			}
			row.createCell(187).setCellValue(obj.getVariety() == null ? "" : obj.getVariety().getVariety());
			row.createCell(188).setCellValue(obj.getStackNumber());
			row.createCell(189).setCellValue(obj.getLocation());
			row.createCell(190).setCellValue(obj.getProduct());
			row.createCell(191).setCellValue(obj.getSampleCode());
			row.createCell(192).setCellValue(obj.getSampleTestDate());
			row.createCell(193).setCellValue(obj.getReportDate());
			row.createCell(194).setCellValue(obj.getMrl());
			row.createCell(195).setCellValue(obj.getResult());

			rowNum = rowNum + 1;
		}
		return rowNum;
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
				tempRowNum = Math.max(tempRowNum, populatePrePurchase(project, tempRowNum, sheet));
				tempRowNum = Math.max(tempRowNum, populatePrcurement(project, rowNum, sheet));
				tempRowNum = Math.max(tempRowNum, populatePacking(project, rowNum, sheet));
				tempRowNum = Math.max(tempRowNum, populateDispatch(project, tempRowNum, sheet));
				tempRowNum = Math.max(tempRowNum, populateStorage(project, rowNum, sheet));
				tempRowNum = Math.max(tempRowNum, populatePostPurchase(project, rowNum, sheet));

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
