package com.business.transfers;

import java.util.ArrayList;

import com.business.enums.InterestsEnum;

public class THost {
	
	private ArrayList<InterestsEnum> listOfInterests;
	private String nickname;
	
	public THost() {}
	
	public THost(String nickname, ArrayList<InterestsEnum> listOfInterests) {
		this.nickname = nickname;
		this.listOfInterests = listOfInterests;
	}
	
	public void setListOfInterests(ArrayList<InterestsEnum> listOfInterests) {
		this.listOfInterests = listOfInterests;
	}
	
	public ArrayList<InterestsEnum> getListOfInterests() {
		return this.listOfInterests;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	
}