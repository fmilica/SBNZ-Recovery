package com.sbnz.recovery.model;

import java.io.Serializable;
import java.util.Date;

import com.sbnz.recovery.model.enums.InjuryBodyPart;
import com.sbnz.recovery.model.enums.InjuryType;

public class Injury implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private Date startDate;
	private Date endDate;
	private String description;
	private InjuryType injuryType;
	private InjuryBodyPart injuryBodyPart;
	
	public Injury() {
		super();
	}
	
	public Injury(String name, Date date, String description, InjuryType injuryType,
			InjuryBodyPart injuryBodyPart) {
		this.name = name;
		this.startDate = date;
		this.description = description;
		this.injuryType = injuryType;
		this.injuryBodyPart = injuryBodyPart;
	}

	public Injury(String name, Date startDate, Date endDate, String description, InjuryType injuryType,
			InjuryBodyPart injuryBodyPart) {
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
		this.injuryType = injuryType;
		this.injuryBodyPart = injuryBodyPart;
	}
	
	public Injury(Long id, String name, Date startDate, Date endDate, String description, InjuryType injuryType,
			InjuryBodyPart injuryBodyPart) {
		this.id = id;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
		this.injuryType = injuryType;
		this.injuryBodyPart = injuryBodyPart;
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
	
	public Date getStartDate() {
		return startDate;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
	public void setEndDate(Date endDate) {
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
