package com.sbnz.recovery.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.kie.api.definition.type.PropertyReactive;

import com.sbnz.recovery.model.enums.Gender;
import com.sbnz.recovery.model.enums.PhysicalActivity;

@Entity
public class Patient implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "username", nullable = false)
	private String username;

	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name="name")
	private String name;
	
	@Column(name="surname")
	private String surname;
	
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
	@ManyToMany(mappedBy = "patients")
	private List<Illness> anamnesis;
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY, mappedBy = "patient")
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
	
	//private List<AppliedTherapy> therapies;
	//private AppliedTherapy currentTherapy;
	
	public Patient() {
		super();
	}

	// kreiranje pacijenta
	public Patient(String username, String password, String name, String surname, Gender gender, Date dateOfBirth, double height,
			double weight, PhysicalActivity physicalActivityBeforeInjury, List<Illness> anamnesis) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
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

	public Patient(String email, String password, String name, String surname, Gender gender, Date dateOfBirth,
			double height, double weight, PhysicalActivity physicalActivityBeforeInjury, double bmr,
			double regularDailyCaloryIntake, PhysicalActivity physicalActivityAfterInjury,
			double dailyCaloryIntakeAfterInjury, List<Injury> medicalHistory) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
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

	public void addTherapyForInjury(AppliedTherapy appliedTherapy, Injury injury) {
		this.medicalHistory.get(this.medicalHistory.indexOf(injury)).addAppliedTherapy(appliedTherapy);
	}
	
	public void addIllness(Illness illness) {
		this.anamnesis.add(illness);
	}
	
	public void addInjury(Injury injury) {
		this.medicalHistory.add(injury);
	}
	
	/*public void addTherapy(AppliedTherapy therapy) {
		this.therapies.add(therapy);
	}*/

	public String getName() {
		return name;
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

	/*public List<AppliedTherapy> getTherapies() {
		return therapies;
	}

	public void setTherapies(List<AppliedTherapy> therapies) {
		this.therapies = therapies;
	}

	public AppliedTherapy getCurrentTherapy() {
		return currentTherapy;
	}

	public void setCurrentTherapy(AppliedTherapy currentTherapy) {
		this.currentTherapy = currentTherapy;
	}*/

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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Patient other = (Patient) obj;
		if (dateOfBirth != other.dateOfBirth)
			return false;
		if (gender != other.gender)
			return false;
		if (height != other.height)
			return false;
		if (medicalHistory == null) {
			if (other.medicalHistory != null)
				return false;
		} else if (!medicalHistory.equals(other.medicalHistory))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (physicalActivityBeforeInjury != other.physicalActivityBeforeInjury)
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		if (Double.doubleToLongBits(weight) != Double.doubleToLongBits(other.weight))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Patient [name=" + name + ", surname=" + surname + ", gender=" + gender + ", dateOfBirth=" + dateOfBirth
				+ ", height=" + height + ", weight=" + weight + ", physicalActivityBeforeInjury="
				+ physicalActivityBeforeInjury + ", anamnesis=" + anamnesis + ", medicalHistory=" + medicalHistory
				+ ", bmr=" + bmr + ", regularDailyCaloryIntake=" + regularDailyCaloryIntake
				+ ", physicalActivityAfterInjury=" + physicalActivityAfterInjury + ", dailyCaloryIntakeAfterInjury="
				+ dailyCaloryIntakeAfterInjury + "]";
	}
}
