package com.business.businessObjects;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

@Entity @IdClass(MatchesKey.class)
public class Matches {

	@Id
	@ManyToOne
	private UserHA userSender;
	
	@Id
	@ManyToOne
	private UserHA userReceiver;

	public Matches() {}

	public Matches(UserHA userSender, UserHA userReceiver) {
		this.userSender = userSender;
		this.userReceiver = userReceiver;
	}
	
	public UserHA getUserSender() {
		return this.userSender;
	}


	public void setUserSender(UserHA userSender) {
		this.userSender = userSender;
	}

	public UserHA getUserReceiver() {
		return userReceiver;
	}

	public void setUserReceiver(UserHA userReceiver) {
		this.userReceiver = userReceiver;
	}
}