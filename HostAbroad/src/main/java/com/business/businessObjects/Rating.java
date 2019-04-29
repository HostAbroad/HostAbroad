package com.business.businessObjects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import com.business.transfers.TRating;

@Entity @IdClass(RatingKey.class)
public class Rating {
	
	@Id
	@ManyToOne
	private UserHA userSender;
	@Id
	@ManyToOne
	private UserHA userReceiver;
	
	private Integer rate;

	public Rating() {}

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

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}
}
