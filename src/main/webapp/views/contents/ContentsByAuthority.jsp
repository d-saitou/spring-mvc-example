<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/contents.css" media="all" />
</head>
<body>
<div id="wrapper">
	
	<%-- Title --%>
	<div id="header">
		<h1><spring:message code="ContentsByAuthority.text.title" /></h1>
	</div>
	
<sec:authorize access="hasAuthority('SHOW_COMMON_PAGE')">
	<%-- Only users with "SHOW_COMMON_PAGE" authority will display the following message --%>
	<span><spring:message code="ContentsByAuthority.msg.common" /></span>
</sec:authorize>
<sec:authorize access="hasAuthority('SHOW_ADMIN_PAGE')">
	<%-- Only users with "SHOW_ADMIN_PAGE" authority will display the following message --%>
	<span><spring:message code="ContentsByAuthority.msg.admin" /></span>
</sec:authorize>
	
</div>
</body>
</html>
