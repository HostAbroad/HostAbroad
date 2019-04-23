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
	private UserHA userOne;
	@ManyToOne
	private UserHA userTwo;
	@Version
	private Integer version;

	public Matches() {}

	public Matches(int id, UserHA userOne, UserHA userTwo) {
		this.id = id;
		this.userOne = userOne;
		this.userTwo = userTwo;
	}

	public Matches(UserHA userOne, UserHA userTwo) {
		this.userOne = userOne;
		this.userTwo = userTwo;
	}

	public UserHA getUserOne() {
		return userOne;
	}

	public void setUserOne(UserHA userOne) {
		this.userOne = userOne;
	}

	public UserHA getUserTwo() {
		return userTwo;
	}

	public void setUserTwo(UserHA userTwo) {
		this.userTwo = userTwo;
	}

	public TMatches toTransfer() {
		return new TMatches(this.userOne.getNickname(), this.userTwo.getNickname());
	}
	
	public void setVersion(Integer version) {
		this.version = version;
	}
	
	public Integer getVersion() {
		return this.version;
	}
}