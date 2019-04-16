package com.business.ASSearch;

import java.util.ArrayList;

import com.business.enums.SearchEnum;
import com.business.transfers.TUser;

public interface ASSearch {
	
	public abstract TUser searchHostByName(String nickname);
	
	public abstract ArrayList<TUser> searchHost();
	
	public abstract ArrayList<TUser> searchTraveler();
	
	public abstract ArrayList<TUser> search(ArrayList<SearchEnum> searchEnums);
}
