package com.sbnz.recovery.model.enums;

public enum Gender {
	MALE (0), 
	FEMALE(1)
	;
	
	private final int genderCode;
	
	Gender(int genderCode) {
	    this.genderCode = genderCode;
	}
	
	public int getGenderCode() {
	    return this.genderCode;
	}
}
