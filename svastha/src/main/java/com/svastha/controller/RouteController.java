package com.svastha.controller;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.svastha.entity.FarmProjects;
import com.svastha.entity.RouteMaster;
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

	@GetMapping("/routes")
	public Page<RouteMaster> getRoutes(@RequestParam(required = false) String routeName,
			@RequestParam(required = false) Integer count, @RequestParam(required = false) Long assignedTo,
			@RequestParam(required = false) Long assignedBy,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
			Pageable pageable) {

		Timestamp startTimestamp = (startDate != null) ? Timestamp.valueOf(startDate.atTime(LocalTime.MIN)) // Appending
																											// 00:00:00
																											// time to
																											// date
				: null;
		Timestamp endTimestamp = (endDate != null) ? Timestamp.valueOf(endDate.atTime(LocalTime.MAX)) // Appends
																										// 23:59:59.999999
																										// time to date
				: null;
		return routes.getRoutesWithFilters(routeName, count, assignedTo, assignedBy, startTimestamp, endTimestamp,
				pageable);
	}

	@GetMapping("/getRoute/{routeId}")
	public @ResponseBody RouteMaster getRouteById(@PathVariable Long routeId) {
		return routes.getRouteById(routeId);
	}

	@PostMapping("/saveRoute")
	public @ResponseBody RouteMaster createRoute(@RequestBody RouteMaster route) {
		return routes.createRoute(route);
	}

	@PostMapping("/deleteRoute")
	public void deleteRoute(@RequestBody RouteMaster route) {
		routes.deleteRoute(route);
	}

	@GetMapping("/getProjectsInRoute")
	public @ResponseBody List<FarmProjects> getProjectsInRoute(@RequestParam Long routeId) {
		return routes.getProjectsInRoute(routeId);
	}

	@GetMapping("/getProjectsWithoutRoute")
	public @ResponseBody Page<FarmProjects> getProjectsWithoutRoute(@RequestParam(required = false) Long yearId,
			@RequestParam(required = false) Long seasonId, @RequestParam(required = false) Long cropId,
			@RequestParam(required = false) String key, @RequestParam(required = false) Long userId,
			@RequestParam(required = false) Long varietyId, @RequestParam(required = false) Long ics,
			@RequestParam(required = false) Long districtId, @RequestParam(required = false) Long thalukId,
			@RequestParam(required = false) Long villageId, Pageable pageable) {
		return routes.getProjectsWithoutRoutes(yearId, seasonId, cropId, key, userId, varietyId, ics, districtId,
				thalukId, villageId, pageable);
	}

	@PostMapping("/saveProjectsInRoute")
	public List<FarmProjects> saveProjectsInRoute(@RequestBody RouteMaster route,
			@RequestBody List<FarmProjects> projects) {
		return routes.saveProjectInRoutes(route, projects);
	}
}
