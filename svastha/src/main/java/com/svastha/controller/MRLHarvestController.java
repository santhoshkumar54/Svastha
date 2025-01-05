package com.svastha.controller;

import com.svastha.entity.FarmProjects;
import com.svastha.entity.HarvestDryingProcess;
import com.svastha.entity.HarvestEstimates;
import com.svastha.entity.HarvestEwayBill;
import com.svastha.entity.HarvestGstInvoice;
import com.svastha.entity.HarvestGstProducts;
import com.svastha.entity.HarvestInvoice;
import com.svastha.entity.HarvestInvoiceProducts;
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
import com.svastha.model.HarvestGstInvoiveDTO;
import com.svastha.model.HarvestInvoiveDTO;
import com.svastha.repository.FarmProjectRepository;
import com.svastha.repository.HarvestDryingProcessRepository;
import com.svastha.repository.HarvestEstimatesRepository;
import com.svastha.repository.HarvestEwayBillRepository;
import com.svastha.repository.HarvestGstInvoiceRepository;
import com.svastha.repository.HarvestGstProductsRepository;
import com.svastha.repository.HarvestInvoiceProductsRepository;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MRLHarvestController {

	@Autowired
	private HarvestDryingProcessRepository dryingProcessDao;

	@Autowired
	private HarvestEwayBillRepository ewayBillDao;

	@Autowired
	private HarvestGstInvoiceRepository gstInvoiceDao;

	@Autowired
	private HarvestGstProductsRepository gstProductsDao;

	@Autowired
	private HarvestInvoiceProductsRepository invoiceProductsDao;

	@Autowired
	private HarvestLabellingRepository labellingDao;

	@Autowired
	private HarvestLoadingSlipRepository loadingSlipDao;

	@Autowired
	private HarvestMillEquipmentUsageRepository millEquipmentUsageDao;

	@Autowired
	private HarvestMillingProcessRepository millingProcessDao;

	@Autowired
	private HarvestPackagingDetailsRepository packagingDetailsDao;

	@Autowired
	private HarvestPaymentDetailsRepository paymentDetailsDao;

	@Autowired
	private HarvestPriceConfirmationRepository priceConfirmationDao;

	@Autowired
	private HarvestInvoiceRepository invoiceDao;

	@Autowired
	private HarvestPurchasePointEntryRepository purchasePointEntryDao;

	@Autowired
	private HarvestQualityAssessmentRepository qualityAssessmentDao;

	@Autowired
	private HarvestQualityStandardRepository qualityStandardDao;

	@Autowired
	private HarvestSampleTestRepository sampleTestDao;

	@Autowired
	private HarvestStockingRepository stockingDao;

	@Autowired
	private HarvestUnloadingSlipRepository unloadingSlipDao;

	@Autowired
	private HarvestWeighmentDetailsRepository weighmenDetailsDao;

	@Autowired
	private FarmProjectRepository projectDao;

	@Autowired
	private HarvestEstimatesRepository estimatesDao;

	@GetMapping("/getWeighmentDetails")
	public List<HarvestWeighmentDetails> getWeighmentDetails(@RequestParam Long projectId) {
		FarmProjects project = projectDao.findById(projectId).get();
		return weighmenDetailsDao.findAllByProjects(project);
	}

	@PostMapping("/saveWeighmentDetails")
	public List<HarvestWeighmentDetails> saveWeighmentDetails(
			@RequestBody List<HarvestWeighmentDetails> weighmentDetails) {
		return weighmenDetailsDao.saveAll(weighmentDetails);
	}

	@GetMapping("/getDryingProcess")
	public List<HarvestDryingProcess> getDryingProcess(@RequestParam Long projectId) {
		FarmProjects project = projectDao.findById(projectId).get();
		return dryingProcessDao.findAllByProjects(project);
	}

	@PostMapping("/saveDryingProcess")
	public List<HarvestDryingProcess> saveDryingProcess(@RequestBody List<HarvestDryingProcess> dryingProcess) {
		return dryingProcessDao.saveAll(dryingProcess);
	}

	@GetMapping("/getEwayBill")
	public List<HarvestEwayBill> getEwayBill(@RequestParam Long projectId) {
		FarmProjects project = projectDao.findById(projectId).get();
		return ewayBillDao.findAllByProjects(project);
	}

	@PostMapping("/saveEwayBill")
	public List<HarvestEwayBill> saveEwayBill(@RequestBody List<HarvestEwayBill> ewayBill) {
		return ewayBillDao.saveAll(ewayBill);
	}

	@GetMapping("/getGstInvoice")
	public HarvestGstInvoiveDTO getGstInvoice(@RequestParam Long projectId) {
		FarmProjects project = projectDao.findById(projectId).get();
		HarvestGstInvoice gst = gstInvoiceDao.findByProjects(project);
		List<HarvestGstProducts> products = gstProductsDao.findAllByInvoice(gst);

		HarvestGstInvoiveDTO dto = new HarvestGstInvoiveDTO();
		dto.setGst(gst);
		dto.setProducts(products);
		return dto;
	}

	@PostMapping("/saveGstInvoice")
	public HarvestGstInvoiveDTO saveGstInvoice(@RequestBody HarvestGstInvoiveDTO gstInvoice) {
		HarvestGstInvoice gst = gstInvoiceDao.save(gstInvoice.getGst());
		List<HarvestGstProducts> products = new ArrayList<>();
		for (HarvestGstProducts product : gstInvoice.getProducts()) {
			product.setInvoice(gst);
			products.add(product);
		}
		gstProductsDao.saveAll(products);
		gstInvoice.setProducts(products);
		gstInvoice.setGst(gst);
		return gstInvoice;
	}

	@GetMapping("/getLabelling")
	public List<HarvestLabelling> getLabelling(@RequestParam Long projectId) {
		FarmProjects project = projectDao.findById(projectId).get();
		return labellingDao.findAllByProjects(project);
	}

	@PostMapping("/saveLabelling")
	public List<HarvestLabelling> saveLabelling(@RequestBody List<HarvestLabelling> labelling) {
		return labellingDao.saveAll(labelling);
	}

	@GetMapping("/getLoadingSlip")
	public List<HarvestLoadingSlip> getLoadingSlip(@RequestParam Long projectId) {
		FarmProjects project = projectDao.findById(projectId).get();
		return loadingSlipDao.findAllByProjects(project);
	}

	@PostMapping("/saveLoadingSlip")
	public List<HarvestLoadingSlip> saveLoadingSlip(@RequestBody List<HarvestLoadingSlip> loadingSlip) {
		return loadingSlipDao.saveAll(loadingSlip);
	}

	@GetMapping("/getMillEquipmentUsage")
	public List<HarvestMillEquipmentUsage> getMillEquipmentUsage(@RequestParam Long projectId) {
		FarmProjects project = projectDao.findById(projectId).get();
		return millEquipmentUsageDao.findAllByProjects(project);
	}

	@PostMapping("/saveMillEquipmentUsage")
	public List<HarvestMillEquipmentUsage> saveMillEquipmentUsage(
			@RequestBody List<HarvestMillEquipmentUsage> millEquipmentUsage) {
		return millEquipmentUsageDao.saveAll(millEquipmentUsage);
	}

	@GetMapping("/getMillingProcess")
	public List<HarvestMillingProcess> getMillingProcess(@RequestParam Long projectId) {
		FarmProjects project = projectDao.findById(projectId).get();
		return millingProcessDao.findAllByProjects(project);
	}

	@PostMapping("/saveMillingProcess")
	public List<HarvestMillingProcess> saveMillingProcess(@RequestBody List<HarvestMillingProcess> millingProcess) {
		return millingProcessDao.saveAll(millingProcess);
	}

	@GetMapping("/getPackagingDetails")
	public List<HarvestPackagingDetails> getPackagingDetails(@RequestParam Long projectId) {
		FarmProjects project = projectDao.findById(projectId).get();
		return packagingDetailsDao.findAllByProjects(project);
	}

	@PostMapping("/savePackagingDetails")
	public List<HarvestPackagingDetails> savePackagingDetails(
			@RequestBody List<HarvestPackagingDetails> packagingDetails) {
		return packagingDetailsDao.saveAll(packagingDetails);
	}

	@GetMapping("/getPaymentDetails")
	public List<HarvestPaymentDetails> getPaymentDetails(@RequestParam Long projectId) {
		FarmProjects project = projectDao.findById(projectId).get();
		return paymentDetailsDao.findAllByProjects(project);
	}

	@PostMapping("/savePaymentDetails")
	public List<HarvestPaymentDetails> savePaymentDetails(@RequestBody List<HarvestPaymentDetails> paymentDetails) {
		return paymentDetailsDao.saveAll(paymentDetails);
	}

	@GetMapping("/getPriceConfirmation")
	public List<HarvestPriceConfirmation> getPriceConfirmation(@RequestParam Long projectId) {
		FarmProjects project = projectDao.findById(projectId).get();
		return priceConfirmationDao.findAllByProjects(project);
	}

	@PostMapping("/savePriceConfirmation")
	public List<HarvestPriceConfirmation> savePriceConfirmation(
			@RequestBody List<HarvestPriceConfirmation> priceConfirmation) {
		return priceConfirmationDao.saveAll(priceConfirmation);
	}

	@GetMapping("/getInvoice")
	public HarvestInvoiveDTO getInvoice(@RequestParam Long projectId) {
		FarmProjects project = projectDao.findById(projectId).get();
		HarvestInvoice gst = invoiceDao.findByProjects(project);
		List<HarvestInvoiceProducts> products = invoiceProductsDao.findAllByInvoice(gst);

		HarvestInvoiveDTO dto = new HarvestInvoiveDTO();
		dto.setInvoice(gst);
		dto.setProducts(products);
		return dto;
	}

	@PostMapping("/saveInvoice")
	public HarvestInvoiveDTO saveInvoice(@RequestBody HarvestInvoiveDTO invoice) {
		HarvestInvoice gst = invoiceDao.save(invoice.getInvoice());
		List<HarvestInvoiceProducts> products = new ArrayList<>();
		for (HarvestInvoiceProducts product : invoice.getProducts()) {
			product.setInvoice(gst);
			products.add(product);
		}
		invoiceProductsDao.saveAll(products);
		invoice.setProducts(products);
		invoice.setInvoice(gst);
		return invoice;
	}

	@GetMapping("/getPurchasePointEntry")
	public List<HarvestPurchasePointEntry> getPurchasePointEntry(@RequestParam Long projectId) {
		FarmProjects project = projectDao.findById(projectId).get();
		return purchasePointEntryDao.findAllByProjects(project);
	}

	@PostMapping("/savePurchasePointEntry")
	public List<HarvestPurchasePointEntry> savePurchasePointEntry(
			@RequestBody List<HarvestPurchasePointEntry> purchasePointEntry) {
		return purchasePointEntryDao.saveAll(purchasePointEntry);
	}

	@GetMapping("/getQualityAssessment")
	public List<HarvestQualityAssessment> getQualityAssessment(@RequestParam Long projectId) {
		FarmProjects project = projectDao.findById(projectId).get();
		return qualityAssessmentDao.findAllByProjects(project);
	}

	@PostMapping("/saveQualityAssessment")
	public List<HarvestQualityAssessment> saveQualityAssessment(
			@RequestBody List<HarvestQualityAssessment> qualityAssessment) {
		return qualityAssessmentDao.saveAll(qualityAssessment);
	}

	@GetMapping("/getQualityStandards")
	public List<HarvestQualityStandards> getQualityStandards(@RequestParam Long projectId) {
		FarmProjects project = projectDao.findById(projectId).get();
		return qualityStandardDao.findAllByProjects(project);
	}

	@PostMapping("/saveQualityStandards")
	public List<HarvestQualityStandards> saveQualityStandards(
			@RequestBody List<HarvestQualityStandards> qualityStandards) {
		return qualityStandardDao.saveAll(qualityStandards);
	}

	@GetMapping("/getSampleTest")
	public List<HarvestSampleTest> getSampleTest(@RequestParam Long projectId) {
		FarmProjects project = projectDao.findById(projectId).get();
		return sampleTestDao.findAllByProjects(project);
	}

	@PostMapping("/saveSampleTest")
	public List<HarvestSampleTest> saveSampleTest(@RequestBody List<HarvestSampleTest> sampleTest) {
		return sampleTestDao.saveAll(sampleTest);
	}

	@GetMapping("/getStocking")
	public List<HarvestStocking> getStocking(@RequestParam Long projectId) {
		FarmProjects project = projectDao.findById(projectId).get();
		return stockingDao.findAllByProjects(project);
	}

	@PostMapping("/saveStocking")
	public List<HarvestStocking> saveStocking(@RequestBody List<HarvestStocking> stocking) {
		return stockingDao.saveAll(stocking);
	}

	@GetMapping("/getUnloadingSlip")
	public List<HarvestUnloadingSlip> getUnloadingSlip(@RequestParam Long projectId) {
		FarmProjects project = projectDao.findById(projectId).get();
		return unloadingSlipDao.findAllByProjects(project);
	}

	@PostMapping("/saveUnloadingSlip")
	public List<HarvestUnloadingSlip> saveUnloadingSlip(@RequestBody List<HarvestUnloadingSlip> unloadingSlip) {
		return unloadingSlipDao.saveAll(unloadingSlip);
	}

	@GetMapping("/getEstimates")
	public List<HarvestEstimates> getEstimates(@RequestParam Long projectId) {
		FarmProjects project = projectDao.findById(projectId).get();
		return estimatesDao.findAllByProjects(project);
	}
}