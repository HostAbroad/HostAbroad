package com.business.businessObjects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

@Entity
@IdClass(LanguagesKey.class)
public class Language implements Comparable<Language>{

	@Id
	@ManyToOne
	private UserHA user;
	
	@Id
	private String language;
	
	public Language(){}
	
	public Language(UserHA user, String language) {
		this.user = user;
		this.language = language;
	}
	
	public UserHA getUser() {
		return this.user;
	}
	
	public void setUser(UserHA user) {
		this.user = user;
	}
	
	public String getLanguage() {
		return this.language;
	}
	
	public void setLanguage(String language) {
		this.language = language;
	}

	@Override
	public int compareTo(Language lang) {
		return this.language.compareTo(lang.getLanguage());
	}
	
}
