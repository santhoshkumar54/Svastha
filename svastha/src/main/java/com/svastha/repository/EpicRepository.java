
package com.svastha.repository;

import com.svastha.entity.Epic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EpicRepository extends JpaRepository<Epic, Long> {

    @Query("SELECT e FROM Epic e WHERE e.season.pk1 = :seasonId AND e.variety.pk1 = :varietyId AND e.year.pk1 = :yearId")
    Epic findBySeasonVarietyYear(@Param("seasonId") Long seasonId, @Param("varietyId") Long varietyId, @Param("yearId") Long yearId);

    List<Epic> findByYearPk1(Long yearId);
}
