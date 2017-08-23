package edu.emory.cci.aiw.cvrg.eureka.cas.authentication;

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

import java.util.HashMap;
import java.util.Map;
import org.jasig.cas.adaptors.jdbc.QueryDatabaseAuthenticationHandler;
import org.jasig.cas.adaptors.ldap.BindLdapAuthenticationHandler;
import org.jasig.cas.adaptors.ldap.FastBindLdapAuthenticationHandler;
import org.jasig.cas.authentication.Authentication;
import org.jasig.cas.authentication.AuthenticationManager;
import org.jasig.cas.authentication.AuthenticationMetaDataPopulator;
import org.jasig.cas.authentication.MutableAuthentication;
import org.jasig.cas.authentication.principal.Credentials;
import org.jasig.cas.authentication.principal.Principal;
import org.jasig.cas.authentication.principal.SimplePrincipal;
import org.jasig.cas.support.oauth.authentication.handler.support.OAuthAuthenticationHandler;

/**
 *
 * @author Andrew Post
 */
public class AuthenticationMethodMetaDataPopulator implements AuthenticationMetaDataPopulator {

	private static final String AUTHENTICATION_METHOD = "authenticationMethod";

	@Override
	public Authentication populateAttributes(Authentication authentication, Credentials credentials) {
		Map<String, Object> attrs = new HashMap<>();
		attrs.putAll(authentication.getPrincipal().getAttributes());
		Map<String, Object> authenticationAttributes = authentication.getAttributes();
		Object authenticationMethod = authenticationAttributes.get(AuthenticationManager.AUTHENTICATION_METHOD_ATTRIBUTE);
		if (OAuthAuthenticationHandler.class.getName().equals(authenticationMethod)) {
			attrs.put(AUTHENTICATION_METHOD, "OAUTH");
		} else if (QueryDatabaseAuthenticationHandler.class.getName().equals(authenticationMethod)) {
			attrs.put(AUTHENTICATION_METHOD, "LOCAL");
		} else if (FastBindLdapAuthenticationHandler.class.getName().equals(authenticationMethod)
				|| BindLdapAuthenticationHandler.class.getName().equals(authenticationMethod)) {
			attrs.put(AUTHENTICATION_METHOD, "LDAP");
		}
		if (authenticationAttributes.containsKey("providerType")) {
			attrs.put("providerType", authenticationAttributes.get("providerType"));
		}
		Principal simplePrincipal = new SimplePrincipal(authentication.getPrincipal().getId(),
				attrs);
		MutableAuthentication mutableAuthentication = new MutableAuthentication(simplePrincipal,
				authentication
				.getAuthenticatedDate());
		return mutableAuthentication;
	}

}
