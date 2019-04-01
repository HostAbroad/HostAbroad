package com.presentation.commands;

import com.business.ASFactory.ASFactory;
import com.business.ASUser.ASUser;
import com.business.transfers.THost;
import com.business.transfers.TUser;

public class CommandReadHostInformation extends Command {

	@Override
	public Pair<Integer, Object> execute(Object transfer) {
		int result;
        ASUser saUser = ASFactory.getInstance().createASUser();     //Create SA
        THost host = saUser.readHostInformation((TUser)transfer);
        result = host != null  ? 1 : 0; // Return value 1 when the user has is host and return 0 when the user is not.

        return new Pair<>(result, host);
	}

}
