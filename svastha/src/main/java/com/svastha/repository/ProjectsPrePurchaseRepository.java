package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmProjects;
import com.svastha.entity.ProjectPrePurchase;

public interface ProjectsPrePurchaseRepository extends JpaRepository<ProjectPrePurchase, Long> {

	List<ProjectPrePurchase> findAllByProjects(FarmProjects projects);

}
