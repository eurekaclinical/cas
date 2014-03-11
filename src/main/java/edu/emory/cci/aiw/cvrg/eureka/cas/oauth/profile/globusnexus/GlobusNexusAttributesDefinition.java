package edu.emory.cci.aiw.cvrg.eureka.cas.oauth.profile.globusnexus;

import org.scribe.up.profile.OAuthAttributesDefinition;
import org.scribe.up.profile.converter.Converters;

/**
 *
 * @author Andrew Post
 */
public class GlobusNexusAttributesDefinition extends OAuthAttributesDefinition {

	public static final String USERNAME = "username";
	public static final String EMAIL = "email";
	public static final String FULLNAME = "fullname";

	public GlobusNexusAttributesDefinition() {
		String[] names = new String[]{
			USERNAME, EMAIL, FULLNAME

		};
		for (String name : names) {
			addAttribute(name, Converters.stringConverter);
		}
	}

}
