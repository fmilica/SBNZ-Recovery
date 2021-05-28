package com.sbnz.recovery.model.templates;

import java.time.LocalDate;

import com.sbnz.recovery.model.enums.Gender;

public class GenderIntervalTemplateModel {

	private LocalDate startDate;
	private LocalDate endDate;
	private Gender gender;
	private Integer genderOrdinal;

	public GenderIntervalTemplateModel() {}

	public GenderIntervalTemplateModel(LocalDate startDate, LocalDate endDate, Gender gender) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.gender = gender;
		this.genderOrdinal = gender.ordinal();
	}
	
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Integer getGenderOrdinal() {
		return genderOrdinal;
	}

	public void setGenderOrdinal(Integer genderOrdinal) {
		this.genderOrdinal = genderOrdinal;
	}
}
