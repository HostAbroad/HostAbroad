package com.presentation.commands;

import com.business.ASUser.ASUser;
import com.business.transfers.TUser;
import com.business.ASFactory.ASFactory;

public class CommandLogin extends Command {

    @Override
    public Pair<Integer, Object> execute(Object transfer) {
        int result;
        ASUser saUser = ASFactory.getInstance().createASUser();
        TUser tUser = saUser.loginUser((TUser) transfer);
        result = tUser != null ? 1 : 0;

        return new Pair<Integer,Object>(result,tUser);
    }

}