package com.business;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Version;

@Entity
@Table
public class Host {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY) 
	private int id; 
	@Version
	private int version;
	@OneToOne 
	private User user;
	private ArrayList<InterestsEnum> listOfInterests;
	
	
	public Host() {}
	
	public Host(ArrayList<InterestsEnum> listOfInterests) {
		this.listOfInterests = listOfInterests;
	}
	
	public Host(int id, User user, ArrayList<InterestsEnum> listOfInterests) {
		this.id = id;
		this.user = user;
		this.listOfInterests = listOfInterests;
	}
	
	public Host(User user, ArrayList<InterestsEnum> listOfInterests) {
		this.user = user;
		this.listOfInterests = listOfInterests;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	public int getVersion() {
		return this.version;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return this.user;
	}
	
	public void setListOfInterests(ArrayList<InterestsEnum> arrayList) {
		this.listOfInterests = arrayList;
	}
	
	public ArrayList<InterestsEnum> getListOfInterests() {
		return this.listOfInterests;
	}
	
	
}
