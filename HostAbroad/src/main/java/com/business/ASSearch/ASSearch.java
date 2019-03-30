package com.business.ASSearch;

import java.util.ArrayList;

import com.business.TUser;

public interface ASSearch {
	
	public abstract TUser searchHostByName(String nickname);
	
	public abstract ArrayList<TUser> searchHost();
	
	public abstract ArrayList<TUser> searchTraveler();

	public abstract ArrayList<TUser> searchTraveler();

}
