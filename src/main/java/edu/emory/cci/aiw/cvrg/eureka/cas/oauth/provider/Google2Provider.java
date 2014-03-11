package edu.emory.cci.aiw.cvrg.eureka.cas.oauth.provider;

/**
 * Fixes bug where the scope is set incorrectly.
 * @author Andrew Post
 */
public class Google2Provider extends org.scribe.up.provider.impl.Google2Provider {

	public Google2Provider() {
		setScope(this.scope);
	}
}
