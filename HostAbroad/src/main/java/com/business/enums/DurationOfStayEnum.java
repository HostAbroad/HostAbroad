package com.business.enums;

public enum DurationOfStayEnum {

	ZeroToSixDays(0.0),
	OneToTwoWeeks(1.0),
	TwoWeeksToAMonth(2.0),
	MoreThanMonth(3.0);
	
	private Double value;
	
	private DurationOfStayEnum(Double value) {
		this.value = value;
	}
	
	public Double getValue() {
		return this.value;
	}
	
}