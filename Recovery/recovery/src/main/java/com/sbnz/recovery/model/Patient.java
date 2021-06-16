package com.sbnz.recovery.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

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
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
	  name = "patient_illness", 
	  joinColumns = @JoinColumn(name = "patient_id"), 
	  inverseJoinColumns = @JoinColumn(name = "illness_id"))
	private Set<Illness> anamnesis;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "patient", fetch = FetchType.EAGER)
	private Set<Injury> medicalHistory;
	
	@Column(name="bmr")
	private double bmr;
	
	@Column(name="regular_daily_calory_intake")
	private double regularDailyCaloryIntake;
	
	@Column(name="physical_activity_after_injury")
	private PhysicalActivity physicalActivityAfterInjury;
	
	@Column(name="daily_calory_intake_after_injury")
	private double dailyCaloryIntakeAfterInjury;
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "patient", fetch = FetchType.EAGER)
	//@LazyCollection(LazyCollectionOption.FALSE)
	//@Fetch(FetchMode.JOIN)
	private Set<DailyMeal> dailyMeals;
	
	public Patient() {
	}

	// kreiranje pacijenta
	public Patient(String username, String password, String name, String surname, Gender gender, Date dateOfBirth, double height,
			double weight, PhysicalActivity physicalActivityBeforeInjury, Set<Illness> anamnesis) {
		super(username, password, name, surname);
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.height = height;
		this.weight = weight;
		this.physicalActivityBeforeInjury = physicalActivityBeforeInjury;
		this.anamnesis = anamnesis;
		this.medicalHistory = new HashSet<Injury>();
		this.dailyMeals = new HashSet<DailyMeal>();

		//this.therapies = new ArrayList<AppliedTherapy>();

	}

	public Patient(String username, String password, String name, String surname, Gender gender, Date dateOfBirth,
			double height, double weight, PhysicalActivity physicalActivityBeforeInjury, double bmr,
			double regularDailyCaloryIntake, PhysicalActivity physicalActivityAfterInjury,
			double dailyCaloryIntakeAfterInjury, Set<Injury> medicalHistory) {
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
		this.dailyMeals = new HashSet<DailyMeal>();
	}

	public void addIllness(Illness illness) {
		this.anamnesis.add(illness);
	}
	
	public void addTherapyForInjury(AppliedTherapy appliedTherapy, Injury injury) {
		appliedTherapy.setInjury(injury);
		injury.addAppliedTherapy(appliedTherapy);
		for (Injury in : this.medicalHistory) {
			if (in.getId() == injury.getId()) {
				this.medicalHistory.remove(in);
				break;
			}
		}
		this.medicalHistory.add(injury);
		//this.medicalHistory.get(this.medicalHistory.indexOf(injury)).addAppliedTherapy(appliedTherapy);
	}
	
	public void finalizeInjury(Injury injury, LocalDate endDate) {
		for (Iterator<Injury> it = this.medicalHistory.iterator(); it.hasNext(); ) {
			Injury in = it.next();
	        if (in.equals(injury)) {
	        	in.setEndDate(endDate);
	        	break;
	        }
	    }
		//this.medicalHistory.get(this.medicalHistory.indexOf(injury)).setEndDate(endDate);
		this.physicalActivityAfterInjury = null;
	}
	
	public void addInjury(Injury injury) {
		this.medicalHistory.add(injury);
		this.physicalActivityAfterInjury = null;
	}

	public Set<DailyMeal> getDailyMeals() {
		return dailyMeals;
	}

	public void setDailyMeals(Set<DailyMeal> dailyMeals) {
		this.dailyMeals = dailyMeals;
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

	public Set<Injury> getMedicalHistory() {
		return medicalHistory;
	}

	public void setMedicalHistory(Set<Injury> medicalHistory) {
		this.medicalHistory = medicalHistory;
	}

	public PhysicalActivity getPhysicalActivityBeforeInjury() {
		return physicalActivityBeforeInjury;
	}

	public void setPhysicalActivityBeforeInjury(PhysicalActivity physicalActivityBeforeInjury) {
		this.physicalActivityBeforeInjury = physicalActivityBeforeInjury;
	}
	
	public Set<Illness> getAnamnesis() {
		return anamnesis;
	}
	
	public void setAnamnesis(Set<Illness> anamnesis) {
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
	
	public void addDailyMeal(DailyMeal dailyMeal) {
		if(this.dailyMeals == null) {
			this.dailyMeals = new HashSet<DailyMeal>();
		}
		this.dailyMeals.add(dailyMeal);
	}

}
