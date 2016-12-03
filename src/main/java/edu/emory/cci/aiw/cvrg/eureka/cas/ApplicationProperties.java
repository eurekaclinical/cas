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
/**
 *
 * @author Andrew Post
 */
public class ApplicationProperties extends AbstractProperties {
	
	public boolean isEphiProhibited() {
		return Boolean.parseBoolean(getValue("eureka.webapp.ephiprohibited"));
	}

	public boolean isDemoMode() {
		return Boolean.parseBoolean(getValue("eureka.webapp.demomode"));
	}

	public boolean isTwitterAuthEnabled() {
		return getTwitterKey() != null || getTwitterSecret() != null;
	}

	public String getTwitterKey() {
		return getValue("cas.auth.oauth.twitter.key");
	}

	public String getTwitterSecret() {
		return getValue("cas.auth.oauth.twitter.secret");
	}

	public boolean isGoogleAuthEnabled() {
		return getGoogleKey() != null || getGoogleSecret() != null;
	}

	public String getGoogleKey() {
		return getValue("cas.auth.oauth.google.key");
	}

	public String getGoogleSecret() {
		return getValue("cas.auth.oauth.google.secret");
	}

	public boolean isGitHubAuthEnabled() {
		return getGitHubKey() != null || getGitHubSecret() != null;
	}

	public String getGitHubKey() {
		return getValue("cas.auth.oauth.github.key");
	}

	public String getGitHubSecret() {
		return getValue("cas.auth.oauth.github.secret");
	}
	
	public boolean isGlobusAuthEnabled() {
		return getGlobusKey() != null || getGlobusSecret() != null;
	}

	public String getGlobusKey() {
		return getValue("cas.auth.oauth.globus.key");
	}

	public String getGlobusSecret() {
		return getValue("cas.auth.oauth.globus.secret");
	}

	public boolean isOAuthEnabled() {
		return isTwitterAuthEnabled()
				|| isGoogleAuthEnabled()
				|| isGitHubAuthEnabled()
				|| isGlobusAuthEnabled();
	}

	public String getFromEmailAddress() {
		return this.getValue("cas.services.email.from");
	}        
}
