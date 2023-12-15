package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.svastha.entity.MasterChemicalBrandMapping;
import com.svastha.entity.MasterChemicals;

@Repository
public interface MasterChemicalBrandMappingRepository extends JpaRepository<MasterChemicalBrandMapping, Long>{
	
     List<MasterChemicalBrandMapping> findAllByChemicals(MasterChemicals chemicals);
}
