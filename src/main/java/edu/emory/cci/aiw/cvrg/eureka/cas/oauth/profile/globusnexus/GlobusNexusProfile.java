package edu.emory.cci.aiw.cvrg.eureka.cas.oauth.profile.globusnexus;

import edu.emory.cci.aiw.cvrg.eureka.cas.oauth.profile.OAuthAttributesDefinitions;
import java.util.Locale;
import org.scribe.up.profile.AttributesDefinition;
import org.scribe.up.profile.BaseOAuthProfile;
import org.scribe.up.profile.CommonProfile;
import org.scribe.up.profile.Gender;

/**
 *
 * @author Andrew Post
 */
public class GlobusNexusProfile extends BaseOAuthProfile implements CommonProfile {
	
	private static final long serialVersionUID = 1;

	@Override
    protected AttributesDefinition getAttributesDefinition() {
        return OAuthAttributesDefinitions.globusNexusDefinition;
    }

	@Override
	public String getEmail() {
		return (String) get(GlobusNexusAttributesDefinition.EMAIL);
	}

	@Override
	public String getFirstName() {
		return null;
	}

	@Override
	public String getFamilyName() {
		return null;
	}

	@Override
	public String getDisplayName() {
		return (String) get(GlobusNexusAttributesDefinition.FULLNAME);
	}

	@Override
	public String getUsername() {
		return (String) get(GlobusNexusAttributesDefinition.USERNAME);
	}

	@Override
	public Gender getGender() {
		return Gender.UNSPECIFIED;
	}

	@Override
	public Locale getLocale() {
		return null;
	}

	@Override
	public String getPictureUrl() {
		return null;
	}

	@Override
	public String getProfileUrl() {
		return null;
	}

	@Override
	public String getLocation() {
		return null;
	}
	
}
