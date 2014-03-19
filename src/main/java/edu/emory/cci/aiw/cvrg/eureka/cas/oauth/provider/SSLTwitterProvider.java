package edu.emory.cci.aiw.cvrg.eureka.cas.oauth.provider;

import com.fasterxml.jackson.databind.JsonNode;
import edu.emory.cci.aiw.cvrg.eureka.cas.oauth.profile.EurekaAttributesDefinition;
import edu.emory.cci.aiw.cvrg.eureka.cas.oauth.profile.TwitterProfile;
import org.scribe.up.profile.JsonHelper;
import org.scribe.up.profile.UserProfile;
import org.scribe.up.profile.twitter.TwitterAttributesDefinition;

/**
 *
 * @author Andrew Post
 */
public class SSLTwitterProvider extends org.scribe.up.provider.impl.TwitterProvider {

	@Override
	protected String getProfileUrl() {
		return "https://api.twitter.com/1.1/account/verify_credentials.json";
	}

	@Override
    protected UserProfile extractUserProfile(final String body) {
        final TwitterProfile profile = new TwitterProfile();
        final JsonNode json = JsonHelper.getFirstNode(body);
        if (json != null) {
            profile.setId(JsonHelper.get(json, "id"));
			profile.addAttribute(EurekaAttributesDefinition.USERNAME, JsonHelper.get(json, TwitterAttributesDefinition.SCREEN_NAME));
			profile.addAttribute(EurekaAttributesDefinition.FULLNAME, JsonHelper.get(json, TwitterAttributesDefinition.NAME));
        }
        return profile;
    }
	
	
	
}
