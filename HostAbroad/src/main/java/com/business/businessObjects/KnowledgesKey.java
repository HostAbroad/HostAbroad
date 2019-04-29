package com.business.businessObjects;

import java.io.Serializable;

public class KnowledgesKey implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String user;
	private String knowledge;
	
	public KnowledgesKey() {}
	
	public KnowledgesKey(String user, String knowledge) {
		this.user = user;
		this.knowledge = knowledge;
	}
	
	@Override
	public int hashCode() {
		return user.hashCode() + knowledge.hashCode();
	}
	
	@Override
	public boolean equals(Object key) {
		return this.knowledge.equals(((LanguagesKey)key).getLanguage()) 
				&& this.user.equals(((LanguagesKey)key).getUser());
	}
	
	public String getUser() {
		return this.user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public String getKnoledge() {
		return this.knowledge;
	}
	
	public void setKnoledge(String knowledge) {
		this.knowledge = knowledge;
	}
}
