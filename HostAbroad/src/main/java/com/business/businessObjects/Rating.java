package com.business.businessObjects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import com.business.transfers.TRating;

@Entity
@Table
public class Rating {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	private UserHA userSender;
	@ManyToOne
	private UserHA userReceiver;
	@Version
	private Integer version;
	
	private Integer rate;

	public Rating() {}

	public Rating(int id, UserHA userSender, UserHA userReceiver, Integer rate) {
		this.id = id;
		this.userSender = userSender;
		this.userReceiver = userReceiver;
		this.rate = rate;
	}

	public Rating(UserHA userSender, UserHA userReceiver, Integer rate) {
		this.userSender = userSender;
		this.userReceiver = userReceiver;
		this.rate = rate;
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

	public TRating toTransfer() {
		return new TRating(this.userSender.getNickname(), this.userReceiver.getNickname(), this.rate);
	}
	
	public void setVersion(Integer version) {
		this.version = version;
	}
	
	public Integer getVersion() {
		return this.version;
	}

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}
}
