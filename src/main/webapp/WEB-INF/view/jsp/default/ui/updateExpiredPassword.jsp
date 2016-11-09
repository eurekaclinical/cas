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
                        <div class="form-group">
                                <label for="oldPassword" class="control-label col-sm-3">Old Password</label>
                                <div class="col-sm-3">
                                        <form:input name="oldPassword" type="password" id="oldPassword" path="oldPassword" class="form-control"/>
                                </div>
                                <span class="col-sm-6 help-inline"></span>                               
                        </div>
                        <div class="form-group">
                                <label for="newPassword" class="control-label col-sm-3">New Password</label>
                                <div class="col-sm-3">
                                        <form:input name="newPassword" type="password" id="newPassword" path="newPassword" class="form-control"/>
                                </div>
                                <span class="col-sm-6 help-inline"></span>
                        </div>
                        <div class="form-group">
                                <label for="verifiedNewPassword" class="control-label col-sm-3">Re-enter New Password</label>
                                <div class="col-sm-3">
                                        <form:input name="verifiedNewPassword" type="password" id="verifiedNewPassword" path="verifiedNewPassword" class="form-control"/>
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
                        <input type="hidden" name="_eventId" value="submitUpdatedPassword" />                                

                </form:form>
                <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.validate.js"></script>
                <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/eureka.password.js"></script>
                <script type="text/javascript">
                        eureka.password.setup('#updateExpiredPasswordForm');
                </script>
<jsp:directive.include file="includes/bottom.jsp"/>

