package com.svastha.controller;

import java.sql.Timestamp;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.svastha.entity.FarmProjects;
import com.svastha.entity.Users;
import com.svastha.model.AssignmentModel;
import com.svastha.repository.FarmProjectRepository;
import com.svastha.repository.UserRepository;

@RestController
public class AssignmentController {

	@Autowired
	private FarmProjectRepository projectDao;
	
	@Autowired
	private UserRepository userDao;

	@PostMapping("/saveAssignment")
	public void assignProjects(@RequestBody AssignmentModel model) {
		for (FarmProjects project : model.getProjects()) {
			Users u = userDao.findByPk1(model.getUser().getPk1());
			project.setAssignedTo(u);
			Users up = userDao.findByPk1(model.getUpdatedUser().getPk1());
			project.setAssignedBy(up);
			project.setAssignedDt(Timestamp.from(Instant.now()));
			projectDao.save(project);
		}
	}
}
