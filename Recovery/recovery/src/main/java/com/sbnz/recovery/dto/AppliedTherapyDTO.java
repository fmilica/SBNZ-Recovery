package com.sbnz.recovery.dto;

import java.time.LocalDate;

public class AppliedTherapyDTO {

	private Long id;
	private LocalDate applicationDate;
	private TherapyDTO therapy;
	
	public AppliedTherapyDTO() {
	}

	public AppliedTherapyDTO(Long id, LocalDate applicationDate, TherapyDTO therapy) {
		this.id = id;
		this.applicationDate = applicationDate;
		this.therapy = therapy;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
