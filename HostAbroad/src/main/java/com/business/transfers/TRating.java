package com.business.transfers;

public class TRating {
	
	private String userSender;
	private String userReceiver;
	private Integer rate;

	public TRating() {}

	public TRating(String userSender, String userReceiver, Integer rate) {
		this.userSender = userSender;
		this.userReceiver = userReceiver;
		this.setRate(rate);
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

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

}
