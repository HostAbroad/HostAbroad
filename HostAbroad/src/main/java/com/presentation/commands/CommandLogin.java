package com.presentation.commands;

import com.business.TUser;
import com.business.ASFactory.ASFactory;

public class CommandLogin extends Command {

	@Override
	public Pair<Integer, Object> execute(Object transfer) {
		int result;
		ASUser saUser = ASFactory.getInstance().createASUser();
		boolean loged = saUser.loginUser(transfer);
		result = loged ? 1 : 0;
		
		return new Pair<Integer,Object>(result,loged);
	}

}