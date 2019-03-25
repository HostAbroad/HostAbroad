package com.presentation.commands;

import java.util.ArrayList;

import com.business.TUser;
import com.business.ASFactory.ASFactory;
import com.business.ASSearch.ASSearch;

public class CommandSearchHost extends Command{

	@Override
	public Pair<Integer, Object> execute(Object transfer) {
		
		int result;
		ASSearch saSearch = ASFactory.getInstance().createASSearch();
		ArrayList<TUser> hosts = saSearch.searchHost();
		result = hosts.size() <= 0 ? 0 : 1;
		
		return new Pair<Integer,Object>(result,hosts);
	}

}
