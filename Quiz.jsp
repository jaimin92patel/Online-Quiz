<!-- CIN-304393575 NAME: JAIMIN PATEL -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Online Quiz</title>
</head>
<body>

<div style="top:200px height=20px">
	<table>
		<tr><td><h4><b>Online &nbsp;Quiz</b></h4></td></tr>
	</table>
</div>
<div>
<form action="/cs320stu163/Quiz" method="post">
<table border="0">
<tr>
	<td><b>${question1.question}</b></td>
</tr>

<tr>
<td>
<input type="radio" name="answer" value="TRUE" />True
<input type="radio" name="answer" value="FALSE" />False
</td>
</tr>
<tr>
<c:if test="${que_count != 5 }">
	<td> 
		<input type="submit" value="Click to Next Question" /> 
	</td>
</c:if>
<c:if test="${que_count == 5 }">
	<td> 
		<input type="submit" value="Finish" /> 
	</td>
</c:if>
</tr>
<tr></tr>
<tr></tr>

<tr>
<td>
Progress: ${progress}
</td>
</tr>
</table>
</form>
</div>
</body>
</html>