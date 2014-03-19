package edu.emory.cci.aiw.cvrg.eureka.cas.oauth.profile;

import org.scribe.up.profile.OAuthAttributesDefinition;
import org.scribe.up.profile.converter.Converters;

/**
 *
 * @author Andrew Post
 */
public class EurekaAttributesDefinition extends OAuthAttributesDefinition {
	public static final String USERNAME = "username";
	public static final String FIRSTNAME = "firstName";
	public static final String LASTNAME = "lastName";
	public static final String FULLNAME = "fullName";
	public static final String EMAIL = "email";
	public static final String ORGANIZATION = "organization";
	
	public EurekaAttributesDefinition() {
		String[] names = new String[]{
			USERNAME, FULLNAME, EMAIL, ORGANIZATION

		};
		for (String name : names) {
			addAttribute(name, Converters.stringConverter);
		}
	}
}
