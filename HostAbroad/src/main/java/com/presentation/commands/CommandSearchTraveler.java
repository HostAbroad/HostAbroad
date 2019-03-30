package com.presentation.commands;

import java.util.ArrayList;

import com.business.TUser;
import com.business.ASFactory.ASFactory;
import com.business.ASSearch.ASSearch;

public class CommandSearchTraveler extends Command{

	@Override
	public Pair<Integer, Object> execute(Object transfer) {
		int result;
		ASSearch saSearch = ASFactory.getInstance().createASSearch();
		ArrayList<TUser> travelers = saSearch.searchTraveler();
		result = travelers == null ? 0 : 1;
		
		return new Pair<Integer,Object>(result,travelers);
	}

}
