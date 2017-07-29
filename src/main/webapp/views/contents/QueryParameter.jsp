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
		<h1><spring:message code="QueryParameter.text.title" /></h1>
	</div>
	
	<%-- Display data --%>
	<table class="table-style">
		<tr>
			<th class="table-header"><spring:message code="QueryParameter.th.param1" /></th>
			<td class="table-form">${param1}</td>
		</tr><tr>
			<th class="table-header"><spring:message code="QueryParameter.th.param2" /></th>
			<td class="table-form">${param2}</td>
		</tr><tr>
			<th class="table-header"><spring:message code="QueryParameter.th.param3" /></th>
			<td class="table-form">${param3}</td>
		</tr><tr>
			<th class="table-header"><spring:message code="QueryParameter.th.param4" /></th>
			<td class="table-form">${param4}</td>
		</tr>
	</table>
	
</div>
</body>
</html>
