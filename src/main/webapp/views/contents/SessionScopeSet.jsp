<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/contents.css" media="all" />
	<script src="${contextPath}/webjars/jquery/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/common.js"></script>
</head>
<body>
<div id="wrapper">
	
	<%-- Title --%>
	<div id="header">
		<h1><spring:message code="SessionScopeSet.text.title" /></h1>
	</div>
	
	<%-- Input form --%>
	<form:form method="post" action="${contextPath}/scope/session/set">
		<table class="table-style">
			<tr>
				<th class="table-header"><spring:message code="SessionScopeSet.th.message" /></th>
				<td class="table-form">
					<input type="text" id="message" name="message" class="wide-form" />
				</td>
			</tr>
		</table>
		<div class="button-area">
			<spring:message code="contents.button.reset" var="resetBtn" />
			<input type="reset" value="${resetBtn}">
			<spring:message code="contents.button.register" var="registerBtn" />
			<input type="submit" value="${registerBtn}">
		</div>
	</form:form>
	
</div>
</body>
</html>
