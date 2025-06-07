
package com.svastha.repository;

import com.svastha.entity.EpicRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EpicRouteRepository extends JpaRepository<EpicRoute, Long> {

    List<EpicRoute> findByEpicPk1(Long epicId);
}
