package com.svastha.model;

import java.io.Serializable;
import java.util.List;

import com.svastha.entity.FarmGrainMarket;
import com.svastha.entity.FarmLiveStock;
import com.svastha.entity.FarmTools;
import com.svastha.entity.FarmWaterSource;
import com.svastha.entity.FarmWorkers;
import com.svastha.entity.Farms;
import com.svastha.entity.LandDetails;

public class FarmModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4467287290082514415L;

	private Farms farm;

	private LandDetails land;

	private List<FarmLiveStock> liveStocks;

	private List<FarmWaterSource> waterSources;

	private List<FarmGrainMarket> grainMarket;

	private FarmWorkers farmWorker;

	private List<FarmTools> tools;

	public Farms getFarm() {
		return farm;
	}

	public void setFarm(Farms farm) {
		this.farm = farm;
	}

	public LandDetails getLand() {
		return land;
	}

	public void setLand(LandDetails land) {
		this.land = land;
	}

	public List<FarmLiveStock> getLiveStocks() {
		return liveStocks;
	}

	public void setLiveStocks(List<FarmLiveStock> liveStocks) {
		this.liveStocks = liveStocks;
	}

	public List<FarmWaterSource> getWaterSources() {
		return waterSources;
	}

	public void setWaterSources(List<FarmWaterSource> waterSources) {
		this.waterSources = waterSources;
	}

	public List<FarmGrainMarket> getGrainMarket() {
		return grainMarket;
	}

	public void setGrainMarket(List<FarmGrainMarket> grainMarket) {
		this.grainMarket = grainMarket;
	}

	public FarmWorkers getFarmWorker() {
		return farmWorker;
	}

	public void setFarmWorker(FarmWorkers farmWorker) {
		this.farmWorker = farmWorker;
	}

	public List<FarmTools> getTools() {
		return tools;
	}

	public void setTools(List<FarmTools> tools) {
		this.tools = tools;
	}
}
