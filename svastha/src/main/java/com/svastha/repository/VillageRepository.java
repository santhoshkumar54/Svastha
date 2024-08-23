package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.svastha.entity.Thaluk;
import com.svastha.entity.Village;

@Repository
public interface VillageRepository extends JpaRepository<Village, Long> {

	List<Village> findByThaluk(Thaluk thaluk);
}

