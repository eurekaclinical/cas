package edu.emory.cci.aiw.cvrg.eureka.cas.oauth.provider;

import edu.emory.cci.aiw.cvrg.eureka.cas.CasProperties;
import java.util.ArrayList;
import java.util.List;
import org.scribe.up.provider.OAuthProvider;
import org.scribe.up.provider.impl.GitHubProvider;

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
	}
	
	public List<OAuthProvider> getProviders() {
		return this.providers;
	}
}
