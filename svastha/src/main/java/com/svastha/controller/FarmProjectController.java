package com.svastha.controller;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.svastha.entity.FarmPlots;
import com.svastha.entity.FarmProjects;
import com.svastha.entity.Farms;
import com.svastha.entity.MasterCropStage;
import com.svastha.entity.MasterCropVariety;
import com.svastha.entity.MasterSeason;
import com.svastha.entity.NurseryManagement;
import com.svastha.entity.ProjectBioFertilizers;
import com.svastha.entity.ProjectImages;
import com.svastha.entity.ProjectPlots;
import com.svastha.entity.ProjectSowingData;
import com.svastha.entity.ProjectSyntheticFertilizers;
import com.svastha.entity.Users;
import com.svastha.model.ImageModel;
import com.svastha.model.ProjectExportModel;
import com.svastha.model.ProjectModel;
import com.svastha.repository.FarmPlotsRepository;
import com.svastha.repository.FarmProjectRepository;
import com.svastha.repository.FarmRepository;
import com.svastha.repository.MasterCropStageRepository;
import com.svastha.repository.MasterCropVarietyRepository;
import com.svastha.repository.MasterProjectTypeRepository;
import com.svastha.repository.MasterSeasonRepository;
import com.svastha.repository.NurseryManagementRepository;
import com.svastha.repository.ProjectImagesRepository;
import com.svastha.repository.ProjectsPlotsRepository;
import com.svastha.repository.ProjectsSowingDataRepository;
import com.svastha.repository.UserRepository;
import com.svastha.service.ExcelWriter;
import com.svastha.service.FilesStorageService;
import com.svastha.service.GpsService;
import com.svastha.util.LocationDTO;
import com.svastha.util.PlotsDTO;
import com.svastha.util.ProjectsDTO;

@RestController
public class FarmProjectController {

	@Autowired
	private FarmProjectRepository projectDao;

	@Autowired
	private NurseryManagementRepository nurseryDao;

	@Autowired
	private FarmRepository farmDao;

	@Autowired
	private FarmPlotsRepository plotsDao;

	@Autowired
	private ProjectsPlotsRepository projectPlotsDao;

	@Autowired
	private MasterCropVarietyRepository varietyDao;

	@Autowired
	private UserRepository userDao;

	@Autowired
	private ProjectsSowingDataRepository sowingDao;

	@Autowired
	private MasterCropStageRepository stageDao;

	@Autowired
	private GpsService gpsService;

	@Autowired
	FilesStorageService storageService;

	@Autowired
	private ProjectImagesRepository imageDao;

	@Autowired
	private MasterProjectTypeRepository projectTypeDao;

	@Autowired
	private MasterSeasonRepository masterSeasonDao;

	@Autowired
	private ExcelWriter excel;

	private static String PROJECT_TYPE = "MRL";

	@GetMapping("/projects")
	public @ResponseBody Page<FarmProjects> getAllProjects(@RequestParam(required = false) Long yearId,
			@RequestParam(required = false) Long seasonId, @RequestParam(required = false) Long cropId,
			@RequestParam(required = false) String key, @RequestParam(required = false) Long userId,
			@RequestParam(required = false) Long varietyId, @RequestParam(required = false) Long ics,
			Pageable pageable) {
		Long projectTypePk1 = projectTypeDao.findByProjectType(PROJECT_TYPE).getPk1();
		Page<FarmProjects> projects = projectDao.findWithFilters(yearId, seasonId, cropId, key, userId, projectTypePk1,
				varietyId, ics, "APPROVED", pageable);
		return projects;
	}

	@GetMapping("/approvals")
	public @ResponseBody Page<FarmProjects> getAllWaitingProjects(@RequestParam(required = false) Long yearId,
			@RequestParam(required = false) Long seasonId, @RequestParam(required = false) Long cropId,
			@RequestParam(required = false) String key, @RequestParam(required = false) Long userId,
			@RequestParam(required = false) Long varietyId, @RequestParam(required = false) Long ics,
			Pageable pageable) {
		Page<FarmProjects> projects = projectDao.findWithFilters(yearId, seasonId, cropId, key, userId, null, varietyId,
				ics, "WAITING", pageable);
		return projects;
	}

