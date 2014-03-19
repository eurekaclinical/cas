package edu.emory.cci.aiw.cvrg.eureka.cas.authentication;

import java.util.HashMap;
import java.util.Map;
import org.jasig.cas.authentication.Authentication;
import org.jasig.cas.authentication.AuthenticationManager;
import org.jasig.cas.authentication.AuthenticationMetaDataPopulator;
import org.jasig.cas.authentication.MutableAuthentication;
import org.jasig.cas.authentication.principal.Credentials;
import org.jasig.cas.authentication.principal.Principal;
import org.jasig.cas.authentication.principal.SimplePrincipal;

/**
 *
 * @author Andrew Post
 */
public class AuthenticationMethodMetaDataPopulator implements AuthenticationMetaDataPopulator {

	private static final String AUTHENTICATION_METHOD = "authenticationMethod";

	@Override
	public Authentication populateAttributes(Authentication authentication, Credentials credentials) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.putAll(authentication.getPrincipal().getAttributes());
		Map<String, Object> authenticationAttributes = authentication.getAttributes();
		Object authenticationMethod = authenticationAttributes.get(AuthenticationManager.AUTHENTICATION_METHOD_ATTRIBUTE);
		if ("org.jasig.cas.support.oauth.authentication.handler.support.OAuthAuthenticationHandler".equals(authenticationMethod)) {
			attrs.put(AUTHENTICATION_METHOD, "OAUTH");
		} else if ("org.jasig.cas.adaptors.jdbc.QueryDatabaseAuthenticationHandler".equals(authenticationMethod)) {
			attrs.put(AUTHENTICATION_METHOD, "LOCAL");
		} else if ("org.jasig.cas.adaptors.ldap.FastBindLdapAuthenticationHandler".equals(authenticationMethod)
				|| "org.jasig.cas.adaptors.ldap.BindLdapAuthenticationHandler".equals(authenticationMethod)) {
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
