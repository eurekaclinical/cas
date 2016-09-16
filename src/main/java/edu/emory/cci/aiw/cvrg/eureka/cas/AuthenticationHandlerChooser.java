package edu.emory.cci.aiw.cvrg.eureka.cas;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Andrew Post
 */
public class AuthenticationHandlerChooser {

	private String authHandlersString;
	private Map authHandlers;

	public String getAuthHandlersString() {
		return authHandlersString;
	}

	public void setAuthHandlersString(String authHandlersString) {
		this.authHandlersString = authHandlersString;
	}

	public Map getAuthHandlers() {
		return authHandlers;
	}

	public void setAuthHandlers(Map authHandlers) {
		this.authHandlers = authHandlers;
	}

	public List getSelectedAuthHandlers() {
		List result = new ArrayList();
		if (this.authHandlersString != null) {
			for (String handlerString : this.authHandlersString.split(",")) {
				if (this.authHandlers.containsKey(handlerString)) {
					result.add(this.authHandlers.get(handlerString));
				} else {
					throw new IllegalStateException("Invalid authentication handler specified: " + handlerString);
				}
			}
		}
		return result;
	}
}
