package com.svastha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.CropHistory;
import com.svastha.entity.Farms;

public interface CropHistoryRepository extends JpaRepository<CropHistory, Long> {


}
