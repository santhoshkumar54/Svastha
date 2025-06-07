package com.svastha.repository;

import com.svastha.entity.Epic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EpicRepository extends JpaRepository<Epic, Long> {

    List<Epic> findBySeasonPk1AndVarietyPk1AndYearPk1(Long seasonId, Long varietyId, Long yearId);

    List<Epic> findByStartDateLessThanEqualAndEndDateGreaterThanEqual(LocalDate currentDate1, LocalDate currentDate2);
}