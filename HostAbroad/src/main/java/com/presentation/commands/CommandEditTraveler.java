package com.presentation.commands;

import com.business.ASFactory.ASFactory;
import com.business.ASUser.ASUser;
import com.business.transfers.TTraveler;

public class CommandEditTraveler extends Command {

	@Override
	public Pair<Integer, Object> execute(Object transfer) {
		
		TTraveler travelerInfo = (TTraveler)transfer;
		ASUser asUser = ASFactory.getInstance().createASUser(); //crea ASUser
		boolean edited = asUser.editTravelerInformation(travelerInfo);
		
		int result = (edited) ? 1 : 0;
		
		return new Pair<Integer,Object>(result,transfer);
	}

}

