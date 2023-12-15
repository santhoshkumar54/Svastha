package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.svastha.entity.MasterChemicalPestMapping;
import com.svastha.entity.MasterPests;

@Repository
public interface MasterPestChemicalMappingRepository extends JpaRepository<MasterChemicalPestMapping, Long>{
	
     List<MasterChemicalPestMapping> findAllByPests(MasterPests pests);
}
