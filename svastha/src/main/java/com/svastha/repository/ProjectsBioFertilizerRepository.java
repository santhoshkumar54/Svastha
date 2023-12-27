package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmProjects;
import com.svastha.entity.ProjectBioFertilizers;

public interface ProjectsBioFertilizerRepository extends JpaRepository<ProjectBioFertilizers, Long> {

	List<ProjectBioFertilizers> findAllByProjects(FarmProjects projects);

}
