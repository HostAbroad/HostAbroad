package com.business.businessObjects;

import java.io.Serializable;

public class CountryKey implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String user;
	private String country;
	
	public CountryKey() {}
	
	public CountryKey(String user, String country) {
		this.user = user;
		this.country = country;
	}
	
	@Override
	public int hashCode() {
		return user.hashCode() + country.hashCode();
	}
	
	@Override
	public boolean equals(Object key) {
		return this.country.equals(((LanguagesKey)key).getLanguage()) 
				&& this.user.equals(((LanguagesKey)key).getUser());
	}
	
	public String getUser() {
		return this.user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public String getCountry() {
		return this.country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
}
