package com.business.businessObjects;

import java.io.Serializable;

public class InterestsKey implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String user;
	private String interest;
	
	public InterestsKey() {}
	
	public InterestsKey(String user, String interest) {
		this.user = user;
		this.interest = interest;
	}
	
	@Override
	public int hashCode() {
		return user.hashCode() + interest.hashCode();
	}
	
	@Override
	public boolean equals(Object key) {
		return this.interest.equals(((LanguagesKey)key).getLanguage()) 
				&& this.user.equals(((LanguagesKey)key).getUser());
	}
	
	public String getUser() {
		return this.user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public String getInterest() {
		return this.interest;
	}
	
	public void setInterest(String interest) {
		this.interest = interest;
	}
}
