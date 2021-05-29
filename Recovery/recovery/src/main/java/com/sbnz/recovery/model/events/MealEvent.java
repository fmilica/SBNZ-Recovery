package com.sbnz.recovery.model.events;

import java.io.Serializable;
import java.util.Date;

import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

@Role(Role.Type.EVENT)
@Timestamp("executionTime")
public class MealEvent implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Date executionTime;
	private String username;
	private Double totalCalories;
	
	public MealEvent() {
		super();
	}

	public MealEvent(String username, Double totalCalories) {
		super();
		this.executionTime = new Date();
		this.username = username;
		this.totalCalories = totalCalories;
	}

	public MealEvent(Date executionTime, String username, Double totalCalories) {
		super();
		this.executionTime = executionTime;
		this.username = username;
		this.totalCalories = totalCalories;
	}

	public Date getExecutionTime() {
		return executionTime;
	}

	public void setExecutionTime(Date executionTime) {
		this.executionTime = executionTime;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Double getTotalCalories() {
		return totalCalories;
	}

	public void setTotalCalories(Double totalCalories) {
		this.totalCalories = totalCalories;
	}
}
