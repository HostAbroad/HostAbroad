package com.presentation.commands;

import com.business.ASFactory.ASFactory;
import com.business.ASSearch.ASSearch;
import com.business.TUser;

import java.util.ArrayList;

public class CommandCreateUser extends Command {
    @Override
    public Pair<Integer, Object> execute(Object transfer) {
        int result;
        ASCreate saCreate = ASFactory.getInstance().createASCreate();     //Create SA
        ArrayList<TUser> hosts = saCreate.searchHost();
        result = hosts == null ? 0 : 1;

        return new Pair<Integer,Object>(result,hosts);
    }
}
