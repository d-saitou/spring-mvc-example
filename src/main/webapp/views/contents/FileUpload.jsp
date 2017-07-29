<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/contents.css" media="all" />
	<script src="${contextPath}/webjars/jquery/jquery.min.js"></script>
	<script>
		// Set event after loading DOM
		$(document).ready(function(){
			// Click event for add button
			$('#addBtn').on('click',function(){
				var target = '#fileListId tbody'
				var len = $(target).children().length;
				var html = '<tr>' +
						   '<td><input type="file" name="files[' + len + ']"></td>' +
						   '</tr>'
				// Add an element with the append method and call trigger to apply CSS
				$(target).append(html).trigger('create');
			});
		});
	</script>
</head>
<body>
<div id="wrapper">
	
	<%-- Title --%>
	<div id="header">
		<h1><spring:message code="FileUpload.text.title" /></h1>
	</div>
	
	<%-- Display message --%>
	<c:if test="${not empty message}">
		<span>${message}</span><br>
	</c:if>
	
	<%-- Input form (single file) --%>
	<span><spring:message code="FileUpload.text.singleupdate" /></span>
	<form:form modelAttribute="singleFileUploadForm"
			enctype="multipart/form-data" action="${contextPath}/file/upload/single">
		<table class="table-style">
		<thead>
			<tr><th><spring:message code="FileUpload.th.file" /></th></tr>
		</thead>
		<tbody>
			<tr><td><input type="file" name="file"></td></tr>
		</tbody>
		</table>
		<div class="button-area">
			<spring:message code="contents.button.reset" var="resetBtn" />
			<input type="reset" value="${resetBtn}">
			<spring:message code="contents.button.register" var="registerBtn" />
			<input type="submit" value="${registerBtn}">
		</div>
	</form:form>
	
	<%-- Input form (multiple files) --%>
	<span><spring:message code="FileUpload.text.multipleupdate" /></span>
	<c:set var="fileListId" value="fileList" />
	<form:form modelAttribute="multiFileUploadForm"
			enctype="multipart/form-data" action="${contextPath}/file/upload/multi">
		<table id="fileListId" class="table-style">
		<thead>
			<tr><th><spring:message code="FileUpload.th.file" /></th></tr>
		</thead>
		<tbody>
			<tr><td><input type="file" name="files[0]"></td></tr>
			<%-- * Add a line with the [Add] button. --%>
		</tbody>
		</table>
		<div class="button-area">
			<spring:message code="contents.button.reset" var="resetBtn" />
			<input type="reset" value="${resetBtn}">
			<spring:message code="FileUpload.button.add" var="addBtn" />
			<input type="button" id="addBtn" value="${addBtn}">
			<spring:message code="contents.button.register" var="registerBtn" />
			<input type="submit" value="${registerBtn}">
		</div>
	</form:form>
	
</div>
</body>
</html>
