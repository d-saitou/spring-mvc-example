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
		<h1><spring:message code="SessionScopeRef.text.title" /></h1>
	</div>
	
	<%-- Display data --%>
	<table class="table-style">
		<tr>
			<th class="table-header"><spring:message code="SessionScopeSet.th.message" /></th>
			<td class="table-form">${message}</td>
		</tr>
	</table>
	
</div>
</body>
</html>
