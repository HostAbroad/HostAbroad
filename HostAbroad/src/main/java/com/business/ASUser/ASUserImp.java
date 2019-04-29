package com.business.ASUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.LockModeType;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.business.businessObjects.Country;
import com.business.businessObjects.Host;
import com.business.businessObjects.Interest;
import com.business.businessObjects.KnowledgeHost;
import com.business.businessObjects.KnowledgeTraveler;
import com.business.businessObjects.Language;
import com.business.businessObjects.Likes;
import com.business.businessObjects.Matches;
import com.business.businessObjects.Place;
import com.business.businessObjects.PlacesKey;
import com.business.businessObjects.Rating;
import com.business.businessObjects.Traveler;
import com.business.businessObjects.UserHA;
import com.business.enums.CountriesEnum;
import com.business.enums.InterestsEnum;
import com.business.enums.KnowledgesEnum;
import com.business.enums.LanguagesEnum;
import com.business.transfers.THost;
import com.business.transfers.TPlace;
import com.business.transfers.TRating;
import com.business.transfers.TTraveler;
import com.business.transfers.TUser;

public class ASUserImp implements ASUser {

	/**
	 * This method checks if a user with that nickname or password exists. the
	 * password is encrypted with hashCode
	 **/
	
	@Override
	public boolean createUser(TUser tUser) {

		boolean result = false;

		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("HostAbroad");
			EntityManager em = emf.createEntityManager();
			EntityTransaction tr = em.getTransaction();
			tr.begin();

			UserHA user;
			try {
				String query = "SELECT * FROM USERHA WHERE NICKNAME = ?1 OR EMAIL = ?2";
				user = (UserHA) em.createNativeQuery(query, UserHA.class).setParameter(1, tUser.getNickname())
						.setParameter(2, tUser.getEmail()).getSingleResult();

			} catch (NoResultException e) {
				user = new UserHA(tUser.getNickname(), tUser.getFullName(), tUser.getEmail(),
						tUser.getPassword().hashCode());
				em.persist(user);
				result = true;
			}

			tr.commit();
			em.close();
			emf.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		return result;
	}

