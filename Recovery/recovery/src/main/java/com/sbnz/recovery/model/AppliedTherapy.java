package com.sbnz.recovery.model;

import java.time.LocalDate;

public class AppliedTherapy {

	private LocalDate applicationDate;
	private Therapy therapy;
	
	public AppliedTherapy() {
	}

	public AppliedTherapy(LocalDate applicationDate, Therapy therapy) {
		super();
		this.applicationDate = applicationDate;
		this.therapy = therapy;
	}
	
	public LocalDate getApplicationDate() {
		return applicationDate;
	}
	public void setApplicationDate(LocalDate applicationDate) {
		this.applicationDate = applicationDate;
	}
	public Therapy getTherapy() {
		return therapy;
	}
	public void setTherapy(Therapy therapy) {
		this.therapy = therapy;
	}
}
