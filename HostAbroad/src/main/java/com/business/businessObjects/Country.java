package com.business.businessObjects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

@Entity @IdClass(CountryKey.class)
public class Country implements Comparable<Country>{
	@Id
	@ManyToOne
	private Traveler user;
	
	@Id
	private String country;
	
	public Country(){}
	
	public Country(Traveler user, String country) {
		this.user = user;
		this.country = country;
	}
	
	public Traveler getUser() {
		return this.user;
	}
	
	public void setUser(Traveler user) {
		this.user = user;
	}
	
	public String getCountry() {
		return this.country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public int compareTo(Country country) {
		return this.country.compareTo(country.getCountry());
	}
	
}
