package com.svastha.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.svastha.entity.*;
import com.svastha.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.svastha.model.ChemicalBrandModel;
import com.svastha.model.DiseaseAndPestModel;
import com.svastha.model.MasterProjectModel;

enum ChemicalStatusEnum {
	recommended, restricted, unknown

}

@RestController
public class MasterController {

	@Autowired
	private LiveStockRepository liveStockDao;

	@Autowired
	private WaterSourceRepository waterSourceDao;

	@Autowired
	private MasterSoilTypeRepository soiltypeDao;

	@Autowired
	private MasterToolsRepository toolsDao;

	@Autowired
	private MasterGrainMarketRepository marketDao;

	@Autowired
	private TransplantMethodRepository transplantMethodDao;

	@Autowired
	private MasterFertilizerRepository fertilizerDao;

	@Autowired
	private MasterBioFertilizerRepository bioFertilizerDao;

	@Autowired
	private MasterManureRepository manureDao;

	@Autowired
	private MasterCropRepository masterCropDao;

	@Autowired
	private MasterYearRepository yearDao;

	@Autowired
	private MasterSeasonRepository seasonDao;

	@Autowired
	private MasterCropStageRepository masterStageDao;

	@Autowired
	private MasterDiseasesAndPestsRepository masterPestsDao;

	@Autowired
	private MasterChemicalRepository chemicalsDao;

	@Autowired
	private MasterChemicalBrandRepository brandsDao;

	@Autowired
	private MasterPestChemicalMappingRepository pestChemicalDao;

	@Autowired
	private MasterChemicalBrandMappingRepository chemicalBrandDao;

	@Autowired
	private MasterWeedicideRepository weedicideDao;

	@Autowired
	private MasterProjectTypeRepository projectTypeDao;

	@Autowired
	private MasterAnnualProgramRepository annualDao;

	@Autowired
	private MasterRiskManagementRepository riskDao;

	@Autowired
	private MasterGrowthPromoterRepository growthDao;

	@Autowired
	private MasterMicroNutrientRepository microDao;

	@Autowired
	private VillageRepository villageDao;

	@Autowired
	private DistrictRepository districtDao;

	@Autowired
	private ThalukRepository thalukDao;

	@Autowired
	private MasterIcsRepository icsDao;

	@Autowired
	private MasterCropVarietyRepository varietyDao;

	@Autowired
	private AwdDeviceRepository awdDeviceDao;

	@GetMapping(path = "/liveStocks")
	public @ResponseBody Iterable<LiveStock> getLiveStocks() {

		return liveStockDao.findAll();
	}

	@PostMapping("/saveLiveStock")
	public @ResponseBody Iterable<LiveStock> saveLiveStock(@RequestBody LiveStock livestock) {

		liveStockDao.save(livestock);
		return liveStockDao.findAll();
	}

	@PostMapping("/deleteLiveStock")
	public @ResponseBody Iterable<LiveStock> deleteLiveStock(@RequestBody LiveStock livestock) {

		liveStockDao.delete(livestock);
		return liveStockDao.findAll();
	}

	@GetMapping(path = "/waterSources")
	public @ResponseBody Iterable<WaterSource> getWaterSources() {

		return waterSourceDao.findAll();
	}

	@PostMapping("/saveWaterSources")
	public @ResponseBody Iterable<WaterSource> saveWaterSources(@RequestBody WaterSource waterSources) {

		waterSourceDao.save(waterSources);
		return waterSourceDao.findAll();
	}

	@PostMapping("/deleteWaterSources")
	public @ResponseBody Iterable<WaterSource> deleteWaterSources(@RequestBody WaterSource waterSources) {

		waterSourceDao.delete(waterSources);
		return waterSourceDao.findAll();
	}

	@GetMapping(path = "/soiltype")
	public @ResponseBody Iterable<MasterSoiltype> getSoiltype() {

		return soiltypeDao.findAll();
	}

