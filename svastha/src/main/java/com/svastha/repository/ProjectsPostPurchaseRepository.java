package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmProjects;
import com.svastha.entity.ProjectPostPurchase;

public interface ProjectsPostPurchaseRepository extends JpaRepository<ProjectPostPurchase, Long> {

	List<ProjectPostPurchase> findAllByProjects(FarmProjects projects);

	List<ProjectPostPurchase> findByProjectsIn(List<FarmProjects> projects);
}
