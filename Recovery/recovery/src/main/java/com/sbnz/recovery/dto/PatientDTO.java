package com.sbnz.recovery.dto;

import java.util.Date;
import java.util.List;

import com.sbnz.recovery.model.enums.Gender;
import com.sbnz.recovery.model.enums.PhysicalActivity;

public class PatientDTO {

	private Long id;
	
	private String email;
	private String password;
	
	private String name;
	private String surname;
	
	private Gender gender;
	private Date dateOfBirth;
	private double height;
	private double weight;
	
	private PhysicalActivity physicalActivity;
	private List<IllnessDTO> anamnesis;
	
	private PhysicalActivity physicalActivityAfterInjury;
	private double regularDailyCaloryIntake;
	private double dailyCaloryIntakeAfterInjury;
	
	
	public PatientDTO() {
	}

	public PatientDTO(Long id, String email, String name, String surname, Gender gender, Date dateOfBirth,
			double height, double weight, PhysicalActivity physicalActivity, List<IllnessDTO> anamnesis,
			PhysicalActivity physicalActivityAfterInjury, double regularDailyCaloryIntake,
			double dailyCaloryIntakeAfterInjury) {
		this.id = id;
		this.email = email;
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.height = height;
		this.weight = weight;
		this.physicalActivity = physicalActivity;
		this.anamnesis = anamnesis;
		this.physicalActivityAfterInjury = physicalActivityAfterInjury;
		this.regularDailyCaloryIntake = regularDailyCaloryIntake;
		this.dailyCaloryIntakeAfterInjury = dailyCaloryIntakeAfterInjury;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public void setHeight(double height) {
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
	public List<IllnessDTO> getAnamnesis() {
		return anamnesis;
	}
	public void setAnamnesis(List<IllnessDTO> anamnesis) {
		this.anamnesis = anamnesis;
	}
	public PhysicalActivity getPhysicalActivityAfterInjury() {
		return physicalActivityAfterInjury;
	}
	public void setPhysicalActivityAfterInjury(PhysicalActivity physicalActivityAfterInjury) {
		this.physicalActivityAfterInjury = physicalActivityAfterInjury;
	}
	public double getRegularDailyCaloryIntake() {
		return regularDailyCaloryIntake;
	}
	public void setRegularDailyCaloryIntake(double regularDailyCaloryIntake) {
		this.regularDailyCaloryIntake = regularDailyCaloryIntake;
	}
	public double getDailyCaloryIntakeAfterInjury() {
		return dailyCaloryIntakeAfterInjury;
	}
	public void setDailyCaloryIntakeAfterInjury(double dailyCaloryIntakeAfterInjury) {
		this.dailyCaloryIntakeAfterInjury = dailyCaloryIntakeAfterInjury;
	}
}
