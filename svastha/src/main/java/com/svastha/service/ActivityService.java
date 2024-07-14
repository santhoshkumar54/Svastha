package com.svastha.service;

import org.springframework.stereotype.Service;

@Service
public interface ActivityService {

	public void addActivity(String activityType,String activtySection,String activityForm,long primaryKey,Long userId);
}
