<%--
  #%L
  CAS Server
  %%
  Copyright (C) 2012 - 2016 Emory University
  %%
  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at
  
       http://www.apache.org/licenses/LICENSE-2.0
  
  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
  #L%
  --%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--

    Licensed to Jasig under one or more contributor license
    agreements. See the NOTICE file distributed with this work
    for additional information regarding copyright ownership.
    Jasig licenses this file to you under the Apache License,
    Version 2.0 (the "License"); you may not use this file
    except in compliance with the License. You may obtain a
    copy of the License at:

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on
    an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied. See the License for the
    specific language governing permissions and limitations
    under the License.

--%>

<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:directive.include file="includes/top.jsp"/>
<%--
	The oauth_provider parameter appears to be found only when the user
	rejects the OAuth authorization request. This provider information throws
	this page into an invalid state where none of the OAuth provider URLs
	get populated. To avoid this problem, we watch for the presence of 
	the oauth_provider parameter and redirect to the Eureka main page.
--%>
<c:if test="${param.oauth_provider != null}">
	<c:redirect url="${casProperties.applicationUrl}"/>
</c:if>
<h3>Login</h3>
<c:choose>
	<c:when test="${not pageContext.request.secure}">
		<div class="label label-danger">
			<p>You are currently accessing CAS over a non-secure connection. Single Sign on WILL NOT WORK. In order to
				have single sign on work, you MUST log in over HTTPS.
			</p>
		</div>
	</c:when>
	<c:otherwise>
		<c:if test="${casProperties.demoMode or casProperties.ephiProhibited}">
			<p>
				*Please note that loading real patient data into the system is strictly prohibited!
			</p>
		</c:if>
		<div class="row">
			<div class="col-sm-6 col-sm-offset-3">
				<form:form method="post" id="fm1" cssClass="" commandName="${commandName}" htmlEscape="true">
					<div class="form-group has-error">
						<div class="help-block">
							<form:errors path="*" cssClass="" id="status" element="span"/>
						</div>
					</div>
					<div class="form-group">
						<label for="username">Username</label>
						<form:input cssClass="form-control" cssErrorClass="" id="username" size="25" tabindex="1"
									accesskey="${userNameAccessKey}" path="username" autocomplete="false"
									htmlEscape="true"/>
					</div>
					<div class="form-group">
						<label for="password">Password</label>
							<%--
							NOTE: Certain browsers will offer the option of caching passwords for a user.  There is a non-standard attribute,
							"autocomplete" that when set to "off" will tell certain browsers not to prompt to cache credentials.  For more
							information, see the following web page:
							http://www.geocities.com/technofundo/tech/web/ie_autocomplete.html
							--%>
						<form:password cssClass="form-control" cssErrorClass="" id="password" size="25" tabindex="2"
									   path="password" accesskey="${passwordAccessKey}" htmlEscape="true"
									   autocomplete="off"/>
					</div>
					<input name="submit" id="submit" class="btn btn-lg btn-primary btn-block" accesskey="l" value="Login"
						   tabindex="4" type="submit"/>
					<div style="text-align: right">
						<a href="${casProperties.applicationUrl}forgot_password.jsp">Forgot Password?</a>
					</div>
					<input type="hidden" name="lt" value="${loginTicket}"/>
					<input type="hidden" name="execution" value="${flowExecutionKey}"/>
					<input type="hidden" name="_eventId" value="submit"/>
				</form:form>
			</div>
		</div>
		<c:if test="${casProperties.OAuthEnabled}">
			<div class="row">
				<div class="col-sm-6 col-sm-offset-3 text-center">
					<h4>or sign in with</h4>
					<c:if test="${casProperties.googleAuthEnabled}">
						<a href="${Google2ProviderUrl}" class="btn btn-social-icon btn-lg btn-google-plus" title="Sign in with Google">
							<i class="fa fa-google-plus"></i>
						</a>
					</c:if>
					<c:if test="${casProperties.gitHubAuthEnabled}">
						<a href="${GitHubProviderUrl}" class="btn btn-social-icon btn-lg btn-github" title="Sign in with GitHub">
							<i class="fa fa-github"></i>
						</a>
					</c:if>
					<c:if test="${casProperties.twitterAuthEnabled}">
						<a href="${SSLTwitterProviderUrl}" class="btn btn-social-icon btn-lg btn-twitter" title="Sign in with Twitter">
							<i class="fa fa-twitter"></i>
						</a>
					</c:if>
					<c:if test="${casProperties.globusAuthEnabled}">
						<a href="${GlobusProviderUrl}"><!--<img src="${pageContext.request.contextPath}/images/globus_45.png" width="45" height="45" title="Sign in with Globus" alt="Globus">-->Globus</a>
					</c:if>
				</div>
			</div>
		</c:if>
	</c:otherwise>
</c:choose>
<script type="text/javascript">
	$('#username').focus();
</script>
<jsp:directive.include file="includes/bottom.jsp"/>
