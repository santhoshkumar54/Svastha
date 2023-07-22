package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.CropType;
import com.example.entity.Crops;
import com.example.repository.CropRepository;
import com.example.repository.CropTypeRepository;

@RestController
public class CropController {

	@Autowired
	private CropRepository cropDao;
	
	@Autowired
	private CropTypeRepository cropTypeDao;
	
	@GetMapping("getCrops")
	public @ResponseBody Iterable<Crops> getCrops()
	{
		return cropDao.findAll();
	}
	
	@GetMapping("getCropTypes")
	public @ResponseBody Iterable<CropType> getCrops( @RequestParam String crop )
	{
		return cropTypeDao.findAllByCrop(Integer.parseInt(crop));
	}
}
