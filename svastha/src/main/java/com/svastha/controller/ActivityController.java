package com.svastha.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.svastha.entity.ActivityTrackers;
import com.svastha.entity.FarmProjects;
import com.svastha.entity.Farms;
import com.svastha.repository.ActivityTrackersRepository;
import com.svastha.repository.FarmProjectRepository;
import com.svastha.repository.FarmRepository;

@RestController
public class ActivityController {

	@Autowired
	private FarmProjectRepository projectDao;

	@Autowired
	private FarmRepository farmDao;

	@Autowired
	private ActivityTrackersRepository activityDao;

	@GetMapping("/getActivityForDate")
	public @ResponseBody List<ActivityTrackers> assignProjects(@RequestParam(required = false) Long userId,
			@RequestParam(required = false) Date startDate, @RequestParam(required = false) Date endDate,
			@RequestParam(required = false) String section, @RequestParam(required = false) String id,
			Pageable pageable) {

		if (id == null) {

			return activityDao.findWithFilters(section, startDate, endDate, userId, pageable).getContent();
		} else {
			List<ActivityTrackers> activities = new ArrayList<>();
			String result = id.replaceFirst("^0+(?!$)", "");

			activities.addAll(activityDao.findWithId(Long.parseLong(result), "Farmer Data", pageable).getContent());

			Farms f = farmDao.findById(Long.parseLong(result)).get();
			List<FarmProjects> projects = projectDao.findAllByFarm(f);

			for (FarmProjects project : projects) {
				if (project.getProjectType().getProjectType().equals("MRL")) {
					activities.addAll(
							activityDao.findWithId(project.getPk1(), "MRL Project", pageable).getContent());
				} else if (project.getProjectType().getProjectType().equals("Organic")) {
					activities.addAll(activityDao.findWithId(project.getPk1(), "Organic Project", pageable)
							.getContent());

				}
			}
			return activities;
		}
	}

}
