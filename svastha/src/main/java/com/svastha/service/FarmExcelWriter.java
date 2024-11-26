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
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.svastha.entity.FarmGrainMarket;
import com.svastha.entity.FarmLiveStock;
import com.svastha.entity.FarmPlots;
import com.svastha.entity.FarmTools;
import com.svastha.entity.FarmWaterSource;
import com.svastha.entity.FarmWorkers;
import com.svastha.entity.Farms;
import com.svastha.entity.LandDetails;
import com.svastha.repository.FarmGrainMarketRepository;
import com.svastha.repository.FarmLiveStockRepository;
import com.svastha.repository.FarmPlotsRepository;
import com.svastha.repository.FarmRepository;
import com.svastha.repository.FarmToolsRepository;
import com.svastha.repository.FarmWaterSourceRepository;
import com.svastha.repository.FarmWorkersRepository;
import com.svastha.repository.LandDetailsRepository;

@Component
public class FarmExcelWriter {

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

	public void createSingleRows(Farms farm, Row row, int rowNum) {
		if (row.getCell(0) == null) {
			row.createCell(0).setCellValue(rowNum - 1);
			row.createCell(1).setCellValue(farm.getFarmerName());
			row.createCell(2).setCellValue(farm.getFatherName());
			row.createCell(3).setCellValue(farm.getRegNumber());
			row.createCell(4).setCellValue(farm.getVillageId() == null ? "" : farm.getVillageId().getName());
			row.createCell(5).setCellValue(farm.getThalukId() == null ? "" : farm.getThalukId().getName());
			row.createCell(6).setCellValue(farm.getDistrictId() == null ? "" : farm.getDistrictId().getName());
			row.createCell(7).setCellValue(farm.getCreatedBy().getUsername());
			row.createCell(8).setCellValue(farm.getCreatedDt());
			row.createCell(9).setCellValue(farm.getFarmerType());
			row.createCell(10).setCellValue(farm.getGender());
			row.createCell(11).setCellValue(farm.getMobile1());
			row.createCell(12).setCellValue(farm.getMobile2());
			row.createCell(13).setCellValue(farm.getMobileType());
			row.createCell(14).setCellValue(farm.getLandline());
			row.createCell(15).setCellValue(farm.getDoorNumber());
			row.createCell(16).setCellValue(farm.getStreet());
			row.createCell(17).setCellValue(farm.getState());
			row.createCell(18).setCellValue(farm.getPincode());
			row.createCell(19).setCellValue(farm.getAadhaar());
			row.createCell(20).setCellValue(farm.getBank());
			row.createCell(21).setCellValue(farm.getBankAccount());
			row.createCell(22).setCellValue(farm.getIfsc());

			LandDetails land = landDetailsDao.findLandDetailsByFarm(farm);
			if (land != null) {
				row.createCell(23).setCellValue(land.getAreaAcres());
				row.createCell(24).setCellValue(land.getAreaLeased());
				row.createCell(25).setCellValue(land.getAreaCultivable());
				row.createCell(26).setCellValue(land.getSoilData());
				row.createCell(27).setCellValue(land.getSurveyNumber());
				row.createCell(28).setCellValue(land.getPattaNumber());
			}

			List<FarmWaterSource> waters = waterSourceDao.findAllWaterSourceByFarm(farm);
			StringBuilder sb = new StringBuilder();
			for (FarmWaterSource water : waters) {
				if (water.isChecked()) {
					sb.append(water.getWaterSource()).append(" | ");
				}
			}
			row.createCell(34).setCellValue(sb == null ? "" : sb.toString());

			FarmWorkers worker = workerDao.findAllFarmWorkersByFarm(farm);
			if (worker != null) {
				row.createCell(39).setCellValue(worker.getOwnWorkers());
				row.createCell(40).setCellValue(worker.getHiredWorkers());
			}

			List<FarmGrainMarket> markets = grainMarketDao.findAllGrainMarketByFarm(farm);
			for (FarmGrainMarket market : markets) {
				if (market.getAgentType().equals("Commission agent")) {
					row.createCell(41).setCellValue(market.getAgentName());
					row.createCell(42).setCellValue(market.getFirmName());
					row.createCell(43).setCellValue(market.getAddress());
					row.createCell(44).setCellValue(market.getContactNumber());
					row.createCell(45).setCellValue(market.getGstNumber());
					row.createCell(46).setCellValue(market.getBank());
					row.createCell(47).setCellValue(market.getAccountNumber());
					row.createCell(48).setCellValue(market.getIfsc());
				} else {
					row.createCell(49).setCellValue(market.getAgentName());
					row.createCell(50).setCellValue(market.getFirmName());
					row.createCell(51).setCellValue(market.getAddress());
					row.createCell(52).setCellValue(market.getContactNumber());
					row.createCell(53).setCellValue(market.getGstNumber());
					row.createCell(54).setCellValue(market.getBank());
					row.createCell(55).setCellValue(market.getAccountNumber());
					row.createCell(56).setCellValue(market.getIfsc());
				}
			}
			sourceRow = row;
		}
	}

