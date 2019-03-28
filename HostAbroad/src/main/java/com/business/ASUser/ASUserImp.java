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

			String consulta = "SELECT user FROM User user WHERE user.nickname = :nickname";
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
			em.close();
			emf.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		return result;
	}

	@Override
	public TUser loginUser(TUser user) {
		TUser logedUser = new TUser();

		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("HostAbroad");
			EntityManager em = emf.createEntityManager();
			EntityTransaction tr = em.getTransaction();
			tr.begin();
			String consulta = "SELECT * FROM USER u WHERE u.email = ':email'AND u.paswd = ':pass'";
			Query query = em.createNativeQuery(consulta, User.class);
			query.setParameter("email", user.getEmail());
			query.setParameter("pass", user.getPassword());
			User result = null;
			try {
				result = (User) query.getSingleResult();
			} catch (NoResultException ex) {
				System.out.println(ex.getMessage());
			}
			if(result != null) {
				Notification correct = new Notification( "Login succesful");
                correct.setDelayMsec(2000);
                correct.setPosition(Position.MIDDLE_CENTER);
                correct.show(Page.getCurrent());
                logedUser.setDescription(result.getDescription());
                logedUser.setHost(result.getHost());
                logedUser.setNickname(result.getNickname());
                logedUser.setRating(result.getRating());
			}
			else {
                Notification wrong = new Notification( "Invalid Username or Password.");
                wrong.setDelayMsec(2000);
                wrong.setPosition(Position.MIDDLE_CENTER);
                wrong.show(Page.getCurrent());
            }
			em.close();
			emf.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return logedUser;
	}
}
