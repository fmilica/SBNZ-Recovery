package com.sbnz.recovery.dto;

import java.util.Date;
import java.util.List;

import com.sbnz.recovery.model.enums.Gender;
import com.sbnz.recovery.model.Illness;
import com.sbnz.recovery.model.enums.PhysicalActivity;

public class PatientDTO {

	private Long id;
	
	private String username;
	private String password;
	
	private String name;
	private String surname;
	
	private Gender gender;
	private Date dateOfBirth;
	private double height;
	private double weight;
	
	private PhysicalActivity physicalActivity;
	private List<Illness> anamnesis;
	
	public PatientDTO() {
		super();
	}

	public PatientDTO(String username, String password, String name, String surname, Gender gender, Date dateOfBirth, double height,
			double weight, PhysicalActivity physicalActivity, List<Illness> anamnesis) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.height = height;
		this.weight = weight;
		this.physicalActivity = physicalActivity;
		this.anamnesis = anamnesis;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public PhysicalActivity getPhysicalActivity() {
		return physicalActivity;
	}
	public void setPhysicalActivity(PhysicalActivity physicalActivity) {
		this.physicalActivity = physicalActivity;
	}
	public List<Illness> getAnamnesis() {
		return anamnesis;
	}
	public void setAnamnesis(List<Illness> anamnesis) {
		this.anamnesis = anamnesis;
	}
}
