<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page session="true" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<spring:theme code="mobile.custom.css.file" var="mobileCss" text="" />
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
	<head>
	    <title>Eureka! Clinical Analytics</title>
        <c:choose>
           <c:when test="${not empty requestScope['isMobile'] and not empty mobileCss}">
                <meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;" />
                <meta name="apple-mobile-web-app-capable" content="yes" />
                <meta name="apple-mobile-web-app-status-bar-style" content="black" />
                <link type="text/css" rel="stylesheet" media="screen" href="<c:url value="/css/fss-framework-1.1.2.css" />" />
                <link type="text/css" rel="stylesheet" href="<c:url value="/css/fss-mobile-${requestScope['browserType']}-layout.css" />" />
                <link type="text/css" rel="stylesheet" href="${mobileCss}" />
           </c:when>
           <c:otherwise>
                <spring:theme code="standard.custom.css.file" var="customCssFile" />
                <link type="text/css" rel="stylesheet" href="<c:url value="${customCssFile}" />" />
           </c:otherwise>
        </c:choose>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link rel="icon" href="<c:url value="/favicon.ico" />" type="image/x-icon" />
<!--[if IE 7]>
<link href="${pageContext.request.contextPath}/css/ie7.css" rel="stylesheet" type="text/css">
<style>
.container { width:1224px; margin: 0 auto;}
ul.nav a { zoom: 1; }
#submit { border:none;}
</style>
<![endif]-->
	</head>
	<body>
		<div class="container">
			<div>
				<div class="header">
					<span><a href="index.html"><img src="images/tag_line.gif" alt="Data Analysis Tool" width="238" align="absmiddle" /></a></span>  </div>
				</div>
				<div>    
					<ul class="nav">
						<li><a href="about.html"><img src="images/about_icon.gif" alt="About" width="30" height="30" align="absmiddle" />About</a></li>
						<img src="images/reg_icon.gif" alt="Register" width="30" height="30" align="absmiddle" />
						<li><a href="register.html">Register</a></li>
						<img src="images/acct_icon.gif" alt="Account" width="30" height="30" align="absmiddle" />
						<li><a href="acct.html">Account</a></li>
						<img src="images/contact_icon.gif" alt="Contact" width="30" height="30" align="absmiddle" />
						<li><a href="contact.html">Contact</a></li>
						<img src="images/help_icon.gif" alt="Help" width="30" height="30" align="absmiddle" />
						<li><a href="help.html">Help</a></li>
					</ul>
				</div>
				<div class="sidebar1">
					<br />
					<p><img src="images/scientific_research.jpg" /></p>
				</div>
				<div class="content">
