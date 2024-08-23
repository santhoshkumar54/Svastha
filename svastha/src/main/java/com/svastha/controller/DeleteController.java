package com.svastha.controller;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.svastha.entity.FarmProjects;
import com.svastha.entity.Farms;
import com.svastha.entity.Users;
import com.svastha.model.DeleteModel;
import com.svastha.model.DeleteProjectModel;
import com.svastha.repository.FarmProjectRepository;
import com.svastha.repository.FarmRepository;
import com.svastha.repository.UserRepository;

@RestController
public class DeleteController {

	@Autowired
	private FarmRepository farmDao;

	@Autowired
	private UserRepository userDao;

	@Autowired
	private FarmProjectRepository projectDao;

	@PostMapping("/deleteFarmer")
	public void deleteFarmers(@RequestBody DeleteModel model) {
		Farms farm = farmDao.findById(model.getFarm().getPk1()).get();
		Users user = userDao.findByPk1(model.getUser().getPk1());
		farm.setDeleted(true);

		farm.setDeletedBy(user);
		farm.setDeletedDt(Timestamp.from(Instant.now()));
		farmDao.save(farm);

		List<FarmProjects> projects = projectDao.findAllByFarm(farm);
		for (FarmProjects project : projects) {
			project.setDeleted(true);
			project.setDeletedBy(user);
			project.setDeletedDt(Timestamp.from(Instant.now()));
			projectDao.save(project);
		}
	}

	@PostMapping("/deleteProject")
	public void deleteFarmers(@RequestBody DeleteProjectModel model) {
		FarmProjects project = projectDao.findById(model.getProject().getPk1()).get();
		Users user = userDao.findByPk1(model.getUser().getPk1());
		project.setDeleted(true);
		project.setDeletedBy(user);
		project.setDeletedDt(Timestamp.from(Instant.now()));
		projectDao.save(project);
	}
}
