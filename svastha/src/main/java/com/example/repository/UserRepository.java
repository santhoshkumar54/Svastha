package com.example.repository;

import org.springframework.stereotype.Repository;

import com.example.entity.Users;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long>{

	
Users findByUsername( String username );
}
