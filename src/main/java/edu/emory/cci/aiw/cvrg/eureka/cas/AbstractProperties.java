/*
 * #%L
 * Eureka Common
 * %%
 * Copyright (C) 2012 - 2013 Emory University
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
package edu.emory.cci.aiw.cvrg.eureka.cas;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author hrathod
 */
public abstract class AbstractProperties {

	/**
	 * The class level logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(
			AbstractProperties.class);
	/**
	 * Name of the system property containing a pathname to the configuration
	 * file.
	 */
	private static final String CONFIG_DIR_SYS_PROP = "eureka.config.dir";

	private static final String PROPERTIES_FILENAME = "application.properties";

	/**
	 * Fallback properties file for application configuration as a resource.
	 */
	private static final String FALLBACK_CONFIG_FILE = '/' + PROPERTIES_FILENAME;

	/**
	 * Holds an instance of the properties object which contains all the
	 * application configuration properties.
	 */
	private final Properties properties;

	private final File casDotProperties;

	private String configDir;


	public AbstractProperties() {
		this.properties = new Properties();
		loadFallbackConfig();
		loadDefaultConfig();
		this.casDotProperties = new File(this.configDir, "cas.properties");
	}
	/**
	 * Loads the application configuration.
	 *
	 * There are two potential sources of application configuration. The
	 * fallback configuration should always be there. The default configuration
	 * directory, <code>/etc/eureka</code>, may optionally have an
	 * application.properties file within it that overrides the fallback
	 * configuration for each configuration property that is specified. The
	 * <code>eureka.config.dir</code> system property allows specifying an
	 * alternative configuration directory.
	 *
	 * @throws IOException if an error occurs reading the default
	 *                     configuration directory's application.properties file 
         *                     (if one exists).
	 */
	private void loadDefaultConfig() {
		this.configDir = System.getProperty(CONFIG_DIR_SYS_PROP);
		if (this.configDir == null) {
			this.configDir = getDefaultConfigDir();
		}
		if (this.configDir == null) {
			throw new AssertionError("eureka.config.dir not specified in " + FALLBACK_CONFIG_FILE);
		}
		File configFile = new File(this.configDir, PROPERTIES_FILENAME);
		if (configFile.exists()) {
			LOGGER.info("Trying to load default configuration from {}",
					configFile.getAbsolutePath());
			InputStream inputStream = null;
			try {
				inputStream = new FileInputStream(configFile);
				this.properties.load(inputStream);
				inputStream.close();
				inputStream = null;
			} catch (IOException ex) {
				LOGGER.error("Error reading application.properties file {}: {}. "
						+ "Built-in defaults will be used, some "
						+ "of which are unlikely to be what you want.",
						configFile.getAbsolutePath(), ex.getMessage());
			} finally {
				if (inputStream != null) {
					try {
						inputStream.close();
					} catch (IOException ignore) {
					}
				}
			}
		} else {
			LOGGER.warn("No configuration directory found at {0}. "
					+ "Built-in defaults will be used, some "
					+ "of which are unlikely to be what you want.",
					configFile.getAbsolutePath());
		}
	}
	
	/**
	 * Gets the default location of configuration file, based on the operating
	 * system.
	 *
	 * @return A String containing the default configuration location.
	 */
	private static String getDefaultConfigDir() {
		return "/etc/eureka";
	}

	private void loadFallbackConfig() {
		InputStream inputStream = getClass().getResourceAsStream(FALLBACK_CONFIG_FILE);
		try {
			this.properties.load(inputStream);
			inputStream.close();
			inputStream = null;
		} catch (IOException ioe) {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException ignore) {
				}
			}
			throw new AssertionError("Fallback configuration is unavailable: " + ioe.getMessage());
		}
		this.properties.remove(CONFIG_DIR_SYS_PROP);
	}

	public String getCasDotPropertiesPathname() {
		return this.casDotProperties.exists() ? "file:" + this.casDotProperties.getPath() : "classpath:/cas.properties";
	}

	/**
	 * Gets the user-configured directory where the INI configuration files for
	 * Protempa are located.
	 *
	 * @return A string containing the path to the directory containing Protempa
	 * INI configuration files.
	 */
	public final String getConfigDir() {
		return this.configDir;
	}

	/**
	 * Get the base URL for the application front-end for external users.
	 *
	 * @return The base URL.
	 */
	public String getApplicationUrl() {
		String result = this.getValue("eureka.webapp.url");
		if (result.endsWith("/")) {
			return result;
		} else {
			return result + "/";
		}
	}

	/**
	 * Returns the String value of the given property name.
	 *
	 * @param propertyName The property name to fetch the value for.
	 * @return A String containing the value of the given property name, or null
	 * if the property name does not exist.
	 */
	protected String getValue(final String propertyName) {
		/*
		 * Don't use this.properties.containsKey(). It doesn't work with the
		 * built-in default value mechanism. Also, the semantics of a
		 * Properties list are such that a null property value is the same
		 * as never specified in the list.
		 */
		return this.properties.getProperty(propertyName);
	}

	/**
	 * Returns the String value of the given property name, or the given default
	 * if the given property name does not exist.
	 *
	 * @param propertyName The name of the property to fetch a value for.
	 * @param defaultValue The default value to return if the property name does
	 * not exist.
	 * @return A string containing either the value of the property, or the
	 * default value.
	 */
	protected String getValue(final String propertyName, String defaultValue) {
		String value = getValue(propertyName);
		if (value == null) {
			if (defaultValue == null) {
				LOGGER.warn(
						"Property '{}' is not specified in "
						+ getClass().getName()
						+ ", and no default is specified.", propertyName);
			}
			value = defaultValue;
		}
		return value;
	}

	/**
	 * Reads in a property value as a whitespace-delimited list of items.
	 *
	 * @param inPropertyName The name of the property to read.
	 * @return A list containing the items in the value, or <code>null</code> if
	 * the property is not found.
	 */
	protected List<String> getStringListValue(final String inPropertyName) {

		List<String> result;
		String value = this.properties.getProperty(inPropertyName);
		if (value != null) {
			String[] temp = value.split("\\s+");
			result = new ArrayList<String>(temp.length);
			for (String s : temp) {
				String trimmed = s.trim();
				if (trimmed.length() > 0) {
					result.add(s.trim());
				}
			}
		} else {
			result = null;
		}
		return result;
	}

	/**
	 * Reads in a property value as a whitespace-delimited list of items.
	 *
	 * @param inPropertyName The name of the property to read.
	 * @param defaultValue The value to return if the property is not found.
	 * @return A list containing the items in the value.
	 */
	protected List<String> getStringListValue(final String inPropertyName,
			List<String> defaultValue) {

		List<String> result = getStringListValue(inPropertyName);
		if (result == null) {
			LOGGER.warn("Property not found in configuration: {}",
					inPropertyName);
			result = defaultValue;
		}
		return result;
	}

	/**
	 * Utility method to get an int from the properties file.
	 *
	 * @param propertyName The name of the property.
	 * @param defaultValue The default value to return, if the property is not
	 * found, or is malformed.
	 * @return The property value, as an int.
	 */
	protected int getIntValue(final String propertyName, int defaultValue) {
		int result;
		String property = this.properties.getProperty(propertyName);
		try {
			result = Integer.parseInt(property);
		} catch (NumberFormatException e) {
			LOGGER.warn("Invalid integer property in configuration: {}",
					propertyName);
			result = defaultValue;
		}
		return result;
	}
}
