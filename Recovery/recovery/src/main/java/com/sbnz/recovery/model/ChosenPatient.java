package com.sbnz.recovery.model;

import com.sbnz.recovery.model.enums.AssignType;

public class ChosenPatient {

	private Long chosenPatientId;
	private AssignType assignType;
	private Long injuryId;
	private boolean resolved;
	
	// pri registraciji
	public ChosenPatient(Long chosenPatientId) {
		this.chosenPatientId = chosenPatientId;
		this.assignType = AssignType.REGISTRATION;
		this.resolved = false;
	}
	
	// pri dodavanju/zakljucivanju povrede
	public ChosenPatient(Long chosenPatientId, Long injuryId) {
		this.chosenPatientId = chosenPatientId;
		this.injuryId = injuryId;
		this.assignType = AssignType.INJURY;
		this.resolved = false;
	}
	
	// pri dodeli obroka/terapija
	public ChosenPatient(Long chosenPatientId, AssignType assignType) {
		this.chosenPatientId = chosenPatientId;
		this.assignType = assignType;
		this.resolved = false;
	}
	
	public Long getChosenPatientId() {
		return chosenPatientId;
	}
	public void setChosenPatientId(Long chosenPatientId) {
		this.chosenPatientId = chosenPatientId;
	}
	public AssignType getAssignType() {
		return assignType;
	}
	public void setAssignType(AssignType assignType) {
		this.assignType = assignType;
	}
	public Long getInjuryId() {
		return injuryId;
	}
	public void setInjuryId(Long injuryId) {
		this.injuryId = injuryId;
	}
	public boolean isResolved() {
		return resolved;
	}
	public void setResolved(boolean resolved) {
		this.resolved = resolved;
	}
}
