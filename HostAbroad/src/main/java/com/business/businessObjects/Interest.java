package com.business.businessObjects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

import com.business.businessObjects.InterestsKey;
import com.business.businessObjects.UserHA;

@Entity @IdClass(InterestsKey.class)
public class Interest implements Comparable<Interest>{
	@Id
	@ManyToOne
	private UserHA user;
	
	@Id
	private String interest;
	
	public Interest(){}
	
	public Interest(UserHA user, String interest) {
		this.user = user;
		this.interest = interest;
	}
	
	public UserHA getUser() {
		return this.user;
	}
	
	public void setUser(UserHA user) {
		this.user = user;
	}
	
	public String getInterest() {
		return this.interest;
	}
	
	public void setInterest(String interest) {
		this.interest = interest;
	}

	@Override
	public int compareTo(Interest lang) {
		return this.interest.compareTo(lang.getInterest());
	}
	
}
