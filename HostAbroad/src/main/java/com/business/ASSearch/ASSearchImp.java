package com.business.ASSearch;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.business.businessObjects.UserHA;
import com.business.enums.SearchEnum;
import com.business.transfers.TUser;

public class ASSearchImp implements ASSearch {
	
	public ArrayList<TUser> search() {
		ArrayList<TUser> list = new ArrayList<TUser>();

		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("HostAbroad");
			EntityManager em = emf.createEntityManager();
			EntityTransaction tr = em.getTransaction();
			tr.begin();

			String consult = "SELECT * FROM USERHA;";
			
			Query query = em.createNativeQuery(consult, UserHA.class);
			
			try {
				List<UserHA> resultList = query.getResultList();
				for(UserHA user : resultList){
					
					list.add(new TUser(user.getNickname(),
										user.getRating(),
										user.getDescription(),
										user.getHost(),
										user.getTraveler()));
				}
				tr.commit();
			}
			catch(NoResultException e){
				System.out.println("NO ENCONTRADO");
			}	
			
			em.close();
			emf.close();
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		
		return list;
	}

	@Override
	public ArrayList<TUser> search(ArrayList<SearchEnum> searchEnums) {
		if(searchEnums.isEmpty())
			return search();
		
		ArrayList<TUser> list = new ArrayList<TUser>();

		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("HostAbroad");
			EntityManager em = emf.createEntityManager();
			EntityTransaction tr = em.getTransaction();
			tr.begin();

			StringBuilder consult = new StringBuilder("SELECT * FROM USERHA WHERE ");
			
			for(int i = 0; i < searchEnums.size() - 1; i++) {
				consult.append(searchEnums.get(i).getString() + " AND ");
			}
			consult.append(searchEnums.get(searchEnums.size() - 1).getString() + ";");
			Query query = em.createNativeQuery(consult.toString(), UserHA.class);
			
			try {
				List<UserHA> resultList = query.getResultList();
				for(UserHA user : resultList){
					
					list.add(new TUser(user.getNickname(),
										user.getRating(),
										user.getDescription(),
										user.getHost(),
										user.getTraveler()));
				}
				tr.commit();
			}
			catch(NoResultException e){
				System.out.println("NO ENCONTRADO");
			}	
			
			em.close();
			emf.close();
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		
		return list;
	}
}
