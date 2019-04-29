package com.business.ASMatches;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import com.business.businessObjects.Likes;
import com.business.businessObjects.LikesKey;
import com.business.businessObjects.Matches;
import com.business.businessObjects.UserHA;
import com.business.transfers.TMatches;

public class ASMatchesImp implements ASMatches {

	
	@Override
	public boolean acceptLike(TMatches tMatches) {
	
		boolean result = false;
		
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("HostAbroad");
			EntityManager em = emf.createEntityManager();
			EntityTransaction tr = em.getTransaction();
			tr.begin();

			UserHA userSender; //Usuario 1 es el que acepta el Like (que es el UserReceiver de Likes)
			UserHA userReceiver; //Usuario 2 es el que envió el like
			try {
				userSender = em.find(UserHA.class, tMatches.getUserSender());
				userReceiver = em.find(UserHA.class, tMatches.getUserReceiver());
			
				LikesKey key = new LikesKey(userSender.getNickname(), userReceiver.getNickname());
				Likes like = em.find(Likes.class, key);
				em.remove(like);
				
				Matches match = new Matches(userSender, userReceiver);
				em.persist(match);
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

	@Override
	public boolean declineLike(TMatches tMatches) {
		
			boolean result = false;	
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("HostAbroad");
			EntityManager em = emf.createEntityManager();
			EntityTransaction tr = em.getTransaction();
			tr.begin();

			UserHA userSender; //Usuario 1 es el que declina el Like (que es el UserReceiver de Likes)
			UserHA userReceiver; //Usuario 2 es el que envió el like
			try {
				userSender = em.find(UserHA.class, tMatches.getUserSender());
				userReceiver = em.find(UserHA.class, tMatches.getUserReceiver());
			
				LikesKey key = new LikesKey(userSender.getNickname(), userReceiver.getNickname());
				Likes like = em.find(Likes.class, key);
				em.remove(like);
				
			}catch(NoResultException e) {}
			
			tr.commit();
			em.close();
			emf.close();
			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		return result;
	}
	
}