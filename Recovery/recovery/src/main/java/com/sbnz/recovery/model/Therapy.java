package com.sbnz.recovery.model;

import java.io.Serializable;

import com.sbnz.recovery.model.enums.InjuryType;
import com.sbnz.recovery.model.enums.TherapyType;

public class Therapy implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private TherapyType therapyType;
	private int maximumMonthlyAppliance;
	private InjuryType injuryType;
	private double temperature;
	
	public Therapy() {
		super();
	}

	public Therapy(String name, TherapyType therapyType, int maximumMonthlyAppliance, InjuryType injuryType,
			double temperature) {
		super();
		this.name = name;
		this.therapyType = therapyType;
		this.maximumMonthlyAppliance = maximumMonthlyAppliance;
		this.injuryType = injuryType;
		this.temperature = temperature;
	}

	public Therapy(Long id, String name, TherapyType therapyType, int maximumMonthlyAppliance, InjuryType injuryType,
			double temperature) {
		super();
		this.id = id;
		this.name = name;
		this.therapyType = therapyType;
		this.maximumMonthlyAppliance = maximumMonthlyAppliance;
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

	public int getMaximumMonthlyAppliance() {
		return maximumMonthlyAppliance;
	}

	public void setMaximumMonthlyAppliance(int maximumMonthlyAppliance) {
		this.maximumMonthlyAppliance = maximumMonthlyAppliance;
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
}