	@PostMapping("/saveSoiltype")
	public @ResponseBody Iterable<MasterSoiltype> saveSoiltype(@RequestBody MasterSoiltype soiltype) {

		soiltypeDao.save(soiltype);
		return soiltypeDao.findAll();
	}

	@PostMapping("/deleteSoiltype")
	public @ResponseBody Iterable<MasterSoiltype> deleteSoiltype(@RequestBody MasterSoiltype soiltype) {

		soiltypeDao.delete(soiltype);
		return soiltypeDao.findAll();
	}

	@GetMapping(path = "/tools")
	public @ResponseBody Iterable<MasterTools> getTools() {

		return toolsDao.findAll();
	}

	@PostMapping("/saveTools")
	public @ResponseBody Iterable<MasterTools> saveTools(@RequestBody MasterTools tools) {

		toolsDao.save(tools);
		return toolsDao.findAll();
	}

	@PostMapping("/deleteTools")
	public @ResponseBody Iterable<MasterTools> deleteTools(@RequestBody MasterTools tools) {

		toolsDao.delete(tools);
		return toolsDao.findAll();
	}

	@GetMapping(path = "/grainmarkets")
	public @ResponseBody Iterable<MasterGrainMarket> getGrainMarkets() {

		return marketDao.findAll();
	}

	@GetMapping(path = "/fertilizers")
	public @ResponseBody Iterable<MasterFertilizers> getFertilizers() {

		return fertilizerDao.findAll();
	}

	@PostMapping("/saveFertilizer")
	public @ResponseBody Iterable<MasterFertilizers> saveFertilizer(@RequestBody MasterFertilizers fertilizer) {

		fertilizerDao.save(fertilizer);
		return fertilizerDao.findAll();
	}

	@PostMapping("/deleteFertilizer")
	public @ResponseBody Iterable<MasterFertilizers> deleteFertilizer(@RequestBody MasterFertilizers fertilizer) {

		fertilizerDao.delete(fertilizer);
		return fertilizerDao.findAll();
	}

	@GetMapping(path = "/biofertilizers")
	public @ResponseBody Iterable<MasterBioFertilizer> getBioFertilizers() {

		return bioFertilizerDao.findAll();
	}

	@PostMapping("/saveBioFertilizer")
	public @ResponseBody Iterable<MasterBioFertilizer> saveBioFertilizer(
			@RequestBody MasterBioFertilizer biofertilizers) {

		bioFertilizerDao.save(biofertilizers);
		return bioFertilizerDao.findAll();
	}

	@PostMapping("/deleteBioFertilizer")
	public @ResponseBody Iterable<MasterBioFertilizer> deleteBioFertilizer(
			@RequestBody MasterBioFertilizer biofertilizers) {

		bioFertilizerDao.delete(biofertilizers);
		return bioFertilizerDao.findAll();
	}

	@GetMapping(path = "/organicmanures")
	public @ResponseBody Iterable<MasterOrganicManure> getManures() {

		return manureDao.findAll();
	}

	@PostMapping("/saveOrganicManure")
	public @ResponseBody Iterable<MasterOrganicManure> saveOrganicManure(
			@RequestBody MasterOrganicManure organicmanures) {

		manureDao.save(organicmanures);
		return manureDao.findAll();
	}

	@PostMapping("/deleteOrganicManure")
	public @ResponseBody Iterable<MasterOrganicManure> deleteOrganicManure(
			@RequestBody MasterOrganicManure organicmanures) {

		manureDao.delete(organicmanures);
		return manureDao.findAll();
	}

	@GetMapping(path = "/cropStage")
	public @ResponseBody Iterable<MasterCropStage> getCropStage() {

		return masterStageDao.findAll();
	}

	@PostMapping("/saveCropStage")
	public @ResponseBody Iterable<MasterCropStage> saveCropStage(@RequestBody MasterCropStage cropStage) {

		masterStageDao.save(cropStage);
		return masterStageDao.findAll();
	}

