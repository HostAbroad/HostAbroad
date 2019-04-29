package com.business.businessObjects;

import java.io.Serializable;

public class LikesKey implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String userSender;
	private String userReceiver;
	
	public LikesKey() {}
	
	public LikesKey(String userSender, String userReceiver) {
		this.userSender = userSender;
		this.userReceiver = userReceiver;
	}
	
	@Override
	public int hashCode() {
		return userSender.hashCode() + userReceiver.hashCode();
	}
	
	@Override
	public boolean equals(Object key) {
		return this.userReceiver.equals(((LanguagesKey)key).getLanguage()) 
				&& this.userSender.equals(((LanguagesKey)key).getUser());
	}
	
	public String getUserSender() {
		return this.userSender;
	}
	
	public void setUserSender(String userSender) {
		this.userSender = userSender;
	}
	
	public String getUserReceiver() {
		return this.userReceiver;
	}
	
	public void setUserReceiver(String userReceiver) {
		this.userReceiver = userReceiver;
	}
}
