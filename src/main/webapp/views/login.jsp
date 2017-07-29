<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<link rel="stylesheet" type="text/css" href="resources/css/base.css" media="all" />
	<link rel="stylesheet" type="text/css" href="resources/css/contents.css" media="all" />
	<script src="${contextPath}/webjars/jquery/jquery.min.js"></script>
	<script>
		// Set event after loading DOM
		// * In the case of a session disconnection, when the login screen is displayed 
		//   in the "contents" frame, the parent frame is redirected to the login screen.
		$(document).ready(function(){
			if (self.frames.name != '') {
				parent.location.href = '${contextPath}/login';
			}
		});
	</script>
	<title><spring:message code="header.text.title" /></title>
</head>
<body>
	
	<%-- Main header --%>
	<div id="mainheader">
		<%-- application title --%>
		<div id="headerbox1">
			<span><spring:message code="main.text.title" /></span>
		</div>
	</div>
	
	<div id="login-box">
		<%-- Login form --%>
		<form name="f" action="login" method="POST">
			<div class="login-form-box">
				<div class="login-form-box1">
					<spring:message code="login.text.userid"/>&nbsp;:&nbsp;
				</div>
				<div class="login-form-box2">
					<input type="text" name="username" value="">
				</div>
			</div>
			<div class="login-form-box">
				<div class="login-form-box1">
					<spring:message code="login.text.password" />&nbsp;:&nbsp;
				</div>
				<div class="login-form-box2">
					<input type="password" name="password"/>
				</div>
			</div>
			<form:errors path="*" />
			<spring:message code="login.button.login" var="loginbtn" />
			<input name="submit" type="submit" value="${loginbtn}"/>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		</form>
		<%-- Display user list --%>
		<table class="table-style">
			<thead>
				<tr>
					<th><spring:message code="login.th.userid"/></th>
					<th><spring:message code="login.th.password"/></th>
					<th><spring:message code="login.th.description"/></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><spring:message code="login.text.userid.admin"/></td>
					<td><spring:message code="login.text.password.admin"/></td>
					<td><spring:message code="login.text.description.admin"/></td>
				</tr><tr>
					<td><spring:message code="login.text.userid.user"/></td>
					<td><spring:message code="login.text.password.user"/></td>
					<td><spring:message code="login.text.description.user"/></td>
				</tr>
			</tbody>
		</table>
		<%-- Display error message --%>
		<c:if test="${not empty error}">
			<span class="errors">${error}</span>
		</c:if>
	</div>
	
</body>
</html>
