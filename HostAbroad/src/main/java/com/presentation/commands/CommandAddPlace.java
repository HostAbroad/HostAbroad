package com.presentation.commands;

import com.business.ASFactory.ASFactory;
import com.business.ASUser.ASUser;
import com.business.transfers.TPlace;

public class CommandAddPlace extends Command{

	@Override
	public Pair<Integer, Object> execute(Object transfer) {
		int result;

	     ASUser saUser = ASFactory.getInstance().createASUser(); 
	     boolean added = saUser.addPlace((TPlace)transfer);
	     result = added ? 1 : 0; 

	     return new Pair<>(result, added);
	}

}
