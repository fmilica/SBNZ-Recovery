package com.sbnz.recovery.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class InjuryType {

private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "therapy_id", referencedColumnName = "id", nullable = true)
	private Therapy therapy;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "injury_id", referencedColumnName = "id")
	private Injury injury;

	public InjuryType() {
		super();
	}
	
	public InjuryType(String name) {
		super();
		this.name = name;
	}

	public InjuryType(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public InjuryType(Long id, String name, Therapy therapy) {
		super();
		this.id = id;
		this.name = name;
		this.therapy = therapy;
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

	public Therapy getTherapy() {
		return therapy;
	}

	public void setTherapy(Therapy therapy) {
		this.therapy = therapy;
	}
}