	@PostMapping("/deleteCropStage")
	public @ResponseBody Iterable<MasterCropStage> deleteCropStage(@RequestBody MasterCropStage cropStage) {

		masterStageDao.delete(cropStage);
		return masterStageDao.findAll();
	}

	@GetMapping(path = "/transplantMethod")
	public @ResponseBody Iterable<TransplantMethods> getTransplantMethods() {

		return transplantMethodDao.findAll();
	}

	@PostMapping("/saveTransplantMethod")
	public @ResponseBody Iterable<TransplantMethods> saveTransplantMethod(
			@RequestBody TransplantMethods transplantMethod) {

		transplantMethodDao.save(transplantMethod);
		return transplantMethodDao.findAll();
	}

	@PostMapping("/deleteTransplantMethod")
	public @ResponseBody Iterable<TransplantMethods> deleteTransplantMethod(
			@RequestBody TransplantMethods transplantMethod) {

		transplantMethodDao.delete(transplantMethod);
		return transplantMethodDao.findAll();
	}

	@GetMapping(path = "/getProjectMaster")
	public @ResponseBody MasterProjectModel getMasterProject() {
		MasterProjectModel master = new MasterProjectModel();
		master.setYear(yearDao.findAll());
		master.setSeason(seasonDao.findAll());
		master.setCrop(masterCropDao.findAll());
		master.setProjectType(projectTypeDao.findAll());
		master.setIcsType(icsDao.findAll());

		return master;
	}

	@GetMapping("/getMasterCrops")
	public @ResponseBody List<MasterCrop> getMasterCrops() {
		return masterCropDao.findAll();
	}

	@PostMapping("/saveMasterCrop")
	public @ResponseBody Iterable<MasterCrop> saveMasterCrop(@RequestBody MasterCrop crop) {

		masterCropDao.save(crop);
		return masterCropDao.findAll();
	}

	@PostMapping("/deleteMasterCrop")
	public @ResponseBody Iterable<MasterCrop> deleteMasterCrop(@RequestBody MasterCrop crop) {

		masterCropDao.delete(crop);
		return masterCropDao.findAll();
	}

	@GetMapping("/getMasterSeasons")
	public @ResponseBody List<MasterSeason> getMasterSeasons() {
		return seasonDao.findAll();
	}

	@PostMapping("/saveMasterSeason")
	public @ResponseBody Iterable<MasterSeason> saveMasterSeason(@RequestBody MasterSeason season) {

		seasonDao.save(season);
		return seasonDao.findAll();
	}

	@PostMapping("/deleteMasterSeason")
	public @ResponseBody Iterable<MasterSeason> deleteMasterSeason(@RequestBody MasterSeason season) {

		seasonDao.delete(season);
		return seasonDao.findAll();
	}

	@GetMapping("/getMasterYear")
	public @ResponseBody List<MasterYear> getMasterYear() {
		return yearDao.findAll();
	}

	@PostMapping("/saveMasterYear")
	public @ResponseBody Iterable<MasterYear> saveMasterYear(@RequestBody MasterYear year) {

		yearDao.save(year);
		return yearDao.findAll();
	}

	@PostMapping("/deleteMasterYear")
	public @ResponseBody Iterable<MasterYear> deleteMasterYear(@RequestBody MasterYear year) {

		yearDao.delete(year);
		return yearDao.findAll();
	}

