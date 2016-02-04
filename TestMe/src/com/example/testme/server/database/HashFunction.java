package com.example.testme.server.database;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Formatter;

/**
 * Unsere Klasse f&uumlr die Verschl&uumlsselung der Passw&oumlrter
 * 
 * @author Alexander Thomas - SWP 2015-GRP A
 * @date 10:22:08 22.05.2015
 */

public class HashFunction {
	
	/**
	 * Methode zur Erzeugung eines Hashs
	 * @param algorithm
	 * @param message
	 * @return Hash
	 * @throws Exception
	 */
	private static String calculateHash(MessageDigest algorithm, String message)
			throws Exception {

		algorithm.update(message.getBytes());

		byte[] hash = algorithm.digest();

		return byteArray2Hex(hash);
	}
	/**
	 * Methode zur Erzeugung einex hexacodes
	 * @param hash
	 * @return formattedString
	 */
	private static String byteArray2Hex(byte[] hash) {
		Formatter formatter = new Formatter();
		String formatterString;
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		formatterString = formatter.toString();
		formatter.close();
		return formatterString;
	}
	/**
	 * Methode zur Erzeugung eines salts
	 * @return formattedString
	 */
	@SuppressWarnings("static-access")
	public static String getSalt() throws NoSuchAlgorithmException{
		SecureRandom random = new SecureRandom().getInstance("SHA1PRNG");
	    byte[] salt = new byte[16];
	    random.nextBytes(salt);
	    Formatter formatter = new Formatter();
		String formatterString;
		for (byte b : salt) {
			formatter.format("%02x", b);
		}
		formatterString = formatter.toString();
		formatter.close();
	    return formatterString;
	  }
	
	/**
	 * Methode zur endg&uumlltigen SHA-256 Verschl&uumlsselung mit Salt
	 * @param pw
	 * @return SHA-256 Hash
	 * @return Salt
	 * @throws Exception
	 */
	public static String[] finalHash(String pw) throws Exception {
		MessageDigest sha1 = MessageDigest.getInstance("SHA-256");
		String salt = getSalt();
		pw=pw+salt;
		return new String[]{calculateHash(sha1, pw), salt};
	}
	/**
	 * Methode f&uumlr das Login zum Auslesen eines Users mit bereits erstelltem Salt aus der DB
	 * @param pw
	 * @param salt
	 * @return hash
	 * @throws Exception
	 */
	public static String finalHashRead(String pw, String salt) throws Exception {
		MessageDigest sha1 = MessageDigest.getInstance("SHA-256");
		pw=pw+salt;
		return calculateHash(sha1, pw);
	}
}