package com.svastha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.svastha.entity.Weather;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Long> {

}
