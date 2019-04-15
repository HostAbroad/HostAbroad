package com.presentation.commands;

import com.business.ASFactory.ASFactory;
import com.business.ASLikes.ASLikes;
import com.business.ASMatches.ASMatches;
import com.business.transfers.TLikes;
import com.business.transfers.TMatches;

public class CommandAcceptLike extends Command{

	@Override
	public Pair<Integer, Object> execute(Object transfer) {
		
        ASMatches asMatches = ASFactory.getInstance().createASMatches();    
        boolean accept = asMatches.acceptLike((TMatches) transfer);
        int result = accept ? 1 : 0;
        
        return new Pair<>(result,transfer);
	}

}
