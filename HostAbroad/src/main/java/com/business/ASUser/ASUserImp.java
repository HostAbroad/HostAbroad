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
				user =  (User) query.getSingleResult();
			}
			catch (NoResultException ex) {
				System.out.println(ex.getMessage());
			}
			
			if (user != null){
				tr.rollback();
				em.close();
				emf.close();
				return false;
			}
			
			else {
				user = new User (tUser.getNickname(),
						tUser.getRating(),
						tUser.getDescription(),
						false,
						false);
				em.persist(user);
			}
			em.close();
			emf.close();
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}		
		
		return true;
	}

}
