package com.sbnz.recovery.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.sbnz.recovery.model.Illness;
import com.sbnz.recovery.model.InjuryType;

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
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY, mappedBy = "therapy")
//	@Transient
	private List<Illness> applicableIllness;
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY, mappedBy = "therapy")
//	@Transient
	private List<InjuryType> applicableInjury;
	
	public Therapy() {
		super();
	}

	public Therapy(String name, TherapyType therapyType, int maximumMonthlyApplication,
			double temperature, int intensity) {
		super();
		this.name = name;
		this.therapyType = therapyType;
		this.maximumMonthlyApplication = maximumMonthlyApplication;
		this.temperature = temperature;
		this.intensity = intensity;
		this.applicableIllness = new ArrayList<Illness>();
		this.applicableInjury = new ArrayList<InjuryType>();
	}

	public Therapy(Long id, String name, TherapyType therapyType, int maximumMonthlyApplication,
			double temperature) {
		super();
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

	public List<Illness> getApplicableIllness() {
		return applicableIllness;
	}

	public void setApplicableIllness(List<Illness> applicableIllness) {
		this.applicableIllness = applicableIllness;
	}

	public List<InjuryType> getApplicableInjury() {
		return applicableInjury;
	}

	public void setApplicableInjury(List<InjuryType> applicableInjury) {
		this.applicableInjury = applicableInjury;
	}

	public int getIntensity() {
		return intensity;
	}

	public void setIntensity(int intensity) {
		this.intensity = intensity;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Therapy other = (Therapy) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (maximumMonthlyApplication != other.maximumMonthlyApplication)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(temperature) != Double.doubleToLongBits(other.temperature))
			return false;
		if (therapyType != other.therapyType)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Therapy [id=" + id + ", name=" + name + ", therapyType=" + therapyType + ", maximumMonthlyApplication="
				+ maximumMonthlyApplication + ", temperature=" + temperature + "]";
	}
}
