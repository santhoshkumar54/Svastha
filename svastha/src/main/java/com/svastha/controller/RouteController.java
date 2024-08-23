package com.svastha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.svastha.service.RouteService;

@RestController
public class RouteController {

	@Autowired
	private RouteService routes;
	
	@GetMapping("/buildRoutes")
	public void buildRoutes() {
		routes.oneTimeRouteCreation();
	}
	
	@GetMapping("/mapLocation")
	public void villageLocationMapper(@RequestParam Long thalukId) {
		routes.villageLocationMapper(thalukId);
	}
	
	
}
