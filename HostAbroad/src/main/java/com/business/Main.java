package com.business;

import java.util.ArrayList;

import com.business.ASLikes.ASLikesImp;
import com.business.ASUser.ASUserImp;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ASUserImp asUser = new ASUserImp();
		ASLikesImp asLikes = new ASLikesImp();
		//asUser.createUser(new TUser("Roni", "Veronika Yanova", "veronikayankova3@gmail.com", "roniponi"));
		
		//asUser.editTravelerInformation(new TTraveler("prueba", null, null, null));
		//TUser u = asUser.loginUser(new TUser("saninazer@gmail.com", "Sanii12"));
		//asUser.addPlace(new TPlace("asd", "asd", null, "", FamilyUnit.Fam1, "sani"));
		//ArrayList<InterestsEnum> interests = new ArrayList<InterestsEnum>();
		//interests.add(InterestsEnum.Int1);
		//asUser.editHostInformation(new THost("sani", interests));
		
		asLikes.sendLike(new TLikes("sani", "Roni"));
	}
}
