package com.business.businessObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import com.business.enums.CountriesEnum;
import com.business.enums.DurationOfStayEnum;
import com.business.enums.KnowledgesEnum;
import com.business.transfers.TTraveler;

@Entity
@Table
public class Traveler{

	@Id
	@OneToOne 
	private UserHA user;
	@Version
	private int version;
	@OneToMany(mappedBy = "user")
	private List<Country> listOfCountries;
	@OneToMany(mappedBy = "user")
	private List<KnowledgeTraveler> listOfKnowledges;
	private DurationOfStayEnum durationOfStay;

	public Traveler() {};

	public Traveler(int version, UserHA user, ArrayList<Country> listOfCountries, 
			ArrayList<KnowledgeTraveler> listOfKnowledges, DurationOfStayEnum durationOfStay) {
		this.version = version;
		this.user = user;
		this.listOfCountries = listOfCountries;
		this.listOfKnowledges = listOfKnowledges;
		this.durationOfStay = durationOfStay;
	}

	public Traveler(UserHA user, ArrayList<Country> listOfCountries, 
			ArrayList<KnowledgeTraveler> listOfKnowledges, DurationOfStayEnum durationOfStay) {
		this.user = user;
		this.listOfCountries = listOfCountries;
		this.listOfKnowledges = listOfKnowledges;
		this.durationOfStay = durationOfStay;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public UserHA getUser() {
		return user;
	}

	public void setUser(UserHA user) {
		this.user = user;
	}

	public void setListOfCountries(ArrayList<Country> listOfCountries) {
		this.listOfCountries = listOfCountries;
	}

	public List<Country> getListOfCountries() {
		return this.listOfCountries;
	}

	public void setListOfKnowledges(ArrayList<KnowledgeTraveler> listOfKnowledges) {
		this.listOfKnowledges = listOfKnowledges;
	}

	public List<KnowledgeTraveler> getListOfKnowledges() {
		return this.listOfKnowledges;
	}

	public void setDurationOfStay(DurationOfStayEnum durationOfStay) {
		this.durationOfStay = durationOfStay;
	}

	public DurationOfStayEnum getDurationOfStay() {
		return this.durationOfStay;
	}

	public TTraveler toTransfer() {
		TreeSet<KnowledgesEnum> myKnowledges = new TreeSet<KnowledgesEnum>();
		for(KnowledgeTraveler k : this.getListOfKnowledges())
			myKnowledges.add(KnowledgesEnum.setToEnum(k.getKnowledge()));
		
		TreeSet<CountriesEnum> myCountries = new TreeSet<CountriesEnum>();
		for(Country c : this.getListOfCountries())
			myCountries.add(CountriesEnum.setToEnum(c.getCountry()));
		
		return new TTraveler(this.user.getNickname(), myCountries, 
				myKnowledges, this.durationOfStay);
	}
}