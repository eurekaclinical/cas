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
<jsp:directive.include file="includes/top.jsp" />
<div id="msg" class="success">
	<c:choose>
		<c:when test="${param.notRegistered}">
			<h3>Not Registered</h3>
			<div id="msg" class="alert alert-danger" role="alert">
				<strong>Login failed</strong> because you are not registered to use Eureka!. Go to <a href="${applicationProperties.applicationUrl}chooseaccounttype" class="alert-link">the registration page</a> to begin.
			</div>
		</c:when>
		<c:when test="${param.awaitingActivation}">
			<h3>Account Awaiting Activation</h3>
			<div id="msg" class="alert alert-danger" role="alert">
				<strong>Login failed</strong> because your account is awaiting activation.
			</div>
		</c:when>
		<c:when test="${param.goHome}">
			<c:redirect url="${applicationProperties.applicationUrl}"/>
		</c:when>
		<c:otherwise>
			<h3>Logout</h3>
			<div id="msg" class="alert alert-success" role="alert">
				<p><strong>You are logged out. For security reasons, exit your web browser.</strong></p>
			</div>
		</c:otherwise>
	</c:choose>
</div>
<jsp:directive.include file="includes/bottom.jsp" />
