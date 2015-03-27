<!-- CIN-304393575 NAME: JAIMIN PATEL -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Report Card</title>
</head>
<body>
<p>
Score Report <br/> <br/>
You answered ${true_ans } of 5 questions correctly. <br/><br/>
Your ranking: ${score} <br/><br/>
<c:remove var="question1"/>
<c:remove var="que_id"/>
<c:remove var="que_count"/>
<c:remove var="true_ans"/>
Progress: ${progress}
<br/><br/>
<a href="/cs320stu163/Quiz">Play Again</a>
</p>
</body>
</html>