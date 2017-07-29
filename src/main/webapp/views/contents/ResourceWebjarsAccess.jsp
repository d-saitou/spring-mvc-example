<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/contents.css" media="all" />
	<script src="${contextPath}/webjars/jquery/jquery.min.js"></script>
	<script>
		// Execute jQuery after loading DOM
		$(window).load(function(){
			alert('jQuery sample');
		});
	</script>
</head>
<body>
<div id="wrapper">
	
	<%-- Title --%>
	<div id="header">
		<h1><spring:message code="ResourceWebjarsAccess.text.title" /></h1>
	</div>
	
</div>
</body>
</html>
