<%--
  #%L
  CAS Server
  %%
  Copyright (C) 2016 Emory University
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

<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:directive.include file="includes/top.jsp"/>
<c:url value="/login" var="formActionUrl" />
<h1>Having trouble logging in?</h1>

<form:form modelAttribute="email" id="resetForgotPasswordResetForm" method="post" action="${formActionUrl}" class="form-horizontal">
	<p>
		Remember, your user name is the email address that you used when you registered.
	</p>
	<p>
		If you cannot remember your password, please enter your email address and click the Reset Password
		button below. You will receive an email with a temporary password. You will be asked to change the
		password the next time you login.
	</p>
	<div class="form-group">
		<label for="emailAddress" class="control-label col-sm-3">Email Address</label>
		<div class="col-sm-3">
			<form:input name="emailAddress" type="text" id="emailAddress" path="emailAddress" class="form-control"/>
		</div>
		<span class="col-sm-6 help-inline"></span>
	</div>
	<div class="form-group">
		<div class="col-sm-1 col-sm-offset-3 text-center">
			<input name="submit" id="submit" type="submit" value="Reset Password" class="btn btn-primary"/>
		</div>
	</div>                               
	<input type="hidden" name="lt" value="${loginTicket}" />
	<input type="hidden" name="execution" value="${flowExecutionKey}" />
	<input type="hidden" name="_eventId" value="submitResetForgotPassword" />   
</form:form>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.16.0/jquery.validate.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/cas.resetForgotPassword.js"></script>
<script type="text/javascript">
	eureka.password.setup('#resetForgotPasswordResetForm');
</script>                        
<jsp:directive.include file="includes/bottom.jsp"/>
