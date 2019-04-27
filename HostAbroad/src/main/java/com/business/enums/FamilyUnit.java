package com.business.enums;

public enum FamilyUnit {

	Alone("Alone"),
	Friends("Friends"),
	Family("Family");
	
	private String name;
	
	private FamilyUnit(String name) {
		this.name = name;
	}
	
	public String getString() {
		return this.name;
	}
}
