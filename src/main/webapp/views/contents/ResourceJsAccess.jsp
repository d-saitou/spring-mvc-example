<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/contents.css" media="all" />
	<script src="${contextPath}/resources/js/sample.js" /></script>
	<script>
		// Execute javascript after loading DOM
		window.onload = function() {
			sample();
		};
	</script>
</head>
<body>
<div id="wrapper">
	
	<%-- Title --%>
	<div id="header">
		<h1><spring:message code="ResourceJsAccess.text.title" /></h1>
	</div>
	
</div>
</body>
</html>
