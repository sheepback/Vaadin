package com.example.shared;

import java.util.Date;

/**
 * @author Alexander Thomas
 * @date 14:22:29 10.03.2016
 */
public class User {

	private String username;

	private Date lastLogin;

	public User() {

	}

	public User(String username) {
		this.username = username;
	}

	public User(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public User(Date lastLogin, String username) {
		this.username = username;
		this.lastLogin = lastLogin;
	}

	public Date getLastLogin() {
		return this.lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public boolean equals(String username){
		if(this.username.equals(username)){
			return true;
		}
		return false;
	}

}
