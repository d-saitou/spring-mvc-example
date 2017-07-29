<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.*" %>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<spring:message code="common.msg.error" />
	<% exception.printStackTrace(new java.io.PrintWriter(out)); %>
</body>
</html>