	@GetMapping("/exportProjects")
	public @ResponseBody String exportProjects(@RequestParam(required = false) Long yearId,
			@RequestParam(required = false) Long seasonId, @RequestParam(required = false) Long cropId,
			@RequestParam(required = false) String key, @RequestParam(required = false) Long userId,
			@RequestParam(required = false) Long varietyId, @RequestParam(required = false) Long ics,
			@RequestParam String email) {
		try {
			System.out.println("year-" + yearId + " season-" + seasonId + " crop-" + cropId + " key-" + key + " user-"
					+ userId + " email-" + email);
			Long projectTypePk1 = projectTypeDao.findByProjectType(PROJECT_TYPE).getPk1();
			excel.startProjectExport(yearId, seasonId, cropId, key, userId, email, projectTypePk1, varietyId, ics);
			return "The exported data will be sent to your email.";
		} catch (Exception e) {
			return "Failed to trigger batch job: " + e.getMessage();
		}
	}

	@GetMapping("/listProjects")
	public @ResponseBody Iterable<FarmProjects> getProjectsUserId(@RequestParam Long userId) {
		Long projectTypePk1 = projectTypeDao.findByProjectType(PROJECT_TYPE).getPk1();
		return projectDao.findWithFilters(null, null, null, null, userId, projectTypePk1, null, null);
	}

	@GetMapping("/getProject")
	public @ResponseBody ProjectModel getFarmById(@RequestParam Long projectId) {
		ProjectModel projectModel = new ProjectModel();
		FarmProjects f = projectDao.findById(projectId).get();
		projectModel.setFarm(f);
		List<NurseryManagement> nursery = nurseryDao.findAllByProject(f);
		projectModel.setNursery(nursery);
		return projectModel;
	}
	
	@GetMapping("/getExamples")
	public void findExample()
	{
		excel.startProjectExportV2(null, null, null, null, 5L, "smsanthoshkumar@ymail.com", null, null, null);
	}

	@GetMapping("/getFarmerPlots")
	public @ResponseBody List<PlotsDTO> getFarmerPlots(@RequestParam Long farmerId,
			@RequestParam(required = false) String location) {

		Farms f = farmDao.findById(farmerId).get();
		List<FarmPlots> plots = plotsDao.findAllPlotsByFarm(f);
		List<PlotsDTO> allPlots = new ArrayList<>();
		for (FarmPlots plot : plots) {
			PlotsDTO p = new PlotsDTO();
			p.setId(plot.getPk1());
			p.setNumber(plot.getPlotNumber());
			if (plot.getLocation() == null || plot.getLocation().isBlank() || plot.getLocation().isEmpty()
					|| location == null || location.isBlank() || location.isEmpty()) {
				p.setUrlName("Plot location not available");
				p.setUrl(null);
			} else {
				try {
					LocationDTO currentLoc = new Gson().fromJson(location, LocationDTO.class);
					double currentLat = currentLoc.getCoords().getLatitude();
					double currentLon = currentLoc.getCoords().getLongitude();
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
					String plotDetails = distance + "m away towards " + bearing + " " + direction + " direction.";
					p.setUrlName(plotDetails);
					String url = gpsService.generateURL(plotLat, plotLon, plot.getPlotNumber());
					p.setUrl(url);
				} catch (Exception ex) {
					p.setUrlName("Problem fetching plot location");
					p.setUrl(null);
				}
			}
			allPlots.add(p);
		}

		return allPlots;
	}

