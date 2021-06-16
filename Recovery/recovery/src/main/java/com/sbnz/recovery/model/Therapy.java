package com.sbnz.recovery.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.sbnz.recovery.model.enums.TherapyType;

@Entity
public class Therapy implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="therapy_type")
	private TherapyType therapyType;
	
	@Column(name="maximum_monthly_application")
	private int maximumMonthlyApplication;
	
	@Column(name="temperature")
	private double temperature;
	
	@Column(name="intensity")
	private int intensity;
	
	//@OneToMany(cascade = {CascadeType.DETACH}, fetch = FetchType.LAZY, mappedBy = "therapy")
	@ManyToMany
	@JoinTable(
	  name = "therapy_illness", 
	  joinColumns = @JoinColumn(name = "therapy_id"), 
	  inverseJoinColumns = @JoinColumn(name = "illness_id"))
	private Set<Illness> applicableIllness;
	
	//@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY, mappedBy = "therapy")
	@ManyToMany
	@JoinTable(
	  name = "therapy_injury_type", 
	  joinColumns = @JoinColumn(name = "therapy_id"), 
	  inverseJoinColumns = @JoinColumn(name = "injury_type_id"))
	private Set<InjuryType> applicableInjury;
	
	public Therapy() {
	}
	
	public Therapy(Long id) {
		this.id = id;
	}

	public Therapy(String name, TherapyType therapyType, int maximumMonthlyApplication,
			double temperature, int intensity) {
		this.name = name;
		this.therapyType = therapyType;
		this.maximumMonthlyApplication = maximumMonthlyApplication;
		this.temperature = temperature;
		this.intensity = intensity;
		this.applicableIllness = new HashSet<Illness>();
		this.applicableInjury = new HashSet<InjuryType>();
	}

	public Therapy(Long id, String name, TherapyType therapyType, int maximumMonthlyApplication,
			double temperature) {
		this.id = id;
		this.name = name;
		this.therapyType = therapyType;
		this.maximumMonthlyApplication = maximumMonthlyApplication;
		this.temperature = temperature;
	}

	
	public void addApplicableIllness(Illness illness) {
		this.applicableIllness.add(illness);
	}
	
	public void addApplicableInjuryType(InjuryType injuryType) {
		this.applicableInjury.add(injuryType);
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TherapyType getTherapyType() {
		return therapyType;
	}

	public void setTherapyType(TherapyType therapyType) {
		this.therapyType = therapyType;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	
	public int getMaximumMonthlyApplication() {
		return maximumMonthlyApplication;
	}

	public void setMaximumMonthlyApplication(int maximumMonthlyApplication) {
		this.maximumMonthlyApplication = maximumMonthlyApplication;
	}

	public Set<Illness> getApplicableIllness() {
		return applicableIllness;
	}

	public void setApplicableIllness(Set<Illness> applicableIllness) {
		this.applicableIllness = applicableIllness;
	}

	public Set<InjuryType> getApplicableInjury() {
		return applicableInjury;
	}

	public void setApplicableInjury(Set<InjuryType> applicableInjury) {
		this.applicableInjury = applicableInjury;
	}

	public int getIntensity() {
		return intensity;
	}

	public void setIntensity(int intensity) {
		this.intensity = intensity;
	}

	@Override
	public String toString() {
		return "Therapy [id=" + id + ", name=" + name + ", therapyType=" + therapyType + ", maximumMonthlyApplication="
				+ maximumMonthlyApplication + ", temperature=" + temperature + "]";
	}
}
