package com.business.ASUser;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.business.TUser;
import com.business.User;

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
	public boolean loginUser(String email, String password) {
		boolean loged = false;

		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("HostAbroad");
			EntityManager em = emf.createEntityManager();
			EntityTransaction tr = em.getTransaction();
			tr.begin();

			// Checks if the email is valid and execute the query
			if (checkEmail(email)) {
				String consulta = "SELECT pass FROM registeredusers r WHERE r.email = :email";
				Query query = em.createQuery(consulta);
				query.setParameter("email", email);
				System.out.println(query);
				// The password is saved into result and if it's valid loged = true
				String result = (String) query.getSingleResult();
				if (checkPassword(result)) {
					loged = true;
				} else {
					throw new Exception(
							"The password must contain at least one number and one letter, and have a length between 6 and 30 characters");
				}
			} else {
				throw new Exception("Invalid email");
			}
			em.close();
			emf.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return loged;
	}

	/**
	 * This method checks if yhe email length is, at least, 30 chars, and the first
	 * 3 chars are before the @ symbol
	 * 
	 * @param email Email to check
	 * @return True if the email is OK, False if not
	 */
	private boolean checkEmail(String email) {
		boolean check = false;
		if ((email.length() <= 30) && (email.charAt(2) != '@')) {
			check = true;
		}
		return check;
	}

	private boolean checkPassword(String password) {
		boolean check = false;

		if ((password.length() <= 30) && (password.substring(0, 2).matches("[A-Za-z0-9]"))) {
			check = true;
		}

		return check;
	}

}
