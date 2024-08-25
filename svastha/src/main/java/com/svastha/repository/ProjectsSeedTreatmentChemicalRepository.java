package com.svastha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.svastha.entity.ProjectSeedTreatment;
import com.svastha.entity.ProjectSeedTreatmentChemical;

public interface ProjectsSeedTreatmentChemicalRepository extends JpaRepository<ProjectSeedTreatmentChemical, Long> {

	List<ProjectSeedTreatmentChemical> findAllChemicalBySeedTreatment(ProjectSeedTreatment seedTreatment);

	List<ProjectSeedTreatmentChemical> findBySeedTreatmentIn(List<ProjectSeedTreatment> seedTreatment);

	@Query(value = "SELECT seed_treatment_pk1 AS seedPk1, "
			+ "GROUP_CONCAT(CONCAT(bio_agent, ' - ', bio_agent_dose) SEPARATOR ' , ') AS bioAgents, "
			+ "GROUP_CONCAT(CONCAT(bio_fertilizer, ' - ', bio_fertilizer_dose) SEPARATOR ' , ') AS bioFertilizers, "
			+ "GROUP_CONCAT(CONCAT(chemical_name, ' - ', chemical_dose) SEPARATOR ' , ') AS chemicals "
			+ "FROM project_seed_treatment_chemical " + "WHERE seed_treatment_pk1 = :seedTreatmentPk1 "
			+ "GROUP BY seed_treatment_pk1", nativeQuery = true)
	Object[] findBySeedTreatmentPk1(@Param("seedTreatmentPk1") Long seedTreatmentPk1);
}
