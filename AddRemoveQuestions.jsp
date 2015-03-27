<!-- CIN-304393575 NAME: JAIMIN PATEL -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ADMIN PAGE</title>
<style>

</style>
</head>
<body>

	<h1>Add/Remove questions</h1>

<a href="AddRemoveQuestions">To Remove Data</a>
<c:forEach items="${entries}" var="entry">
		<br/>Question:${entry.question}
		<br/>Answer: ${entry.answer}<br/>
		<a href="AddRemoveQuestions?delete=${entry.id}">Delete</a><br/>	
		
	</c:forEach>

	<h1>Add New Question</h1>

	<form action="AddRemoveQuestions" method="post">
		Question: <input type="text" name="que" /> <br />
		Answer:<input type="radio" name="true" value="TRUE">True
				<input type="radio" name="false" value="FALSE">False
		<br /> <input type="submit" name="add" value="Add" />
	</form>


</body>
</html>