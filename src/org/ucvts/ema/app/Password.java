package org.ucvts.ema.app;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Password {
	
	public static String hash(String password) throws NoSuchAlgorithmException {
		String hashed = null;
		
		
		SecureRandom sr = new SecureRandom();
		
		byte[] salt = new byte[16];
		sr.nextBytes(salt);
		
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		md.update(salt);
		
		byte[] bytes = md.digest(password.getBytes());
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < bytes.length; i++) {
			sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		
		hashed = sb.toString();
		return hashed;
		
	}

}
