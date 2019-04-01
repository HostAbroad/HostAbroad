package com.presentation.commands;

import java.util.ArrayList;

import com.business.ASFactory.ASFactory;
import com.business.ASSearch.ASSearch;
import com.business.ASUser.ASUser;
import com.business.transfers.TUser;

public class CommandSendersLike extends Command {

	@Override
	public Pair<Integer, Object> execute(Object transfer) {
		int result;
		ASUser asUser= ASFactory.getInstance().createASUser();
		ArrayList<TUser> sendersLike = asUser.SendersLike((TUser)transfer);
		result = sendersLike.isEmpty() ? 0 : 1;
		
		return new Pair<Integer,Object>(result,sendersLike);
	}

}
