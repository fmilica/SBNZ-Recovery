package com.sbnz.recovery.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.sbnz.recovery.model.enums.Gender;
import com.sbnz.recovery.model.enums.PhysicalActivity;

@Entity
public class Patient extends User {
	
	@Column(name="gender")
	private Gender gender;
	
	@Column(name="date_of_birth")
	private Date dateOfBirth;
	
	@Column(name="height")
	private double height;
	
	@Column(name="weight")
	private double weight;
	
	@Column(name="physical_activity_before_injury")
	private PhysicalActivity physicalActivityBeforeInjury;
	
//	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY, mappedBy = "patient")
//	@Transient
//	@ManyToMany(mappedBy = "patients")
	@ManyToMany
	@JoinTable(
	  name = "patient_illness", 
	  joinColumns = @JoinColumn(name = "patient_id"), 
	  inverseJoinColumns = @JoinColumn(name = "illness_id"))
	private List<Illness> anamnesis;
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "patient")
	@LazyCollection(LazyCollectionOption.FALSE)
//	@Transient
	private List<Injury> medicalHistory;
	
	@Column(name="bmr")
	private double bmr;
	
	@Column(name="regular_daily_calory_intake")
	private double regularDailyCaloryIntake;
	
	@Column(name="physical_activity_after_injury")
	private PhysicalActivity physicalActivityAfterInjury;
	
	@Column(name="daily_calory_intake_after_injury")
	private double dailyCaloryIntakeAfterInjury;
	
//	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY, mappedBy = "patient")
//	@Transient
//	private List<Meal> meals;
	
	public Patient() {
		super();
	}

	// kreiranje pacijenta
	public Patient(String username, String password, String name, String surname, Gender gender, Date dateOfBirth, double height,
			double weight, PhysicalActivity physicalActivityBeforeInjury, List<Illness> anamnesis) {
		super(username, password, name, surname);
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.height = height;
		this.weight = weight;
		this.physicalActivityBeforeInjury = physicalActivityBeforeInjury;
		this.anamnesis = anamnesis;
		this.medicalHistory = new ArrayList<Injury>();

		//this.therapies = new ArrayList<AppliedTherapy>();
//		this.meals = new ArrayList<Meal>();

	}

	public Patient(String username, String password, String name, String surname, Gender gender, Date dateOfBirth,
			double height, double weight, PhysicalActivity physicalActivityBeforeInjury, double bmr,
			double regularDailyCaloryIntake, PhysicalActivity physicalActivityAfterInjury,
			double dailyCaloryIntakeAfterInjury, List<Injury> medicalHistory) {
		super(username, password, name, surname);
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.height = height;
		this.weight = weight;
		this.physicalActivityBeforeInjury = physicalActivityBeforeInjury;
		this.bmr = bmr;
		this.regularDailyCaloryIntake = regularDailyCaloryIntake;
		this.physicalActivityAfterInjury = physicalActivityAfterInjury;
		this.dailyCaloryIntakeAfterInjury = dailyCaloryIntakeAfterInjury;
		this.medicalHistory = medicalHistory;
//		this.meals = new ArrayList<Meal>();
	}

	public void addIllness(Illness illness) {
		this.anamnesis.add(illness);
	}
	
	public void addTherapyForInjury(AppliedTherapy appliedTherapy, Injury injury) {
		this.medicalHistory.get(this.medicalHistory.indexOf(injury)).addAppliedTherapy(appliedTherapy);
	}
	
	public void finalizeInjury(Injury injury, LocalDate endDate) {
		this.medicalHistory.get(this.medicalHistory.indexOf(injury)).setEndDate(endDate);
		this.physicalActivityAfterInjury = null;
	}
	
	public void addInjury(Injury injury) {
		this.medicalHistory.add(injury);
		this.physicalActivityAfterInjury = null;
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

	public void setAge(Date dateOfBirth) {
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

	public List<Injury> getMedicalHistory() {
		return medicalHistory;
	}

	public void setMedicalHistory(List<Injury> medicalHistory) {
		this.medicalHistory = medicalHistory;
	}

	public PhysicalActivity getPhysicalActivityBeforeInjury() {
		return physicalActivityBeforeInjury;
	}

	public void setPhysicalActivityBeforeInjury(PhysicalActivity physicalActivityBeforeInjury) {
		this.physicalActivityBeforeInjury = physicalActivityBeforeInjury;
	}
	
	public List<Illness> getAnamnesis() {
		return anamnesis;
	}
	
	public void setAnamnesis(List<Illness> anamnesis) {
		this.anamnesis = anamnesis;
	}

	public double getBmr() {
		return bmr;
	}

	public void setBmr(double bmr) {
		this.bmr = bmr;
	}

	public double getRegularDailyCaloryIntake() {
		return regularDailyCaloryIntake;
	}

	public void setRegularDailyCaloryIntake(double regularDailyCaloryIntake) {
		this.regularDailyCaloryIntake = regularDailyCaloryIntake;
	}

	public PhysicalActivity getPhysicalActivityAfterInjury() {
		return physicalActivityAfterInjury;
	}

	public void setPhysicalActivityAfterInjury(PhysicalActivity physicalActivityAfterInjury) {
		this.physicalActivityAfterInjury = physicalActivityAfterInjury;
	}

	public double getDailyCaloryIntakeAfterInjury() {
		return dailyCaloryIntakeAfterInjury;
	}

	public void setDailyCaloryIntakeAfterInjury(double dailyCaloryIntakeAfterInjury) {
		this.dailyCaloryIntakeAfterInjury = dailyCaloryIntakeAfterInjury;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

//	public List<Meal> getMeals() {
//		return meals;
//	}
//
//	public void setMeals(List<Meal> meals) {
//		this.meals = meals;
//	}
//	
//	public void addMeal(Meal meal) {
//		this.meals.add(meal);
//	}

}