	/**
	 * this method logs in the user if it exists
	 */
	@Override
	public TUser loginUser(TUser tUser) {
		TUser logedUser = null;

		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("HostAbroad");
			EntityManager em = emf.createEntityManager();
			EntityTransaction tr = em.getTransaction();
			tr.begin();

			String consulta = "SELECT * FROM USERHA WHERE email = ?1 AND password = ?2";
			UserHA result;
			try {
				result = (UserHA) em.createNativeQuery(consulta, UserHA.class).setParameter(1, tUser.getEmail())
						.setParameter(2, tUser.getPassword().hashCode()).getSingleResult();
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
	 * This method receives tHost with nickname and list of interests. If a user
	 * with that nickname doesn't exist the method returns false; If the user
	 * exists, then it is checked if the host exists, in which case we just modify
	 * the interests. If host does not exist, a new one is created with the
	 * corresponding nickname. The user's atribute host (boolean) is updated only if
	 * needed.
	 **/
	@Override
	public boolean editHostInformation(THost tHost) {

		boolean updated = false;
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("HostAbroad");
		EntityManager em = emfactory.createEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();

		UserHA user = em.find(UserHA.class, tHost.getNickname());

		if (user != null) {
			Host host;
			List<KnowledgeHost> oldKnowledgeHost = new ArrayList<KnowledgeHost>();
			try {
				host = em.find(Host.class, tHost.getNickname());
				oldKnowledgeHost = host.getListOfKnowledges();
			} catch (NullPointerException e) {
				host = new Host();
				host.setUser(user);
				user.setHost(true);
				em.persist(user);
				em.persist(host);
			}
			
			ArrayList<KnowledgesEnum> newKnowledges = new ArrayList<KnowledgesEnum>(tHost.getListOfKnowledges());
			int i = 0;
			int j = 0;
			Collections.sort(oldKnowledgeHost);
			
			while(i < oldKnowledgeHost.size() && j < newKnowledges.size()) {
				if(oldKnowledgeHost.get(i).getKnowledge().equals(newKnowledges.get(j).getString())) {
					i++;
					j++;
				}
				else if(oldKnowledgeHost.get(i).getKnowledge().compareTo(newKnowledges.get(j).getString()) < 0) {
					em.remove(oldKnowledgeHost.get(i));
					i++;
				}
				else {
					em.persist(new KnowledgeHost(host, newKnowledges.get(j).getString()));
					j++;
				}
			}
			
			while(i < oldKnowledgeHost.size()) {
				em.remove(oldKnowledgeHost.get(i));
				i++;
			}
			
			while(j < newKnowledges.size()) {
				em.persist(new KnowledgeHost(host, newKnowledges.get(j).getString()));
				j++;
			}

			if (!user.getHost()) {
				user.setHost(true);
				user.setHostEntity(host);
				em.persist(user);
			}

			updated = true;
		}

		try {
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		em.close();
		emfactory.close();

		return updated;
	}

	/**
	 * This method edits a traveler information
	 */
	@Override
	public boolean editTravelerInformation(TTraveler tTraveler) {
		boolean updated = false;

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("HostAbroad");
		EntityManager em = emfactory.createEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();

		UserHA user = em.find(UserHA.class, tTraveler.getNickname());

		if (user != null) {
			Traveler traveler;
			List<Country> oldCountries = new ArrayList<Country>();
			List<KnowledgeTraveler> oldKnowledges = new ArrayList<KnowledgeTraveler>();
			try {
				traveler = em.find(Traveler.class, tTraveler.getNickname());
				oldCountries = traveler.getListOfCountries();
				oldKnowledges = traveler.getListOfKnowledges();
			} catch (NullPointerException e) {
				traveler = new Traveler();
				traveler.setUser(user);
			}
			traveler.setDurationOfStay(tTraveler.getDurationOfStay());
			em.persist(traveler);
			
			ArrayList<CountriesEnum> newCountries;
			try {
				newCountries = new ArrayList<CountriesEnum>();
			}catch(NullPointerException e) {
				newCountries = new ArrayList<>();
			}
			
			int i = 0;
			int j = 0;
			Collections.sort(oldCountries);
			
			while(i < oldCountries.size() && j < newCountries.size()) {
				if(oldCountries.get(i).getCountry().equals(newCountries.get(j).getString())) {
					i++;
					j++;
				}
				else if(oldCountries.get(i).getCountry().compareTo(newCountries.get(j).getString()) < 0) {
					em.remove(oldCountries.get(i));
					i++;
				}
				else {
					em.persist(new Country(traveler, newCountries.get(j).getString()));
					j++;
				}
			}
			
			while(i < oldCountries.size()) {
				em.remove(oldCountries.get(i));
				i++;
			}
			
			while(j < newCountries.size()) {
				em.persist(new Country(traveler, newCountries.get(j).getString()));
				j++;
			}

			ArrayList<KnowledgesEnum> newKnowledges;
			try {
				newKnowledges = new ArrayList<KnowledgesEnum>(tTraveler.getListOfKnowledges());
			}catch(NullPointerException e) {
				newKnowledges = new ArrayList<KnowledgesEnum>();
			}
			
			i = 0;
			j = 0;
			Collections.sort(oldCountries);
			
			while(i < oldKnowledges.size() && j < newKnowledges.size()) {
				if(oldKnowledges.get(i).getKnowledge().equals(newKnowledges.get(j).getString())) {
					i++;
					j++;
				}
				else if(oldKnowledges.get(i).getKnowledge().compareTo(newKnowledges.get(j).getString()) < 0) {
					em.remove(oldKnowledges.get(i));
					i++;
				}
				else {
					em.persist(new KnowledgeTraveler(traveler, newKnowledges.get(j).getString()));
					j++;
				}
			}
			
			while(i < oldKnowledges.size()) {
				em.remove(oldKnowledges.get(i));
				i++;
			}
			
			while(j < newKnowledges.size()) {
				em.persist(new KnowledgeTraveler(traveler, newKnowledges.get(j).getString()));
				j++;
			}
			

			if (!user.getTraveler()) {
				user.setTraveler(true);
				user.setTravelerEntity(traveler);
				em.persist(user);
			}

			updated = true;
		}

		try {
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		em.close();
		emfactory.close();

		return updated;
	}

	/**
	 * This method adds a place to a existing host
	 */
	@Override
	public boolean addPlace(TPlace tPlace) {
		boolean updated = false;

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("HostAbroad");
		EntityManager em = emfactory.createEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		
		PlacesKey key = new PlacesKey(tPlace.getNickname(), tPlace.getAddress());
		Place place;
		place = em.find(Place.class, key);
		if(place == null) {
			Host host = em.find(Host.class, tPlace.getNickname());
			place = new Place();
			place.setHost(host);
		}
		place.setAddress(tPlace.getAddress());
		place.setDescription(tPlace.getDescription());
		place.setNoAvaliableDates(tPlace.getNoAvaliableDates());
		place.setPhoto(tPlace.getPhoto());
		place.setFamilyUnit(tPlace.getFamilyUnit());
		em.persist(place);
		
		t.commit();
		em.close();
		emfactory.close();
		return updated;
	}

	@Override
	public THost readHostInformation(TUser user) {
		THost host = null;
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("HostAbroad");
		EntityManager em = emfactory.createEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		
		try {
			host = em.find(Host.class, user.getNickname()).toTransfer();	
		}
		catch(NullPointerException e) {
			e.printStackTrace();
		}
		
		em.getTransaction().commit();
        em.close();
 		emfactory.close();
		return host;
	}

	@Override
	public TTraveler readTravelerInformation(TUser user) {

		TTraveler traveler = null;
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("HostAbroad");
		EntityManager em = emfactory.createEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		
		String query = "SELECT * FROM TRAVELER WHERE USER_NICKNAME = ?1";
		try {
			Traveler travelerEntity = (Traveler)em.createNativeQuery(query, Traveler.class)
					.setParameter(1, user.getNickname()).getSingleResult();
			traveler = travelerEntity.toTransfer();	
		}catch(NoResultException e) {}
		
		em.getTransaction().commit();
        em.close();
 		emfactory.close();
		return traveler;
	}

	public ArrayList<TUser> getMyLikes(TUser tUser) {

		 ArrayList<TUser> sendersUser = new ArrayList<TUser>(); //usuarios que nos han enviado like
		
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("HostAbroad");
			EntityManager em = emf.createEntityManager();
			EntityTransaction tr = em.getTransaction();
			tr.begin();
			
			UserHA user = em.find(UserHA.class, tUser.getNickname());
			for(Likes like : user.getLikes()) 
				sendersUser.add(like.getUserSender().toTransfer());
	
			tr.commit();
			em.close();
			emf.close();
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}	
		return  sendersUser;
	}

	/**
	 * This method modifies the basic information of an user
	 * */
	public boolean modifyInformation(TUser tUser) {
		boolean isEditPossible = true;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("HostAbroad");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		
		UserHA user = em.find(UserHA.class, tUser.getNickname());
		em.lock(user, LockModeType.OPTIMISTIC);
		
		String newEmail = tUser.getEmail();
		
		if(!newEmail.equals(user.getEmail())) {
			String query = "SELECT * FROM USER WHERE EMAIL = ?1";
			try {
				UserHA userWithEmailFromTransfer = (UserHA) em.createNativeQuery(query, UserHA.class)
																	.setParameter(1, tUser.getEmail())
																	.getSingleResult();
				isEditPossible = false;
			}catch(NoResultException ex) {
			}
		}
		
		if(isEditPossible) {
			user.setFullName(tUser.getFullName());
			user.setEmail(newEmail);
			user.setDescription(tUser.getDescription());
			//user.setPhoto(tUser.getPhoto());
			user.setGender(tUser.getGender());
			user.setBirthday(tUser.getBirthday());
			this.newLanguages(user.getLanguages(), tUser.getLanguages(), em, user);

			em.persist(user);
		}
		
		tr.commit();
		em.close();
		emf.close();
		return isEditPossible;
	}

 private void newLanguages(List<Language> oldLanguages, 
			TreeSet<LanguagesEnum> newLanguages, EntityManager em, UserHA user){
		int i = 0;
		int j = 0;
		Collections.sort(oldLanguages);
		
		ArrayList<LanguagesEnum> newLanguagesA = new ArrayList<LanguagesEnum>(newLanguages);
		
		while(i < oldLanguages.size() && j < newLanguagesA.size()) {
			if(oldLanguages.get(i).getLanguage().equals(newLanguagesA.get(j).getString())) {
				i++;
				j++;
			}
			else if(oldLanguages.get(i).getLanguage().compareTo(newLanguagesA.get(j).getString()) < 0) {
				em.remove(oldLanguages.get(i));
				i++;
			}
			else {
				em.persist(new Language(user, newLanguagesA.get(j).getString()));
				j++;
			}
		}
		
		while(i < oldLanguages.size()) {
			em.remove(oldLanguages.get(i));
			i++;
		}
		
		while(j < newLanguagesA.size()) {
			em.persist(new Language(user, newLanguagesA.get(j).getString()));
			j++;
		}
	}
	
	@Override
	public boolean rateUser(TRating tRating) {

		boolean result = false; //Devolverá false si ha habido algún error o si ese Sender ya ha valorado a Receiver
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("HostAbroad");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		
		try {
			UserHA userSender; // Usuario 1 es el que envia valoracion
			UserHA userReceiver; // Usuario 2 es el que la recibe
			userSender = em.find(UserHA.class, tRating.getUserSender());
			userReceiver = em.find(UserHA.class, tRating.getUserReceiver());
			em.persist(new Rating(userSender, userReceiver, tRating.getRate()));
			
			tr.commit();
			result = true;
		} catch (Exception ex) {
			tr.rollback();
		}
			
		em.close();
		emf.close();
		return result;
	}

	@Override
	public TUser readUserNickName(TUser user) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("HostAbroad");
		EntityManager em = emf.createEntityManager();
		
		String queryStr = "SELECT NICKNAME FROM USERHA WHERE EMAIL = ?1";
		Query query = em.createNativeQuery(queryStr, UserHA.class);
		query.setParameter(1, user.getEmail());
		TUser nickname = null;
		try {
			UserHA userNick = (UserHA)query.getSingleResult();
			nickname = userNick.toTransfer();
		}
		catch (NoResultException ex) {
			System.out.println(ex.getMessage());
		}
		
		em.close();
		emf.close();
		
		return nickname;
	}
	
	@Override
	public TUser readUser(TUser tUser) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("HostAbroad");
		EntityManager em = emf.createEntityManager();		
		UserHA user = em.find(UserHA.class, tUser.getNickname());
		em.close();
		emf.close();
		return user.toTransfer();
	}

	@Override
	public ArrayList<TUser> readMyMatches(TUser tUser) {
		 ArrayList<TUser> myMatches = new ArrayList<TUser>();
			
			
			try {
				EntityManagerFactory emf = Persistence.createEntityManagerFactory("HostAbroad");
				EntityManager em = emf.createEntityManager();
				EntityTransaction tr = em.getTransaction();
				tr.begin();
				UserHA user = em.find(UserHA.class, tUser.getNickname());
				for(Matches match : user.getMatchesReceiver())
						myMatches.add(match.getUserSender().toTransfer());
				for(Matches match : user.getMatchesSender())
						myMatches.add(match.getUserReceiver().toTransfer());
				tr.commit();
				em.close();
				emf.close();
			}
			catch (Exception ex) {
				System.out.println(ex.getMessage());
			}	
			return myMatches;
	}

	@Override
	public void modifyInterests(TUser tUser) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("HostAbroad");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tr = em.getTransaction();
		tr.begin();
		
		UserHA user = em.find(UserHA.class, tUser.getNickname());
		em.lock(user, LockModeType.OPTIMISTIC);
		
		List<Interest> oldInterests = user.getInterests();
		ArrayList<InterestsEnum> newInterests = new ArrayList<InterestsEnum>(tUser.getInterests());
		int i = 0;
		int j = 0;
		Collections.sort(oldInterests);
		
		while(i < oldInterests.size() && j < newInterests.size()) {
			if(oldInterests.get(i).getInterest().equals(newInterests.get(j).getString())) {
				i++;
				j++;
			}
			else if(oldInterests.get(i).getInterest().compareTo(newInterests.get(j).getString()) < 0) {
				em.remove(oldInterests.get(i));
				i++;
			}
			else {
				em.persist(new Interest(user, newInterests.get(j).getString()));
				j++;
			}
		}
		
		while(i < oldInterests.size()) {
			em.remove(oldInterests.get(i));
			i++;
		}
		
		while(j < newInterests.size()) {
			em.persist(new Interest(user, newInterests.get(j).getString()));
			j++;
		}
		tr.commit();
		em.close();
		emf.close();
	}
}
