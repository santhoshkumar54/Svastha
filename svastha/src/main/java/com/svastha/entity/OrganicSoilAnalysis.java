package com.svastha.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class OrganicSoilAnalysis {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pk1;

	@ManyToOne
	private MasterSoiltype soiltype;

	private String soilEc;

	private String soilPh;

	private String nitrogen;

	private String phosporus;

	private String potassium;

	private String sulphur;

	private String zinc;

	private String boron;

	private String iron;

	private String manganese;

	private String copper;

	private String organic_copper;

	@ManyToOne
	@JoinColumn(name = "created_by_pk1", updatable = false)
	private Users createdBy;

	@Column(name = "created_dt", nullable = false, updatable = false, insertable = false)
	private Timestamp createdDt;

	@ManyToOne
	private Users updatedBy;

	private Timestamp updatedDt;

	@ManyToOne
	private FarmProjects projects;

	@ManyToOne
	private FarmPlots plots;

	public Long getPk1() {
		return pk1;
	}

	public void setPk1(Long pk1) {
		this.pk1 = pk1;
	}

	public FarmProjects getProjects() {
		return projects;
	}

	public void setProjects(FarmProjects projects) {
		this.projects = projects;
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

	public MasterSoiltype getSoiltype() {
		return soiltype;
	}

	public void setSoiltype(MasterSoiltype soiltype) {
		this.soiltype = soiltype;
	}

	public String getSoilEc() {
		return soilEc;
	}

	public void setSoilEc(String soilEc) {
		this.soilEc = soilEc;
	}

	public String getSoilPh() {
		return soilPh;
	}

	public void setSoilPh(String soilPh) {
		this.soilPh = soilPh;
	}

	public String getNitrogen() {
		return nitrogen;
	}

	public void setNitrogen(String nitrogen) {
		this.nitrogen = nitrogen;
	}

	public String getPhosporus() {
		return phosporus;
	}

	public void setPhosporus(String phosporus) {
		this.phosporus = phosporus;
	}

	public String getPotassium() {
		return potassium;
	}

	public void setPotassium(String potassium) {
		this.potassium = potassium;
	}

	public String getSulphur() {
		return sulphur;
	}

	public void setSulphur(String sulphur) {
		this.sulphur = sulphur;
	}

	public String getZinc() {
		return zinc;
	}

	public void setZinc(String zinc) {
		this.zinc = zinc;
	}

	public String getBoron() {
		return boron;
	}

	public void setBoron(String boron) {
		this.boron = boron;
	}

	public String getIron() {
		return iron;
	}

	public void setIron(String iron) {
		this.iron = iron;
	}

	public String getManganese() {
		return manganese;
	}

	public void setManganese(String manganese) {
		this.manganese = manganese;
	}

	public String getCopper() {
		return copper;
	}

	public void setCopper(String copper) {
		this.copper = copper;
	}

	public String getOrganic_copper() {
		return organic_copper;
	}

	public void setOrganic_copper(String organic_copper) {
		this.organic_copper = organic_copper;
	}

	public FarmPlots getPlots() {
		return plots;
	}

	public void setPlots(FarmPlots plots) {
		this.plots = plots;
	}

}