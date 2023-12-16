package com.svastha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.svastha.entity.Fertilizers;

@Repository
public interface FertilizerRepository extends JpaRepository<Fertilizers, Long>{

}
