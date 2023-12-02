package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.svastha.entity.ProjectSeedTreatment;
import com.svastha.entity.ProjectSeedTreatmentChemical;

public interface ProjectsSeedTreatmentChemicalRepository extends JpaRepository<ProjectSeedTreatmentChemical, Long> {

	List<ProjectSeedTreatmentChemical> findAllChemicalBySeedTreatment(ProjectSeedTreatment seedTreatment);

}
