<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<link rel="stylesheet" type="text/css" href="resources/css/base.css" media="all" />
	<title><spring:message code="header.text.title" /></title>
</head>
<body>
	
	<%-- Main header --%>
	<div id="mainheader">
		<%-- Application title --%>
		<div id="headerbox1">
			<span><spring:message code="main.text.title" /></span>
		</div>
		<%-- User ID / User name --%>
		<div id="headerbox2">
			<%-- Display proprietary properties of UserDetailsImpl class --%>
			<spring:message code="main.text.userid" /> : <sec:authentication property="principal.userid" />
			<spring:message code="main.text.username" /> : <sec:authentication property="principal.showname" />
		</div>
		<%-- Logout button --%>
		<div id="headerbox3">
			<form:form action="${contextPath}/logout">
				<spring:message code="main.button.logout" var="logoutBtn" />
				<input name="submit" type="submit" value="${logoutBtn}"/>
			</form:form>
		</div>
	</div>
	
	<%-- Content frames
	 * When using the frame tag and iframe tag, it is necessary to set the "sec: frame-options" tag 
	   in the Spring Security configuration file --%>
	<div class="item">
		<%-- Menu frame --%>
		<div id="menuframe" class="item">
			<iframe name="menu" class="iframe" src="menu"></iframe>
		</div>
		<%-- Main contents frame --%>
		<div id="contentsframe" class="item">
			<iframe name="contents" class="iframe"></iframe>
		</div>
	</div>
	
</body>
