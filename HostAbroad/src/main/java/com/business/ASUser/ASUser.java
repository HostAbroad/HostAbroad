package com.business.ASUser;

import com.business.TUser;

public interface ASUser {
	
	public abstract boolean createUser(TUser user);

	/**
	 * This method checks if the email exists in the DB and if exists then checks the password
	 * and if it's right then the user is loged, if not appears an alert showing a error message
	 * 
	 * For this test this method in local, I've create a new table with three users with the following
	 * SQL commands:
	 * 		CREATE TABLE registeredusers (email VARCHAR(30), password VARCHAR(30), PRIMARY KEY(email))
	 * 		INSERT INTO `registeredusers` (`email`, `password`) VALUES ('ernesvivar@gmail.com', 'estaEsCorrecta1');
	 * 		INSERT INTO `registeredusers` (`email`, `password`) VALUES ('no@gmail.com', 'elEmailNoFunciona');
	 * 		INSERT INTO `registeredusers` (`email`, `password`) VALUES ('nofuncionalapass@gmail.com', 'noVa'); 
	 * 
	 * @param email: User email, with @ symbol
	 * @param password: User password
	 * @return True if the user is loged, False if not
	 */
	public abstract boolean loginUser(String email, String password);
	
}
