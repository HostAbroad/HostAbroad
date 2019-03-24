package com.presentation.commands;

import com.business.ASFactory.ASFactory;

public class CommandLogin extends Command {

	@Override
	public Pair<Integer, Object> execute(Object transfer) {
		int result;
		ASUser saUser = ASFactory.getInstance().createASUser();
		boolean loged = saUser.loginUser();
		result = loged ? 0 : 1;
		
		return new Pair<Integer,Object>(result,loged);
	}

}