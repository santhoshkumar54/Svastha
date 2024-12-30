package com.svastha.repository;

import com.svastha.entity.AwdDevice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AwdDeviceRepository extends JpaRepository<AwdDevice, Long> {

        @Query("SELECT a FROM AwdDevice a WHERE (:deviceId IS NULL OR a.deviceId = :deviceId) "
                + "AND (:id IS NULL OR a.pk1 = :id) "
                + "AND (:user IS NULL OR a.createdBy.pk1 = :user) "
                + "ORDER BY a.pk1 DESC")
        Page<AwdDevice> findWithFilters(@Param("deviceId") String deviceId,
                                        @Param("id") Long id,
                                        @Param("user") Long user,
                                        Pageable pageable);

}
