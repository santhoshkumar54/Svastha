package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.svastha.entity.Thaluk;

@Repository
public interface ThalukRepository extends JpaRepository<Thaluk, Long> {

	List<Thaluk> findByDistrictBetween(int minValue,int MaxValue);
}
