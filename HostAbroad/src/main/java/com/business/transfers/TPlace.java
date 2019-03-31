package com.business.transfers;

import java.util.ArrayList;
import java.util.Date;

import com.business.enums.FamilyUnit;

public class TPlace { 
	private String address;
	private String description;
	private ArrayList<Date> noAvaliableDates;
	private String photo;
	private FamilyUnit familyUnit;
	private String nickname;
	
	public TPlace() {}
	
	public TPlace(String addres, String description, ArrayList<Date> noAvailableDates,
			String photo, FamilyUnit familuUnit, String nickname) {
		this.address = addres;
		this.description = description;
		this.noAvaliableDates = noAvailableDates;
		this.photo = photo;
		this.familyUnit = familuUnit;
		this.nickname = nickname;
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

	public FamilyUnit getFamilyUnit() { 
		return familyUnit;
	}

	public void setFamilyUnit(FamilyUnit familyUnit) { 
		this.familyUnit = familyUnit;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getNickname() {
		return this.nickname;
	}
}