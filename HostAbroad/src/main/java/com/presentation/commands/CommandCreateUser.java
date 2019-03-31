package com.presentation.commands;

import com.business.ASFactory.ASFactory;
import com.business.ASUser.ASUser;
import com.business.transfers.TUser;

public class CommandCreateUser extends Command {
    @Override
    public Pair<Integer, Object> execute(Object transfer) {
        int result;
        ASUser saCreate = ASFactory.getInstance().createASUser();     //Create SA
        boolean created = saCreate.createUser((TUser)transfer);
        result = created ? 1 : 0; // Return value 1 when the user has been created and return 0 when the user has not been created.

        return new Pair<>(result,created);
    }
}
