package com.sbnz.recovery.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Illness implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "ingredient_id", referencedColumnName = "id", nullable = true)
	private Ingredient ingredient;
	
//	@ManyToOne
//	@JoinColumn(name = "patient_id", referencedColumnName = "id", nullable = true)
	@ManyToMany
	@JoinTable(
	  name = "patient_illness", 
	  joinColumns = @JoinColumn(name = "illness_id"), 
	  inverseJoinColumns = @JoinColumn(name = "patient_id"))
	private List<Patient> patients;
	
	//@ManyToOne
	//@JoinColumn(name = "therapy_id", referencedColumnName = "id", nullable = true)
	@ManyToMany(mappedBy = "applicableIllness")
	private List<Therapy> therapies;

	public Illness() {
		super();
		this.patients = new ArrayList<Patient>();
	}
	
	public Illness(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
		this.patients = new ArrayList<Patient>();
	}

	public Illness(Long id, String name, Ingredient ingredient, List<Therapy> therapies) {
		super();
		this.id = id;
		this.name = name;
		this.ingredient = ingredient;
		this.therapies = therapies;
		this.patients = new ArrayList<Patient>();
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

	public Ingredient getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	public List<Patient> getPatient() {
		return patients;
	}

	public void setPatient(List<Patient> patient) {
		this.patients = patient;
	}
	
	public void addPatient(Patient patient) {
		this.patients.add(patient);
	}

	public List<Therapy> getTherapies() {
		return therapies;
	}

	public void setTherapies(List<Therapy> therapies) {
		this.therapies = therapies;
	}
}
