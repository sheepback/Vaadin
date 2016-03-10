package com.example.testme.server.database;

import com.example.shared.User;

/**
 * @author Alexander Thomas
 * @date 16:10:29 13.01.2016
 */
public interface UserDAO {
	
	/**
	 * @param username
	 * @param pw
	 * @return
	 */
	User login(String name, String password);

	/**
	 * @param username
	 * @param pw
	 * @return
	 */
	boolean create(String username, String pw);
	
}