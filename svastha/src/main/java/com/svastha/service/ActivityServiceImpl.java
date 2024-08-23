package com.svastha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.svastha.entity.ActivityTracker;
import com.svastha.entity.Users;
import com.svastha.repository.ActivityTrackerRepository;
import com.svastha.repository.UserRepository;

@Service
public class ActivityServiceImpl {

	@Autowired
	private ActivityTrackerRepository activityDao;

	@Autowired
	private UserRepository userDao;

	public void addActivity(String activityType, String activtySection, String activityForm, long primaryKey,
			Long userId, int count) {
		Users user = userDao.findByPk1(userId);
		ActivityTracker activity = new ActivityTracker();

		activity.setActivityType(activityType);
		activity.setActivtySection(activtySection);
		activity.setActivityForm(activityForm);
		activity.setPrimaryKey(primaryKey);
		activity.setUser(user);
		activity.setCount(count);
		activityDao.save(activity);
	}
}
