<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/contents.css" media="all" />
	<link rel="stylesheet" type="text/css" href="${contextPath}/webjars/jquery-ui/jquery-ui.min.css" media="all" />
	<script src="${contextPath}/webjars/jquery/jquery.min.js"></script>
	<script src="${contextPath}/webjars/jquery-ui/jquery-ui.min.js"></script>
	<script src="${contextPath}/resources/js/common.js"></script>
	<%-- Get date format from message source --%>
	<spring:message code="contents.format.inputdate" var="inputDateFormat" />
	<script>
		// Set event after loading DOM
		$(document).ready(function(){
			// Set datepicker to scheduledate
			setDatepicker('scheduledate', '${inputDateFormat}')
			// Disable key input for scheduledate
			setFormKeydownDisable('scheduledate');
		});
	</script>
</head>
<body>
<div id="wrapper">
	
	<%-- Title --%>
	<div id="header">
		<h1><spring:message code="DbInsert.text.title" /></h1>
	</div>
	
	<%-- Display message --%>
	<c:if test="${not empty message}">
		<span>${message}</span>
	</c:if>
	
	<%-- Input form
	 * The object name of "modelAttribute" should match with the form class name.
	   (the first letter is lowercase)
	   The reason is that "form: errors" is not displayed when not implementing as above. --%>
	<form:form action="${contextPath}/db/insert" modelAttribute="taskForm">
		<table class="table-style">
			<tr>
				<th class="table-header"><spring:message code="DbInsert.th.title" /></th>
				<td class="table-form">
					<form:input path="title" cssClass="wide-form" />
					<form:errors path="title" cssClass="errors"></form:errors>
				</td>
			</tr><tr>
				<th class="table-header"><spring:message code="DbInsert.th.scheduledate" /></th>
				<td class="table-form">
					<form:input path="scheduledate" cssClass="wide-form date-text" />
					<form:errors path="scheduledate" cssClass="errors"></form:errors>
				</td>
			</tr><tr>
				<th class="table-header"><spring:message code="DbInsert.th.status" /></th>
				<td class="table-form">
					<form:checkbox path="status" />
					<form:errors path="status" cssClass="errors"></form:errors>
				</td>
			</tr><tr>
				<th class="table-header"><spring:message code="DbInsert.th.description" /></th>
				<td class="table-form">
					<form:input path="description" cssClass="wide-form" />
					<form:errors path="description" cssClass="errors"></form:errors>
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