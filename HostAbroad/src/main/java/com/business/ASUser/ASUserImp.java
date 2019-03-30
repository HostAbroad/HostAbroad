package com.business.ASUser;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.business.Host;
import com.business.THost;
import com.business.TTraveler;
import com.business.TUser;
import com.business.Traveler;
import com.business.User;

public class ASUserImp implements ASUser {

	
	/**
	*This method checks if a user 
	 **/
	@Override
	public boolean createUser(TUser tUser) {

		boolean result = false;

		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("HostAbroad");
			EntityManager em = emf.createEntityManager();
			EntityTransaction tr = em.getTransaction();
			tr.begin();

			try {
				String query = "SELECT * FROM USER WHERE NICKNAME = ?1 OR EMAIL = ?2";
				User user = (User)em.createNativeQuery(query, User.class)
						.setParameter(1, tUser.getNickname()).setParameter(2, 
								tUser.getEmail()).getSingleResult();
				user = new User(tUser.getNickname(), tUser.getFullName(), tUser.getEmail(), 
				tUser.getPassword().hashCode());
				em.persist(user);
				result = true;
			}catch(NoResultException e) {}
			
			tr.commit();
			em.close();
			emf.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		return result;
	}
	
	/**
 	* 
 	*/
	@Override
	public TUser loginUser(TUser tUser) {
		TUser logedUser = null;

		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("HostAbroad");
			EntityManager em = emf.createEntityManager();
			EntityTransaction tr = em.getTransaction();
			tr.begin();
			
			String consulta = "SELECT * FROM USER WHERE email = ?1 AND password = ?2";
			User result;
			try {
				result = (User)em.createNativeQuery(consulta, User.class)
						.setParameter(1, tUser.getEmail()).setParameter(2, 
								tUser.getPassword().hashCode()).getSingleResult();
				logedUser = new TUser();
				
				logedUser.setNickname(result.getNickname());
				logedUser.setFullName(result.getFullName());
				logedUser.setPassword(result.getPassword().toString());
				logedUser.setRating(result.getRating());
        		logedUser.setDescription(result.getDescription());
        		logedUser.setHost(result.getHost());
				logedUser.setTraveler(result.getTraveler());
        		logedUser.setEmail(result.getEmail());
			} catch (NoResultException ex) {
				System.out.println(ex.getMessage());
			}
			
			tr.commit();
			em.close();
			emf.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return logedUser;
	}
	
	/**
	 This method receives tHost with nickname and list of interests.
	 If a user with that nickname doesn't exist the method returns false;
	 If the user exists, then it is checked if the host exists, in which case we just modify the interests.
	 If host does not exist, a new one is created with the corresponding nickname.
	 The user's atribute host (boolean) is updated only if needed.
	**/
	@Override
	public boolean editHostInformation(THost tHost) { 
		
		boolean updated = false;
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("HostAbroad");
		EntityManager em = emfactory.createEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		
		User user = em.find(User.class, tHost.getNickname());
		
		if(user != null) {
			Host host;
			
			try {
				String query = "SELECT * FROM HOST WHERE USER_NICKNAME = ?1";
				host = (Host)em.createNativeQuery(query, Host.class).setParameter(1, tHost.getNickname()).getSingleResult();
				host.setListOfInterests(tHost.getListOfInterests());
			}catch(NoResultException e) {
				host = new Host(user, tHost.getListOfInterests());
			}
			
			em.persist(host);
			
			if(!user.getHost()) {
				user.setHost(true);
				user.setHostEntity(host);
				em.persist(user);
			}
			
			updated = true;
		}
		
		try{
			em.getTransaction().commit();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		em.close();
		emfactory.close();
		
		return updated;
	}

	@Override
	public boolean editTravelerInformation(TTraveler tTraveler) {
		boolean updated = false;
		
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("HostAbroad");
		EntityManager em = emfactory.createEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		
		User user = em.find(User.class, tTraveler.getNickname());
		
		if(user != null) {
			Traveler traveler;
			
			try {
				String query = "SELECT * FROM TRAVELER WHERE USER_NICKNAME = ?1";
				traveler = (Traveler)em.createNativeQuery(query, Traveler.class).setParameter(1, tTraveler.getNickname()).getSingleResult();
				traveler.setDurationOfStay(tTraveler.getDurationOfStay());
				traveler.setListOfCountries(tTraveler.getListOfCountries());
				traveler.setListOfKnowledges(tTraveler.getListOfKnowledges());
			}catch(NoResultException e) {
				traveler = new Traveler(user, tTraveler.getListOfCountries(), tTraveler.getListOfKnowledges(), tTraveler.getDurationOfStay());
			}
			
			em.persist(traveler);
			
			if(!user.getTraveler()) {
				user.setTraveler(true);
				user.setTravelerEntity(traveler);
				em.persist(user);
			}
			
			updated = true;
		}
		
		try{
			em.getTransaction().commit();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		em.close();
		emfactory.close();
		
		return updated;
	}
	
}
