package com.business.transfers;

public class TMatches {

	private String userSender;
	private String userReceiver;

	public TMatches() {}

	public TMatches(String userSender, String userReceiver) {
		this.userSender = userSender;
		this.userReceiver = userReceiver;
	}

	public String getUserSender() {
		return userSender;
	}

	public void setUserOne(String userSender) {
		this.userSender = userSender;
	}

	public String getUserReceiver() {
		return userReceiver;
	}

	public void setUserReceiver(String userReceiver) {
		this.userReceiver = userReceiver;
	}
}