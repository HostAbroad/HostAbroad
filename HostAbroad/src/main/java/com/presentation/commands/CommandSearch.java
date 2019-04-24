package com.presentation.commands;

import java.util.ArrayList;

import com.business.ASFactory.ASFactory;
import com.business.ASSearch.ASSearch;
import com.business.enums.SearchEnum;
import com.business.transfers.TUser;

public class CommandSearch extends Command{

	@Override
	public Pair<Integer, Object> execute(Object searchArray) {
		int result;
		ASSearch saSearch = ASFactory.getInstance().createASSearch();
		ArrayList<TUser> res = saSearch.search((ArrayList<SearchEnum>)searchArray);
		result = res.size() <= 0 ? 0 : 1;
		
		return new Pair<Integer,Object>(result,res);
	}

}
