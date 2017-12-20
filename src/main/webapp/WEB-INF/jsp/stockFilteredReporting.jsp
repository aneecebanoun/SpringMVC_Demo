<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>Stock Filtered Reporting</title>
</head>

<body style="background-color: black; color: green">
	<center>
	
		<pre style="white-space: pre-wrap;"><h2>${stockFilteredReporting}</h2></pre>
	</center>
</body>

</html>

<script >
$( document ).ready(function() {
	$("label").click(function(event) {
		window.location = "/stockReporting";
		});
	
});
</script>