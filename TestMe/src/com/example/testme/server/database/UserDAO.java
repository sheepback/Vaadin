package com.example.testme.server.database;

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
	boolean login(String name, String password);

	/**
	 * @param username
	 * @param pw
	 * @return
	 */
	boolean create(String username, String pw);
	
}