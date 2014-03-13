package edu.emory.cci.aiw.cvrg.eureka.cas.oauth.provider;

import com.fasterxml.jackson.databind.JsonNode;
import edu.emory.cci.aiw.cvrg.eureka.cas.oauth.api.GlobusNexusApi;
import edu.emory.cci.aiw.cvrg.eureka.cas.oauth.profile.EurekaProfile;
import edu.emory.cci.aiw.cvrg.eureka.cas.oauth.service.GlobusNexusOAuth20ServiceImpl;
import java.util.concurrent.TimeUnit;
import org.scribe.model.OAuthConfig;
import org.scribe.model.ProxyOAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.SignatureType;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.up.profile.JsonHelper;
import org.scribe.up.profile.UserProfile;
import org.scribe.up.provider.BaseOAuth20Provider;
import org.scribe.up.provider.exception.HttpException;

/**
 *
 * @author Andrew Post
 */
public class GlobusNexusProvider extends BaseOAuth20Provider {
	@Override
	protected GlobusNexusProvider newProvider() {
		return new GlobusNexusProvider();
	}
	
	@Override
	protected void internalInit() {
		this.service = new GlobusNexusOAuth20ServiceImpl(new GlobusNexusApi(),
			new OAuthConfig(this.key, this.secret, this.callbackUrl,
			SignatureType.Header, "user", null), this.proxyHost, 
				this.proxyPort);
	}
	
	@Override
	protected String getProfileUrl() {
		return "https://nexus.api.globusonline.org/users/%s";
	}

	@Override
	protected String sendRequestForData(Token accessToken, String dataUrl) throws HttpException {
		String dataUrlCompleted = String.format(dataUrl, ((GlobusNexusOAuth20ServiceImpl) this.service).getUsername());
        final ProxyOAuthRequest request = new ProxyOAuthRequest(Verb.GET, dataUrlCompleted, this.proxyHost, this.proxyPort);
        if (this.connectTimeout != 0) {
            request.setConnectTimeout(this.connectTimeout, TimeUnit.MILLISECONDS);
        }
        if (this.readTimeout != 0) {
            request.setReadTimeout(this.readTimeout, TimeUnit.MILLISECONDS);
        }
        this.service.signRequest(accessToken, request);
		request.addHeader("Content-Type", "application/json");
        final Response response = request.send();
        final int code = response.getCode();
        final String body = response.getBody();
        if (code != 200) {
            throw new HttpException(code, body);
        }
        return body;
	}
	
	@Override
	protected UserProfile extractUserProfile(final String body) {
		EurekaProfile profile = new EurekaProfile();
		JsonNode json = JsonHelper.getFirstNode(body);
		if (json != null) {
			//profile.setId(JsonHelper.get(json, EurekaAttributesDefinition.USERNAME));
			
		}
		return profile;
	}
}
