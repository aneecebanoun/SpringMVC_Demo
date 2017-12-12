<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Product Selection</title>
<jsp:include page="js.jsp"></jsp:include>
</head>
<body>
	<spring:url value="/confirmation" var="formUrl" />
	<c:set var="divStyle" scope="session"
		value="border:1px solid black;width:33%;height:150px; float:left;" />

	<c:set var="newsLabel" scope="session"
		value="News:" />

	<c:set var="specialNewsLabel" scope="session"
		value="Sport Specials:" />


	<form:form action="${formUrl}" method="POST" modelAttribute="basket"
		autocomplete="off">
		<div style="${divStyle}">
			News:<br />
			<form:checkboxes path="newsChecks" items="${newsChecks}" delimiter="<br/>"/>
		</div>

		<c:if test="${not empty sportsChecks}">
			
			<div style="${divStyle}">
				${specialNewsLabel}<br />
				<form:checkboxes path="sportsChecks" items="${sportsChecks}" delimiter="<br/>"/>
			</div>
		</c:if>
		
		<div style="${divStyle}">
			<label id="basketLabel">Basket:</label> <br />
			<button type="submit">Checkout</button>
		</div>
	</form:form>
</body>
</html>