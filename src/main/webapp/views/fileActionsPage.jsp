<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>File Upload here</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<br />

	<div id="testRequestDiv">
		<input type="button" id="testRequestResponseButton"
			value="Test Request Response" onclick="testRequestResponse()">
		<textarea id="testRequestResponseResponseData" rows="5" cols="50"></textarea>	
	</div>

	<br />

	<div id="fileUploadDiv">
		<input id="fileUpload" type="file" name="uploadedFile"> <input
			id="fileUploadButton" type="button" onclick="fileUploadFunction()"
			value="Upload"> <input id="fileUploadStatus" type="text">
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
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>