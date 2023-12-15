package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.svastha.entity.MasterChemicals;

@Repository
public interface MasterChemicalRepository extends JpaRepository<MasterChemicals, Long> {

	List<MasterChemicals> findAllByStatus(String status);
}
