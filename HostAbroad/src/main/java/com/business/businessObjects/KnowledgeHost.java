package com.business.businessObjects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

@Entity @IdClass(KnowledgesKey.class)
public class KnowledgeHost implements Comparable<KnowledgeHost>{
	@Id
	@ManyToOne
	private Host user;
	
	@Id
	private String knowledge;
	
	public KnowledgeHost(){}
	
	public KnowledgeHost(Host user, String knowledge) {
		this.user = user;
		this.knowledge = knowledge;
	}
	
	public Host getUser() {
		return this.user;
	}
	
	public void setUser(Host user) {
		this.user = user;
	}
	
	public String getKnowledge() {
		return this.knowledge;
	}
	
	public void setKnowledge(String knowledge) {
		this.knowledge = knowledge;
	}

	@Override
	public int compareTo(KnowledgeHost knowledge) {
		return this.knowledge.compareTo(knowledge.getKnowledge());
	}
	
}
