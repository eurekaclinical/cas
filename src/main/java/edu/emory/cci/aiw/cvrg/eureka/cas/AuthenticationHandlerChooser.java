package edu.emory.cci.aiw.cvrg.eureka.cas;

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
