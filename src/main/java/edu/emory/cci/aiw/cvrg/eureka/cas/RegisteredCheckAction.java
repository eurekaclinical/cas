package edu.emory.cci.aiw.cvrg.eureka.cas;

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

import org.jasig.cas.authentication.principal.UsernamePasswordCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.webflow.action.AbstractAction;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

/**
 *
 * @author Andrew Post
 */
public class RegisteredCheckAction extends AbstractAction {

	/**
	 * The class level logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(RegisteredCheckAction.class);

	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate inJdbcTemplate) {
		this.jdbcTemplate = inJdbcTemplate;
	}

	public boolean getUsername(String username) {
		String sql = "select active from users where username='" + username + "'";
		try {
			return this.jdbcTemplate.queryForObject(sql, Boolean.class);
		} catch (EmptyResultDataAccessException ex) {
			return false;
		}
	}

	@Override
	protected Event doExecute(RequestContext rc) throws Exception {
		UsernamePasswordCredentials credentials = (UsernamePasswordCredentials) rc.getFlowScope().get("credentials");
		if (getUsername(credentials.getUsername())) {
			return success();
		} else {
			return error();
		}
	}

}
