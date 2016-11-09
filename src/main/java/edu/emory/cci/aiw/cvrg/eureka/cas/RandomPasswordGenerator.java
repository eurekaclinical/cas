package edu.emory.cci.aiw.cvrg.eureka.cas;
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

import java.math.BigInteger;
import java.security.SecureRandom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author miaoai
 */
public class RandomPasswordGenerator {
        /**
         * The class level logger.
         */
        private static final Logger LOGGER = LoggerFactory.getLogger(RandomPasswordGenerator.class);       
	/**
	 * A secure random number generator to be used to create passwords.
	 */
	private static final SecureRandom RANDOM = new SecureRandom();    
	public String generatePassword() {
		final int length = 15 + RANDOM.nextInt(10);
		return new BigInteger(130, RANDOM).toString(32).substring(0, length);
	}        
}
