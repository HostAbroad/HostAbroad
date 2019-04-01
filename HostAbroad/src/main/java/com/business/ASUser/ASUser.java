package com.business.ASUser;

import java.util.ArrayList;

import com.business.transfers.THost;
import com.business.transfers.TPlace;
import com.business.transfers.TTraveler;
import com.business.transfers.TUser;

public interface ASUser {
	
	public abstract boolean createUser(TUser user);

	public abstract TUser loginUser(TUser user);
	
	public abstract boolean editHostInformation(THost tHost);

	public abstract boolean editTravelerInformation(TTraveler tTraveler);
	
	public abstract boolean addPlace(TPlace place);
	
	public abstract THost readHostInformation(TUser user);
	
	public abstract TTraveler readTravelerInformation(TUser user);
	
	public abstract ArrayList<TUser> SendersLike(TUser tUser);
}
