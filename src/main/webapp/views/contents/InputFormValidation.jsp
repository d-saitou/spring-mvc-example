<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/contents.css" media="all" />
	<script src="${contextPath}/webjars/jquery/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/common.js"></script>
	<%-- Get date format from message source --%>
	<spring:message code="contents.format.inputdate" var="inputDateFormat" />
	<spring:message code="contents.format.date" var="dateFormat" />
</head>
<body>
<div id="wrapper">
	
	<%-- Title --%>
	<div id="header">
		<h1><spring:message code="InputFormValidation.text.title" /></h1>
	</div>
	
	<%-- Input form
	 * The object name of "modelAttribute" should match with the form class name.
	   (the first letter is lowercase)
	   The reason is that "form: errors" is not displayed when not implementing as above. --%>
	<form:form action="${contextPath}/form/validation" modelAttribute="employeeForm">
		<table class="table-style">
		<tbody>
			<tr>
				<th class="table-header"><spring:message code="InputFormValidation.th.name" /></th>
				<td class="table-form">
					<form:input path="name" size="20" />
					<form:errors path="name" cssClass="errors"></form:errors>
				</td>
			</tr><tr>
				<th class="table-header"><spring:message code="InputFormValidation.th.gender" /></th>
				<td class="table-form">
					<%-- <form:select path="gender" multiple="true"> --%>
					<form:select path="gender">
						<form:option value=""></form:option>
						<form:option value="MALE"><spring:message code="InputFormValidation.text.male" /></form:option>
						<form:option value="FEMALE"><spring:message code="InputFormValidation.text.female" /></form:option>
					</form:select>
					<form:errors path="gender" cssClass="errors" ></form:errors>
				</td>
			</tr><tr>
				<th class="table-header"><spring:message code="InputFormValidation.th.birthday" /></th>
				<td class="table-form">
					<form:input path="birthday" size="20" /> (${inputDateFormat}) 
					<form:errors path="birthday" cssClass="errors" ></form:errors>
				</td>
			</tr><tr>
				<th class="table-header"><spring:message code="InputFormValidation.th.age" /></th>
				<td class="table-form">
					<form:input path="age" size="20" />
					<form:errors path="age" cssClass="errors" ></form:errors>
				</td>
			</tr><tr>
				<th class="table-header"><spring:message code="InputFormValidation.th.phone" /></th>
				<td class="table-form">
					<form:input path="phone" size="20" />
					<form:errors path="phone" cssClass="errors" ></form:errors>
				</td>
			</tr><tr>
				<th class="table-header"><spring:message code="InputFormValidation.th.email" /></th>
				<td class="table-form">
					<form:input path="email" size="20" />
					<form:errors path="email" cssClass="errors" ></form:errors>
				</td>
			</tr><tr>
				<th class="table-header"><spring:message code="InputFormValidation.th.credit" /></th>
				<td class="table-form">
					<form:input path="credit" size="20" /> (${dateFormat}) 
					<form:errors path="credit" cssClass="errors" ></form:errors>
				</td>
			</tbody>
		</table>
		<div class="button-area">
			<spring:message code="contents.button.reset" var="resetBtn" />
			<button type="reset">${resetBtn}</button>
			<spring:message code="contents.button.register" var="registerBtn" />
			<button type="submit">${registerBtn}</button>
		</div>
	</form:form>
	
	<%-- Display data --%>
	<c:if test="${not empty resultForm}">
		<table class="table-style">
			<thead>
				<tr>
					<th><spring:message code="InputFormValidation.th.name" /></th>
					<th><spring:message code="InputFormValidation.th.gender" /></th>
					<th><spring:message code="InputFormValidation.th.birthday" /></th>
					<th><spring:message code="InputFormValidation.th.age" /></th>
					<th><spring:message code="InputFormValidation.th.phone" /></th>
					<th><spring:message code="InputFormValidation.th.email" /></th>
					<th><spring:message code="InputFormValidation.th.credit" /></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td class="validation-column1"><c:out value="${resultForm.name}" /></td>
					<td class="validation-column2">
						<c:choose>
							<c:when test="${resultForm.gender == 'MALE'}">
								<spring:message code="InputFormValidation.text.male" />
							</c:when>
							<c:when test="${resultForm.gender == 'FEMALE'}">
								<spring:message code="InputFormValidation.text.female" />
							</c:when>
							<c:otherwise></c:otherwise>
						</c:choose>
					</td>
					<td class="validation-column3">
						<fmt:formatDate value="${resultForm.birthday}" pattern="${dateFormat}" />
					</td>
					<td class="validation-column4">
						<c:out value="${resultForm.age}" />
					</td>
					<td class="validation-column5">
						<c:out value="${resultForm.phone}" />
					</td>
					<td class="validation-column6">
						<c:out value="${resultForm.email}" />
					</td>
					<td class="validation-column3">
						<c:out value="${resultForm.credit}" />
					</td>
				</tr>
			</tbody>
		</table>
	</c:if>
	
</div>
</body>
</html>
