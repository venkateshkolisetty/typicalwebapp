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
<title>Page Not Found</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="http-error-container">
		<h1>TypicalWebApp - 404 Page Not Found</h1>
		<p class="message-text">
			The page you requested is not available. You might try returning to
			the <a href="<c:url value="/"/>">Home page</a>.
		</p>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>