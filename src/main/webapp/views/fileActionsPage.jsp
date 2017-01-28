<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>File Upload here</title>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/styles/typicalwebappStyling.css">

<script
	src="${pageContext.request.contextPath}/resources/scripts/core/jquery/jquery-3.1.1.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/scripts/core/jquery.fileDownload.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/scripts/app/typicalwebappScripting.js"></script>
<%-- <script
	src="${pageContext.request.contextPath}/resources/scripts/jquery/jQuery-File-Upload-9.14.2/js/vendor/jquery.ui.widget.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/scripts/jquery/jQuery-File-Upload-9.14.2/js/jquery.iframe-transport.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/scripts/jquery/jQuery-File-Upload-9.14.2/js/jquery.fileupload.js"></script> --%>

</head>
<body>

	<br />

	<div id="testRequestDiv">
		<input type="button" id="testRequestResponseButton"
			value="Test Request Response" onclick="testRequestResponse()">
	</div>

	<br />

	<div id="fileUploadDiv">
		<input id="fileUploadButton" type="button"
			onclick="fileUploadFunction()" value="Upload"> <input
			id="fileUploadStatus" type="text">
	</div>
	
	<br />
	
	<div id="progress">
		<div class="bar" style="width: 0%;"></div>
	</div>
	
	<br />
	
	<div id="fileDownloadDiv">
		<input id="fileDownloadButton" type="button"
			onclick="fileDownloadFunction()" value="Download"> <input
			id="fileDownloadStatus" type="text">
	</div>
	
	<br />

</body>
</html>