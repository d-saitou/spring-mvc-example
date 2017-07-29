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
	<spring:message code="contents.format.date" var="dateFormat" />
	<spring:message code="contents.format.inputdate" var="inputDateFormat" />
	<script>
		// Set event after loading DOM
		$(document).ready(function(){
			// Set datepicker to inputdate
			setDatepicker('inputdate', '${inputDateFormat}')
			// Disable key input for inputdate
			setFormKeydownDisable('inputdate');
		});
	</script>
</head>
<body>
<div id="wrapper">
	
	<%-- Title --%>
	<div id="header">
		<h1><spring:message code="InputFormUseFormObj.text.title" /></h1>
	</div>
	
	<%-- Input form --%>
	<form:form action="${contextPath}/form/input/useformobj" modelAttribute="inputForm" >
		<table class="table-style">
			<tr>
				<th class="table-header"><spring:message code="InputForm.th.inputstr" /></th>
				<td class="table-form">
					<form:input path="inputstr" size="20" />
				</td>
			</tr><tr>
				<th class="table-header"><spring:message code="InputForm.th.inputint" /></th>
				<td class="table-form">
					<form:input path="inputint" size="20" />
				</td>
			</tr><tr>
				<th class="table-header"><spring:message code="InputForm.th.inputdate" /></th>
				<td class="table-form">
					<form:input path="inputdate" />
					<form:errors path="inputdate" cssClass="errors"></form:errors>
				</td>
			</tr><tr>
				<th class="table-header"><spring:message code="InputForm.th.password" /></th>
				<td class="table-form">
					<form:password path="password" showPassword="false" />
				</td>
			</tr><tr>
				<th class="table-header"><spring:message code="InputForm.th.textarea" /></th>
				<td class="table-form">
					<form:textarea path="textarea" cols="20" rows="5" cssClass="wide-form" />
				</td>
			</tr><tr>
				<th class="table-header"><spring:message code="InputForm.th.radiobutton" /></th>
				<td class="table-form">
					<form:radiobutton path="radiobutton" value="sample" />
				</td>
			</tr><tr>
				<th class="table-header"><spring:message code="InputForm.th.radiobuttons" /></th>
				<td class="table-form">
					<form:radiobuttons path="radiobuttons" items="${sampleItems}" />
				</td>
			</tr><tr>
				<th class="table-header"><spring:message code="InputForm.th.checkbox" /></th>
				<td class="table-form">
					<form:checkbox path="checkbox" value="sample" />
				</td>
			</tr><tr>
				<th class="table-header"><spring:message code="InputForm.th.checkboxbool" /></th>
				<td class="table-form">
					<form:checkbox path="checkboxbool" value="sample" />
				</td>
			</tr><tr>
				<th class="table-header"><spring:message code="InputForm.th.checkboxes" /></th>
				<td class="table-form">
					<form:checkboxes path="checkboxes" items="${sampleItems}" delimiter="/" />
				</td>
			</tr><tr>
				<th class="table-header"><spring:message code="InputForm.th.select" /></th>
				<td class="table-form">
					<form:select path="select" size="1" items="${sampleItems}" multiple="true" />
				</td>
			</tr><tr>
				<th class="table-header"><spring:message code="InputForm.th.selectoption" /></th>
				<td class="table-form">
					<form:select path="selectoption" size="1" multiple="false" >
						<form:option value="sample1">sample1</form:option>
						<form:option value="sample2">sample2</form:option>
					</form:select>
				</td>
			</tr><tr>
				<th class="table-header"><spring:message code="InputForm.th.hidden" /></th>
				<td class="table-form">
					<form:hidden path="hidden" />${inputForm.hidden}
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
	
	<%-- Display data --%>
	<c:if test="${not empty resultForm}">
		<table class="table-style">
			<tr>
				<th class="table-header"><spring:message code="InputForm.th.inputstr" /></th>
				<td class="table-form">${resultForm.inputstr}</td>
			</tr><tr>
				<th class="table-header"><spring:message code="InputForm.th.inputint" /></th>
				<td class="table-form">${resultForm.inputint}</td>
			</tr><tr>
				<th class="table-header"><spring:message code="InputForm.th.inputdate" /></th>
				<td class="table-form">
					<fmt:formatDate value="${resultForm.inputdate}" pattern="${dateFormat}" />
				</td>
			</tr><tr>
				<th class="table-header"><spring:message code="InputForm.th.password" /></th>
				<td class="table-form">${resultForm.password}</td>
			</tr><tr>
				<th class="table-header"><spring:message code="InputForm.th.textarea" /></th>
				<td class="table-form">${resultForm.textarea}</td>
			</tr><tr>
				<th class="table-header"><spring:message code="InputForm.th.radiobutton" /></th>
				<td class="table-form">${resultForm.radiobutton}</td>
			</tr><tr>
				<th class="table-header"><spring:message code="InputForm.th.radiobuttons" /></th>
				<td class="table-form">${resultForm.radiobuttons}</td>
			</tr><tr>
				<th class="table-header"><spring:message code="InputForm.th.checkbox" /></th>
				<td class="table-form">${resultForm.checkbox}</td>
			</tr><tr>
				<th class="table-header"><spring:message code="InputForm.th.checkboxbool" /></th>
				<td class="table-form">${resultForm.checkboxbool}</td>
			</tr><tr>
				<th class="table-header"><spring:message code="InputForm.th.checkboxes" /></th>
				<td class="table-form">
					<c:forEach items="${resultForm.checkboxes}" var="value">
						<c:out value="${value}"/> 
					</c:forEach>
				</td>
			</tr><tr>
				<th class="table-header"><spring:message code="InputForm.th.select" /></th>
				<td class="table-form">
					<c:forEach items="${resultForm.select}" var="value">
						<c:out value="${value}"/> 
					</c:forEach>
				</td>
			</tr><tr>
				<th class="table-header"><spring:message code="InputForm.th.selectoption" /></th>
				<td class="table-form">${resultForm.selectoption}</td>
			</tr><tr>
				<th class="table-header"><spring:message code="InputForm.th.hidden" /></th>
				<td class="table-form">${resultForm.hidden}</td>
			</tr>
		</table>
	</c:if>
	
</div>
</body>
</html>
