package com.business.businessObjects;

import java.io.Serializable;

import org.h2.engine.User;

import net.bytebuddy.build.HashCodeAndEqualsPlugin;

public class LanguagesKey implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String user;
	private String language;
	
	public LanguagesKey() {}
	
	public LanguagesKey(String user, String language) {
		this.user = user;
		this.language = language;
	}
	
	@Override
	public int hashCode() {
		return user.hashCode() + language.hashCode();
	}
	
	@Override
	public boolean equals(Object key) {
		return this.language.equals(((LanguagesKey)key).getLanguage()) 
				&& this.user.equals(((LanguagesKey)key).getUser());
	}
	
	public String getUser() {
		return this.user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public String getLanguage() {
		return this.language;
	}
	
	public void setLanguage(String language) {
		this.language = language;
	}
}

