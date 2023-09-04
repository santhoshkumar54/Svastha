package com.svastha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.LiveStock;

public interface LiveStockRepository extends JpaRepository<LiveStock, Long>{

}
