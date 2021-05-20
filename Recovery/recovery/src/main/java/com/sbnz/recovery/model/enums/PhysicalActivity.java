package com.sbnz.recovery.model.enums;

public enum PhysicalActivity {
	//SEDENTARY, LIGHT_ACTIVE, MODERATE, VERY_ACTIVE, EXTRA_ACTIVE
	SEDENTARY  (0),
	LIGHT_ACTIVE (1),
	MODERATE   (2),
	VERY_ACTIVE (3),
	EXTRA_ACTIVE (4)
	;
	
	private final int activityCode;
	
	PhysicalActivity(int activityCode) {
	    this.activityCode = activityCode;
	}
	
	public int getActivityCode() {
	    return this.activityCode;
	}
}