package com.business.businessObjects;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import com.business.transfers.TMatches;

@Entity
@Table
public class Matches {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	private UserHA userSender;
	@ManyToOne
	private UserHA userReceiver;
	@Version
	private Integer version;

	public Matches() {}

	public Matches(int id, UserHA userSender, UserHA userReceiver) {
		this.id = id;
		this.userSender = userSender;
		this.userReceiver = userReceiver;
	}

	public Matches(UserHA userSender, UserHA userReceiver) {
		this.userSender = userSender;
		this.userReceiver = userReceiver;
	}
	
	public Integer getId() {
		return id;
	}

	public UserHA getUserSender() {
		return userSender;
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

	public TMatches toTransfer() {
		return new TMatches(this.userSender.getNickname(), this.userReceiver.getNickname());
	}
	
	public void setVersion(Integer version) {
		this.version = version;
	}
	
	public Integer getVersion() {
		return this.version;
	}
}