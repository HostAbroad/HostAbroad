package com.business.ASUser;

import com.business.TUser;

public interface ASUser {
	
	public abstract boolean createUser(TUser user);

	public abstract TUser loginUser(TUser user);
	
}
