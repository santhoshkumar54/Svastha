package com.svastha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.svastha.entity.ViewFarmerThaluk;
import com.svastha.entity.viewFarmerCreatedweek;
import com.svastha.entity.viewFarmerProjectByuser;
import com.svastha.repository.ViewFarmerCreatedWeekRepository;
import com.svastha.repository.ViewFarmerProjectByuserRepository;
import com.svastha.repository.ViewFarmerThalukRepository;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

	@Autowired
    private ViewFarmerThalukRepository thalukDao;
	
	@Autowired
    private ViewFarmerCreatedWeekRepository createdDao;
	
	@Autowired
    private ViewFarmerProjectByuserRepository projectDao;
	
	@GetMapping("/farmerThaluk")
    public List<ViewFarmerThaluk> getFarmerThaluk()
    {
		return thalukDao.findAll();
    }
	
	@GetMapping("/farmerCreatedWeek")
    public List<viewFarmerCreatedweek> getFarmerCreatedWeek()
    {
		return createdDao.findAll();
    }
	
	@GetMapping("/getFarmerProject")
    public List<viewFarmerProjectByuser> getFarmerProject()
    {
		return projectDao.findAll();
    }
}
