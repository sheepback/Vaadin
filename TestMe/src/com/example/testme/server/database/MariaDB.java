package com.example.testme.server.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Alexander Thomas
 * @date 16:07:32 13.01.2016
 */
public class MariaDB {
	private Connection con = null;
	Logger logger = Logger.getLogger("DatabaseLogger");
	
	/**
	 * @param con Connection-Attribut
	 * @param logger Logger-Attribut
	 */
	
	/**
	 * Setzt die Connection durch den Aufruf der Methode getConnect() 
	 */
	public MariaDB() {
		con = getConnection();
	}
	
	/**
	 * Methode die, die Verbindung aufbaut<br>
	 * Oberer Teil beinhaltet die Daten zum Login in die DB<br>
	 * mittels DriverManager wird die Verbindung aufgebaut
	 * @exception InstantiationException, IllegalAccessException, ClassNotFoundException;
	 * @return Connection
	 */
	private Connection getConnection() {
		//String url = "jdbc:mysql://localhost:3306/";
		String url = "jdbc:mariadb://localhost:3306/";
		String db = "vaadin";
		String driver = "org.mariadb.jdbc.Driver";
		String user = "root";
		String pass = "****";

		try {
			Class.forName(driver).newInstance();
		} catch (InstantiationException e) {
			logger.log(Level.SEVERE, e.getMessage());
		} catch (IllegalAccessException e) {
			logger.log(Level.SEVERE, e.getMessage());
		} catch (ClassNotFoundException e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
		try {
			con = DriverManager.getConnection(url + db, user, pass);
		} catch (SQLException e) {
			System.err.println("MariaDB Connection Error: ");
			logger.log(Level.SEVERE, e.getMessage());
		}
		return con;
	}
	
	/**
	 * Gibt die Connection zur&uumlck
	 * @return Connection
	 */
	public Connection getCon() {
		return con;
	}
	
	/**
	 * Schliesst die Verbindung zur DB
	 * @exception
	 */
	public void closeCon() {
		try {
			if (con != null) {
				con.close();
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
	}
}