	@GetMapping(path = "/getDiseasesAndPests")
	public @ResponseBody Iterable<DiseaseAndPestModel> getDiseasesAndPests() {

		List<DiseaseAndPestModel> pests = new ArrayList<DiseaseAndPestModel>();

		List<MasterPests> master = masterPestsDao.findAll();
		for (MasterPests pest : master) {
			DiseaseAndPestModel pestModel = new DiseaseAndPestModel();

			List<MasterChemicalPestMapping> chemicals = pestChemicalDao.findAllByPests(pest);
			List<ChemicalBrandModel> chemicalBrandModels = new ArrayList<>();
			for (MasterChemicalPestMapping chemical : chemicals) {
				ChemicalBrandModel chemicalBrandModel = new ChemicalBrandModel();

				List<MasterChemicalBrandMapping> brandsMap = chemicalBrandDao
						.findAllByChemicals(chemical.getChemicals());
				List<MasterChemicalBrands> brands = new ArrayList<>();
				for (MasterChemicalBrandMapping brand : brandsMap) {
					MasterChemicalBrands chemicalBrand = brand.getBrands();
					brands.add(chemicalBrand);
				}
				chemicalBrandModel.setChemicals(chemical.getChemicals());
				chemicalBrandModel.setBrands(brands);
				chemicalBrandModels.add(chemicalBrandModel);
			}

			pestModel.setPests(pest);
			pestModel.setChemicals(chemicalBrandModels);
			pests.add(pestModel);

		}
		return pests;
	}

	@GetMapping(path = "/getRestrictedChemicals")
	public @ResponseBody Iterable<MasterChemicals> getRestrictedChemicals() {

		return chemicalsDao.findAllByStatus(ChemicalStatusEnum.restricted.toString());
	}

	@GetMapping(path = "/getWeedicide")
	public @ResponseBody Iterable<MasterWeedicide> getWeedicide() {

		return weedicideDao.findAll();
	}

	@PostMapping("/saveWeedicide")
	public @ResponseBody Iterable<MasterWeedicide> saveWeedicide(@RequestBody MasterWeedicide weedicide) {

		weedicideDao.save(weedicide);
		return weedicideDao.findAll();
	}

	@PostMapping("/deleteWeedicide")
	public @ResponseBody Iterable<MasterWeedicide> deleteWeedicide(@RequestBody MasterWeedicide weedicide) {

		weedicideDao.delete(weedicide);
		return weedicideDao.findAll();
	}

	@GetMapping(path = "/getProjectType")
	public @ResponseBody Iterable<MasterProjectType> getProjectType() {

		return projectTypeDao.findAll();
	}

	@PostMapping("/saveProjectType")
	public @ResponseBody Iterable<MasterProjectType> saveProjectType(@RequestBody MasterProjectType projectType) {

		projectTypeDao.save(projectType);
		return projectTypeDao.findAll();
	}

	@PostMapping("/deleteProjectType")
	public @ResponseBody Iterable<MasterProjectType> deleteProjectType(@RequestBody MasterProjectType projectType) {

		projectTypeDao.delete(projectType);
		return projectTypeDao.findAll();
	}

	@GetMapping(path = "/getMasterAnnualProgram")
	public @ResponseBody Iterable<MasterAnnualProgram> getMasterAnnualProgram() {

		return annualDao.findAll();
	}

	@PostMapping("/saveMasterAnnualProgram")
	public @ResponseBody Iterable<MasterAnnualProgram> saveMasterAnnualProgram(
			@RequestBody MasterAnnualProgram annualProgram) {

		annualDao.save(annualProgram);
		return annualDao.findAll();
	}

	@PostMapping("/deleteMasterAnnualProgram")
	public @ResponseBody Iterable<MasterAnnualProgram> deleteMasterAnnualProgram(
			@RequestBody MasterAnnualProgram annualProgram) {

		annualDao.delete(annualProgram);
		return annualDao.findAll();
	}

	@GetMapping(path = "/getMasterRiskManagement")
	public @ResponseBody Iterable<MasterRiskManagement> getMasterRiskManagement() {

		return riskDao.findAll();
	}

	@PostMapping("/saveMasterRiskManagement")
	public @ResponseBody Iterable<MasterRiskManagement> saveMasterRiskManagement(
			@RequestBody MasterRiskManagement riskManagement) {

		riskDao.save(riskManagement);
		return riskDao.findAll();
	}

