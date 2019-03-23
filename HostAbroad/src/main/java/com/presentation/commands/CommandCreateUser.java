package com.presentation.commands;

import com.business.ASFactory.ASFactory;
import com.business.ASUser.ASUser;
import com.business.TUser;



public class CommandCreateUser extends Command {
    @Override
    public Pair<Integer, Object> execute(Object transfer) {
        int result;
        ASUser saCreate = ASFactory.getInstance().createASUser();     //Create SA USer
        boolean created = saCreate.createUser((TUser)transfer);
        result = created ? 0 : 1; // Return value 0 when the user has been created and return 1 when the user has not been created.

        return new Pair<>(result,created);
    }
}
