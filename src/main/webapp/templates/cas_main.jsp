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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/tlds/function.tld" prefix="myfn" %>
<%
	response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader(
			"Expires", 0); //prevents caching at the proxy server
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
	<meta name="viewport" content="width=device-width, initial-scale=1"/>
	<meta name="keywords"
		  content="informatics, i2b2, biomedical, clinical research, research, de-identification, clinical data analysis, analytics, medical research, data analysis tool, clinical database, eureka!, eureka, scientific research, temporal patterns, bioinformatics, ontology, ontologies, ontology editor, data mining, etl, cvrg, CardioVascular Research Grid"/>
	<meta name="Description"
		  content="A Clinical Analysis Tool for Biomedical Informatics and Data"/>
	<link rel="SHORTCUT ICON"
		  href="${pageContext.request.contextPath}/favicon.ico">
	<link href="//fonts.googleapis.com/css?family=Source+Sans+Pro:400,600,700,400italic,600italic,700italic"
		  rel="stylesheet" type="text/css">
	<link rel="stylesheet"
		  href="${pageContext.request.contextPath}/bower_components/bootstrap/dist/css/bootstrap.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bower_components/font-awesome/css/font-awesome.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/bootstrap-social-20150401.css">
	<link rel="stylesheet"
		  href="${pageContext.request.contextPath}/assets/css/eureka.css"/>
	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.2/html5shiv.js"></script>
	<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->
	<script src="${pageContext.request.contextPath}/bower_components/jquery/dist/jquery.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/bower_components/bootstrap/dist/js/bootstrap.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/assets/js/eureka.bootbar.js" type="text/javascript"></script>
	<title>CAS</title>
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
			<a href="${pageContext.request.contextPath}/" class="navbar-brand">
				<span class="brand-text">CAS</span>
			</a>
		</div>
		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right menu-text">
				<li>
					<a href="${pageContext.request.contextPath}/#/help">
						<i class="fa fa-question-circle"></i>
						Help
					</a>
				</li>
			</ul>
		</div>
	</div>
</div>
<div class="container container-big">
	<template:get name="content"/>
</div>
<div class="footer">
	Copyright &copy; ${initParam['inception-year']}&ndash;${initParam['current-year']} ${initParam['eureka-organization-name']}. All rights reserved.
</div>
<c:if test="${userIsActivated}">
	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/eureka.util.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/eureka.idletimeout.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(document).ready(function (){
		$(document).idleTimeout({
			  idleTimeLimit: ${pageContext.session.maxInactiveInterval - 30}, //Time out with 30 seconds to spare to make sure the server session doesn't expire first
			  redirectUrl: '${pageContext.request.contextPath}/logout',
			  alertDisplayLimit: 60, // Display 60 seconds before send of session.
			  sessionKeepAliveTimer: ${pageContext.session.maxInactiveInterval - 15} //Send a keep alive signal with 15 seconds to spare.
			});
		});
	</script>
</c:if>
</body>
</html>
