package com.sbnz.recovery.model;

import java.util.Date;

public class AppliedTherapy {

	private Date applicationDate;
	private Therapy therapy;
	
	public AppliedTherapy() {
	}

	public AppliedTherapy(Date applicationDate, Therapy therapy) {
		super();
		this.applicationDate = applicationDate;
		this.therapy = therapy;
	}
	
	public Date getApplicationDate() {
		return applicationDate;
	}
	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}
	public Therapy getTherapy() {
		return therapy;
	}
	public void setTherapy(Therapy therapy) {
		this.therapy = therapy;
	}
}
