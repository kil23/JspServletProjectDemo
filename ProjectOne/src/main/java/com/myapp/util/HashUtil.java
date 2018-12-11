package com.myapp.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {
	
	public static final String SALT = "my-salt-text";
	
	public static String passwordHashing(String password) {
		
		String saltedPassword = SALT + password;
		String hashedPassword = generateHash(saltedPassword);
		return hashedPassword;
	}
	
	public static String generateHash(String input) {
		try { 
			  
            MessageDigest md = MessageDigest.getInstance("SHA-256"); 
  
            byte[] messageDigest = md.digest(input.getBytes()); 
  
            BigInteger no = new BigInteger(1, messageDigest); 
   
            String hashtext = no.toString(16); 
  
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
            return hashtext; 
        } 
        catch (NoSuchAlgorithmException e) { 
            System.out.println("Exception thrown"
                               + " for incorrect algorithm: " + e); 
  
            return null; 
        }
	}
	

}
