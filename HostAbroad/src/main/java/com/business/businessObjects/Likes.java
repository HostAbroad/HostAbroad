package com.business.businessObjects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import com.business.transfers.TLikes;

@Entity
@Table
public class Likes {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	private User userSender;
	@ManyToOne
	private User userReceiver;
	@Version
	private Integer version;

	public Likes() {}

	public Likes(int id, User userSender, User userReceiver) {
		this.id = id;
		this.userSender = userSender;
		this.userReceiver = userReceiver;
	}

	public Likes(User userSender, User userReceiver) {
		this.userSender = userSender;
		this.userReceiver = userReceiver;
	}

	public User getUserSender() {
		return userSender;
	}

	public void setUserSender(User userSender) {
		this.userSender = userSender;
	}

	public User getUserReceiver() {
		return userReceiver;
	}

	public void setUserReceiver(User userReceiver) {
		this.userReceiver = userReceiver;
	}

	public TLikes toTransfer() {
		return new TLikes(this.userSender.getNickname(), this.userReceiver.getNickname());
	}
	
	public void setVersion(Integer version) {
		this.version = version;
	}
	
	public Integer getVersion() {
		return this.version;
	}
}