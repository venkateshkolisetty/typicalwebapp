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
<title>Welcome Page</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	${greeting}
	<br />

	<br />
	<div>
		<table id="typicalWebAppBeanIdInputTable" frame="box">
			<tbody>
				<tr>
					<td><input type="text" id="typicalWebAppBeanIdInput"
						oninput="verifyIfNumber($(this));" /></td>
					<td><input type="button" id="getTypicalWebAppBeanButton"
						value="Get Bean"
						onclick="getTypicalWebAppBean($('#typicalWebAppBeanIdInput').val());"></td>
				</tr>
			</tbody>
		</table>
	</div>
	<br />
	<a id="fileActionsPageLink" href="fileActionsPage">File Upload Page</a>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>