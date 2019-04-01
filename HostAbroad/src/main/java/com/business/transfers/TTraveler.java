package com.business.transfers;

import java.util.ArrayList;

import com.business.enums.CountriesEnum;
import com.business.enums.DurationOfStayEnum;
import com.business.enums.KnowledgesEnum;

public class TTraveler{

	private String nickname;
	private ArrayList<CountriesEnum> listOfCountries;
	private ArrayList<KnowledgesEnum> listOfKnowledges;
	private DurationOfStayEnum durationOfStay;

	public TTraveler() {};

	public TTraveler(String nickname, ArrayList<CountriesEnum> listOfCountries, 
			ArrayList<KnowledgesEnum> listOfKnowledges, DurationOfStayEnum durationOfStay) {
		this.nickname = nickname;
		this.listOfCountries = listOfCountries;
		this.listOfKnowledges = listOfKnowledges;
		this.durationOfStay = durationOfStay;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setListOfCountries(ArrayList<CountriesEnum> listOfCountries) {
		this.listOfCountries = listOfCountries;
	}

	public ArrayList<CountriesEnum> getListOfCountries() {
		return this.listOfCountries;
	}

	public void setListOfKnowledges(ArrayList<KnowledgesEnum> listOfKnowledges) {
		this.listOfKnowledges = listOfKnowledges;
	}

	public ArrayList<KnowledgesEnum> getListOfKnowledges() {
		return this.listOfKnowledges;
	}

	public void setDurationOfStay(DurationOfStayEnum durationOfStay) {
		this.durationOfStay = durationOfStay;
	}

	public DurationOfStayEnum getDurationOfStay() {
		return this.durationOfStay;
	}
}