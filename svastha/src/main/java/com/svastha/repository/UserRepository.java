package com.svastha.repository;

import org.springframework.stereotype.Repository;

import com.svastha.entity.Users;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long>{

	
Users findByUsername( String username );

Users findByPk1( Long pk1 );
}
