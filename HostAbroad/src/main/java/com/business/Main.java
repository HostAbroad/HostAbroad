package com.business;

import java.util.ArrayList;

import com.business.ASUser.ASUserImp;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ASUserImp asUser = new ASUserImp();
		
		ArrayList<InterestsEnum> listOfInterests = new ArrayList<InterestsEnum>();
		listOfInterests.add(InterestsEnum.Int2);
		
		asUser.editHostInformation(new THost("prueba", listOfInterests));
		
		
	}

}
