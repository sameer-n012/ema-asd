package org.ucvts.ema.app;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Password {
	
	//Class for SHA-512 hashing using SecureRandom and MessageDigest
	
	private static SecureRandom sr = new SecureRandom();
	private static MessageDigest md;
	
	public static boolean checkPassword(String password, String hashedpassword, byte[] salt) {
		if(hash(password, salt).equals(hashedpassword)) { return true; }
		else { return false; }
	}
	
	public static String hash(String password, byte[] salt) {
		
		String hashedPassword = null;
		
		try {
			hashedPassword = sha512(password, salt);
		} 
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace(); }
		return hashedPassword;
		
	}
	
	
	public static byte[] getSalt() {
		byte[] salt = new byte[16];
		sr.nextBytes(salt);
		
		return salt;
	}
	
	
	private static String sha512(String password, byte[] salt) throws NoSuchAlgorithmException {
		
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		md.update(salt);
		
		byte[] bytes = md.digest(password.getBytes());
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < bytes.length; i++) {
			sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		
		return sb.toString();
		
	}

}
