package com.svastha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.Roles;

public interface RoleRepository extends JpaRepository<Roles, Long>{

    Roles findByPk1(int pk1);
}
