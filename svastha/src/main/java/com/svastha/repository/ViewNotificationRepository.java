package com.svastha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.svastha.entity.viewNotification;

@Repository
public interface ViewNotificationRepository extends JpaRepository<viewNotification, Long> {

}
