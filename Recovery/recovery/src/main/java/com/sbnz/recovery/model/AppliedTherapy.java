package com.sbnz.recovery.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "applied_therapy")
public class AppliedTherapy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="application_date")
	private LocalDate applicationDate;
	
	@Column(name="therapy")
	private Therapy therapy;
	
	@ManyToOne
	@JoinColumn(name = "injury_id", referencedColumnName = "id", nullable = true)
	private Injury injury;
	
	public AppliedTherapy() {
	}

	public AppliedTherapy(LocalDate applicationDate, Therapy therapy) {
		super();
		this.applicationDate = applicationDate;
		this.therapy = therapy;
	}
	
	public AppliedTherapy(Long id, LocalDate applicationDate, Therapy therapy) {
		super();
		this.id = id;
		this.applicationDate = applicationDate;
		this.therapy = therapy;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getApplicationDate() {
		return applicationDate;
	}
	public void setApplicationDate(LocalDate applicationDate) {
		this.applicationDate = applicationDate;
	}
	public Therapy getTherapy() {
		return therapy;
	}
	public void setTherapy(Therapy therapy) {
		this.therapy = therapy;
	}
}
