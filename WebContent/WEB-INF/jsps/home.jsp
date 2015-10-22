<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@page language="java" import="com.course.dao.*"%>
  <%@page language="java" import="com.course.model.*"%>
 <%@page import="java.util.ArrayList" %>   
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
   <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <meta name="viewport" content="width=device-width, initial-scale=1">
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <style type="text/css">
body {
	background: url("${pageContext.request.contextPath}/images/background.jpg");
}
h4{
	color: red;
    margin-left: 32px;
    margin-top: 0;
    position: relative;
}
.button {
	padding: 10px 15px;
	font-size: 14px;
	line-height: 100%;
	text-shadow: 0 1px rgba(0, 0, 0, 0.4);
	color: #fff;
	
	vertical-align: middle;
	text-align: center;
	cursor: pointer;
	font-weight: bold;
	transition: background 0.1s ease-in-out;
	-webkit-transition: background 0.1s ease-in-out;
	-moz-transition: background 0.1s ease-in-out;
	-ms-transition: background 0.1s ease-in-out;
	-o-transition: background 0.1s ease-in-out;
	text-shadow: 0 1px rgba(0, 0, 0, 0.3);
	color: #fff;
	-webkit-border-radius: 40px;
	-moz-border-radius: 40px;
	border-radius: 40px;
	font-family: 'Helvetica Neue', Helvetica, sans-serif;
}

.button, .button:hover, .button:active {
	outline: 0 none;
	text-decoration: none;
        color: #fff;
}

.username {
	background-color: #2ecc71;
	box-shadow: 0px 3px 0px 0px #239a55;
}

</style>
  
   <title>AT & TUTORIALS</title>
</head>
<body>

<div id='cssmenu'>
<ul>
   <li class=''><a href='${pageContext.request.contextPath}'><span>Home</span></a></li>
   <li><a href='${pageContext.request.contextPath}/login'><span>Login</span></a></li>
   <li><a href='${pageContext.request.contextPath}/register'><span>Register</span></a></li>
   <li class='#'><a href='#'><span>Submit a Question</span></a></li>
   <li class='#'><a href='#'><span>Feedback</span></a></li>
   <li><a href='#'><span>Contribute</span></a></li>
   <li><a href='#'><span>Contact us</span></a></li>
</ul>
</div>

<c:if test='${not empty sessionScope.name}'>

<div style="position:absolute;top:70px;left:1089px">
Logged as <a href="#" class="button username">${sessionScope.name}</a>
</div>

<div style="position:absolute;top:70px;left:1300px">
<%-- <a href='${pageContext.request.contextPath}/logout'>Logout</a>//
 --%><a href='LogoutServlet'>Logout</a>
</div>

</c:if>

<div style="position:absolute;left:120px;top:60px">
<table cellpadding="0" cellspacing="50">
 
<%
String  countNumber = session.getAttribute("countOfCOurse").toString();
int i = Integer.parseInt(countNumber);
System.out.println("********"+i);
int j = i/5+1;
int count = 0;
%>
<%out.print("<tr>"); %>
<c:forEach items="${courses}" var="course">
 <%count++; %>
    <c:choose>
     <c:when test="${course.fileExtension=='pptx'}">
     <td><a href="CourseController?action=launch&cid=<c:out value="${course.cid}"/>"><img height="120" width="120" src="${pageContext.request.contextPath}/images/Assesment_PPt.png"/></a><h4><c:out value="${course.cname}" /></h4></td>
     </c:when>
	<c:otherwise>
	 <td><a href="CourseController?action=launchVideo&fileName=<c:out value="${course.cfileName} &cid=${course.cid}"/>"><img height="120" width="120" src="${pageContext.request.contextPath}/images/Asses_Video.png" /></a><h4><c:out value="${course.cname}" /></h4></td>
	</c:otherwise>
	</c:choose>
<%if(count == 5){
	System.out.println("Count In If Block"+count);
	count=0;
	%>
	<%out.print("</tr>"); %>
	<%}%>
<%System.out.println(" Count  For Loop"+count); %>
 </c:forEach>
</table>
</div>
</body>
</html>
