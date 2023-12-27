package com.svastha.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MasterFertilizers {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pk1;

	private String name;

	private String ingredients;

	private String dose;

	private String firstDose;

	private String secondDose;

	private String thirdDose;

	private String fourthDose;

	private String applicationMethod;

	public Long getPk1() {
		return pk1;
	}

	public void setPk1(Long pk1) {
		this.pk1 = pk1;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public String getDose() {
		return dose;
	}

	public void setDose(String dose) {
		this.dose = dose;
	}

	public String getFirstDose() {
		return firstDose;
	}

	public void setFirstDose(String firstDose) {
		this.firstDose = firstDose;
	}

	public String getSecondDose() {
		return secondDose;
	}

	public void setSecondDose(String secondDose) {
		this.secondDose = secondDose;
	}

	public String getThirdDose() {
		return thirdDose;
	}

	public void setThirdDose(String thirdDose) {
		this.thirdDose = thirdDose;
	}

	public String getFourthDose() {
		return fourthDose;
	}

	public void setFourthDose(String fourthDose) {
		this.fourthDose = fourthDose;
	}

	public String getApplicationMethod() {
		return applicationMethod;
	}

	public void setApplicationMethod(String applicationMethod) {
		this.applicationMethod = applicationMethod;
	}
}