package com.presentation.commands;

import com.business.ASFactory.ASFactory;
import com.business.ASUser.ASUser;
import com.business.transfers.TUser;

public class CommandModifyBasicInformation extends Command{

	public Pair<Integer, Object> execute(Object transfer) {
		ASUser as = ASFactory.getInstance().createASUser();
		
		boolean res = as.modifyInformation((TUser)transfer);
		int result = res ? 1 : 0;
		
		return new Pair<Integer, Object>(result, res);
	}

}
