package com.business.ASUser;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.business.TUser;
import com.business.User;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Notification;

public class ASUserImp implements ASUser {

	@Override
	public boolean createUser(TUser tUser) {

		boolean result = false;

		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("HostAbroad");
			EntityManager em = emf.createEntityManager();
			EntityTransaction tr = em.getTransaction();
			tr.begin();

			String consulta = "SELECT * FROM user WHERE user.nickname = :nickname";
			Query query = em.createQuery(consulta);
			query.setParameter("nickname", tUser.getNickname());

			User user = null;

			try {
				user = (User) query.getSingleResult();
			} catch (NoResultException ex) {
				System.out.println(ex.getMessage());
			}

			if (user == null) {
				user = new User(tUser.getNickname(), tUser.getRating(), tUser.getDescription(), tUser.getHost(),
						tUser.getTraveler());
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

	@Override
	public TUser loginUser(TUser user) {
		TUser logedUser = null;

		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("HostAbroad");
			EntityManager em = emf.createEntityManager();
			EntityTransaction tr = em.getTransaction();
			tr.begin();
			String consulta = "SELECT * FROM USER u WHERE u.email = ?1 AND u.passwd = ?2";
			Query query = em.createNativeQuery(consulta, User.class);
			query.setParameter(1, user.getEmail());
			query.setParameter(2, user.getPassword());
			User result = null;
			
			try {
				result = (User) query.getSingleResult();
			} catch (NoResultException ex) {
				System.out.println(ex.getMessage());
			}
			
			if(result != null) {
				logedUser = new TUser();
                		logedUser.setDescription(result.getDescription());
                		logedUser.setHost(result.getHost());
						logedUser.setTraveler(result.getTraveler());
                		logedUser.setNickname(result.getNickname());
                		logedUser.setRating(result.getRating());
                		logedUser.setEmail(result.getEmail());
                		logedUser.setPassword(result.getPassword());
			}
	
			tr.commit();
			em.close();
			emf.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return logedUser;
	}
}
