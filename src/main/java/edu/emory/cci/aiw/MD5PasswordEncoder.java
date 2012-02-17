package edu.emory.cci.aiw;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.jasig.cas.authentication.handler.PasswordEncoder;

/**
 * Encodes the given password using MD5 + Hex encoding to match the I2B2
 * password encoding mechanism.
 * 
 * @author hrathod
 * 
 */
public class MD5PasswordEncoder implements PasswordEncoder {

	@Override
	public String encode(String inPassword) {
		StringBuilder hexBuilder = new StringBuilder();
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.update(inPassword.getBytes());
			for (byte b : digest.digest()) {
				hexBuilder.append(Integer.toHexString(b & 0x00FF));
			}
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		return hexBuilder.toString();
	}

}
