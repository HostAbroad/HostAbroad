package com.presentation.commands;

import com.business.ASFactory.ASFactory;
import com.business.ASUser.ASUser;
import com.business.transfers.THost;

public class CommandEditHost extends Command {

	@Override
	public Pair<Integer, Object> execute(Object transfer) {

		 int result;

	     ASUser saCreate = ASFactory.getInstance().createASUser(); //Create SA
	     boolean created = saCreate.editHostInformation((THost)transfer);
	     result = created ? 1 : 0; // Return value 1 when the edit has been modified

	     return new Pair<>(result,created);
	}

}