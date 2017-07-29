<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/contents.css" media="all" />
	<script src="${contextPath}/webjars/jquery/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/common.js" /></script>
	<%-- Get date format from message source --%>
	<spring:message code="contents.format.date" var="dateFormat" />
	<%-- CRSF token definition in HTTP header (required only for CRSF measures) --%>
	<sec:csrfMetaTags />
	<script type="text/javascript">
		// Get CSRF token in HTTP header (required only for CRSF measures)
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		
		// Set event when sending Ajax request (required only for CRSF measures)
		$(document).ajaxSend(function(e, xhr, options) {
			xhr.setRequestHeader(header, token);
		});
		
		// Set event after loading DOM
		$(document).ready(function() {
			// Set event when search button is pressed
			$("#search").click(function() {
				$.ajax({
					type     : "GET",				// Optional
					url      : "${contextPath}/rest/json",
					dataType : "json",				// Optional
					//user    : "XXXXXX",			// User name when requesting authentication. Optional
					//password: "XXXXXX",			// Password when requesting authentication. Optional
					//data: {						// Request parameters. Optional
					//	"key" : "value"
					//},
					statusCode: {					// Error handling with HTTP code. Optional
						404: function(){ alert("Page not found."); }
					}
				}).done(function(data, textStatus, jqXHR){
					success(data);
				}).fail(function(data, textStatus, errorThrown){
					alert("status:" + textStatus);	// Display exception information
					console.log(XMLHttpRequest);
					console.log(textStatus);
					console.log(errorThrown.message);
				}).always(function(data, textStatus, returnedObject) {
					// Currently not processed
				});
			});
		});
		
		// Processing when the Ajax request is successful
		function success(json) {
			//var str = JSON.stringify(json);	// Convert JSON data to text with jQuery
			//alert("success:" + str);
			
			// Delete existing data
			$('#data-table tbody *').remove();
			
			// Redraw task data list
			var scheduleDateTime = "";
			var title = "";
			var description = "";
			var status = "";
			var html = "";
			for (i = 0; i < json.length; i++) {
				id = json[i]["id"];
				title = escapeHtml(json[i]["title"]);
				scheduledate = json[i]["scheduledate"].replace(/-/g, "/");
				description = escapeHtml(json[i]["description"]);
				userid = json[i]["userid"];
				if(json[i]["status"] == true) {
					status = "<spring:message code="DbSelectAll.text.status" />";
				} else {
					status = "";
				}
				// Generate html of task data line
				html = '<tr>' +
					   '<td class="select-column1">' + id + '</td>' +
					   '<td class="select-column2">' + title + '</td>' +
					   '<td class="select-column3">' + scheduledate + '</td>' +
					   '<td class="select-column4">' + status + '</td>' +
					   '<td class="select-column5">' + description + '</td>' +
					   '<td class="select-column6">' + userid + '</td>' +
					   '</tr>'
				// Add an element with the append method and call trigger to apply CSS
				$("#data-table tbody").append(html).trigger("create");
			}
		}
	</script>
</head>
<body>
<div id="wrapper">
	
	<%-- Title --%>
	<div id="header">
		<h1><spring:message code="JsonAjaxRequest.text.title" /></h1>
	</div>
	
	<%-- Display button --%>
	<div class="button-area">
		<spring:message code="contents.button.search" var="searchBtn"/>
		<input type="button" id="search" value="${searchBtn}" >
	</div>
	
	<%-- Display data --%>
	<table id="data-table" class="table-style">
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
	<tbody></tbody>
	</table>
	
</div>
</body>
</html>
