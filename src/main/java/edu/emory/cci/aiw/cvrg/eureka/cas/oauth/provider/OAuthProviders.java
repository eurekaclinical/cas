package edu.emory.cci.aiw.cvrg.eureka.cas.oauth.provider;

/*-
 * #%L
 * CAS Server
 * %%
 * Copyright (C) 2012 - 2016 Emory University
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

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
