package com.presentation.commands;

import com.business.ASFactory.ASFactory;
import com.business.ASUser.ASUser;
import com.business.transfers.TUser;

public class CommandReadUserNickName extends Command{

	@Override
	public Pair<Integer, Object> execute(Object transfer) {
		int result;
        ASUser saUser = ASFactory.getInstance().createASUser();     
        TUser userNick = saUser.readUserNickName((TUser)transfer);
        result = userNick != null  ? 1 : 0;
        
        return new Pair<>(result, userNick);
	}

}
