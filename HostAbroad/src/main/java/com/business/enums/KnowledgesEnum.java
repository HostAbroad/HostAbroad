package com.business.enums;

public enum KnowledgesEnum {

	Programming("Programming"),
	Physics("Physics"),
	History("History"),
	Arts("Arts"),
	Music("Music"),
	Literature("Literature"),
	Biology("Biology"),
	Chemistry("Chemistry"),
	Cooking("Cooking"),
	Sports("Sports"),
	Languages("Languages");
	
	private String name;
	
	private KnowledgesEnum(String name) {
		this.name = name;
	}
	
	public String getString() {
		return this.name;
	}
}