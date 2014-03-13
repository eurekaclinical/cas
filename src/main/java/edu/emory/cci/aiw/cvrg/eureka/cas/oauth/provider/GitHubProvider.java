package edu.emory.cci.aiw.cvrg.eureka.cas.oauth.provider;

import com.fasterxml.jackson.databind.JsonNode;
import edu.emory.cci.aiw.cvrg.eureka.cas.oauth.profile.EurekaAttributesDefinition;
import edu.emory.cci.aiw.cvrg.eureka.cas.oauth.profile.EurekaProfile;
import edu.emory.cci.aiw.cvrg.eureka.cas.oauth.profile.GitHubProfile;
import org.scribe.up.profile.JsonHelper;
import org.scribe.up.profile.UserProfile;

/**
 *
 * @author Andrew Post
 */
public class GitHubProvider extends org.scribe.up.provider.impl.GitHubProvider {

	@Override
	protected UserProfile extractUserProfile(String body) {
		final GitHubProfile profile = new GitHubProfile();
        final JsonNode json = JsonHelper.getFirstNode(body);
        if (json != null) {
            profile.setId(JsonHelper.get(json, "id"));
			profile.addAttribute(EurekaAttributesDefinition.FULLNAME, JsonHelper.get(json, "name"));
			profile.addAttribute(EurekaAttributesDefinition.EMAIL, JsonHelper.get(json, "email"));
			profile.addAttribute(EurekaAttributesDefinition.ORGANIZATION, JsonHelper.get(json, "company"));
			//profile.setAccessToken(key);
        }
        return profile;
	}
	
}
