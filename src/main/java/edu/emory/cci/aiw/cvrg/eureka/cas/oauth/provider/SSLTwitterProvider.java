package edu.emory.cci.aiw.cvrg.eureka.cas.oauth.provider;

/**
 *
 * @author Andrew Post
 */
public class SSLTwitterProvider extends org.scribe.up.provider.impl.TwitterProvider {

	@Override
	protected String getProfileUrl() {
		return "https://api.twitter.com/1.1/account/verify_credentials.json";
	}
	
}
