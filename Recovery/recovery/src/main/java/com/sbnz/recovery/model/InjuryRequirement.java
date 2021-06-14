package com.sbnz.recovery.model;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.sbnz.recovery.model.enums.InjuryBodyPart;
import com.sbnz.recovery.model.enums.PhysicalActivity;

@Entity
public class InjuryRequirement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "lower_age_boundry")
	private int lowerAgeBoundry;
	
	@Column(name = "upper_age_boundry")
	private int upperAgeBoundry;
	
	@ManyToOne
    @JoinColumn(name = "injury_type_id", referencedColumnName = "id", nullable = false)
	private InjuryType injuryType;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "injury_req_body_part", joinColumns = @JoinColumn(name = "injury_req_id", referencedColumnName = "id"))
	private Set<InjuryBodyPart> injuryBodyPart;
	
	@Column(name = "activity_after_injury")
	private PhysicalActivity activityAfterInjury;

	public InjuryRequirement() {
	}

	public InjuryRequirement(Long id, int lowerAgeBoundry, int upperAgeBoundry, InjuryType injuryType,
			Set<InjuryBodyPart> injuryBodyPart, PhysicalActivity activityAfterInjury) {
		this.id = id;
		this.lowerAgeBoundry = lowerAgeBoundry;
		this.upperAgeBoundry = upperAgeBoundry;
		this.injuryType = injuryType;
		this.injuryBodyPart = injuryBodyPart;
		this.activityAfterInjury = activityAfterInjury;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getLowerAgeBoundry() {
		return lowerAgeBoundry;
	}
	public void setLowerAgeBoundry(int lowerAgeBoundry) {
		this.lowerAgeBoundry = lowerAgeBoundry;
	}
	public int getUpperAgeBoundry() {
		return upperAgeBoundry;
	}
	public void setUpperAgeBoundry(int upperAgeBoundry) {
		this.upperAgeBoundry = upperAgeBoundry;
	}
	public InjuryType getInjuryType() {
		return injuryType;
	}
	public void setInjuryType(InjuryType injuryType) {
		this.injuryType = injuryType;
	}
	public Set<InjuryBodyPart> getInjuryBodyPart() {
		return injuryBodyPart;
	}
	public void setInjuryBodyPart(Set<InjuryBodyPart> injuryBodyPart) {
		this.injuryBodyPart = injuryBodyPart;
	}
	public PhysicalActivity getActivityAfterInjury() {
		return activityAfterInjury;
	}
	public void setActivityAfterInjury(PhysicalActivity activityAfterInjury) {
		this.activityAfterInjury = activityAfterInjury;
	}
}
