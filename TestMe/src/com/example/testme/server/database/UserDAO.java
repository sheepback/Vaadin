package com.example.testme.server.database;

/**
 * @author Alexander Thomas
 * @date 16:10:29 13.01.2016
 */
public interface UserDAO {

	boolean login(String name, String password);
	
}