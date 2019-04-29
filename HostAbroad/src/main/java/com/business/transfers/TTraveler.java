package com.business.transfers;

import java.util.TreeSet;

import com.business.enums.CountriesEnum;
import com.business.enums.DurationOfStayEnum;
import com.business.enums.KnowledgesEnum;

public class TTraveler{

	private String nickname;
	private TreeSet<CountriesEnum> listOfCountries;
	private TreeSet<KnowledgesEnum> listOfKnowledges;
	private DurationOfStayEnum durationOfStay;

	public TTraveler() {};

	public TTraveler(String nickname, TreeSet<CountriesEnum> listOfCountries, 
			TreeSet<KnowledgesEnum> listOfKnowledges, DurationOfStayEnum durationOfStay) {
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

	public void setListOfCountries(TreeSet<CountriesEnum> listOfCountries) {
		this.listOfCountries = listOfCountries;
	}

	public TreeSet<CountriesEnum> getListOfCountries() {
		return this.listOfCountries;
	}

	public void setListOfKnowledges(TreeSet<KnowledgesEnum> listOfKnowledges) {
		this.listOfKnowledges = listOfKnowledges;
	}

	public TreeSet<KnowledgesEnum> getListOfKnowledges() {
		return this.listOfKnowledges;
	}

	public void setDurationOfStay(DurationOfStayEnum durationOfStay) {
		this.durationOfStay = durationOfStay;
	}

	public DurationOfStayEnum getDurationOfStay() {
		return this.durationOfStay;
	}
}