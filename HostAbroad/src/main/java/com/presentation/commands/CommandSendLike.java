package com.presentation.commands;

import com.business.ASFactory.ASFactory;
import com.business.ASLikes.ASLikes;
import com.business.transfers.TLikes;

public class CommandSendLike extends Command{

	@Override
	public Pair<Integer, Object> execute(Object transfer) {
		 int result;
	        ASLikes asLikes = ASFactory.getInstance().createASLikes();    
	        boolean like = asLikes.sendLike((TLikes)transfer);
	        result = like ? 1 : 0; 

	        return new Pair<>(result,like);
	}

}
