package com.business;

import java.util.ArrayList;
import java.util.TreeSet;

import com.business.ASUser.ASUserImp;
import com.business.enums.LanguagesEnum;
import com.business.transfers.TUser;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ASUserImp asUser = new ASUserImp();
		//ASLikesImp asLikes = new ASLikesImp();
		//asUser.createUser(new TUser("Roni", "Veronika Yanova", "veronikayankova3@gmail.com", "roniponi"));
		TreeSet<LanguagesEnum> l = new TreeSet<LanguagesEnum>();
		l.add(LanguagesEnum.Spanish);
		l.add(LanguagesEnum.Spanish);
		l.add(LanguagesEnum.Bulgarian);
		l.add(LanguagesEnum.Spanish);
		asUser.modifyInformation(new TUser("Roni", "Veronika Yanova",  "roniponi", "veronikayankova3@gmail.com",
				null, null, null, 5, false, false, null, null, l, null));
		
		//asUser.editTravelerInformation(new TTraveler("prueba", null, null, null));
		//TUser u = asUser.loginUser(new TUser("saninazer@gmail.com", "Sanii12"));
		//asUser.addPlace(new TPlace("asd", "asd", null, "", FamilyUnit.Fam1, "sani"));
		//ArrayList<InterestsEnum> interests = new ArrayList<InterestsEnum>();

		//interests.add(InterestsEnum.Int1);
		//asUser.editHostInformation(new THost("sani", interests));
		//System.out.println(asUser.readHostInformation(new TUser("sani", 10, "", true)).getNickname());;
		//asLikes.sendLike(new TLikes("sani", "Roni"));
		
		//for modify basic info
		//TUser tuser = new TUser("sani", "Gasan", "gasan@gasan.gasan", "I am Gasan", "photo.jpg");// positive
		//TUser tuser2 = new TUser("sani", "Gasan2", "roni@roni.roni", "I am Gasan", "photo.jpg");// negative
		//TUser tuser3 = new TUser("sani", "sani", "sani@sani.sani", "I am Sani", "photo.jpg");// positive
		//Pair<Integer,Object> result = Controller.getInstance().action(Commands.CommandModifyBasicInformation, tuser3);
		//System.out.println("Is edited: " + result.getRight());

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
