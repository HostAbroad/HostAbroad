package com.business.ASFactory;

import com.business.ASSearch.ASSearch;
import com.business.ASSearch.ASSearchImp;
import com.business.ASUser.ASUser;
import com.business.ASUser.ASUserImp;

public class ASFactoryImp extends ASFactory {	
	
	public ASSearch createASSearch() {
		return new ASSearchImp();
	}

	public ASUser createASUser() {
		return ASUserImp();
	}
	
	

}
