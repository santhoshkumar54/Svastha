package com.svastha.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Weather {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pk1;

	private String thaluk;

	private Date capturedDate;

	private Date generatedDate;
	
	private Double minTemp;
	
	private Double maxTemp;
	
	private int minPrec;
	
	private int maxPrec;
	
	private Double minHumidity;
	
	private Double maxHumidity;
	
	private int minUvIndex;
	
	private int maxUvIndex;

	public Long getPk1() {
		return pk1;
	}

	public void setPk1(Long pk1) {
		this.pk1 = pk1;
	}

	public String getThaluk() {
		return thaluk;
	}

	public void setThaluk(String thaluk) {
		this.thaluk = thaluk;
	}

	public Date getCapturedDate() {
		return capturedDate;
	}

	public void setCapturedDate(Date capturedDate) {
		this.capturedDate = capturedDate;
	}

	public Date getGeneratedDate() {
		return generatedDate;
	}

	public void setGeneratedDate(Date generatedDate) {
		this.generatedDate = generatedDate;
	}

	public Double getMinTemp() {
		return minTemp;
	}

	public void setMinTemp(Double minTemp) {
		this.minTemp = minTemp;
	}

	public Double getMaxTemp() {
		return maxTemp;
	}

	public void setMaxTemp(Double maxTemp) {
		this.maxTemp = maxTemp;
	}

	public int getMinPrec() {
		return minPrec;
	}

	public void setMinPrec(int minPrec) {
		this.minPrec = minPrec;
	}

	public int getMaxPrec() {
		return maxPrec;
	}

	public void setMaxPrec(int maxPrec) {
		this.maxPrec = maxPrec;
	}

	public Double getMinHumidity() {
		return minHumidity;
	}

	public void setMinHumidity(Double minHumidity) {
		this.minHumidity = minHumidity;
	}

	public Double getMaxHumidity() {
		return maxHumidity;
	}

	public void setMaxHumidity(Double maxHumidity) {
		this.maxHumidity = maxHumidity;
	}

	public int getMinUvIndex() {
		return minUvIndex;
	}

	public void setMinUvIndex(int minUvIndex) {
		this.minUvIndex = minUvIndex;
	}

	public int getMaxUvIndex() {
		return maxUvIndex;
	}

	public void setMaxUvIndex(int maxUvIndex) {
		this.maxUvIndex = maxUvIndex;
	}
}