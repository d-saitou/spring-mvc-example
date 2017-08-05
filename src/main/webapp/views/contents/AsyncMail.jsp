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
		<h1><spring:message code="AsyncMail.text.title" /></h1>
	</div>
	
	<%-- Display message --%>
	<c:if test="${not empty message}">
		<span>${message}</span><br>
	</c:if>
	
	<%-- Input form --%>
	<form:form action="${contextPath}/async/mail" modelAttribute="mailForm" >
		<table class="table-style">
			<tr>
				<th class="table-header"><spring:message code="AsyncMail.th.from" /></th>
				<td class="table-form"><c:out value="${mailForm.from}" /></td>
			</tr><tr>
				<th class="table-header"><spring:message code="AsyncMail.th.to" /></th>
				<td class="table-form"><c:out value="${mailForm.to}" /></td>
			</tr><tr>
				<th class="table-header"><spring:message code="AsyncMail.th.subject" /></th>
				<td class="table-form">
					<form:input path="subject" cssClass="wide-form" />
				</td>
			</tr><tr>
				<th class="table-header"><spring:message code="AsyncMail.th.message" /></th>
				<td class="table-form">
					<form:textarea path="message" rows="20" cssClass="wide-form" />
				</td>
			</tr>
		</table>
		<div class="button-area">
			<spring:message code="contents.button.reset" var="resetBtn" />
			<button type="reset">${resetBtn}</button>
			<spring:message code="AsyncMail.button.send" var="sendBtn" />
			<button type="submit">${sendBtn}</button>
		</div>
	</form:form>
	
</div>
</body>
</html>