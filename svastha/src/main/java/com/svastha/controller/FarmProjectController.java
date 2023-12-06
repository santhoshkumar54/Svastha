package com.svastha.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.svastha.dto.LocationDTO;
import com.svastha.dto.PlotsDTO;
import com.svastha.dto.ProjectsDTO;
import com.svastha.entity.FarmPlots;
import com.svastha.entity.FarmProjects;
import com.svastha.entity.Farms;
import com.svastha.entity.LandPreparation;
import com.svastha.entity.NurseryManagement;
import com.svastha.entity.ProjectPlots;
import com.svastha.entity.TransplantManagement;
import com.svastha.entity.Users;
import com.svastha.model.ProjectModel;
import com.svastha.repository.FarmPlotsRepository;
import com.svastha.repository.FarmProjectRepository;
import com.svastha.repository.FarmRepository;
import com.svastha.repository.LandPreparationRepository;
import com.svastha.repository.NurseryManagementRepository;
import com.svastha.repository.ProjectsPlotsRepository;
import com.svastha.repository.TransplantManagementRepository;
import com.svastha.repository.UserRepository;
import com.svastha.service.GpsService;

@RestController
public class FarmProjectController {

	@Autowired
	private FarmProjectRepository projectDao;

	@Autowired
	private LandPreparationRepository landDao;

	@Autowired
	private NurseryManagementRepository nurseryDao;

	@Autowired
	private TransplantManagementRepository transplantDao;

	@Autowired
	private FarmRepository farmDao;

	@Autowired
	private FarmPlotsRepository plotsDao;

	@Autowired
	private ProjectsPlotsRepository projectPlotsDao;

	@Autowired
	private UserRepository userDao;

	@Autowired
	private GpsService gpsService;

	@GetMapping("/projects")
	public @ResponseBody Iterable<FarmProjects> getAllProjects() {
		return projectDao.findAll();
	}

	@GetMapping("/listProjects")
	public @ResponseBody Iterable<FarmProjects> getProjectsUserId(@RequestParam Long userId) {
		Users u = userDao.findByPk1(userId);
		return projectDao.findByCreatedBy(u);
	}

	@GetMapping("/getProject")
	public @ResponseBody ProjectModel getFarmById(@RequestParam Long projectId) {
		ProjectModel projectModel = new ProjectModel();
		FarmProjects f = projectDao.findById(projectId).get();
		projectModel.setFarm(f);
		List<LandPreparation> land = landDao.findAllByProject(f);
		List<NurseryManagement> nursery = nurseryDao.findAllByProject(f);
		List<TransplantManagement> transplant = transplantDao.findAllByProject(f);
		projectModel.setLandPreparation(land);
		projectModel.setNursery(nursery);
		projectModel.setTransplant(transplant);
		return projectModel;
	}

	@GetMapping("/getFarmerPlots")
	public @ResponseBody List<PlotsDTO> getFarmerPlots(@RequestParam Long farmerId, @RequestParam String location) {
		LocationDTO currentLoc = new Gson().fromJson(location, LocationDTO.class);
		double currentLat = currentLoc.getCoords().getLatitude();
		double currentLon = currentLoc.getCoords().getLongitude();
		Farms f = farmDao.findById(farmerId).get();
		List<FarmPlots> plots = plotsDao.findAllPlotsByFarm(f);
		List<PlotsDTO> allPlots = new ArrayList<>();
		for (FarmPlots plot : plots) {
			PlotsDTO p = new PlotsDTO();
			p.setId(plot.getPk1());
			p.setNumber(plot.getPlotNumber());
			LocationDTO loc = new Gson().fromJson(plot.getLocation(), LocationDTO.class);
			p.setLocation(plot.getLocation());
			double plotLat = loc.getCoords().getLatitude();
			double plotLon = loc.getCoords().getLongitude();
			p.setLat(loc.getCoords().getLatitude());
			p.setLon(loc.getCoords().getLongitude());
			long distance = gpsService.calculateDistance(currentLat, currentLon, plotLat, plotLon);
			long bearing = gpsService.calculateBearing(currentLat, currentLon, plotLat, plotLon);
			p.setDistance(distance);
			p.setBearing(bearing);
			String direction = gpsService.calculateDirection(bearing);
			String plotDetails = "PLot no: " + plot.getPlotNumber() + " in " + distance + "m away towards " + bearing
					+ " " + direction + " direction.";
			p.setUrlName(plotDetails);
			String url = gpsService.generateURL(plotLat, plotLon, plot.getPlotNumber());
			p.setUrl(url);
			allPlots.add(p);
		}

		return allPlots;
	}

