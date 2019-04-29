package com.business.businessObjects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

@Entity @IdClass(KnowledgesKey.class)
public class KnowledgeTraveler implements Comparable<KnowledgeTraveler>{
	@Id
	@ManyToOne
	private Traveler user;
	
	@Id
	private String knowledge;
	
	public KnowledgeTraveler(){}
	
	public KnowledgeTraveler(Traveler user, String knowledge) {
		this.user = user;
		this.knowledge = knowledge;
	}
	
	public Traveler getUser() {
		return this.user;
	}
	
	public void setUser(Traveler user) {
		this.user = user;
	}
	
	public String getKnowledge() {
		return this.knowledge;
	}
	
	public void setKnowledge(String knowledge) {
		this.knowledge = knowledge;
	}

	@Override
	public int compareTo(KnowledgeTraveler knowledge) {
		return this.knowledge.compareTo(knowledge.getKnowledge());
	}
	
}
