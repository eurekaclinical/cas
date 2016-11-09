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

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.ByteArrayOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author miaoai
 */
public class FreeMarkerEmailSender {
	private static final Logger LOGGER = LoggerFactory.getLogger(FreeMarkerEmailSender.class);

	/**
	 * The FreeMarker configuration object.
	 */
	private final Configuration configuration;
	/**
	 * The mail session used to send the email.
	 */
	private final Session session;
	/**
	 * The application configuration properties.
	 */
	private final ApplicationProperties applicationProperties;

	/**
	 * Default constructor, creates a FreeMarker configuration object.
	 *
	 * @param inApplicationProperties The application configuration object.
	 * @param inSession The mail session to use when sending a message.
	 */

	public FreeMarkerEmailSender( final ApplicationProperties inApplicationProperties, final Session inSession) {
		this.applicationProperties = inApplicationProperties;
		this.session = inSession;
		this.configuration = new Configuration();
		this.configuration.setClassForTemplateLoading(this.getClass(),
				"/templates/");
		this.configuration.setObjectWrapper(new DefaultObjectWrapper());
	}
        
	public void sendPasswordResetMessage(String inEmailAdress, final String inFirstName, final String inLastName, String inNewPassword)
			throws EmailException {
		Map<String, Object> params = new HashMap<>();
		params.put("plainTextPassword", inNewPassword);
		sendMessage1(inEmailAdress, inFirstName, inLastName, 
                        "password_reset.ftl", "Password reset on Eureka! Clinical Analytics", params);
	}

        
	private void sendMessage1(final String inEmailAdress, final String inFirstName, final String inLastName, 
                final String templateName, final String emailSubject, Map<String, Object> params) 
                throws EmailException {
            
		params.put("firstName", inFirstName);
                params.put("lastName", inLastName);
		params.put("config", this.applicationProperties);
		sendMessage2(templateName, emailSubject, inEmailAdress, params);
	}
        
	private void sendMessage2(final String templateName, final String emailSubject, final String emailAddress, final Map<String, Object> params)
                throws EmailException {
            
		Writer stringWriter = new StringWriter();
		try {
			Template template = this.configuration.getTemplate(templateName);
			template.process(params, stringWriter);
		} catch (TemplateException | IOException e) {
			throw new EmailException(e);
		}

		String content = stringWriter.toString();
		MimeMessage message = new MimeMessage(this.session);
		try {
			InternetAddress fromEmailAddress = null;
			String fromEmailAddressStr
					= this.applicationProperties.getFromEmailAddress();
			if (fromEmailAddressStr != null) {
				fromEmailAddress = new InternetAddress(fromEmailAddressStr);
			}
			if (fromEmailAddress == null) {
				fromEmailAddress
						= InternetAddress.getLocalAddress(this.session);
			}
			if (fromEmailAddress == null) {
				try {
					fromEmailAddress = new InternetAddress("no-reply@"
							+ InetAddress.getLocalHost().getCanonicalHostName());
				} catch (UnknownHostException ex) {
					fromEmailAddress = new InternetAddress("no-reply@localhost");
				}
			}
			message.setFrom(fromEmailAddress);
			message.setSubject(emailSubject);
			message.setContent(content, "text/plain");
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					emailAddress));
			message.setSender(fromEmailAddress);
			Transport.send(message);
		} catch (MessagingException e) {
			LOGGER.error("Error sending the following email message:");
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			try {
				message.writeTo(out);
				out.close();
			} catch (IOException | MessagingException ex) {
				try {
					out.close();
				} catch (IOException ignore) {
				}
			}
			LOGGER.error(out.toString());
			throw new EmailException(e);
		}
	}        
}