	@PostMapping("/deleteMasterRiskManagement")
	public @ResponseBody Iterable<MasterRiskManagement> deleteMasterRiskManagement(
			MasterRiskManagement riskManagement) {

		riskDao.delete(riskManagement);
		return riskDao.findAll();
	}

	@GetMapping(path = "/getMasterGrowthPromoter")
	public @ResponseBody Iterable<MasterGrowthPromoter> getMasterGrowthPromoter() {

		return growthDao.findAll();
	}

	@PostMapping("/saveGrowthPromoter")
	public @ResponseBody Iterable<MasterGrowthPromoter> saveGrowthPromoter(
			@RequestBody MasterGrowthPromoter growthPromoter) {

		growthDao.save(growthPromoter);
		return growthDao.findAll();
	}

	@PostMapping("/deleteGrowthPromoter")
	public @ResponseBody Iterable<MasterGrowthPromoter> deleteGrowthPromoter(
			@RequestBody MasterGrowthPromoter growthPromoter) {

		growthDao.delete(growthPromoter);
		return growthDao.findAll();
	}

	@GetMapping(path = "/getMasterMicroNutrient")
	public @ResponseBody Iterable<MasterMicroNutrient> getMasterMicroNutrient() {

		return microDao.findAll();
	}

	@PostMapping("/saveMicroNutrient")
	public @ResponseBody Iterable<MasterMicroNutrient> saveMicroNutrient(@RequestBody MasterMicroNutrient micro) {

		microDao.save(micro);
		return microDao.findAll();
	}

	@PostMapping("/deleteMicroNutrient")
	public @ResponseBody Iterable<MasterMicroNutrient> deleteMicroNutrient(@RequestBody MasterMicroNutrient micro) {

		microDao.delete(micro);
		return microDao.findAll();
	}

	@GetMapping("/getVillages")
	public List<Village> getVillages() {
		return villageDao.findAll();
	}

	@PostMapping("/saveVillages")
	public @ResponseBody Iterable<Village> saveVillages(@RequestBody Village village) {

		villageDao.save(village);
		return villageDao.findAll();
	}

	@PostMapping("/deleteVillages")
	public @ResponseBody Iterable<Village> deleteVillages(@RequestBody Village village) {

		villageDao.delete(village);
		return villageDao.findAll();
	}

	@GetMapping("/getThaluk")
	public List<Thaluk> getThaluk() {
		return thalukDao.findAll();
	}

	@PostMapping("/saveThaluk")
	public @ResponseBody Iterable<Thaluk> saveThaluk(@RequestBody Thaluk thaluk) {

		thalukDao.save(thaluk);
		return thalukDao.findAll();
	}

	@PostMapping("/deleteThaluk")
	public @ResponseBody Iterable<Thaluk> deleteThaluk(@RequestBody Thaluk thaluk) {

		thalukDao.delete(thaluk);
		return thalukDao.findAll();
	}

	@GetMapping("/getDistrict")
	public List<District> getDistrict() {
		return districtDao.findAll();
	}

	@PostMapping("/saveDistrict")
	public @ResponseBody Iterable<District> saveDistrict(@RequestBody District district) {

		districtDao.save(district);
		return districtDao.findAll();
	}

	@PostMapping("/deleteDistrict")
	public @ResponseBody Iterable<District> deleteDistrict(@RequestBody District district) {

		districtDao.delete(district);
		return districtDao.findAll();
	}

	@GetMapping("/getIcs")
	public List<MasterIcs> getIcs() {
		return icsDao.findAll();
	}

	@PostMapping("/saveIcs")
	public @ResponseBody Iterable<MasterIcs> saveIcs(@RequestBody MasterIcs ics) {

		icsDao.save(ics);
		return icsDao.findAll();
	}

	@PostMapping("/deleteIcs")
	public @ResponseBody Iterable<MasterIcs> deleteIcs(@RequestBody MasterIcs ics) {

		icsDao.delete(ics);
		return icsDao.findAll();
	}