	public String getSowingDate(FarmProjects projects, FarmPlots plot) {
		Sort sort = Sort.by(Sort.Direction.ASC, "sowingDate");
		List<ProjectSowingData> sowings = sowingDao.findAllByProjectsAndPlots(projects, plot, sort);
		if (!sowings.isEmpty()) {

			return sowings.get(0).getSowingDate();
		} else {
			return "";
		}
	}

	public Date convertToDate(String dateString) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/M/dd");

		try {
			Date date = dateFormat.parse(dateString);

			return date;
		} catch (ParseException e) {
			System.out.println("sowing date ex:" + e);

			return new Date();
		}
	}

	public String getStage(Date d) {
		long differenceInMillis = new Date().getTime() - d.getTime();

		int differenceInDays = Math.toIntExact(TimeUnit.MILLISECONDS.toDays(differenceInMillis));

		List<MasterCropStage> stage = stageDao.findByCustomQuery(differenceInDays);

		if (!stage.isEmpty()) {
			return stage.get(0).getCropStage();

		}

		return "";
	}

	@GetMapping("/getProjectPlots")
	public @ResponseBody List<PlotsDTO> getProjectPlots(@RequestParam Long projectId,
			@RequestParam(required = false) String location) {

		FarmProjects fPlots = projectDao.findById(projectId).get();
		List<ProjectPlots> plots = projectPlotsDao.findAllPlotsByProject(fPlots);
		List<PlotsDTO> allPlots = new ArrayList<>();
		for (ProjectPlots projectPlot : plots) {
			PlotsDTO p = new PlotsDTO();
			FarmPlots plot = projectPlot.getPlots();
			p.setId(plot.getPk1());
			p.setNumber(plot.getPlotNumber());
			if (plot.getLocation() == null || plot.getLocation().isBlank() || plot.getLocation().isEmpty()
					|| location == null || location.isBlank() || location.isEmpty()) {
				p.setUrlName("Plot No: " + plot.getPlotNumber());
				p.setUrl(null);
			} else {
				try {
					LocationDTO currentLoc = new Gson().fromJson(location, LocationDTO.class);
					double currentLat = currentLoc.getCoords().getLatitude();
					double currentLon = currentLoc.getCoords().getLongitude();
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
					String plotDetails = "Plot No: " + plot.getPlotNumber() + " - " + distance + "m away towards "
							+ bearing;
					p.setUrlName(plotDetails);
					String url = gpsService.generateURL(plotLat, plotLon, plot.getPlotNumber());
					p.setUrl(url);
				} catch (Exception ex) {
					p.setUrlName("Plot No: " + plot.getPlotNumber());
					p.setUrl(null);
				}
			}
			String sowingDate = getSowingDate(fPlots,plot);
			p.setSowingDate(sowingDate);
			p.setCropStage(getStage(convertToDate(sowingDate)));

			allPlots.add(p);
		}
		return allPlots;
	}

	@PostMapping("/addProject")
	public @ResponseBody FarmProjects saveFarm(@RequestBody ProjectsDTO projectsDTO) {
		try {
			FarmProjects f = projectsDTO.getProjects();
			f.setStatus("WAITING");
			f.setAssignedTo(f.getCreatedBy());

			f = projectDao.save(f);
			
			List<ProjectSowingData> sowings = new ArrayList<>();
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
				if(f.getSowingDate()!=null)
				{
					ProjectSowingData sowing = new ProjectSowingData();
					sowing.setCreatedBy(f.getCreatedBy());
					sowing.setPlots(fp);
					sowing.setProjects(f);
					sowing.setSowingDate(f.getSowingDate());
					sowing.setVariety(f.getVariety());
					sowings.add(sowing);
				}
			}
			projectPlotsDao.saveAll(plotsentity);
			sowingDao.saveAll(sowings);
			return f;

		} catch (Exception e) {
			throw e;
		}
	}

	@GetMapping(path = "/getCropVariety")
	public @ResponseBody Iterable<MasterCropVariety> getCropVariety(@RequestParam Long projectId) {
		FarmProjects p = projectDao.findById(projectId).get();

		return varietyDao.findAllBySeason(p.getSeason());
	}

	@GetMapping(path = "/getCropVarietyBySeason")
	public @ResponseBody Iterable<MasterCropVariety> getCropVarietyBySeason(@RequestParam Long seasonId) {
		MasterSeason s = masterSeasonDao.findById(seasonId).get();
		return varietyDao.findAllBySeason(s);
	}

	public static final String SEPARATOR = FileSystems.getDefault().getSeparator();

	@PostMapping("/uploadProjectImages")
	public @ResponseBody String uploadPhoto(@RequestParam MultipartFile[] file, @RequestParam String projectId,
			@RequestParam String userId) {
		Long fid = Long.parseLong(projectId);
		Long uid = Long.parseLong(userId);
		FarmProjects f = projectDao.findById(fid).get();
		Users u = userDao.findById(uid).get();

		String folderPath = SEPARATOR + "projectImage" + SEPARATOR + projectId;
		Path p = storageService.createFolder(folderPath);

		for (MultipartFile multipartFile : file) {
			ProjectImages i = new ProjectImages();
			i.setCreatedBy(u);
			i.setProject(f);
			String filePath = storageService.save(multipartFile, p);
			i.setFileName(filePath);
			i.setPath(p.toString());
			i.setImageUrl("/farmer/images" + folderPath + SEPARATOR + filePath);
			imageDao.save(i);
		}
		return "Success";
	}

	@PostMapping("/uploadProjectImagesV2")
	public @ResponseBody String uploadPhoto(@RequestParam MultipartFile[] file, @RequestParam String projectId,
			@RequestParam String userId,@RequestParam(required = false) String formName, @RequestParam(required = false) Long primaryKey) {
		Long fid = Long.parseLong(projectId);
		Long uid = Long.parseLong(userId);
		FarmProjects f = projectDao.findById(fid).get();
		Users u = userDao.findById(uid).get();

		String folderPath = SEPARATOR + "projectImage" + SEPARATOR + fid;
		Path p = storageService.createFolder(folderPath);

		for (MultipartFile multipartFile : file) {
			ProjectImages i = new ProjectImages();
			i.setCreatedBy(u);
			i.setProject(f);
			String filePath = storageService.save(multipartFile, p);
			i.setFileName(filePath);
			i.setPath(p.toString());
			if (formName != null && primaryKey != null) {
				i.setPrimaryKey(primaryKey);
				i.setFormName(formName);
			}
			i.setImageUrl("/farmer/images" + folderPath + SEPARATOR + filePath);
			imageDao.save(i);
		}
		return "Success";
	}

	@GetMapping("/getProjectImages")
	public @ResponseBody List<String> getPhotos(@RequestParam Long projectId,
			@RequestParam(required = false) Long primaryKey, @RequestParam(required = false) String formName) {
		FarmProjects f = projectDao.findById(projectId).get();
		List<ProjectImages> images = new ArrayList<ProjectImages>();
		if (formName != null && primaryKey != null) {
			images = imageDao.findAllImagesByProjectAndFormNameAndPrimaryKey(f,formName,primaryKey);
		} else {
			images = imageDao.findAllImagesByProject(f);
		}
		List<String> photos = new ArrayList<>();
		for (ProjectImages image : images) {
			photos.add(image.getImageUrl());
		}
		return photos;
	}

	@PostMapping("/approveProject")
	public @ResponseBody String approveProject(@RequestParam Long projectId, @RequestParam String approvalStatus,
			@RequestParam Long userId) {
		FarmProjects project = projectDao.findById(projectId).get();
		Users u = userDao.findById(userId).get();
		project.setStatus(approvalStatus);
		project.setUpdatedBy(u);
		projectDao.save(project);
		return "Success";
	}

	@Scheduled(cron = "0 15 2 * * *")
	public void updateCompletion() {
		projectDao.updateFarmProjectCompletionPercentage();
	}
}