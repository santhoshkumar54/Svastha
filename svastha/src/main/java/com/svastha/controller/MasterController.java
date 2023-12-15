package com.svastha.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.svastha.entity.Fertilizers;
import com.svastha.entity.LiveStock;
import com.svastha.entity.MasterChemicalBrandMapping;
import com.svastha.entity.MasterChemicalBrands;
import com.svastha.entity.MasterChemicalPestMapping;
import com.svastha.entity.MasterChemicals;
import com.svastha.entity.MasterCropStage;
import com.svastha.entity.MasterGrainMarket;
import com.svastha.entity.MasterPests;
import com.svastha.entity.MasterSoiltype;
import com.svastha.entity.MasterTools;
import com.svastha.entity.TransplantMethods;
import com.svastha.entity.WaterSource;
import com.svastha.model.ChemicalBrandModel;
import com.svastha.model.DiseaseAndPestModel;
import com.svastha.model.MasterProjectModel;
import com.svastha.repository.FertilizerRepository;
import com.svastha.repository.LiveStockRepository;
import com.svastha.repository.MasterChemicalBrandMappingRepository;
import com.svastha.repository.MasterChemicalRepository;
import com.svastha.repository.MasterCropRepository;
import com.svastha.repository.MasterCropStageRepository;
import com.svastha.repository.MasterDiseasesAndPestsRepository;
import com.svastha.repository.MasterGrainMarketRepository;
import com.svastha.repository.MasterPestChemicalMappingRepository;
import com.svastha.repository.MasterSeasonRepository;
import com.svastha.repository.MasterSoilTypeRepository;
import com.svastha.repository.MasterToolsRepository;
import com.svastha.repository.MasterYearRepository;
import com.svastha.repository.TransplantMethodRepository;
import com.svastha.repository.WaterSourceRepository;

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
	private FertilizerRepository fertilizerDao;

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
	private MasterPestChemicalMappingRepository pestChemicalDao;

	@Autowired
	private MasterChemicalBrandMappingRepository chemicalBrandDao;

//	@Autowired
//	private MasterChemicalBrandRepository brandsDao;

	@GetMapping(path = "/liveStocks")
	public @ResponseBody Iterable<LiveStock> getLiveStocks() {

		return liveStockDao.findAll();
	}

	@GetMapping(path = "/waterSources")
	public @ResponseBody Iterable<WaterSource> getWaterSources() {

		return waterSourceDao.findAll();
	}

	@GetMapping(path = "/soiltype")
	public @ResponseBody Iterable<MasterSoiltype> getSoiltype() {

		return soiltypeDao.findAll();
	}

	@GetMapping(path = "/tools")
	public @ResponseBody Iterable<MasterTools> getTools() {

		return toolsDao.findAll();
	}

	@GetMapping(path = "/grainmarkets")
	public @ResponseBody Iterable<MasterGrainMarket> getGrainMarkets() {

		return marketDao.findAll();
	}

	@GetMapping(path = "/fertilizers")
	public @ResponseBody Iterable<Fertilizers> getFertilizers() {

		return fertilizerDao.findAll();
	}

	@GetMapping(path = "/cropStage")
	public @ResponseBody Iterable<MasterCropStage> getCropStage() {

		return masterStageDao.findAll();
	}

	@GetMapping(path = "/transplantMethod")
	public @ResponseBody Iterable<TransplantMethods> getTransplantMethods() {

		return transplantMethodDao.findAll();
	}

	@GetMapping(path = "/getProjectMaster")
	public @ResponseBody MasterProjectModel getMasterProject() {
		MasterProjectModel master = new MasterProjectModel();
		master.setYear(yearDao.findAll());
		master.setSeason(seasonDao.findAll());
		master.setCrop(masterCropDao.findAll());
		return master;
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
	public @ResponseBody Iterable<MasterChemicals> getChemicals() {

		return chemicalsDao.findAllByStatus(ChemicalStatusEnum.restricted.toString());
	}

}
