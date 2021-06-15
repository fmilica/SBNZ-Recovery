package com.sbnz.recovery.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class InjuryType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name")
	private String name;
	
	//@ManyToOne
	//@JoinColumn(name = "therapy_id", referencedColumnName = "id", nullable = true)
	@ManyToMany(mappedBy = "applicableInjury")
	private List<Therapy> therapies;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "injuryType")
	private List<Injury> injury;

	public InjuryType() {
	}
	
	public InjuryType(String name) {
		this.name = name;
	}
	
	public InjuryType(Long id) {
		this.id = id;
	}

	public InjuryType(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public InjuryType(Long id, String name, List<Therapy> therapies) {
		super();
		this.id = id;
		this.name = name;
		this.therapies = therapies;
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

	public List<Therapy> getTherapies() {
		return therapies;
	}

	public void setTherapies(List<Therapy> therapies) {
		this.therapies = therapies;
	}
}
