package com.presentation.commands;

import com.business.ASFactory.ASFactory;
import com.business.ASUser.ASUser;
import com.business.transfers.TTraveler;
import com.business.transfers.TUser;

public class CommandReadTravelerInformation extends Command {

	@Override
	public Pair<Integer, Object> execute(Object transfer) {
		int result;
        ASUser saUser = ASFactory.getInstance().createASUser();     //Create SA
        TTraveler traveler = saUser.readTravelerInformation((TUser)transfer);
        result = traveler != null  ? 1 : 0; // Return value 1 when the user has is traveler and return 0 when the user is not.

        return new Pair<>(result, traveler);
	}

}
