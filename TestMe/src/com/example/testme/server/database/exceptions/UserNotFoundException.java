package com.example.testme.server.database.exceptions;
/**
 * @author Alexander Thomas
 * @date 16:13:38 13.01.2016
 */
@SuppressWarnings("serial")
public class UserNotFoundException extends RuntimeException {

	/**
	 * Wirft eine Exception wenn ein User nicht gefunden wurde 
	 * @param u String 
	 */
    public UserNotFoundException(String u) {
        super("User "+ u + " was not found!");
    }

}
