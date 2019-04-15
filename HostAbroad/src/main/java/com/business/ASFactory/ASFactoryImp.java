package com.business.ASFactory;

import com.business.ASLikes.ASLikes;
import com.business.ASLikes.ASLikesImp;
import com.business.ASMatches.ASMatches;
import com.business.ASMatches.ASMatchesImp;
import com.business.ASSearch.ASSearch;
import com.business.ASSearch.ASSearchImp;
import com.business.ASUser.ASUser;
import com.business.ASUser.ASUserImp;

public class ASFactoryImp extends ASFactory {	
	
	public ASSearch createASSearch() {
		return new ASSearchImp();
	}

	public ASUser createASUser() {
		return new ASUserImp();
	}

	@Override
	public ASLikes createASLikes() {
		return new ASLikesImp();
	}
	
	@Override
	public ASMatches createASMatches() {
		return new ASMatchesImp();
	}
	

}
