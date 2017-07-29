<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/contents.css" media="all" />
	<%-- Get date format from message source --%>
	<spring:message code="contents.format.date" var="dateFormat" />
</head>
<body>
<div id="wrapper">
	
	<%-- Title --%>
	<div id="header">
		<h1><spring:message code="DbPagingSelect.text.title" /></h1>
	</div>
	
	<%-- Display data --%>
	<table class="table-style">
	<thead>
		<tr>
			<th><spring:message code="DbSelectAll.th.id" /></th>
			<th><spring:message code="DbSelectAll.th.title" /></th>
			<th><spring:message code="DbSelectAll.th.scheduledate" /></th>
			<th><spring:message code="DbSelectAll.th.status" /></th>
			<th><spring:message code="DbSelectAll.th.description" /></th>
			<th><spring:message code="DbSelectAll.th.userid" /></th>
		</tr>
	</thead>
	<c:if test="${not empty taskpage}">
	<tbody>
		<c:forEach items="${taskpage.tasklist}" var="task">
		<tr>
			<td class="select-column1"><c:out value="${task.id}" /></td>
			<td class="select-column2"><c:out value="${task.title}" /></td>
			<td class="select-column3">
				<fmt:formatDate value="${task.scheduledate}" pattern="${dateFormat}" />
			</td>
			<td class="select-column4">
				<c:if test="${task.status}"><spring:message code="DbSelectAll.text.status" /></c:if>
			</td>
			<td class="select-column5"><c:out value="${task.description}" /></td>
			<td class="select-column6"><c:out value="${task.userid}" /></td>
		</tr>
		</c:forEach>
	</tbody>
	</c:if>
	</table>
	
	<%-- Display page navigation --%>
	<c:if test="${not empty taskpage}">
	<div class="button-area">
	<ul class="page-navigation">
	<c:set var="url" value="${contextPath}/db/pagination" />
	<spring:message var="firstBtn" code="contents.button.first" />
	<spring:message var="prevBtn" code="contents.button.prev" />
	<spring:message var="nextBtn" code="contents.button.next" />
	<spring:message var="lastBtn" code="contents.button.last" />
	<c:choose>
		<c:when test="${taskpage.hasPreviousPage && not taskpage.hasNextPage}">
			<li><a href="${url}?page=1&size=${taskpage.pageSize}">&laquo; ${firstBtn}</a></li>
			<li><a href="${url}?page=${taskpage.currentPage - 1}&size=${taskpage.pageSize}">&lt; ${prevBtn}</a></li>
			<li><span>${taskpage.currentPage} / ${taskpage.totalPages}</span></li>
			<li><span>${nextBtn} &gt;</span></li>
			<li><span>${lastBtn} &raquo;</span></li>
		</c:when>
		<c:when test="${not taskpage.hasPreviousPage && taskpage.hasNextPage}">
			<li><span>&laquo; ${firstBtn}</span></li>
			<li><span>&lt; ${prevBtn}</span></li>
			<li><span>${taskpage.currentPage} / ${taskpage.totalPages}</span></li>
			<li><a href="${url}?page=${taskpage.currentPage + 1}&size=${taskpage.pageSize}">${nextBtn} &gt;</a></li>
			<li><a href="${url}?page=${taskpage.totalPages}&size=${taskpage.pageSize}">${lastBtn} &raquo;</a></li>
		</c:when>
		<c:when test="${taskpage.hasPreviousPage && taskpage.hasNextPage}">
			<li><a href="${url}?page=1&size=${taskpage.pageSize}">&laquo; ${firstBtn}</a></li>
			<li><a href="${url}?page=${taskpage.currentPage - 1}&size=${taskpage.pageSize}">&lt; ${prevBtn}</a></li>
			<li><span>${taskpage.currentPage} / ${taskpage.totalPages}</span></li>
			<li><a href="${url}?page=${taskpage.currentPage + 1}&size=${taskpage.pageSize}">${nextBtn} &gt;</a></li>
			<li><a href="${url}?page=${taskpage.totalPages}&size=${taskpage.pageSize}">${lastBtn} &raquo;</a></li>
		</c:when>
		<c:otherwise>
			<li><span>&laquo; ${firstBtn}</span></li>
			<li><span>&lt; ${prevBtn}</span></li>
			<li><span>${taskpage.currentPage} / ${taskpage.totalPages}</span></li>
			<li><span>${nextBtn} &gt;</span></li>
			<li><span>${lastBtn} &raquo;</span></li>
		</c:otherwise>
	</c:choose>
	</ul>
	</div>
	</c:if>
	
</div>
</body>
</html>
