<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="js.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Confirmation</title>
</head>
<body>
   <c:set var="divClass"
	 value="col-xs-12 col-sm-12 well well-lg" />
	<c:if test="${not empty basket.newsChecks && not empty basket.sportsChecks}">
		<c:set var="divClass" value="col-xs-6 col-sm-6 well well-lg" />
	</c:if>
	 

<div class="container">
  <div class="row">
	<c:choose>
		<c:when
			test="${not empty basket.sportsChecks || not empty basket.newsChecks}">
  	        The following products been selected<br />
			<c:if test="${not empty basket.newsChecks}">
				<div class="${divClass}">
					${newsLabel}<br />
					<c:forEach items="${basket.newsChecks}" var="news">
                  <br/><li>${news} </li>
					</c:forEach>
				</div>
			</c:if>
			<c:if test="${not empty basket.sportsChecks}">
				<div class="${divClass}">
					${specialNewsLabel}<br />
					<c:forEach items="${basket.sportsChecks}" var="sport">
                  <br/><li>${sport}</li>
					</c:forEach>
				</div>
			</c:if>
		</c:when>
		<c:otherwise>
            Nothing Been Selected
        </c:otherwise>
	</c:choose>
  </div>
</div>
	
	
</body>
</html>