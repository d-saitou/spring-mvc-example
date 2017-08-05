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
	<script>
		// Set event after loading DOM
		$(document).ready(function(){
			// Set datepicker to inputdate
			setDatepicker('inputdate', '${dateFormat}')
			// Disable key input for inputdate
			setFormKeydownDisable('inputdate');
		});
	</script>
</head>
<body>
<div id="wrapper">
	
	<%-- Title --%>
	<div id="header">
		<h1><spring:message code="InputFormNotUseFormObj.text.title" /></h1>
	</div>
	
	<%-- Input form --%>
	<form:form method="post" action="${contextPath}/form/input/nonformobj">
		<table class="table-style">
			<tr>
				<th class="table-header"><spring:message code="InputForm.th.inputstr" /></th>
				<td class="table-form">
					<input type="text" id="inputstr" name="inputstr" size="20" />
				</td>
			</tr><tr>
				<th class="table-header"><spring:message code="InputForm.th.inputint" /></th>
				<td class="table-form">
					<input type="text" id="inputint" name="inputint" size="20" />
				</td>
			</tr><tr>
				<th class="table-header"><spring:message code="InputForm.th.inputdate" /></th>
				<td class="table-form">
					<input type="text" id="inputdate" name="inputdate" />
					<form:errors path="inputdate" cssClass="errors"></form:errors>
				</td>
			</tr><tr>
				<th class="table-header"><spring:message code="InputForm.th.password" /></th>
				<td class="table-form">
					<input type="password" id="password" name="password" />
				</td>
			</tr><tr>
				<th class="table-header"><spring:message code="InputForm.th.textarea" /></th>
				<td class="table-form">
					<textarea id="textarea" name="textarea" rows="5" cols="20" class="wide-form" ></textarea>
				</td>
			</tr><tr>
				<th class="table-header"><spring:message code="InputForm.th.radiobutton" /></th>
				<td class="table-form">
					<input type="radio" id="radiobutton" name="radiobutton" value="sample">
				</td>
			</tr><tr>
				<th class="table-header"><spring:message code="InputForm.th.radiobuttons" /></th>
				<td class="table-form">
					<span>
						<input type="radio" id="radiobuttons1" name="radiobuttons" value="sample1">
						<label for="radiobuttons1">sample1</label>
					</span><span>
						<input type="radio" id="radiobuttons2" name="radiobuttons" value="sample2">
						<label for="radiobuttons2">sample2</label>
					</span>
				</td>
			</tr><tr>
				<th class="table-header"><spring:message code="InputForm.th.checkbox" /></th>
				<td class="table-form">
					<input type="checkbox" id="checkbox" name="checkbox" value="sample">
				</td>
			</tr><tr>
				<th class="table-header"><spring:message code="InputForm.th.checkboxbool" /></th>
				<td class="table-form">
					<input type="checkbox" id="checkboxbool" name="checkboxbool" value="sample">
				</td>
			</tr><tr>
				<th class="table-header"><spring:message code="InputForm.th.checkboxes" /></th>
				<td class="table-form">
					<span>
						<input type="checkbox" id="checkboxes1" name="checkboxes" value="sample1">
						<label for="checkboxes1">sample1</label>
					</span>/<span>
						<input type="checkbox" id="checkboxes2" name="checkboxes" value="sample2">
						<label for="checkboxes2">sample2</label>
					</span>
				</td>
			</tr><tr>
				<th class="table-header"><spring:message code="InputForm.th.select" /></th>
				<td class="table-form">
					<select id="select" name="select" multiple="multiple" size="1">
						<option value="sample1">sample1</option>
						<option value="sample2">sample2</option>
					</select>
				</td>
			</tr><tr>
				<th class="table-header"><spring:message code="InputForm.th.selectoption" /></th>
				<td class="table-form">
					<select id="selectoption" name="selectoption" size="1">
						<option value="sample1">sample1</option>
						<option value="sample2">sample2</option>
					</select>
				</td>
			</tr><tr>
				<th class="table-header"><spring:message code="InputForm.th.hidden" /></th>
				<td class="table-form">
					<input type="hidden" id="hidden" name="hidden" value="${hidden}">${hidden}
				</td>
			</tr>
		</table>
		<div class="button-area">
			<spring:message code="contents.button.reset" var="resetBtn" />
			<button type="reset">${resetBtn}</button>
			<spring:message code="contents.button.register" var="registerBtn" />
			<button type="submit">${registerBtn}</button>
		</div>
	</form:form>
	
	<%-- Display data --%>
	<c:if test="${not empty postFlg}">
		<table class="table-style">
			<tr>
				<th class="table-header"><spring:message code="InputForm.th.inputstr" /></th>
				<td class="table-form">${inputstr}</td>
			</tr><tr>
				<th class="table-header"><spring:message code="InputForm.th.inputint" /></th>
				<td class="table-form">${inputint}</td>
			</tr><tr>
				<th class="table-header"><spring:message code="InputForm.th.inputdate" /></th>
				<td class="table-form">
					<fmt:formatDate value="${inputdate}" pattern="${dateFormat}" />
				</td>
			</tr><tr>
				<th class="table-header"><spring:message code="InputForm.th.password" /></th>
				<td class="table-form">${password}</td>
			</tr><tr>
				<th class="table-header"><spring:message code="InputForm.th.textarea" /></th>
				<td class="table-form">${textarea}</td>
			</tr><tr>
				<th class="table-header"><spring:message code="InputForm.th.radiobutton" /></th>
				<td class="table-form">${radiobutton}</td>
			</tr><tr>
				<th class="table-header"><spring:message code="InputForm.th.radiobuttons" /></th>
				<td class="table-form">${radiobuttons}</td>
			</tr><tr>
				<th class="table-header"><spring:message code="InputForm.th.checkbox" /></th>
				<td class="table-form">${checkbox}</td>
			</tr><tr>
				<th class="table-header"><spring:message code="InputForm.th.checkboxbool" /></th>
				<td class="table-form">${checkboxbool}</td>
			</tr><tr>
				<th class="table-header"><spring:message code="InputForm.th.checkboxes" /></th>
				<td class="table-form">
					<c:forEach items="${checkboxes}" var="value">
						<c:out value="${value}"/> 
					</c:forEach>
				</td>
			</tr><tr>
				<th class="table-header"><spring:message code="InputForm.th.select" /></th>
				<td class="table-form">
					<c:forEach items="${select}" var="value">
						<c:out value="${value}"/> 
					</c:forEach>
				</td>
			</tr><tr>
				<th class="table-header"><spring:message code="InputForm.th.selectoption" /></th>
				<td class="table-form">${selectoption}</td>
			</tr><tr>
				<th class="table-header"><spring:message code="InputForm.th.hidden" /></th>
				<td class="table-form">${hidden}</td>
			</tr>
		</table>
	</c:if>
	
</div>
</body>
</html>
