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
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page session="true" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:useBean id="applicationProperties" scope="application" class="edu.emory.cci.aiw.cvrg.eureka.cas.ApplicationProperties"/>
<%--
<spring:theme code="mobile.custom.css.file" var="mobileCss" text="" />
--%>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
	<head>
		<meta charset="utf-8"/>
		<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
		<meta name="viewport" content="width=device-width, initial-scale=1"/>
		<link rel="SHORTCUT ICON" href="${pageContext.request.contextPath}/favicon.ico">
		<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Source+Sans+Pro:400,600,700,400italic,600italic,700italic">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/bootstrap-3.3.4-dist/css/bootstrap.min.css">
		<!--<link rel="stylesheet" type="text/css"
		  href="${pageContext.request.contextPath}/assets/bootstrap-3.3.4-dist/css/bootstrap-theme.min.css"/>-->
		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.2/html5shiv.js"></script>
		<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/eureka${initParam['eureka-build-timestamp']}.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/font-awesome-4.3.0/css/font-awesome.min.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/bootstrap-social-20150401.css">
		<script src="${pageContext.request.contextPath}/assets/js/jquery-2.1.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/assets/bootstrap-3.3.4-dist/js/bootstrap.min.js"></script>
		<script src="//ajax.googleapis.com/ajax/libs/webfont/1.4.7/webfont.js"></script>
		<title>Eureka! Clinical Analytics</title>
	</head>
	<body>
	<div class="navbar navbar-inverse navbar-static-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a href="${applicationProperties.applicationUrl}" class="navbar-brand">
					<span class="brand-text">Eureka!</span>
				</a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right menu-text">
					<li>
						<a href="${applicationProperties.applicationUrl}help.jsp">
							<span class="glyphicon glyphicon-question-sign"></span>
							Help
						</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<div class="container container-big content">