	@GetMapping("/getVariety")
	public List<MasterCropVariety> getVariety() {
		return varietyDao.findAll();
	}

	@GetMapping("/getVarietyBySeason")
	public List<MasterCropVariety> getVarietyBySeason(@RequestParam MasterSeason season) {
		return varietyDao.findAllBySeason(season);
	}

	@GetMapping("/getVarietyByName")
	public List<MasterCropVariety> getVarietyByName(@RequestParam String variety) {
		return varietyDao.findAllByvariety(variety);
	}

	@PostMapping("/saveVariety")
	public @ResponseBody Iterable<MasterCropVariety> saveVariety(@RequestBody MasterCropVariety variety) {

		varietyDao.save(variety);
		return varietyDao.findAll();
	}

	@PostMapping("/deleteVariety")
	public @ResponseBody Iterable<MasterCropVariety> deleteVariety(@RequestBody MasterCropVariety variety) {
		varietyDao.delete(variety);
		return varietyDao.findAll();
	}

	@GetMapping("getPests")
	public @ResponseBody Iterable<MasterPests> getPests() {
		return masterPestsDao.findAll();
	}

	@PostMapping("/savePests")
	public @ResponseBody Iterable<MasterPests> savePests(@RequestBody MasterPests pests) {
		masterPestsDao.save(pests);
		return masterPestsDao.findAll();
	}

	@PostMapping("/deletePests")
	public @ResponseBody Iterable<MasterPests> deletePests(@RequestBody MasterPests pests) {
		masterPestsDao.delete(pests);
		return masterPestsDao.findAll();
	}

	@GetMapping("/getChemicals")
	public @ResponseBody Iterable<MasterChemicals> getChemicals() {
		return chemicalsDao.findAll();
	}

	@PostMapping("/saveChemicals")
	public @ResponseBody Iterable<MasterChemicals> saveChemicals(@RequestBody MasterChemicals chemicals) {
		chemicalsDao.save(chemicals);
		return chemicalsDao.findAll();
	}

	@PostMapping("/deleteChemicals")
	public @ResponseBody Iterable<MasterChemicals> deleteChemicals(@RequestBody MasterChemicals chemicals) {
		chemicalsDao.delete(chemicals);
		return chemicalsDao.findAll();
	}

	@GetMapping("/getChemicalsWithBrand")
	public @ResponseBody List<ChemicalBrandModel> getChemicalsWithBrand() {
		List<MasterChemicals> chemicals = chemicalsDao.findAll();
		Map<Long, List<MasterChemicalBrandMapping>> brandMappingMap = chemicalBrandDao.findAllByChemicalsIn(chemicals)
				.stream().collect(Collectors.groupingBy(mapping -> mapping.getChemicals().getPk1()));

		// Construct the response using a streamlined approach
		return chemicals.stream().map(chemical -> {
			ChemicalBrandModel chemicalBrandModel = new ChemicalBrandModel();
			chemicalBrandModel.setChemicals(chemical);

			// Get the brand mappings for the current chemical
			List<MasterChemicalBrandMapping> brandMappings = brandMappingMap.getOrDefault(chemical.getPk1(),
					Collections.emptyList());

			// Extract brands from the mappings
			List<MasterChemicalBrands> brands = brandMappings.stream().map(MasterChemicalBrandMapping::getBrands)
					.collect(Collectors.toList());

			chemicalBrandModel.setBrands(brands);
			return chemicalBrandModel;
		}).collect(Collectors.toList());
	}

