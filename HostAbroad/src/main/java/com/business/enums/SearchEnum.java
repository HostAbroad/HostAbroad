package com.business.enums;

public enum SearchEnum {
	isHost ("HOST = 1"),
	isTraveler ("TRAVELER = 1");
	
	private String name;
	
	private SearchEnum(String name) {
		this.name = name;
	}
	
	public String getString() {
		return this.name;
	}
}
