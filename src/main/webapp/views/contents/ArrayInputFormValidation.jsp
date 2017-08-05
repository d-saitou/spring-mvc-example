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
	<spring:message code="contents.format.date" var="dateFormat" />
	<script>
		// Set event after loading DOM
		$(document).ready(function() {
			// Search input tags
			matchObjBirth = new RegExp('.birthday');
			matchObjCredit = new RegExp('.credit');
			selectObj = document.getElementsByTagName('input');
			for (i = 0; i < selectObj.length; i++) {
				// Set datepicker to birthday and credit
				if (selectObj[i].id.match(matchObjBirth)) {
					setDatepickerByName(selectObj[i].name, '${inputDateFormat}')
				} else if (selectObj[i].id.match(matchObjCredit)){
					setDatepickerByName(selectObj[i].name, '${dateFormat}')
				}
			}
		});
	</script>
</head>
<body>
<div id="wrapper">
	
	<%-- Title --%>
	<div id="header">
		<h1><spring:message code="ArrayInputFormValidation.text.title"/></h1>
	</div>
	
	<%-- Display message --%>
	<c:if test="${not empty message}">
		<span class="errors">${message}</span><br>
	</c:if>
	
	<%-- Input form
	 * The object name of "modelAttribute" should match with the form class name.
	   (the first letter is lowercase)
	   The reason is that "form: errors" is not displayed when not implementing as above. --%>
	<form:form action="${contextPath}/form/array" modelAttribute="employeeListForm">
		<div class="button-area">
			<spring:message code="contents.button.reset" var="resetBtn"/>
			<button type="reset">${resetBtn}</button>
			<spring:message code="contents.button.register" var="registerBtn"/>
			<button type="submit">${registerBtn}</button>
		</div>
		<table class="table-style">
			<thead>
				<tr>
					<th><spring:message code="InputFormValidation.th.name"/></th>
					<th><spring:message code="InputFormValidation.th.gender"/></th>
					<th><spring:message code="InputFormValidation.th.birthday"/>(${inputDateFormat})</th>
					<th><spring:message code="InputFormValidation.th.age"/></th>
					<th><spring:message code="InputFormValidation.th.phone"/></th>
					<th><spring:message code="InputFormValidation.th.email"/></th>
					<th><spring:message code="InputFormValidation.th.credit"/>(${dateFormat})</th>
				</tr>
			</thead>
			<c:if test="${not empty employeeListForm}">
			<tbody>
				<c:forEach items="${employeeListForm.employeelist}" var="employee" varStatus="stat">
				<tr>
					<td class="array-form-column1">
						<form:input path="employeelist[${stat.index}].name" cssClass="wide-form" maxlength="30"/><br>
						<form:errors path="employeelist[${stat.index}].name" cssClass="errors"></form:errors>
					</td>
					<td class="array-form-column2">
						<form:select path="employeelist[${stat.index}].gender">
							<form:option value=""></form:option>
							<form:option value="MALE"><spring:message code="InputFormValidation.text.male" /></form:option>
							<form:option value="FEMALE"><spring:message code="InputFormValidation.text.female" /></form:option>
						</form:select>
						<form:errors path="employeelist[${stat.index}].gender" cssClass="errors" ></form:errors>
					</td>
					<td class="array-form-column3">
						<fmt:formatDate var="birthday" value="${employee.birthday}" pattern="${inputDateFormat}"/>
						<form:input path="employeelist[${stat.index}].birthday" value="${birthday}" cssClass="wide-form center-text"/>
						<form:errors path="employeelist[${stat.index}].birthday" cssClass="errors" ></form:errors>
					</td>
					<td class="array-form-column4">
						<form:input path="employeelist[${stat.index}].age" cssClass="wide-form" maxlength="3"/><br>
						<form:errors path="employeelist[${stat.index}].age" cssClass="errors"></form:errors>
					</td>
					<td class="array-form-column5">
						<form:input path="employeelist[${stat.index}].phone" cssClass="wide-form" maxlength="11"/><br>
						<form:errors path="employeelist[${stat.index}].phone" cssClass="errors"></form:errors>
					</td>
					<td class="array-form-column6">
						<form:input path="employeelist[${stat.index}].email" cssClass="wide-form" maxlength="256"/><br>
						<form:errors path="employeelist[${stat.index}].email" cssClass="errors"></form:errors>
					</td>
					<td class="array-form-column7">
						<form:input path="employeelist[${stat.index}].credit" cssClass="wide-form center-text"/>
						<form:errors path="employeelist[${stat.index}].credit" cssClass="errors" ></form:errors>
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
