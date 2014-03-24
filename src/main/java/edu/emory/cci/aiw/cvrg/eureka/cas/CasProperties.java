package edu.emory.cci.aiw.cvrg.eureka.cas;

import org.slf4j.LoggerFactory;

/**
 *
 * @author Andrew Post
 */
public class CasProperties extends AbstractProperties {

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
}
