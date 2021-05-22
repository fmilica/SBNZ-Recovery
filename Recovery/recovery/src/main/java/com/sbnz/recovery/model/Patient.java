package com.sbnz.recovery.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sbnz.recovery.model.enums.Gender;
import com.sbnz.recovery.model.enums.Illness;
import com.sbnz.recovery.model.enums.PhysicalActivity;

public class Patient implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String username;
	private String password;
	
	private String name;
	private String surname;
	
	private Gender gender;
	private Date dateOfBirth;
	private int height;
	private double weight;
	
	private PhysicalActivity physicalActivityBeforeInjury;
	private List<Illness> anamnesis;
	private List<Injury> medicalHistory;
	
	private double bmr;
	private double regularDailyCaloryIntake;
	private PhysicalActivity physicalActivityAfterInjury;
	private double dailyCaloryIntakeAfterInjury;
	
	//private List<AppliedTherapy> therapies;
	//private AppliedTherapy currentTherapy;
	
	public Patient() {
		super();
	}

	// kreiranje pacijenta
	public Patient(String username, String password, String name, String surname, Gender gender, Date dateOfBirth, int height,
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
	}

	public Patient(Long id, String username, String password, String name, String surname, Gender gender, Date dateOfBirth,
			int height, double weight, PhysicalActivity physicalActivityBeforeInjury, double bmr,
			double regularDailyCaloryIntake, PhysicalActivity physicalActivityAfterInjury,
			double dailyCaloryIntakeAfterInjury, List<Injury> medicalHistory) {
		super();
		this.id = id;
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

	public void setAge(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (physicalActivityBeforeInjury != other.physicalActivityBeforeInjury)
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		if (Double.doubleToLongBits(weight) != Double.doubleToLongBits(other.weight))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Patient [id=" + id + ", username=" + username + ", password=" + password + ", name=" + name
				+ ", surname=" + surname + ", gender=" + gender + ", dateOfBirth=" + dateOfBirth + ", height=" + height + ", weight="
				+ weight + ", physicalActivityBeforeInjury=" + physicalActivityBeforeInjury
				+ ", physicalActivityAfterInjury=" + physicalActivityAfterInjury + ", medicalHistory=" + medicalHistory
				+ "]";
	}
}
