package edu.emory.cci.aiw.cvrg.eureka.cas.oauth.api;

import org.scribe.builder.api.DefaultApi20;
import org.scribe.extractors.AccessTokenExtractor;
import org.scribe.extractors.JsonTokenExtractor;
import org.scribe.model.OAuthConfig;
import org.scribe.model.Verb;
import org.scribe.utils.OAuthEncoder;
import org.scribe.utils.Preconditions;

/**
 *
 * @author Andrew Post
 */
public class GlobusNexusApi extends DefaultApi20 {
	private static final String AUTHORIZE_URL = "https://www.globusonline.org/OAuth?response_type=code&client_id=%s&redirect_uri=%s";
	
	@Override
	public String getAccessTokenEndpoint() {
		return "https://nexus.api.globusonline.org/goauth/token";
	}
	
	@Override
	public Verb getAccessTokenVerb() {
		return Verb.POST;
	}

	@Override
	public AccessTokenExtractor getAccessTokenExtractor() {
		return new JsonTokenExtractor();
	}
	
	@Override
	public String getAuthorizationUrl(OAuthConfig config) {
		Preconditions.checkValidUrl(config.getCallback(),
				"Must provide a valid url as callback.");
		return String.format(AUTHORIZE_URL, config.getApiKey(), 
				OAuthEncoder.encode(config.getCallback()));
	}
}
