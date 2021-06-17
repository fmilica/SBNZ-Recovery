package com.sbnz.recovery.dto;

import java.time.LocalDate;

public class InjuryCountDTO {

	private LocalDate startDate;
	private LocalDate endDate;
	
	private int startAge;
	private int endAge;
	
	private Long injuryTypeId;
	
	public InjuryCountDTO() {
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
	public int getStartAge() {
		return startAge;
	}
	public void setStartAge(int startAge) {
		this.startAge = startAge;
	}
	public int getEndAge() {
		return endAge;
	}
	public void setEndAge(int endAge) {
		this.endAge = endAge;
	}
	public Long getInjuryTypeId() {
		return injuryTypeId;
	}
	public void setInjuryTypeId(Long injuryTypeId) {
		this.injuryTypeId = injuryTypeId;
	}
	
}
