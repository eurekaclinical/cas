package edu.emory.cci.aiw.cvrg.eureka.cas.oauth.service;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.regex.Pattern;
import org.scribe.builder.api.DefaultApi20;
import org.scribe.model.OAuthConfig;
import org.scribe.model.OAuthRequest;
import org.scribe.model.ProxyOAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuth20ServiceImpl;
import org.scribe.up.profile.JsonHelper;
import org.scribe.up.provider.exception.HttpException;

/**
 *
 * @author Andrew Post
 */
public class GlobusNexusOAuth20ServiceImpl extends OAuth20ServiceImpl {
	protected final DefaultApi20 api;
	protected final OAuthConfig config;
	protected final String proxyHost;
	protected final int proxyPort;
	private String username;

	public GlobusNexusOAuth20ServiceImpl(DefaultApi20 api, OAuthConfig config,
			String proxyHost, int proxyPort) {
		super(api, config);
		this.api = api;
		this.config = config;
		this.proxyHost = proxyHost;
		this.proxyPort = proxyPort;
	}

	public String getUsername() {
		return username;
	}
	
	@Override
	public Token getAccessToken(Token requestToken, Verifier verifier) {
		OAuthRequest request = 
				new ProxyOAuthRequest(this.api.getAccessTokenVerb(), 
						this.api.getAccessTokenEndpoint(), this.proxyHost, this.proxyPort);
		String userpass = this.config.getApiKey() + ":" + this.config.getApiSecret();
		String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes());
		request.addHeader("Authorization", basicAuth);
		request.addBodyParameter("grant_type", "authorization_code");
		request.addBodyParameter("code", verifier.getValue());
		Response response = request.send();
		String body = response.getBody();
		JsonNode json = JsonHelper.getFirstNode(body);
		if (json != null) {
			this.username = (String) JsonHelper.get(json, "user_name");
			return new Token((String) JsonHelper.get(json, "access_token"), "", body);
		} else {
			return null;
		}
		
	}

	@Override
	public void signRequest(Token accessToken, OAuthRequest request) {
		request.addHeader("X-Globus-Goauthtoken", accessToken.getToken());
	}
}
