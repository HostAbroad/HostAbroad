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
	
	@Override
	public TUser searchHostByName(String nickname) {
		TUser tUser = new TUser();
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("HostAbroad");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		
		try {
			String queryString = "SELECT user FROM User user WHERE user.nickname = :nickname";
			Query query = em.createQuery(queryString);
			query.setParameter("nickname", nickname);
			UserHA user = null;
			try {
				user =  (UserHA) query.getSingleResult();
			}
			catch (NoResultException ex) {
				System.out.println(ex.getMessage());
			}
			if(user != null){
				tUser.setNickname(user.getNickname());
				tUser.setRating(user.getRating());
				tUser.setDescription(user.getDescription());
				tUser.setHost(user.getHost());
			}
		}
		catch (NoResultException e) {
			System.out.println(e.getMessage());
		}
			
		return tUser;
	}


	@Override
	public ArrayList<TUser> searchHost() {
		ArrayList<TUser> list = new ArrayList<TUser>();
		
		
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("HostAbroad");
			EntityManager em = emf.createEntityManager();
			EntityTransaction tr = em.getTransaction();
			tr.begin();

			String consult = "SELECT * FROM User WHERE host = 1;";
			
			Query query = em.createNativeQuery(consult, UserHA.class);
			
			
			try {
				List<UserHA> resultList = query.getResultList();
				System.out.println(resultList.size() +"A");
				for(UserHA user : resultList){
					
					list.add(new TUser(user.getNickname(),
										user.getRating(),
										user.getDescription(),
										user.getHost(),
										user.getTraveler()));
				}
				tr.commit();
				System.out.println(resultList.toString());
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
	public ArrayList<TUser> searchTraveler() {
		
		ArrayList<TUser> list = new ArrayList<TUser>();
		
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("HostAbroad");
			EntityManager em = emf.createEntityManager();
			EntityTransaction tr = em.getTransaction();
			tr.begin();

			String consult = "SELECT * FROM User WHERE traveler = 1;";
			Query query = em.createNativeQuery(consult, UserHA.class);
		
			try {
				@SuppressWarnings("unchecked")
				List<UserHA> resultList = query.getResultList();
				for(UserHA user : resultList){
					
					list.add(new TUser(user.getNickname(),
										user.getRating(),
										user.getDescription()));
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
