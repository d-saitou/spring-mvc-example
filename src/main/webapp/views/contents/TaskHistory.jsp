<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/contents.css" media="all" />
	<%-- Get date format from message source --%>
	<spring:message code="contents.format.datetime" var="datetimeFormat" />
</head>
<body>
<div id="wrapper">
	
	<%-- Title --%>
	<div id="header">
		<h1><spring:message code="TaskHistory.text.title" /></h1>
	</div>
	
	<%-- Display data --%>
	<table class="table-style">
	<thead>
		<tr>
			<th><spring:message code="TaskHistory.th.id" /></th>
			<th><spring:message code="TaskHistory.th.method" /></th>
			<th><spring:message code="TaskHistory.th.message" /></th>
			<th><spring:message code="TaskHistory.th.updatedate" /></th>
		</tr>
	</thead>
	<c:if test="${not empty taskHistoryList}">
	<tbody>
		<c:forEach items="${taskHistoryList}" var="taskHistory">
		<tr>
			<td class="history-column1"><c:out value="${taskHistory.id}" /></td>
			<td class="history-column2"><c:out value="${taskHistory.method}" /></td>
			<td class="history-column3"><c:out value="${taskHistory.message}" /></td>
			<td class="history-column4">
				<fmt:formatDate value="${taskHistory.updatedate}" pattern="${datetimeFormat}" />
			</td>
		</tr>
		</c:forEach>
	</tbody>
	</c:if>
	</table>
	
</div>
</body>
</html>
