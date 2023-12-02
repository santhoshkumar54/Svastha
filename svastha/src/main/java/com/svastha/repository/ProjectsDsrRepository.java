package com.svastha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmProjects;
import com.svastha.entity.ProjectDSRMethod;

public interface ProjectsDsrRepository extends JpaRepository<ProjectDSRMethod, Long> {

	ProjectDSRMethod findDsrByProjects(FarmProjects projects);

}
