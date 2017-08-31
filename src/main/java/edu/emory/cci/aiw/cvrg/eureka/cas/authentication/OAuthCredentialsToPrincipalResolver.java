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
import org.jasig.cas.authentication.principal.AbstractPersonDirectoryCredentialsToPrincipalResolver;
import org.jasig.cas.authentication.principal.Credentials;
import org.jasig.cas.authentication.principal.CredentialsToPrincipalResolver;
import org.jasig.cas.support.oauth.authentication.principal.OAuthCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author arpost
 */
public class OAuthCredentialsToPrincipalResolver extends AbstractPersonDirectoryCredentialsToPrincipalResolver
		implements CredentialsToPrincipalResolver {

	private static final Logger LOGGER = LoggerFactory.getLogger(OAuthCredentialsToPrincipalResolver.class);
	private static final String SQL = "select active from users where username=?";

	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return this.jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	protected String extractPrincipalId(Credentials credentials) {
		final OAuthCredentials oauthCredentials = (OAuthCredentials) credentials;
		final String principalId = oauthCredentials.getUserProfile().getTypedId();
		if (principalId != null) {
			boolean active;
			try {
				active = this.jdbcTemplate.queryForObject(SQL, Boolean.class, principalId);
				LOGGER.info("result is {}", active);
			} catch (EmptyResultDataAccessException ex) {
				LOGGER.info("no rows returned", ex);
				active = false;
			}
			if (!active) {
				return null;
			}
		}
		log.debug("principalId : {}", principalId);
		return principalId;
	}

	@Override
	public boolean supports(Credentials credentials) {
		return credentials != null && (OAuthCredentials.class.isAssignableFrom(credentials.getClass()));
	}

}
