package com.sbnz.recovery.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sbnz.recovery.model.enums.InjuryBodyPart;

public class InjuryDTO {

	private Long id;
	private String name;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate startDate;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate endDate;
	private String description;
	private Long injuryTypeId;
	private InjuryBodyPart injuryBodyPart;
	
	public InjuryDTO() {
	}
	
	public InjuryDTO(Long id, String name, LocalDate startDate, LocalDate endDate, String description, Long injuryTypeId,
			InjuryBodyPart injuryBodyPart) {
		this.id = id;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
		this.injuryTypeId = injuryTypeId;
		this.injuryBodyPart = injuryBodyPart;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getInjuryTypeId() {
		return injuryTypeId;
	}
	public void setInjuryTypeId(Long injuryTypeId) {
		this.injuryTypeId = injuryTypeId;
	}
	public InjuryBodyPart getInjuryBodyPart() {
		return injuryBodyPart;
	}
	public void setInjuryBodyPart(InjuryBodyPart injuryBodyPart) {
		this.injuryBodyPart = injuryBodyPart;
	}
}
