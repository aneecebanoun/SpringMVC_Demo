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
	<c:set var="divClass" value="col-xs-6 col-sm-6 well well-lg" />
	<c:if test="${not empty sportsChecks}">
		<c:set var="divClass" value="col-xs-4 col-sm-4 well well-lg" />
	</c:if>
	<c:set var="newsLabel" scope="session"
		value="News:" />

	<c:set var="specialNewsLabel" scope="session"
		value="Sport Specials:" />
<div class="container">
    <div class="row">
	<form:form action="${formUrl}" method="POST" modelAttribute="basket"
		autocomplete="off">
		<div class="${divClass}">
			News:<br /><br />
			<form:checkboxes path="newsChecks" items="${newsChecks}" delimiter="<br/><br/>"/>
		</div>

		<c:if test="${not empty sportsChecks}">
			<div class="${divClass}">
				${specialNewsLabel}<br /><br />
				<form:checkboxes path="sportsChecks" items="${sportsChecks}" delimiter="<br/><br />"/>
			</div>
		</c:if>
		<div class="${divClass}">
			<div id="basketLabel">Basket:</div><br/>
			<button type="submit">Checkout</button>
		</div>
	</form:form>
   </div>
</div>
</body>
</html>