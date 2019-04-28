package com.business.enums;

public enum DurationOfStayEnum {

	ZeroToSixDays("1-6 days"),
	OneToTwoWeeks("1-2 weeks"),
	TwoWeeksToAMonth("2 weeks to a month"),
	MoreThanMonth("More than a month");
	
	private String name;
	
	private DurationOfStayEnum(String name) {
		this.name = name;
	}
	
	public String getString() {
		return this.name;
	}
}