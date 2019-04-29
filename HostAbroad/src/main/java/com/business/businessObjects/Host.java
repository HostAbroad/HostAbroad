package com.business.businessObjects;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.business.enums.KnowledgesEnum;
import com.business.transfers.THost;

@Entity
@Table
public class Host {
	
	@Id
	@OneToOne 
	private UserHA user;
	
	@OneToMany(mappedBy = "user")
	private List<KnowledgeHost> listOfKnowledges;
	
	@OneToMany(mappedBy="host")
	private Collection<Place> places;
	
	public Host() {}
	
	//full constructor
	public Host(UserHA user, ArrayList<KnowledgeHost> listOfKnowledges,
			ArrayList<Place> places) {
		this.user = user;
		this.listOfKnowledges = listOfKnowledges;
		this.places = places;
	}
	
	public Host(ArrayList<KnowledgeHost> listOfKnowledges) {
		this.listOfKnowledges = listOfKnowledges;
	}
	
	public Host(UserHA user, ArrayList<KnowledgeHost> listOfKnowledges) {
		this.user = user;
		this.listOfKnowledges = listOfKnowledges;
	}
	
	public void setUser(UserHA user) {
		this.user = user;
	}
	
	public UserHA getUser() {
		return this.user;
	}
	
	public void setListOfKnowledges(ArrayList<KnowledgeHost> listOfKnowledges) {
		this.listOfKnowledges = listOfKnowledges;
	}
	
	public List<KnowledgeHost> getListOfKnowledges() {
		return this.listOfKnowledges;
	}
	
	public void setPlaces(ArrayList<Place> places) {
		this.places = places;
	}
	
	public Collection<Place> getPlaces(){
		return this.places;
	}
	
	public THost toTransfer() {
		TreeSet<KnowledgesEnum> myKnowledges = new TreeSet<KnowledgesEnum>();
		for(KnowledgeHost k : this.getListOfKnowledges())
			myKnowledges.add(KnowledgesEnum.setToEnum(k.getKnowledge()));
			
		return new THost(this.user.getNickname(), myKnowledges);
	}
}