	@PostMapping("/saveChemicalsWithBrand")
	public @ResponseBody List<ChemicalBrandModel> saveChemicalsWithBrand(@RequestBody ChemicalBrandModel chemicals) {
		MasterChemicals chemical = chemicals.getChemicals();
//		chemical = chemicalsDao.save(chemical);
		List<MasterChemicalBrandMapping> existing = chemicalBrandDao.findAllByChemicals(chemical);
		List<MasterChemicalBrands> incoming = chemicals.getBrands();

		List<MasterChemicalBrands> commonItems = incoming.stream()
				.filter(in -> existing.stream().anyMatch(ex -> ex.getBrands().getPk1().equals(in.getPk1())))
				.collect(Collectors.toList());

		List<MasterChemicalBrandMapping> notInIncoming = existing.stream()
				.filter(ex -> incoming.stream().noneMatch(in -> ex.getBrands().getPk1().equals(in.getPk1())))
				.collect(Collectors.toList());

		incoming.removeAll(commonItems);

		for (MasterChemicalBrands masterChemicalBrands : incoming) {
			MasterChemicalBrandMapping newMapping = new MasterChemicalBrandMapping();
			newMapping.setChemicals(chemical);
			newMapping.setBrands(masterChemicalBrands);
			chemicalBrandDao.save(newMapping);
		}

		for (MasterChemicalBrandMapping masterChemicalBrandMapping : notInIncoming) {
			System.out.println(
					"Items in existing but not in incoming (unique brands): " + masterChemicalBrandMapping.getPk1());

		}

		chemicalBrandDao.deleteAll(notInIncoming);

		return getChemicalsWithBrand();
	}

	@PostMapping("/savePestWithChemicals")
	public @ResponseBody Iterable<DiseaseAndPestModel> savePestWithChemicals(@RequestBody DiseaseAndPestModel pests) {
		MasterPests pest = pests.getPests();
//		pest = masterPestsDao.save(pest);
		List<MasterChemicalPestMapping> existing = pestChemicalDao.findAllByPests(pest);
		List<ChemicalBrandModel> incoming = pests.getChemicals();

		List<ChemicalBrandModel> commonItems = incoming.stream().filter(
				in -> existing.stream().anyMatch(ex -> ex.getChemicals().getPk1().equals(in.getChemicals().getPk1())))
				.collect(Collectors.toList());

		List<MasterChemicalPestMapping> notInIncoming = existing.stream().filter(
				ex -> incoming.stream().noneMatch(in -> ex.getChemicals().getPk1().equals(in.getChemicals().getPk1())))
				.collect(Collectors.toList());

		incoming.removeAll(commonItems);

		for (ChemicalBrandModel chem : incoming) {
			MasterChemicalPestMapping newMapping = new MasterChemicalPestMapping();
			newMapping.setChemicals(chem.getChemicals());
			newMapping.setPests(pest);
			pestChemicalDao.save(newMapping);
		}
		pestChemicalDao.deleteAll(notInIncoming);

		return getDiseasesAndPests();
	}

	@GetMapping("/getBrands")
	public @ResponseBody Iterable<MasterChemicalBrands> getBrands() {
		return brandsDao.findAll();
	}

	@PostMapping("/saveBrands")
	public @ResponseBody Iterable<MasterChemicalBrands> saveBrands(@RequestBody MasterChemicalBrands brands) {
		brandsDao.save(brands);
		return brandsDao.findAll();
	}

	@PostMapping("/deleteBrands")
	public @ResponseBody Iterable<MasterChemicalBrands> deleteBrands(@RequestBody MasterChemicalBrands brands) {
		brandsDao.delete(brands);
		return brandsDao.findAll();
	}

	@GetMapping("/awdDevices")
	public @ResponseBody Page<AwdDevice> getAllAwdDevices(Pageable pageable) {
		return awdDeviceDao.findAll(pageable);
	}

	@PostMapping("/saveAwdDevice")
	public @ResponseBody Iterable<AwdDevice> saveAwdDevice(@RequestBody AwdDevice awdDevice) {
		awdDeviceDao.save(awdDevice);
		return awdDeviceDao.findAll();
	}

	@PostMapping("/deleteAwdDevice")
	public @ResponseBody Iterable<AwdDevice> deleteAwdDevice(@RequestBody AwdDevice awdDevice) {
		awdDeviceDao.delete(awdDevice);
		return awdDeviceDao.findAll();
	}
}
