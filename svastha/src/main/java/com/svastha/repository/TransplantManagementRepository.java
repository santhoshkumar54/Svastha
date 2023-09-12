package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmProjects;
import com.svastha.entity.TransplantManagement;

public interface TransplantManagementRepository extends JpaRepository<TransplantManagement, Long> {

	List<TransplantManagement> findAllByProject(FarmProjects project);

}
