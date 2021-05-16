package com.sbnz.recovery.model;

import java.io.Serializable;
import java.util.List;

import com.sbnz.recovery.model.enums.Gender;
import com.sbnz.recovery.model.enums.PhysicalActivity;

public class Patient implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	private String username;
	private String password;
	private String name;
	private String surname;
	private Gender gender;
	private int age;
	private int height;
	private double weight;
	private PhysicalActivity physicalActivity;
	private List<Injury> medicalHistory;
	
	public Patient() {
		super();
	}

	public Patient(String username, String password, String name, String surname, Gender gender, int age, int height,
			double weight, PhysicalActivity physicalActivity, List<Injury> medicalHistory) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.age = age;
		this.height = height;
		this.weight = weight;
		this.physicalActivity = physicalActivity;
		this.medicalHistory = medicalHistory;
	}

	public Patient(Long id, String username, String password, String name, String surname, Gender gender, int age,
			int height, double weight, PhysicalActivity physicalActivity, List<Injury> medicalHistory) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.age = age;
		this.height = height;
		this.weight = weight;
		this.physicalActivity = physicalActivity;
		this.medicalHistory = medicalHistory;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getHeight() {
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

	public List<Injury> getMedicalHistory() {
		return medicalHistory;
	}

	public void setMedicalHistory(List<Injury> medicalHistory) {
		this.medicalHistory = medicalHistory;
	}
}
