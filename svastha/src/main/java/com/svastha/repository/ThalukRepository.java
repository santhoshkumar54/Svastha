package com.svastha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.svastha.entity.Thaluk;

@Repository
public interface ThalukRepository extends JpaRepository<Thaluk, Long> {

}
