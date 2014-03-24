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
<jsp:useBean id="casProperties" scope="page" class="edu.emory.cci.aiw.cvrg.eureka.cas.CasProperties"/>
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
		<form:form method="post" id="fm1" cssClass="" commandName="${commandName}" htmlEscape="true">
			<div class="form-group has-error">
				<div class="help-block">
					<form:errors path="*" cssClass="" id="status" element="span"/>
				</div>
			</div>
			<div class="form-group">
				<label for="username">E-mail address:</label>
				<form:input cssClass="form-control" cssErrorClass="" id="username" size="25" tabindex="1"
							accesskey="${userNameAccessKey}" path="username" autocomplete="false"
							htmlEscape="true"/>
			</div>
			<div class="form-group">
				<label for="password">Password:</label>
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
			<input name="submit" id="submit" class="btn btn-primary" accesskey="l" value="Login"
				   tabindex="4" type="submit"/>
			<%--<input name="reset" id="submit" class="btn-reset"  accesskey="c" value="Clear" tabindex="5" type="reset" />--%>
			<a class="btn btn-primary" href="<%= url %>forgot_password.jsp">Login Help</a>
			<input type="hidden" name="lt" value="${loginTicket}"/>
			<input type="hidden" name="execution" value="${flowExecutionKey}"/>
			<input type="hidden" name="_eventId" value="submit"/>
		</form:form>
	</c:otherwise>
</c:choose>
<script type="text/javascript">
	$('#username').focus();
</script>
<jsp:directive.include file="includes/bottom.jsp"/>
