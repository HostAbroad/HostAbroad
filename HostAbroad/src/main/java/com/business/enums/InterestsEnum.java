package com.business.enums;

public enum InterestsEnum {

	Movies("Movies"),
	Reading("Reading"),
	Sports("Sports"),
	Traveling("Traveling"),
	Gaming("Gaming"),
	Fashion("Fashion"),
	Makeup("Makeup"),
	Photography("Photography"),
	Music("Photography"),
	Gastronomy("Gastronomy"),
	Partying("Partying"),
	Animals("Partying"),
	Nature("Nature"),
	Arts("Arts"),
	Cars("Cars");
	
	private String name;
	
	private InterestsEnum(String name) {
		this.name = name;
	}
	
	public String getString() {
		return this.name;
	}
}