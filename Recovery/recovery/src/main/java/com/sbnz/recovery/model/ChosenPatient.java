package com.sbnz.recovery.model;

public class ChosenPatient {

	private Long chosenPatientId;
	private boolean resolved;
	
	public ChosenPatient(Long chosenPatientId) {
		this.chosenPatientId = chosenPatientId;
		this.resolved = false;
	}
	
	public Long getChosenPatientId() {
		return chosenPatientId;
	}
	public void setChosenPatientId(Long chosenPatientId) {
		this.chosenPatientId = chosenPatientId;
	}
	public boolean isResolved() {
		return resolved;
	}
	public void setResolved(boolean resolved) {
		this.resolved = resolved;
	}
}
