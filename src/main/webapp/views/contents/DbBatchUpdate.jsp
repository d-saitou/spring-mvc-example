<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/contents.css" media="all"/>
	<link rel="stylesheet" type="text/css" href="${contextPath}/webjars/jquery-ui/jquery-ui.min.css" media="all" />
	<script src="${contextPath}/webjars/jquery/jquery.min.js"></script>
	<script src="${contextPath}/webjars/jquery-ui/jquery-ui.min.js"></script>
	<script src="${contextPath}/resources/js/common.js"></script>
	<%-- Get date format from message source --%>
	<spring:message code="contents.format.inputdate" var="inputDateFormat" />
	<script>
		// Set event after loading DOM
		$(document).ready(function() {
			// Search input tags
			selectObj = document.getElementsByTagName('input');
			matchObj = new RegExp('.scheduledate');
			for (i = 0; i < selectObj.length; i++) {
				if (selectObj[i].id.match(matchObj)) {
					// Set datepicker to scheduledate
					setDatepickerByName(selectObj[i].name, '${inputDateFormat}')
					// Disable key input for scheduledate
					setFormKeydownDisableByName(selectObj[i].name);
				}
			}
		});
	</script>
</head>
<body>
<div id="wrapper">
	
	<%-- Title --%>
	<div id="header">
		<h1><spring:message code="DbBatchUpdate.text.title"/></h1>
	</div>
	
	<%-- Display message --%>
	<c:if test="${not empty message}">
		<span>${message}</span>
	</c:if>
	
	<%-- Display data --%>
	<form:form action="${contextPath}/db/batchupdate" modelAttribute="taskListForm">
		<div class="button-area">
			<spring:message code="contents.button.reset" var="resetBtn"/>
			<button type="reset">${resetBtn}</button>
			<spring:message code="contents.button.register" var="registerBtn"/>
			<button type="submit">${registerBtn}</button>
		</div>
		<table class="table-style">
			<thead>
				<tr>
					<th><spring:message code="DbSelectAll.th.id"/></th>
					<th><spring:message code="DbSelectAll.th.title"/></th>
					<th><spring:message code="DbSelectAll.th.scheduledate"/></th>
					<th><spring:message code="DbSelectAll.th.status"/></th>
					<th><spring:message code="DbSelectAll.th.description"/></th>
					<th><spring:message code="DbSelectAll.th.userid"/></th>
				</tr>
			</thead>
			<c:if test="${not empty taskListForm}">
			<tbody>
				<c:forEach items="${taskListForm.tasklist}" var="task" varStatus="status">
				<tr>
					<td class="select-column1">
						<c:out value="${task.id}"/>
						<form:hidden path="tasklist[${status.index}].id"/>
					</td>
					<td class="select-column2">
						<form:input path="tasklist[${status.index}].title" cssClass="wide-form" maxlength="100"/><br>
						<form:errors path="tasklist[${status.index}].title" cssClass="errors"></form:errors>
					</td>
					<td class="select-column3">
						<fmt:formatDate var="scheduledate" value="${task.scheduledate}" pattern="${inputDateFormat}"/>
						<form:input path="tasklist[${status.index}].scheduledate" value="${scheduledate}" cssClass="wide-form center-text"/>
					</td>
					<td class="select-column4">
						<form:checkbox path="tasklist[${status.index}].status"/>
					</td>
					<td class="select-column5">
						<form:input path="tasklist[${status.index}].description" cssClass="wide-form" maxlength="200"/>
					</td>
					<td class="select-column6">
						<c:out value="${task.userid}"/>
						<form:hidden path="tasklist[${status.index}].userid"/>
					</td>
				</tr>
				</c:forEach>
			</tbody>
			</c:if>
		</table>
	</form:form>
	
</div>
</body>
</html>
