package com.sbnz.recovery.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	private Set<Ingredient> ingredients;
	
//	@ManyToOne
//	@JoinColumn(name = "patient_id", referencedColumnName = "id", nullable = true)
//	@ManyToMany
//	@JoinTable(
//	  name = "patient_illness", 
//	  joinColumns = @JoinColumn(name = "illness_id"), 
//	  inverseJoinColumns = @JoinColumn(name = "patient_id"))
	@ManyToMany(mappedBy = "anamnesis")
	private Set<Patient> patients;
	
	//@ManyToOne
	//@JoinColumn(name = "therapy_id", referencedColumnName = "id", nullable = true)
	@ManyToMany(mappedBy = "applicableIllness")
	private Set<Therapy> therapies;

	public Illness() {
		this.patients = new HashSet<Patient>();
		this.ingredients = new HashSet<Ingredient>();
		this.therapies = new HashSet<Therapy>();
	}
	
	public Illness(Long id, String name) {
		this.id = id;
		this.name = name;
		this.patients = new HashSet<Patient>();
		this.ingredients = new HashSet<Ingredient>();
		this.therapies = new HashSet<Therapy>();
	}

	public Illness(Long id, String name, Set<Ingredient> ingredients, Set<Therapy> therapies) {
		this.id = id;
		this.name = name;
		this.ingredients = ingredients;
		this.therapies = therapies;
		this.patients = new HashSet<Patient>();
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

	public Set<Patient> getPatient() {
		return patients;
	}

	public void setPatient(Set<Patient> patient) {
		this.patients = patient;
	}
	
	public void addPatient(Patient patient) {
		this.patients.add(patient);
	}

	public Set<Therapy> getTherapies() {
		return therapies;
	}

	public void setTherapies(Set<Therapy> therapies) {
		this.therapies = therapies;
	}

	public Set<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Set<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public Set<Patient> getPatients() {
		return patients;
	}

	public void setPatients(Set<Patient> patients) {
		this.patients = patients;
	}

	@Override
	public int hashCode() {
		return id.intValue();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Illness other = (Illness) obj;
		if (id == other.id) {
			return true;
		}
		return false;
	}
}
