package edu.emory.cci.aiw;

/*-
 * #%L
 * CAS Server
 * %%
 * Copyright (C) 2012 - 2016 Emory University
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

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
