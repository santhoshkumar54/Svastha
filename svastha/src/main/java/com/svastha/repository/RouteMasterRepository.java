
package com.svastha.repository;

import com.svastha.entity.RouteMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteMasterRepository extends JpaRepository<RouteMaster, Long> {
    
    List<RouteMaster> findByIsActiveTrue();
    
    RouteMaster findByRouteCode(String routeCode);
    
    List<RouteMaster> findByRouteNameContainingIgnoreCase(String routeName);
}
