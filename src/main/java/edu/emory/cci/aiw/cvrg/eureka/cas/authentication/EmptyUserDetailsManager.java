package edu.emory.cci.aiw.cvrg.eureka.cas.authentication;

/*-
 * #%L
 * CAS Server
 * %%
 * Copyright (C) 2012 - 2017 Emory University
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

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;

/**
 * Provides an empty list of authorized users for the service management 
 * webapp. We do not support the service management webapp, but there is no way 
 * to turn the application off. Part of barring access to it is passing into 
 * the application an empty list of authorized users. The built-in 
 * {@link UserDetailsService} does not permit an empty list, so we need our own 
 * implementation.
 * 
 * @author Andrew Post
 */
public class EmptyUserDetailsManager implements UserDetailsService, UserDetailsManager {

	@Override
	public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
		throw new UsernameNotFoundException("Service Management Webapp is disabled");
	}

	@Override
	public void createUser(UserDetails ud) {
	}

	@Override
	public void updateUser(UserDetails ud) {
	}

	@Override
	public void deleteUser(String string) {
	}

	@Override
	public void changePassword(String string, String string1) {
	}

	@Override
	public boolean userExists(String string) {
		return false;
	}
	
}
