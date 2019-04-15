package com.presentation.commands;

import com.business.ASFactory.ASFactory;
import com.business.ASMatches.ASMatches;
import com.business.transfers.TMatches;

public class CommandDeclineLike extends Command {

	@Override
	public Pair<Integer, Object> execute(Object transfer) {
		
		 ASMatches asMatches = ASFactory.getInstance().createASMatches();    
	     boolean decline = asMatches.declineLike((TMatches) transfer);
	     int result = decline ? 1 : 0;
	       
	     return new Pair<>(result,transfer);
	}

}
