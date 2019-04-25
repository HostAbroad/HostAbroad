package com.presentation.commands;

import com.business.ASFactory.ASFactory;
import com.business.ASUser.ASUser;
import com.business.transfers.TTraveler;
import com.business.transfers.TUser;

public class CommandSaveImage extends Command{

	@Override
	public Pair<Integer, Object> execute(Object transfer) {
		
		TUser TUser = (TUser)transfer;
		ASUser asUser = ASFactory.getInstance().createASUser(); //crea ASUser
		boolean saved = asUser.saveImage(TUser);
		
		int result = (saved) ? 1 : 0;
		
		return new Pair<Integer,Object>(result,transfer);
	}

}
