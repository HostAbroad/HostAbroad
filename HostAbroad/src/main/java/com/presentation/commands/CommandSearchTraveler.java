package com.presentation.commands;

import java.util.ArrayList;

import com.business.TUser;
import com.business.ASFactory.ASFactory;
import com.business.ASSearch.ASSearch;

public class CommandSearchTraveler extends Command{
	
	public Pair<Integer, Object> execute(Object transfer) {
		
		int result;
		ASSearch saSearch = ASFactory.getInstance().createASSearch();
		ArrayList<TUser> hosts = saSearch.searchTraveler();
		System.out.println(hosts.size());
		result = hosts.size() <= 0 ? 0 : 1;
		
		return new Pair<Integer,Object>(result,hosts);
	}

}
