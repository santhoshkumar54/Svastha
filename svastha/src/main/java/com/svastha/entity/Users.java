package com.svastha.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "users")
public class Users {


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pk1;
	
	private String first_name;
	
	private String last_name;
	
	private String username;
	
	private String password;
	
	private String phone_number;
	
	@ManyToOne
	private Roles role;
	
	private Timestamp createddt;

	public Integer getPk1() {
		return pk1;
	}

	public void setPk1(Integer pk1) {
		this.pk1 = pk1;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public Roles getRoles() {
		return role;
	}

	public void setRoles(Roles role) {
		this.role = role;
	}

	public Timestamp getCreateddt() {
		return createddt;
	}

	public void setCreateddt(Timestamp createddt) {
		this.createddt = createddt;
	}
	
	
}
