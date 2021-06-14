package com.sbnz.recovery.dto;

import com.sbnz.recovery.model.enums.TherapyType;

public class TherapyDTO {
	
	private Long id;
	private String name;
	private TherapyType therapyType;
	private int maximumMonthlyApplication;
	private double temperature;
	private int intensity;
	
	public TherapyDTO() {
	}
	
	public TherapyDTO(Long id, String name, TherapyType therapyType) {
		this.id = id;
		this.name = name;
		this.therapyType = therapyType;
	}

	public TherapyDTO(String name, TherapyType therapyType, int maximumMonthlyApplication, double temperature, int intensity) {
		this.name = name;
		this.therapyType = therapyType;
		this.maximumMonthlyApplication = maximumMonthlyApplication;
		this.temperature = temperature;
		this.intensity = intensity;
	}
	
	public TherapyDTO(Long id, String name, TherapyType therapyType, int maximumMonthlyApplication, double temperature, int intensity) {
		this.id = id;
		this.name = name;
		this.therapyType = therapyType;
		this.maximumMonthlyApplication = maximumMonthlyApplication;
		this.temperature = temperature;
		this.intensity = intensity;
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

	public int getMaximumMonthlyApplication() {
		return maximumMonthlyApplication;
	}

	public void setMaximumMonthlyApplication(int maximumMonthlyApplication) {
		this.maximumMonthlyApplication = maximumMonthlyApplication;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public int getIntensity() {
		return intensity;
	}

	public void setIntensity(int intensity) {
		this.intensity = intensity;
	}
}
