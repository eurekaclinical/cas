package edu.emory.cci.aiw.cvrg.eureka.cas.oauth.provider;

import com.fasterxml.jackson.databind.JsonNode;
import edu.emory.cci.aiw.cvrg.eureka.cas.oauth.profile.EurekaAttributesDefinition;
import edu.emory.cci.aiw.cvrg.eureka.cas.oauth.profile.GitHubProfile;
import org.scribe.up.profile.JsonHelper;
import org.scribe.up.profile.UserProfile;
import org.scribe.up.profile.github.GitHubAttributesDefinition;

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
			profile.addAttribute(EurekaAttributesDefinition.USERNAME, JsonHelper.get(json, GitHubAttributesDefinition.LOGIN));
			profile.addAttribute(EurekaAttributesDefinition.FULLNAME, JsonHelper.get(json, GitHubAttributesDefinition.NAME));
			profile.addAttribute(EurekaAttributesDefinition.EMAIL, JsonHelper.get(json, GitHubAttributesDefinition.EMAIL));
			profile.addAttribute(EurekaAttributesDefinition.ORGANIZATION, JsonHelper.get(json, GitHubAttributesDefinition.COMPANY));
        }
        return profile;
	}
	
}
