package com.sbnz.recovery.dto;

import java.util.List;

public class ViewTherapyDTO {

	private TherapyDTO therapyInfo;
	private List<String> applicableIllness;
	private List<String> applicableInjury;
	
	public ViewTherapyDTO(TherapyDTO therapyInfo, List<String> applicableIllness, List<String> applicableInjury) {
		this.therapyInfo = therapyInfo;
		this.applicableIllness = applicableIllness;
		this.applicableInjury = applicableInjury;
	}
	
	public TherapyDTO getTherapyInfo() {
		return therapyInfo;
	}
	public void setTherapyInfo(TherapyDTO therapyInfo) {
		this.therapyInfo = therapyInfo;
	}
	public List<String> getApplicableIllness() {
		return applicableIllness;
	}
	public void setApplicableIllness(List<String> applicableIllness) {
		this.applicableIllness = applicableIllness;
	}
	public List<String> getApplicableInjury() {
		return applicableInjury;
	}
	public void setApplicableInjury(List<String> applicableInjury) {
		this.applicableInjury = applicableInjury;
	}
}
