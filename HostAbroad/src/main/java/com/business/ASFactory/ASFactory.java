package com.business.ASFactory;

import com.business.ASLikes.ASLikes;
import com.business.ASSearch.ASSearch;
import com.business.ASUser.ASUser;

public abstract class ASFactory {
	
	private static ASFactory asFactory;
	
	public static synchronized ASFactory getInstance() {
		if (asFactory == null) {
			asFactory = new ASFactoryImp();
		}
		return asFactory;
	}
	
	public abstract ASSearch createASSearch();
	
	public abstract ASUser createASUser();
	
	public abstract ASLikes createASLikes();

}
