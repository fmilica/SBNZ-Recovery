package com.sbnz.recovery.dto;

import java.util.Map;

import com.sbnz.recovery.model.enums.Gender;

public class TemplateDTO {
	
	private Gender gender;
	private double count;
	
	public TemplateDTO() {}

	public TemplateDTO(Gender gender, double count) {
		super();
		this.gender = gender;
		this.count = count;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public double getCount() {
		return count;
	}

	public void setCount(double count) {
		this.count = count;
	}
}
