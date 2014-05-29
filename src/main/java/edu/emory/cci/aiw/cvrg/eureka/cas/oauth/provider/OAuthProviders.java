package edu.emory.cci.aiw.cvrg.eureka.cas.oauth.provider;

import org.eurekaclinical.scribeupext.provider.SSLTwitterProvider;
import org.eurekaclinical.scribeupext.provider.Google2Provider;
import org.eurekaclinical.scribeupext.provider.GitHubProvider;
import org.eurekaclinical.scribeupext.provider.GlobusProvider;
import edu.emory.cci.aiw.cvrg.eureka.cas.CasProperties;
import java.util.ArrayList;
import java.util.List;
import org.scribe.up.provider.OAuthProvider;

/**
 *
 * @author Andrew Post
 */
public final class OAuthProviders {
	private final List<OAuthProvider> providers;

	public OAuthProviders() {
		this.providers = new ArrayList<OAuthProvider>();
		CasProperties casProperties = new CasProperties();
		if (casProperties.isTwitterAuthEnabled()) {
			SSLTwitterProvider twitterProvider = new SSLTwitterProvider();
			twitterProvider.setKey(casProperties.getTwitterKey());
			twitterProvider.setSecret(casProperties.getTwitterSecret());
			this.providers.add(twitterProvider);
		}
		if (casProperties.isGoogleAuthEnabled()) {
			Google2Provider googleProvider = new Google2Provider();
			googleProvider.setKey(casProperties.getGoogleKey());
			googleProvider.setSecret(casProperties.getGoogleSecret());
			this.providers.add(googleProvider);
		}
		if (casProperties.isGitHubAuthEnabled()) {
			GitHubProvider gitHubProvider = new GitHubProvider();
			gitHubProvider.setKey(casProperties.getGitHubKey());
			gitHubProvider.setSecret(casProperties.getGitHubSecret());
			this.providers.add(gitHubProvider);
		}
		if (casProperties.isGlobusAuthEnabled()) {
			GlobusProvider globusProvider = new GlobusProvider();
			globusProvider.setKey(casProperties.getGlobusKey());
			globusProvider.setSecret(casProperties.getGlobusSecret());
			this.providers.add(globusProvider);
		}
	}
	
	public List<OAuthProvider> getProviders() {
		return this.providers;
	}
}
