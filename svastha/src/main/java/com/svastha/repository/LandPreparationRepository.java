package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmProjects;
import com.svastha.entity.LandPreparation;

public interface LandPreparationRepository extends JpaRepository<LandPreparation, Long> {

	List<LandPreparation> findAllByProject(FarmProjects project);

}
