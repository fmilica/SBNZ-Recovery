package com.sbnz.recovery.model;

import java.io.Serializable;

import com.sbnz.recovery.model.enums.InjuryType;
import com.sbnz.recovery.model.enums.TherapyType;

public class Therapy implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private TherapyType therapyType;
	private int maximumMonthlyApplication;
	private InjuryType injuryType;
	private double temperature;
	
	public Therapy() {
		super();
	}

	public Therapy(String name, TherapyType therapyType, int maximumMonthlyApplication, InjuryType injuryType,
			double temperature) {
		super();
		this.name = name;
		this.therapyType = therapyType;
		this.maximumMonthlyApplication = maximumMonthlyApplication;
		this.injuryType = injuryType;
		this.temperature = temperature;
	}

	public Therapy(Long id, String name, TherapyType therapyType, int maximumMonthlyApplication, InjuryType injuryType,
			double temperature) {
		super();
		this.id = id;
		this.name = name;
		this.therapyType = therapyType;
		this.maximumMonthlyApplication = maximumMonthlyApplication;
		this.injuryType = injuryType;
		this.temperature = temperature;
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

	public int getmaximumMonthlyApplication() {
		return maximumMonthlyApplication;
	}

	public void setmaximumMonthlyApplication(int maximumMonthlyApplication) {
		this.maximumMonthlyApplication = maximumMonthlyApplication;
	}

	public InjuryType getInjuryType() {
		return injuryType;
	}

	public void setInjuryType(InjuryType injuryType) {
		this.injuryType = injuryType;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
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
		if (injuryType != other.injuryType)
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
				+ maximumMonthlyApplication + ", injuryType=" + injuryType + ", temperature=" + temperature + "]";
	}
}
