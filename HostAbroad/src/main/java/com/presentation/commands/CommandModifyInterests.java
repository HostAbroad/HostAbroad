package com.presentation.commands;

import com.business.ASFactory.ASFactory;
import com.business.ASUser.ASUser;
import com.business.transfers.TUser;

public class CommandModifyInterests extends Command{

	@Override
	public Pair<Integer, Object> execute(Object transfer) {
		ASUser as = ASFactory.getInstance().createASUser();
		as.modifyInterests((TUser)transfer);
		
		return new Pair<Integer, Object>(1, null);
	}

}
