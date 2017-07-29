<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<!--
	 - jsTree 3.3.3
	 -  Copyright (c) 2014 Ivan Bozhanov (http://vakata.com)
	 -  Licensed under the MIT license.
	 -  https://raw.githubusercontent.com/vakata/jstree/master/LICENSE-MIT
	 -->
	<link rel="stylesheet" type="text/css"
		  href="${contextPath}/webjars/jstree/themes/default/style.min.css" media="all" />
	<script src="${contextPath}/webjars/jquery/jquery.min.js"></script>
	<script src="${contextPath}/webjars/jstree/jstree.min.js"></script>
	<script src="${contextPath}/resources/js/menu.js"></script>
</head>
<body>
	
	<!-- Root icon -->
	<span>&nbsp;</span><img src="${contextPath}/resources/img/folder.png" width="16px" height="16px">
	<a id="openall" href="#"><spring:message code="menu.text.openall" /></a>
	<span>/</span>
	<a id="closeall" href="#"><spring:message code="menu.text.closeall" /></a>
	
	<!-- Menu tree
	 * Usually in jsTree, <a> tag in <li> tag does not work as a link, so JavaScript detects 
	   the click event of <a> tag and transitions the screen. (see menu.js) -->
	<div id="contentsTree">
		<ul>
		<li data-jstree='{ "opened" : false, "icon" : "${contextPath}/resources/img/folder.png"  }'>
			<a href="#">
				<b><spring:message code="menu.text.requestparam" /></b>
			</a>
			<ul>
				<!-- "Get query parameters" screen (to QueryParameterController class) -->
				<li data-jstree='{ "icon" : "${contextPath}/resources/img/webpage.png" }'>
					<a target="contents" href="queryparam/test/999?param1=param1&param2=param2">
					<spring:message code="QueryParameter.text.title" />
					</a>
				</li>
			</ul>
		</li>
		<li data-jstree='{ "opened" : false, "icon" : "${contextPath}/resources/img/folder.png" }'>
			<a href="#">
				<b><spring:message code="menu.text.form" /></b>
			</a>
			<ul>
				<!-- "Input form (not use form class)" screen (to InputFormController class) -->
				<li data-jstree='{ "icon" : "${contextPath}/resources/img/webpage.png" }'>
					<a target="contents" href="form/input/nonformobj">
					<spring:message code="InputFormNotUseFormObj.text.title" />
					</a>
				</li>
				<!-- "Input form (use form class)" screen (to InputFormController class) -->
				<li data-jstree='{ "icon" : "${contextPath}/resources/img/webpage.png" }'>
					<a target="contents" href="form/input/useformobj">
					<spring:message code="InputFormUseFormObj.text.title" />
					</a>
				</li>
				<!-- "Input form validation" (to InputFormValidationController class) -->
				<li data-jstree='{ "icon" : "${contextPath}/resources/img/webpage.png" }'>
					<a target="contents" href="form/validation">
					<spring:message code="InputFormValidation.text.title" />
					</a>
				</li>
				<!-- "Array input form validation" screen (to ArrayInputFormValidationController class) -->
				<li data-jstree='{ "icon" : "${contextPath}/resources/img/webpage.png" }'>
					<a target="contents" href="form/array">
					<spring:message code="ArrayInputFormValidation.text.title" />
					</a>
				</li>
			</ul>
		</li>
		<li data-jstree='{ "opened" : false, "icon" : "${contextPath}/resources/img/folder.png" }'>
			<a href="#">
				<b><spring:message code="menu.text.file" /></b>
			</a>
			<ul>
				<!-- "File upload" screen (to FileUploadController class) -->
				<li data-jstree='{ "icon" : "${contextPath}/resources/img/webpage.png" }'>
					<a target="contents" href="file/upload">
					<spring:message code="FileUpload.text.title" />
					</a>
				</li>
				<!-- "File download" link (to FileDownloadController class) -->
				<li data-jstree='{ "icon" : "${contextPath}/resources/img/download.png" }'>
					<a target="contents" href="file/download">
					<spring:message code="FileDownload.text.title" />
					</a>
				</li>
			</ul>
		</li>
		<li data-jstree='{ "opened" : false, "icon" : "${contextPath}/resources/img/folder.png" }'>
			<a href="#">
				<b><spring:message code="menu.text.db" /></b>
			</a>
			<ul>
				<!-- "DB-CRUD (select/update/delete)" screen (to DbInsertController class) -->
				<li data-jstree='{ "icon" : "${contextPath}/resources/img/webpage.png" }'>
					<a target="contents" href="db/insert">
					<spring:message code="DbInsert.text.title" />
					</a>
				</li>
				<!-- "DB-CRUD (insert)" screen (to DbSelectAllController class) -->
				<li data-jstree='{ "icon" : "${contextPath}/resources/img/webpage.png" }'>
					<a target="contents" href="db/select">
					<spring:message code="DbSelectAll.text.title" />
					</a>
				</li>
				<!-- "DB-CRUD (batch-update)" screen (to DbBatchUpdateController class) -->
				<li data-jstree='{ "icon" : "${contextPath}/resources/img/webpage.png" }'>
					<a target="contents" href="db/batchupdate">
					<spring:message code="DbBatchUpdate.text.title" />
					</a>
				</li>
				<!-- "DB paging select" screen (to DbPagingSelectController class) -->
				<li data-jstree='{ "icon" : "${contextPath}/resources/img/webpage.png" }'>
					<a target="contents" href="db/pagination?page=1&size=${pageSize}">
					<spring:message code="DbPagingSelect.text.title" />
					</a>
				</li>
				<!-- "DB transaction test" link (to DbTransactionTestController class) -->
				<li data-jstree='{ "icon" : "${contextPath}/resources/img/webpage.png" }'>
					<a target="contents" href="db/transaction">
					<spring:message code="DbTransactionTest.text.title" />
					</a>
				</li>
			</ul>
		</li>
		<li data-jstree='{ "opened" : false, "icon" : "${contextPath}/resources/img/folder.png" }'>
			<a href="#">
				<b><spring:message code="menu.text.rest" /></b>
			</a>
			<ul>
				<!-- "REST-API (text)" link (to RestApiController class) -->
				<li data-jstree='{ "icon" : "${contextPath}/resources/img/webpage.png" }'>
					<a target="contents" href="rest/text">
					<spring:message code="RestApiText.text.title" />
					</a>
				</li>
				<!-- "REST-API (xml)" link (to RestApiController class) -->
				<li data-jstree='{ "icon" : "${contextPath}/resources/img/webpage.png" }'>
					<a target="contents" href="rest/xml">
					<spring:message code="RestApiXml.text.title" />
					</a>
				</li>
				<!-- "REST-API (JSON)" link (to RestApiController class) -->
				<li data-jstree='{ "icon" : "${contextPath}/resources/img/webpage.png" }'>
					<a target="contents" href="rest/json">
					<spring:message code="RestApiJson.text.title" />
					</a>
				</li>
				<!-- "REST-API (JSON) request" screen (to JsonRequestController class) -->
				<li data-jstree='{ "icon" : "${contextPath}/resources/img/webpage.png" }'>
					<a target="contents" href="rest/jsonrequest">
					<spring:message code="JsonRequest.text.title" />
					</a>
				</li>
				<!-- "REST-API (JSON) Ajax request" screen (to JsonAjaxRequestController class) -->
				<li data-jstree='{ "icon" : "${contextPath}/resources/img/webpage.png" }'>
					<a target="contents" href="rest/jsonajaxrequest">
					<spring:message code="JsonAjaxRequest.text.title" />
					</a>
				</li>
			</ul>
		</li>
		<li data-jstree='{ "opened" : false, "icon" : "${contextPath}/resources/img/folder.png" }'>
			<a href="#">
				<b><spring:message code="menu.text.scope" /></b>
			</a>
			<ul>
				<!-- "Session scope bean (set)" screen (to SessionScopeController class) -->
				<li data-jstree='{ "icon" : "${contextPath}/resources/img/webpage.png" }'>
					<a target="contents" href="scope/session/set">
					<spring:message code="SessionScopeSet.text.title" />
					</a>
				</li>
			</ul>
		</li>
		<li data-jstree='{ "opened" : false, "icon" : "${contextPath}/resources/img/folder.png" }'>
			<a href="#">
				<b><spring:message code="menu.text.async" /></b>
			</a>
			<ul>
				<!-- "Asynchronous (send mail)" screen (to AsyncMailController class) -->
				<li data-jstree='{ "icon" : "${contextPath}/resources/img/webpage.png" }'>
					<a target="contents" href="async/mail">
					<spring:message code="AsyncMail.text.title" />
					</a>
				</li>
			</ul>
		</li>
		<li data-jstree='{ "opened" : false, "icon" : "${contextPath}/resources/img/folder.png" }'>
			<a href="#">
				<b><spring:message code="menu.text.task" /></b>
			</a>
			<ul>
				<!-- "Scheduled task history" screen (to TaskHistoryController class) -->
				<li data-jstree='{ "icon" : "${contextPath}/resources/img/webpage.png" }'>
					<a target="contents" href="task/history">
					<spring:message code="TaskHistory.text.title" />
					</a>
				</li>
			</ul>
		</li>
		<li data-jstree='{ "opened" : false, "icon" : "${contextPath}/resources/img/folder.png" }'>
			<a href="#">
				<b><spring:message code="menu.text.resource" /></b>
			</a>
			<ul>
				<!-- "Resource import (JavaScript)" screen (to ResourceAccessController class) -->
				<li data-jstree='{ "icon" : "${contextPath}/resources/img/webpage.png" }'>
					<a target="contents" href="resource/js">
					<spring:message code="ResourceJsAccess.text.title" />
					</a>
				</li>
				<!-- "Resource import (Webjars)" screen (to ResourceAccessController class) -->
				<li data-jstree='{ "icon" : "${contextPath}/resources/img/webpage.png" }'>
					<a target="contents" href="resource/webjars">
					<spring:message code="ResourceWebjarsAccess.text.title" />
					</a>
				</li>
			</ul>
		</li>
		<li data-jstree='{ "opened" : false, "icon" : "${contextPath}/resources/img/folder.png" }'>
			<a href="#">
				<b><spring:message code="menu.text.authority" /></b>
			</a>
			<ul>
				<!-- "Reference authority (admin user only)" screen (to AuthorityController class) -->
				<li data-jstree='{ "icon" : "${contextPath}/resources/img/webpage.png" }'>
					<a target="contents" href="authority/reference">
					<spring:message code="ReferenceAuthority.text.title" />
					</a>
				</li>
				<!-- "Contents by authority" screen (to AuthorityController class) -->
				<li data-jstree='{ "icon" : "${contextPath}/resources/img/webpage.png" }'>
					<a target="contents" href="authority/contentsbyauth">
					<spring:message code="ContentsByAuthority.text.title" />
					</a>
				</li>
			</ul>
		</li>
		<li data-jstree='{ "opened" : false, "icon" : "${contextPath}/resources/img/folder.png" }'>
			<a href="#">
				<b><spring:message code="menu.text.exception" /></b>
			</a>
			<ul>
				<!-- "Exception handle test (@ExceptionHandler)" link (to ExceptionHandleTestController class) -->
				<li data-jstree='{ "icon" : "${contextPath}/resources/img/webpage.png" }'>
					<a target="contents" href="exception/null">
					<spring:message code="ExceptionHandleTestNullPointerException.text.title" />
					</a>
				</li>
				<!-- "Exception handle test (HandlerExceptionResolver)" link (to ExceptionHandleTestController class) -->
				<li data-jstree='{ "icon" : "${contextPath}/resources/img/webpage.png" }'>
					<a target="contents" href="exception/illegal">
					<spring:message code="ExceptionHandleTestIllegalArgumentException.text.title" />
					</a>
				</li>
			</ul>
		</li>
		</ul>
	</div>
	
</body>
</html>