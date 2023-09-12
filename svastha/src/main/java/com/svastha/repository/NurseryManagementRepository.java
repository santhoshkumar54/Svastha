package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmProjects;
import com.svastha.entity.NurseryManagement;

public interface NurseryManagementRepository extends JpaRepository<NurseryManagement, Long> {

	List<NurseryManagement> findAllByProject(FarmProjects project);

}
