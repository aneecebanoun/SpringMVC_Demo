<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Confirmation</title>
</head>
<body>
	<c:choose>
		<c:when
			test="${not empty basket.sportsChecks || not empty basket.newsChecks}">
  	        The following products been selected<br />
			<c:if test="${not empty basket.newsChecks}">
				<div style="${divStyle}">
					${newsLabel}<br />
					<c:forEach items="${basket.newsChecks}" var="news">
                  -${news} <br />
					</c:forEach>
				</div>
			</c:if>
			<c:if test="${not empty basket.sportsChecks}">
				<div style="${divStyle}">
					${specialNewsLabel}<br />
					<c:forEach items="${basket.sportsChecks}" var="sport">
                  -${sport}<br />
					</c:forEach>
				</div>
			</c:if>
		</c:when>
		<c:otherwise>
            Nothing Been Selected
        </c:otherwise>
	</c:choose>
</body>
</html>