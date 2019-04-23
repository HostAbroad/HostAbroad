package com.presentation.commands;

import com.business.ASFactory.ASFactory;
import com.business.ASUser.ASUser;
import com.business.transfers.TRating;

public class CommandRateUser extends Command {

	@Override
	public Pair<Integer, Object> execute(Object transfer) {
		int result;
        ASUser saUser = ASFactory.getInstance().createASUser();
        boolean rated = saUser.rateUser((TRating)transfer);
        result = rated ? 1 : 0; //Si se ha valorado result es 1, sino 0

        return new Pair<>(result, transfer);
	}

}
