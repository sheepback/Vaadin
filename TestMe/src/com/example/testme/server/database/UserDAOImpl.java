package com.example.testme.server.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
	 * keine SQL Injection möglich, da preparedStatements mit Validierung durch Server. 
	 * @param name name vom client
	 * @param pw
	 * passwort vom client
	 * @return Returnt einen gefundenen User.
	 * @exception SQLException, Exception
	 * @throws UserNotFoundException
	 */
	@Override
	public boolean login(String name, String pw) throws UserNotFoundException{
		Database con = new Database();
		logger.log(Level.INFO,"Versuche User "+name+" einzuloggen...");
		try {
			PreparedStatement preparedStatement = con
					.getCon()
					.prepareStatement(
							"SELECT * FROM test.user WHERE name = ? AND password = ?");
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, pw);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next() == true){
				logger.log(Level.INFO, "User "+name+" gefunden...");
				resultSet.close();
				preparedStatement.close();
				con.closeCon();
				return true;
				}
			else{
				logger.log(Level.INFO, name+" nicht gefunden mit diesem Passwort...");
				resultSet.close();
				preparedStatement.close();
				con.closeCon();
				return false;
			}

		} catch (SQLException e) {
			logger.log(Level.SEVERE, e.getMessage());
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
		logger.log(Level.INFO, "Something went wrong...");
		con.closeCon();
		return false;
	}
	
	@Override
	public boolean create(String username, String pw){
		Database con = new Database();
		try {
		PreparedStatement preparedStatement = con.getCon().prepareStatement("INSERT INTO test.user VALUES (?, ?)");
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, pw);
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
}