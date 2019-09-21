package com.prestamosprima.app.ws.shared;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.prestamosprima.app.ws.ui.controller.UserController;


@Component
public class Utils {
	
	private static Logger log= LoggerFactory.getLogger(Utils.class);

	
	private final Random RANDOM= new SecureRandom();
	private final String ALPHABET="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private final int ITERATIONS= 10000;
	private final int KEY_LENGTH= 256;
	private final int saltRounds= 10;
	
	public String encode(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
		
		String salt= BCrypt.gensalt(saltRounds);
		String hashed= BCrypt.hashpw(password, salt);
		
		return hashed;
		
	}
	
	public Boolean checkEncode(String password, String hashed) {
		return BCrypt.checkpw(password, hashed);
	}
	
	/**
	 * 
	 * @param password
	 * @return encryptedPassword
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public String encode2(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
		String encryptedPassword= generateStrongPasswordHash(password);
		return encryptedPassword;
		
	}
	
	private static String generateStrongPasswordHash(String password) throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        int iterations = 1000;
        char[] chars = password.toCharArray();
        byte[] salt = getSalt();
         
        PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = skf.generateSecret(spec).getEncoded();
        return iterations + ":" + toHex(salt) + ":" + toHex(hash);
    }
     
    private static byte[] getSalt() throws NoSuchAlgorithmException
    {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }
     
    private static String toHex(byte[] array) throws NoSuchAlgorithmException
    {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if(paddingLength > 0)
        {
            return String.format("%0"  +paddingLength + "d", 0) + hex;
        }else{
            return hex;
        }
    }
    
    /**
     * 
     * @param length
     * @return randomString
     */
    public String generateUserId(int length) {
		return generateRandomString(length);
	}
    
    private String generateRandomString(int length) {
		StringBuilder returnValue= new StringBuilder(length);
		
		for(int i=0; i<length; i++) {
			returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
		}
		
		return new String(returnValue);
		
		
	}

}
