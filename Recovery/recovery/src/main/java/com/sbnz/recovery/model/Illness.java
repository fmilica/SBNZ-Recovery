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

@Entity
public class Illness implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name")
	private String name;
	
//	@JoinColumn(name = "ingredient_id", referencedColumnName = "id", nullable = true)
	@ManyToMany(mappedBy = "illnesses")
	private List<Ingredient> ingredients;
	
//	@ManyToOne
//	@JoinColumn(name = "patient_id", referencedColumnName = "id", nullable = true)
//	@ManyToMany
//	@JoinTable(
//	  name = "patient_illness", 
//	  joinColumns = @JoinColumn(name = "illness_id"), 
//	  inverseJoinColumns = @JoinColumn(name = "patient_id"))
	@ManyToMany(mappedBy = "anamnesis")
	private List<Patient> patients;
	
	//@ManyToOne
	//@JoinColumn(name = "therapy_id", referencedColumnName = "id", nullable = true)
	@ManyToMany(mappedBy = "applicableIllness")
	private List<Therapy> therapies;

	public Illness() {
		super();
		this.patients = new ArrayList<Patient>();
		this.ingredients = new ArrayList<Ingredient>();
		this.therapies = new ArrayList<Therapy>();
	}
	
	public Illness(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
		this.patients = new ArrayList<Patient>();
		this.ingredients = new ArrayList<Ingredient>();
		this.therapies = new ArrayList<Therapy>();
	}

	public Illness(Long id, String name, List<Ingredient> ingredients, List<Therapy> therapies) {
		super();
		this.id = id;
		this.name = name;
		this.ingredients = ingredients;
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

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public List<Patient> getPatients() {
		return patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}
}
