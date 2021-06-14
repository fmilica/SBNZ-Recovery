package com.sbnz.recovery.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.sbnz.recovery.model.enums.InjuryBodyPart;

@Entity
public class Injury implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="start_date")
	private LocalDate startDate;
	
	@Column(name="end_date")
	private LocalDate endDate;
	
	@Column(name="description")
	private String description;
	
	@ManyToOne
    @JoinColumn(name = "injury_type_id", referencedColumnName = "id", nullable = false)
	private InjuryType injuryType;
	
	@Column(name="injury_body_parts")
	private InjuryBodyPart injuryBodyPart;
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY, mappedBy = "injury")
	private List<AppliedTherapy> appliedTherapies;
	
	@ManyToOne
	@JoinColumn(name = "patient_id", referencedColumnName = "id", nullable = false)
	private Patient patient;
	
	@Transient
	private boolean proccesed;
	
	public Injury() {
		super();
	}

	public Injury(String name, LocalDate startDate, LocalDate endDate, String description, InjuryType injuryType,
			InjuryBodyPart injuryBodyPart) {
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
		this.injuryType = injuryType;
		this.injuryBodyPart = injuryBodyPart;
		this.appliedTherapies = new ArrayList<AppliedTherapy>();
	}
	
	public Injury(Long id, String name, LocalDate startDate, LocalDate endDate, String description,
			InjuryBodyPart injuryBodyPart) {
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
		this.injuryBodyPart = injuryBodyPart;
		this.appliedTherapies = new ArrayList<AppliedTherapy>();
	}
	
	public Injury(Long id, String name, LocalDate startDate, LocalDate endDate, String description, InjuryType injuryType,
			InjuryBodyPart injuryBodyPart) {
		this.id = id;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
		this.injuryType = injuryType;
		this.injuryBodyPart = injuryBodyPart;
		this.appliedTherapies = new ArrayList<AppliedTherapy>();
	}
	
	public Injury(Long id, String name, LocalDate startDate, LocalDate endDate, String description, InjuryType injuryType,
			InjuryBodyPart injuryBodyPart, Patient patient) {
		this.id = id;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
		this.injuryType = injuryType;
		this.injuryBodyPart = injuryBodyPart;
		this.patient = patient;
	}
	
	public void addAppliedTherapy(AppliedTherapy appliedTherapy) {
		this.appliedTherapies.add(appliedTherapy);
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
	
	public LocalDate getStartDate() {
		return startDate;
	}
	
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	
	public LocalDate getEndDate() {
		return endDate;
	}
	
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public InjuryType getInjuryType() {
		return injuryType;
	}
	
	public void setInjuryType(InjuryType injuryType) {
		this.injuryType = injuryType;
	}
	
	public InjuryBodyPart getInjuryBodyPart() {
		return injuryBodyPart;
	}
	
	public void setInjuryBodyPart(InjuryBodyPart injuryBodyPart) {
		this.injuryBodyPart = injuryBodyPart;
	}
	
	public List<AppliedTherapy> getAppliedTherapies() {
		return appliedTherapies;
	}

	public void setAppliedTherapies(List<AppliedTherapy> appliedTherapies) {
		this.appliedTherapies = appliedTherapies;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public boolean isProccesed() {
		return proccesed;
	}

	public void setProccesed(boolean proccesed) {
		this.proccesed = proccesed;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Injury other = (Injury) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (injuryBodyPart != other.injuryBodyPart)
			return false;
		if (injuryType != other.injuryType)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Injury [id=" + id + ", name=" + name + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", description=" + description + ", injuryType=" + injuryType + ", injuryBodyPart=" + injuryBodyPart
				+ "]";
	}
}
