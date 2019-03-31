package com.business.businessObjects;

import java.util.*;
import javax.persistence.Entity;
import javax.persistence.Id; 
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.business.enums.FamilyUnit;
 
@Entity 
@Table
public class Place {
	@Id
	private String address;
	private String description;
	private ArrayList<Date> noAvaliableDates;
	private String photo;
	private FamilyUnit familyUnit;
	@ManyToOne
	private Host host; 
	
	public Place(){};

	//full constructor
	public Place(String address, String description, ArrayList<Date> noAvaliableDates, 
			String photo, FamilyUnit familyUnit, Host host) {
		super();
		this.address = address;
		this.description = description;
		this.noAvaliableDates = noAvaliableDates;
		this.photo = photo;
		this.familyUnit = familyUnit; 
		this.host = host;
	}

	public String getAddress() {
		return address; 
	}

	public void setAddress(String address) {
		this.address = address; 
	}

	public String getDescription() {
		return description; 
	}

	public void setDescription(String description) {
		this.description = description; 
	}

	public ArrayList<Date> getNoAvaliableDates() {
		return noAvaliableDates; 
	}

	public void setNoAvaliableDates(ArrayList<Date> noAvaliableDates) {
		this.noAvaliableDates = noAvaliableDates; 
	}

	public String getPhoto() {
		return photo; 
	}
 
	public void setPhoto(String photo) {
		this.photo = photo;
	}
 
	public FamilyUnit isFamilyUnit() {
		return familyUnit;
	}

	public void setFamilyUnit(FamilyUnit familyUnit) {
		this.familyUnit = familyUnit;
	} 
	
	public void setHost(Host host) {
		this.host = host;
	}
	
	public Host getHost() {
		return this.host;
	}
}