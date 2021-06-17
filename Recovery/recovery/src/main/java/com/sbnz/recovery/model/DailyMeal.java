package com.sbnz.recovery.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class DailyMeal implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="day")
	@Temporal(TemporalType.DATE)
	private Date day;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
	  name = "daily_meal_meal", 
	  joinColumns = @JoinColumn(name = "daily_meal_id"), 
	  inverseJoinColumns = @JoinColumn(name = "meal_id"))
	private Set<Meal> meals;
	
	@ManyToOne
	@JoinColumn(name = "patient_id", referencedColumnName = "id", nullable = false)
	private Patient patient;

	public DailyMeal() {
	}
	
	public DailyMeal(Date day, Set<Meal> meals) {
		super();
		this.day = day;
		this.meals = meals;
	}

	public DailyMeal(Long id, Date day, Set<Meal> meals) {
		super();
		this.id = id;
		this.day = day;
		this.meals = meals;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
	}

	public Set<Meal> getMeals() {
		return meals;
	}

	public void setMeals(Set<Meal> meals) {
		this.meals = meals;
	}
	
	public void addMeal(Meal meal) {
		if(this.meals == null) {
			this.meals = new HashSet<Meal>();
		}
		this.meals.add(meal);
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
}
