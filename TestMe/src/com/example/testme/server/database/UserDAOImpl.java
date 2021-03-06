package com.example.testme.server.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.shared.User;
import com.example.testme.server.database.exceptions.UserNotFoundException;


/**
 * @author Alexander Thomas
 * @date 16:11:56 13.01.2016
 */
public class UserDAOImpl implements UserDAO {

    Logger logger = Logger.getLogger("UserDAOImpl");
    /**
     * @param logger
     * logger Attribut
     */

    /**
	 * Liest einen User aus der Datenbank.<br>
	 * keine SQL Injection m�glich, da preparedStatements mit Validierung durch Server. 
	 * @param name name vom client
	 * @param pw
	 * passwort vom client
	 * @return Returnt einen gefundenen User.
	 * @exception SQLException, Exception
	 * @throws UserNotFoundException
	 */
	@Override
	public User login(String name, String pw) throws UserNotFoundException{
		User u = null;
		MariaDB con = new MariaDB();
		logger.log(Level.INFO,"Versuche User "+name+" einzuloggen...");
		try {
			String hash = HashFunction.finalHashRead(pw, readSalt(name));
			PreparedStatement preparedStatement = con
					.getCon()
					.prepareStatement(
							"SELECT * FROM vaadin.user WHERE name = ? AND password = ?");
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, hash);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next() == true){
				u = new User();
				logger.log(Level.INFO, "User "+name+" gefunden...");
				u.setUsername(resultSet.getString("name"));
				u.setLastLogin(new Date(resultSet.getInt("lastlogin")));
				resultSet.close();
				preparedStatement.close();
				con.closeCon();
				return u;
			}
			else{
				logger.log(Level.INFO, name+" nicht gefunden mit diesem Passwort...");
				resultSet.close();
				preparedStatement.close();
				con.closeCon();
				return u;
			}

		} catch (SQLException e) {
			logger.log(Level.SEVERE, e.getMessage());
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
		logger.log(Level.INFO, "Something went wrong...");
		con.closeCon();
		return u;
	}
	
	@Override
	public boolean create(String username, String pw){
		MariaDB con = new MariaDB();
		try {
		String hash[] = HashFunction.finalHash(pw);
		PreparedStatement preparedStatement = con.getCon().prepareStatement("INSERT INTO vaadin.user VALUES (?, ?, ?)");
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, hash[0]);
        preparedStatement.setString(3, hash[1]);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        con.closeCon();
        return true;
		}
		catch(SQLException e){
			logger.log(Level.SEVERE, e.getMessage());
		}
		catch(Exception e){
			logger.log(Level.SEVERE, e.getMessage());
		}
		logger.log(Level.INFO, "Something went wrong...");
        con.closeCon();
		return false;
	}
	
	private String readSalt(String name){
    	String salt = null;
    	try {
    		MariaDB con = new MariaDB();
			PreparedStatement preparedStatement = con
					.getCon()
					.prepareStatement(
							"SELECT salt FROM vaadin.user WHERE name = ?");
			preparedStatement.setString(1, name);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet == null){
				throw new UserNotFoundException(name);
			}
			while (resultSet.next()) {
				salt = resultSet.getString("salt");
			}
			resultSet.close();
			preparedStatement.close();
			con.closeCon();
		} catch(Exception e){
			logger.log(Level.SEVERE, "SQL EXCEPTION " + e.getMessage());
			}
    	return salt;
    }
}