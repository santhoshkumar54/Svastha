package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmImages;
import com.svastha.entity.Farms;

public interface FarmImagesRepository extends JpaRepository<FarmImages, Long> {

	List<FarmImages> findAllImagesByFarm(Farms farm);

}
