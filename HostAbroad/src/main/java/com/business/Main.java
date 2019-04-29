package com.business;

import java.util.TreeSet;

import com.business.ASUser.ASUserImp;
import com.business.enums.CountriesEnum;
import com.business.enums.DurationOfStayEnum;
import com.business.enums.KnowledgesEnum;
import com.business.transfers.THost;
import com.business.transfers.TTraveler;
import com.business.transfers.TUser;
import com.presentation.commands.CommandEnum.Commands;
import com.presentation.controller.Controller;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//ASUserImp asUser = new ASUserImp();
		//asUser.createUser(new TUser("Veronika", "Veronika Yanova", "veronikayankova3@gmail.com", "roniponi"));
		//ASLikesImp asLikes = new ASLikesImp();
		
//		TreeSet<InterestsEnum> l = new TreeSet<InterestsEnum>();
//		l.add(InterestsEnum.Animals);
//		l.add(InterestsEnum.Nature);
//		asUser.modifyInterests(new TUser("Veronika", l));
//		System.out.println("Interest modified");
		
		//asUser.editTravelerInformation(new TTraveler("prueba", null, null, null));
		//TUser u = asUser.loginUser(new TUser("saninazer@gmail.com", "Sanii12"));
		//asUser.addPlace(new TPlace("asd", "asd", null, "", FamilyUnit.Fam1, "sani"));
//		ArrayList<InterestsEnum> interests = new ArrayList<InterestsEnum>();
//
//		interests.add(InterestsEnum.Animals);
//		asUser.editHostInformation(new THost("Veronika", interests));
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
		//ASMatchesImp saMatches = new ASMatchesImp();
		//saMatches.acceptLike(new TMatches("Sani", "Roni"));
		//System.out.println(asUser.readMyMatches(new TUser("Roni")).get(0).getNickname());
//		TPlace place = new TPlace("Calle Moondo", "I am a place", new ArrayList(), "", FamilyUnit.Alone, "Veronika");
//		Pair<Integer,Object> pair = Controller.getInstance().action(Commands.CommandAddPlace, place);
//		System.out.println("Added place:" + pair.getRight());
		
		TreeSet<CountriesEnum> interests = new TreeSet<CountriesEnum>();
		interests.add(CountriesEnum.Albania);
		
		TreeSet<KnowledgesEnum> interests2 = new TreeSet<KnowledgesEnum>();
		interests2.add(KnowledgesEnum.Biology);
		
		
//		ASUserImp asu = new ASUserImp();
//		System.out.println(asu.readHostInformation(new TUser("User")).getNickname());
		Controller.getInstance().action(Commands.CommandEditTraveler, new TTraveler("Veronika", interests, interests2, DurationOfStayEnum.MoreThanMonth));
	}
}
