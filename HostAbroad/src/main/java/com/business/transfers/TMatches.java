package com.business.transfers;

public class TMatches {

	private String userOne;
	private String userTwo;

	public TMatches() {}

	public TMatches(String userOne, String userTwo) {
		this.userOne = userOne;
		this.userTwo = userTwo;
	}

	public String getUserOne() {
		return userOne;
	}

	public void setUserOne(String userOne) {
		this.userOne = userOne;
	}

	public String getUserTwo() {
		return userTwo;
	}

	public void setUserTwo(String userTwo) {
		this.userTwo = userTwo;
	}
}