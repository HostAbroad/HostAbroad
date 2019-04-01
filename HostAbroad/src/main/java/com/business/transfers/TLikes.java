package com.business.transfers;

public class TLikes {

	private String userSender;
	private String userReceiver;

	public TLikes() {}

	public TLikes(String userSender, String userReceiver) {
		this.userSender = userSender;
		this.userReceiver = userReceiver;
	}

	public String getUserSender() {
		return userSender;
	}

	public void setUserSender(String userSender) {
		this.userSender = userSender;
	}

	public String getUserReceiver() {
		return userReceiver;
	}

	public void setUserReceiver(String userReceiver) {
		this.userReceiver = userReceiver;
	}
}