	@GetMapping("/getProjectPlots")
	public @ResponseBody List<PlotsDTO> getProjectPlots(@RequestParam Long projectId, @RequestParam String location) {
		LocationDTO currentLoc = new Gson().fromJson(location, LocationDTO.class);
		double currentLat = currentLoc.getCoords().getLatitude();
		double currentLon = currentLoc.getCoords().getLongitude();
		FarmProjects fPlots = projectDao.findById(projectId).get();
		List<ProjectPlots> plots = projectPlotsDao.findAllPlotsByProject(fPlots);
		List<PlotsDTO> allPlots = new ArrayList<>();
		for (ProjectPlots projectPlot : plots) {
			PlotsDTO p = new PlotsDTO();
			FarmPlots plot = projectPlot.getPlots();
			p.setId(plot.getPk1());
			p.setNumber(plot.getPlotNumber());
			LocationDTO loc = new Gson().fromJson(plot.getLocation(), LocationDTO.class);
			p.setLocation(plot.getLocation());
			double plotLat = loc.getCoords().getLatitude();
			double plotLon = loc.getCoords().getLongitude();
			p.setLat(loc.getCoords().getLatitude());
			p.setLon(loc.getCoords().getLongitude());
			long distance = gpsService.calculateDistance(currentLat, currentLon, plotLat, plotLon);
			long bearing = gpsService.calculateBearing(currentLat, currentLon, plotLat, plotLon);
			p.setDistance(distance);
			p.setBearing(bearing);
			String direction = gpsService.calculateDirection(bearing);
			String plotDetails = "Plot no: " + plot.getPlotNumber() + " in " + distance + "m away towards " + bearing
					+ " " + direction + " direction.";
			p.setUrlName(plotDetails);
			String url = gpsService.generateURL(plotLat, plotLon, plot.getPlotNumber());
			p.setUrl(url);
			allPlots.add(p);
		}
		return allPlots;
	}

	@PostMapping("/addProject")
	public @ResponseBody FarmProjects saveFarm(@RequestBody ProjectsDTO projectsDTO) {
		try {
			FarmProjects f = projectDao.save(projectsDTO.getProjects());
			Iterable<PlotsDTO> plots = projectsDTO.getPlots();
			List<ProjectPlots> plotsentity = new ArrayList<>();
			for (PlotsDTO plot : plots) {
				ProjectPlots p = new ProjectPlots();
				p.setCreatedBy(f.getCreatedBy());
				p.setDisabled(false);
				FarmPlots fp = plotsDao.findById(plot.getId()).get();
				p.setPlots(fp);
				p.setProject(f);
				plotsentity.add(p);
			}
			projectPlotsDao.saveAll(plotsentity);
			return f;

		} catch (Exception e) {
			throw e;
		}
	}

	@PostMapping("addLandPreparation")
	public @ResponseBody String saveLandPreparations(@RequestBody Iterable<LandPreparation> landPreparation) {
		try {
			landDao.saveAll(landPreparation);
			return "Success";

		} catch (Exception e) {
			throw e;
		}
	}

	@PostMapping("addNurseryManagement")
	public @ResponseBody String saveNurseryManagement(@RequestBody Iterable<NurseryManagement> nurseryManagement) {
		try {
			nurseryDao.saveAll(nurseryManagement);
			return "Success";

		} catch (Exception e) {
			throw e;
		}
	}

	@PostMapping("addTransplantManagement")
	public @ResponseBody String saveTransplantManagement(
			@RequestBody Iterable<TransplantManagement> transplantManagement) {
		try {
			transplantDao.saveAll(transplantManagement);
			return "Success";

		} catch (Exception e) {
			throw e;
		}
	}
}