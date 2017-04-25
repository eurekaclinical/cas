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
                <h3>Password Expiration</h3>
                <p>Your password has expired. Enter a new one below:</p>


                <form:form modelAttribute="passwords" id="updateExpiredPasswordForm" method="post" class="form-horizontal">
                    
                            <c:if test="${not empty updateExpiredPasswordValidationError}">
                            <div class="errors" style="width:250px; color: #b94a48;">
                                <spring:message code="updateExpiredPassword.generic-error" />
                            </div>
                            </c:if>
                    
                        <div class="form-group">
                                <label for="oldPassword" class="control-label col-sm-3">Old Password</label>
                                <div class="col-sm-3">
                                        <form:password name="oldPassword" id="oldPassword" path="oldPassword" class="form-control"/>
                                        <form:errors path="oldPassword" style="color: #b94a48;"/>
                                </div>
                                <span class="col-sm-6 help-inline"></span> 

                        </div>
                        <div class="form-group">
                                <label for="newPassword" class="control-label col-sm-3">New Password</label>
                                <div class="col-sm-3">
                                        <form:password name="newPassword" id="newPassword" path="newPassword" class="form-control"/>
                                        <form:errors path="newPassword" style="color: #b94a48;"/>
                                </div>
                                <span class="col-sm-6 help-inline"></span>
                        </div>
                        <div class="form-group">
                                <label for="verifiedNewPassword" class="control-label col-sm-3">Re-enter New Password</label>
                                <div class="col-sm-3">
                                        <form:password name="verifiedNewPassword" id="verifiedNewPassword" path="verifiedNewPassword" class="form-control"/>
                                </div>
                                <span class="col-sm-6 help-inline"></span>
                        </div>
                                
                        <div class="form-group">
                                <div class="col-sm-1 col-sm-offset-3 text-center">
                                        <input name="submit" id="submit" type="submit" value="Save" class="btn btn-primary"/>
                                </div>
                        </div>
                        <input type="hidden" name="lt" value="${loginTicket}" />
                        <input type="hidden" name="execution" value="${flowExecutionKey}" />
                        <input type="hidden" name="_eventId" value="submitUpdatedExpiredPassword" />                                

                </form:form>
                <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.16.0/jquery.validate.min.js"></script>
                <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/cas.updateExpiredPassword.js"></script>
                <script type="text/javascript">
                        eureka.password.setup('#updateExpiredPasswordForm');
                </script>
<jsp:directive.include file="includes/bottom.jsp"/>

