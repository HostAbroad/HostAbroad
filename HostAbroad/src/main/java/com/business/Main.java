package com.business;

import java.util.ArrayList;

import com.business.ASLikes.ASLikesImp;
import com.business.ASSearch.ASSearchImp;
import com.business.ASUser.ASUserImp;
import com.business.enums.InterestsEnum;
import com.business.enums.SearchEnum;
import com.business.transfers.THost;
import com.business.transfers.TUser;
import com.presentation.commands.CommandSearch;

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
		//interests.add(InterestsEnum.Animals);
		//asUser.editHostInformation(new THost("Roni", interests));
		//System.out.println(asUser.readHostInformation(new TUser("sani", 10, "", true)).getNickname());;
		//asLikes.sendLike(new TLikes("sani", "Roni"));
		
		//CommandSearch comS = new CommandSearch();
		//ArrayList<SearchEnum> s = new ArrayList();
		//s.add(SearchEnum.isHost);
		//
		//System.out.println(((ArrayList<TUser>)comS.execute(s).getRight()).get(0).getNickname());
		}
}
