package edu.emory.cci.aiw.cvrg.eureka.cas.oauth.profile;

import java.util.Locale;
import org.scribe.up.profile.AttributesDefinition;
import org.scribe.up.profile.BaseOAuthProfile;
import org.scribe.up.profile.CommonProfile;
import org.scribe.up.profile.Gender;

/**
 *
 * @author arpost
 */
public class EurekaProfile extends BaseOAuthProfile implements CommonProfile {
	
	private static final long serialVersionUID = 1;

	@Override
    protected AttributesDefinition getAttributesDefinition() {
        return OAuthAttributesDefinitions.eurekaDefinition;
    }
	
	@Override
	public String getEmail() {
		return (String) get(EurekaAttributesDefinition.EMAIL);
	}

	@Override
	public String getFirstName() {
		return (String) get(EurekaAttributesDefinition.FIRSTNAME);
	}

	@Override
	public String getFamilyName() {
		return (String) get(EurekaAttributesDefinition.LASTNAME);
	}

	@Override
	public String getDisplayName() {
		return (String) get(EurekaAttributesDefinition.FULLNAME);
	}

	@Override
	public String getUsername() {
		return (String) get(EurekaAttributesDefinition.USERNAME);
	}

	@Override
	public Gender getGender() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Locale getLocale() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public String getPictureUrl() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public String getProfileUrl() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public String getLocation() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
}
