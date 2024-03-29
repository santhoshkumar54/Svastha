package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmProjects;
import com.svastha.entity.ProjectSeedTreatment;

public interface ProjectsSeedTreatmentRepository extends JpaRepository<ProjectSeedTreatment, Long> {

	ProjectSeedTreatment findSeedTreatmentByProjects(FarmProjects projects);

	List<ProjectSeedTreatment> findByProjectsIn(List<FarmProjects> projects);

}
