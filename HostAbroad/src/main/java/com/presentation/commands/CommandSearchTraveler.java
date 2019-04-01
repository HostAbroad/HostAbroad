package com.presentation.commands;

import java.util.ArrayList;

import com.business.ASFactory.ASFactory;
import com.business.ASSearch.ASSearch;
import com.business.transfers.TUser;

public class CommandSearchTraveler extends Command{

	@Override
	public Pair<Integer, Object> execute(Object transfer) {
		int result;
		ASSearch saSearch = ASFactory.getInstance().createASSearch();
		ArrayList<TUser> travelers = saSearch.searchTraveler();
		result = travelers.size() <= 0 ? 0 : 1;
		
		return new Pair<Integer,Object>(result,travelers);
	}

}
