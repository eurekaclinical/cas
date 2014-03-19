package edu.emory.cci.aiw.cvrg.eureka.cas.oauth.provider;

import com.fasterxml.jackson.databind.JsonNode;
import edu.emory.cci.aiw.cvrg.eureka.cas.oauth.profile.EurekaAttributesDefinition;
import edu.emory.cci.aiw.cvrg.eureka.cas.oauth.profile.TwitterProfile;
import org.scribe.up.profile.JsonHelper;
import org.scribe.up.profile.UserProfile;
import org.scribe.up.profile.google2.Google2AttributesDefinition;

/**
 * Fixes bug where the scope is set incorrectly.
 * @author Andrew Post
 */
public class Google2Provider extends org.scribe.up.provider.impl.Google2Provider {

	public Google2Provider() {
		setScope(this.scope);
	}
	
	@Override
    protected UserProfile extractUserProfile(final String body) {
        final TwitterProfile profile = new TwitterProfile();
        final JsonNode json = JsonHelper.getFirstNode(body);
        if (json != null) {
            profile.setId(JsonHelper.get(json, "id"));
			profile.addAttribute(EurekaAttributesDefinition.USERNAME, JsonHelper.get(json, Google2AttributesDefinition.EMAIL));
			profile.addAttribute(EurekaAttributesDefinition.FIRSTNAME, JsonHelper.get(json, Google2AttributesDefinition.GIVEN_NAME));
			profile.addAttribute(EurekaAttributesDefinition.LASTNAME, JsonHelper.get(json, Google2AttributesDefinition.FAMILY_NAME));
			profile.addAttribute(EurekaAttributesDefinition.FULLNAME, JsonHelper.get(json, Google2AttributesDefinition.NAME));
			profile.addAttribute(EurekaAttributesDefinition.EMAIL, JsonHelper.get(json, Google2AttributesDefinition.EMAIL));
        }
        return profile;
    }
}
