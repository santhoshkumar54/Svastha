package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.FarmProjects;
import com.svastha.entity.ProjectImages;

public interface ProjectImagesRepository extends JpaRepository<ProjectImages, Long> {

	List<ProjectImages> findAllImagesByProject(FarmProjects project);

}
