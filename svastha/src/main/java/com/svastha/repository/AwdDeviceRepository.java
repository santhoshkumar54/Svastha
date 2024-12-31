package com.svastha.repository;

import com.svastha.entity.AwdDevice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AwdDeviceRepository extends JpaRepository<AwdDevice, Long> {

        @Query("SELECT a FROM AwdDevice a ORDER BY a.pk1 DESC")
        Page<AwdDevice> findAll(Pageable pageable);

        List<AwdDevice> findAllById(Iterable<Long> ids);
}