package com.svastha.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.svastha.entity.Weather;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Long> {

	@Query("SELECT DISTINCT w FROM Weather w WHERE (:thaluk IS NULL OR w.thaluk = :thaluk) "
			+ "and (:capturedDateStart is NULL or w.capturedDate >= TIMESTAMPADD(HOUR, :timezoneOffset, :capturedDateStart)) "
			+ "and (:capturedDateEnd is NULL or w.capturedDate <= TIMESTAMPADD(HOUR, :timezoneOffset, :capturedDateEnd))")
	List<Weather> findByDateAndLocation(@Param("thaluk") String thaluk,
			@Param("capturedDateStart") Date capturedDateStart, @Param("capturedDateEnd") Date capturedDateEnd,
			@Param("timezoneOffset") Integer timezoneOffset);
}
