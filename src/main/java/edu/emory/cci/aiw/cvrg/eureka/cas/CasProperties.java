package edu.emory.cci.aiw.cvrg.eureka.cas;

/**
 *
 * @author Andrew Post
 */
public class CasProperties extends AbstractProperties {
	public boolean isEphiProhibited() {
		return Boolean.parseBoolean(getValue("eureka.webapp.ephiprohibited"));
	}
	public boolean isDemoMode() {
		return Boolean.parseBoolean(getValue("eureka.webapp.demomode"));
	}
}
