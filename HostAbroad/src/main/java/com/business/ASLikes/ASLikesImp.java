package com.business.ASLikes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import com.business.businessObjects.Likes;
import com.business.businessObjects.UserHA;
import com.business.transfers.TLikes;

public class ASLikesImp implements ASLikes{

	@Override
	public boolean sendLike(TLikes tLikes) {
		boolean result = false;

		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("HostAbroad");
			EntityManager em = emf.createEntityManager();
			EntityTransaction tr = em.getTransaction();
			tr.begin();

			if(!tLikes.getUserReceiver().equals(tLikes.getUserSender())) {
				UserHA userSender;
				UserHA userReceiver;
				try {
					String query = "SELECT * FROM USERHA WHERE NICKNAME = ?1";
					userSender = (UserHA)em.createNativeQuery(query, UserHA.class)
							.setParameter(1, tLikes.getUserSender()).getSingleResult();
					userReceiver = (UserHA)em.createNativeQuery(query, UserHA.class)
							.setParameter(1, tLikes.getUserReceiver()).getSingleResult();
					
					query = "SELECT * FROM LIKES WHERE (USERRECEIVER_NICKNAME = ?1 AND USERSENDER_NICKNAME = ?2) OR (USERRECEIVER_NICKNAME = ?2 AND USERSENDER_NICKNAME = ?1)  ";
					Likes like;
					try {
						like = (Likes)em.createNativeQuery(query, Likes.class).setParameter(1, tLikes.getUserReceiver()).setParameter(2, tLikes.getUserSender()).getSingleResult();
					}catch(Exception e) {
						like = new Likes(userSender, userReceiver, true);
						em.persist(like);
						
						userReceiver.getLikes().add(like);
						em.persist(userReceiver);
						result = true;
					}
				}catch(NoResultException e) {}
			}
			
			tr.commit();
			em.close();
			emf.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		return result;
	}

}
