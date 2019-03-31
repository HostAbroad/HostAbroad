package com.business;

import java.util.ArrayList;

import com.business.ASUser.ASUserImp;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ASUserImp asUser = new ASUserImp();
		
		//asUser.createUser(new TUser("sani", "Gasan Nazer", "saninazer@gmail.com", "sanii12"));
		
		//asUser.editTravelerInformation(new TTraveler("prueba", null, null, null));
		//TUser u = asUser.loginUser(new TUser("saninazer@gmail.com", "Sanii12"));
		asUser.addPlace(new TPlace("asd", "asd", null, "", FamilyUnit.Fam1, "sani"));
		//ArrayList<InterestsEnum> interests = new ArrayList<InterestsEnum>();
		//interests.add(InterestsEnum.Int1);
		//asUser.editHostInformation(new THost("sani", interests));
	}
}
