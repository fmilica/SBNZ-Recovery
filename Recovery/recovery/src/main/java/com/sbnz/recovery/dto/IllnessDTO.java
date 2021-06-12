package com.sbnz.recovery.dto;

public class IllnessDTO {

	private Long id;
	private String name;
	
	public IllnessDTO() {
	}
	
	public IllnessDTO(Long id) {
		this.id = id;
	}

	public IllnessDTO(Long id, String name) {
		this.id = id;
		this.name = name;
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
}
