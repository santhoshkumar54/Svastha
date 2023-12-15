package com.svastha.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ProjectSeedTreatmentChemical {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pk1;

	private String chemicalName;

	private String chemicalDose;

	private String bioAgent;

	private String bioAgentDose;

	private String bioFertilizer;

	private String bioFertilizerDose;
	
	@ManyToOne
	private ProjectSeedTreatment seedTreatment;

	@ManyToOne
	private Users createdBy;

	private Timestamp createdDt;
	
	@ManyToOne
	private Users updatedBy;

	private Timestamp updatedDt;

	public Long getPk1() {
		return pk1;
	}

	public void setPk1(Long pk1) {
		this.pk1 = pk1;
	}

	public String getChemicalName() {
		return chemicalName;
	}

	public void setChemicalName(String chemicalName) {
		this.chemicalName = chemicalName;
	}

	public String getChemicalDose() {
		return chemicalDose;
	}

	public void setChemicalDose(String chemicalDose) {
		this.chemicalDose = chemicalDose;
	}

	public String getBioAgent() {
		return bioAgent;
	}

	public void setBioAgent(String bioAgent) {
		this.bioAgent = bioAgent;
	}

	public String getBioAgentDose() {
		return bioAgentDose;
	}

	public void setBioAgentDose(String bioAgentDose) {
		this.bioAgentDose = bioAgentDose;
	}

	public String getBioFertilizer() {
		return bioFertilizer;
	}

	public void setBioFertilizer(String bioFertilizer) {
		this.bioFertilizer = bioFertilizer;
	}

	public String getBioFertilizerDose() {
		return bioFertilizerDose;
	}

	public void setBioFertilizerDose(String bioFertilizerDose) {
		this.bioFertilizerDose = bioFertilizerDose;
	}

	public Users getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Users createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDt() {
		return createdDt;
	}

	public void setCreatedDt(Timestamp createdDt) {
		this.createdDt = createdDt;
	}

	public ProjectSeedTreatment getSeedTreatment() {
		return seedTreatment;
	}

	public void setSeedTreatment(ProjectSeedTreatment seedTreatment) {
		this.seedTreatment = seedTreatment;
	}
	
	public Users getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Users updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedDt() {
		return updatedDt;
	}

	public void setUpdatedDt(Timestamp updatedDt) {
		this.updatedDt = updatedDt;
	}
}