	@Async
	public void startFarmExportV2(Long districtId, Long thalukId, Long villageId, String key, Long userId, String type,
			String email) {
		List<Farms> farms = farmDao.findWithFilters(thalukId, districtId, villageId, key, userId, type);

		String excelName = "farmers" + System.currentTimeMillis() + ".xlsx";
		String excelPath = excelFilePath + excelName;
		File f = new File(excelFilePath);
		if (!f.exists()) {
			f.mkdirs();
		}
		File template = new File(excelFilePath + "FarmTemplate.xlsx");
		File file = new File(excelPath);
		try (InputStream inputStream = new FileInputStream(template);
				Workbook workbook = new XSSFWorkbook(inputStream);
				FileOutputStream outputStream = new FileOutputStream(file);) {
			Thread.sleep(5000); // Sleep for 5 seconds
			Sheet sheet = workbook.getSheetAt(0);

			int rowNum = 2;
			for (Farms farm : farms) {
				Row farmRow = sheet.createRow(rowNum);
				createSingleRows(farm, farmRow, rowNum);
				// multiple entires
				int tempRowNum = rowNum;
				tempRowNum = Math.max(tempRowNum, populatePlotData(farm, tempRowNum, sheet));
				tempRowNum = Math.max(tempRowNum, populateLiveStock(farm, rowNum, sheet));
				tempRowNum = Math.max(tempRowNum, populateTools(farm, rowNum, sheet));
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

	private int populatePlotData(Farms farm, int rowNum, Sheet sheet) {
		List<FarmPlots> objs = plotsDao.findAllPlotsByFarm(farm);

		for (FarmPlots obj : objs) {

			Row row = sheet.getRow(rowNum);
			if (row == null) {
				row = sheet.createRow(rowNum);
				createSingleRows(farm, row, rowNum);
			}
			row.createCell(29).setCellValue(obj.getPlotType());
			row.createCell(30).setCellValue(obj.getPlotNumber());
			row.createCell(31).setCellValue(obj.getSurveyNumber());
			row.createCell(32).setCellValue(obj.getAreaInAcres());
			row.createCell(33).setCellValue(obj.getLocation() == null ? "No" : "Yes");

			rowNum = rowNum + 1;
		}
		return rowNum;
	}

	private int populateLiveStock(Farms farm, int rowNum, Sheet sheet) {
		List<FarmLiveStock> objs = liveStockDao.findAllLiveStocksByFarm(farm);

		for (FarmLiveStock obj : objs) {

			Row row = sheet.getRow(rowNum);
			if (row == null) {
				row = sheet.createRow(rowNum);
				createSingleRows(farm, row, rowNum);
			}
			row.createCell(35).setCellValue(obj.getLivestock());
			row.createCell(36).setCellValue(obj.getCount());

			rowNum = rowNum + 1;
		}
		return rowNum;
	}

	private int populateTools(Farms farm, int rowNum, Sheet sheet) {
		List<FarmTools> objs = toolsDao.findAllToolsByFarm(farm);

		for (FarmTools obj : objs) {

			Row row = sheet.getRow(rowNum);
			if (row == null) {
				row = sheet.createRow(rowNum);
				createSingleRows(farm, row, rowNum);
			}
			row.createCell(37).setCellValue(obj.getTool());
			row.createCell(38).setCellValue(obj.getCount());

			rowNum = rowNum + 1;
		}
		return rowNum;
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
