package com.sbnz.recovery.dto;

import java.time.LocalDate;

public class AppliedTherapyDTO {

	private LocalDate applicationDate;
	private TherapyDTO therapy;
	
	public AppliedTherapyDTO() {
		super();
	}

	public AppliedTherapyDTO(LocalDate applicationDate, TherapyDTO therapy) {
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

	public TherapyDTO getTherapy() {
		return therapy;
	}

	public void setTherapy(TherapyDTO therapy) {
		this.therapy = therapy;
	}
}
