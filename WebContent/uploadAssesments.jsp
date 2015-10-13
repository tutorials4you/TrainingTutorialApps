<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta charset="ISO-8859-1">
<title>Upload Assesment</title>
<link href="cssfile/assesments.css" rel="stylesheet" type="text/css"/>
<head>
<script type="text/javascript" src="javascript/utill.js"></script>
</head>
<body>
	<div class="inputform" style="float: left;">
		<h2 class="design">Upload Assesment</h2>
		<form action="InsertCourseDetails" method="post" enctype="multipart/form-data">
		<table>
		<tr id =coursename>
		<td >Assesments Name:</td>
		<td id="coursenametestBox"><input type="text" id="email" name="cname"/>
		</td>
		</tr>
		<tr id = courseauthor>
		<td>Time Limit</td>
		<td id ="courseauthorTextBox"><input id="password" type="text" name="cauthor"
		/></td>
		</tr>
		<tr>
		<select name="empno">
        <option>
       <c:forEach items="${result.}" var="actualbean">  
       <option value="${actualbean.value}"><c:out value="${actualbean.value}"/></option>  
       </c:forEach>                 
            </option>

        </select>
		
		</tr>
		<tr id= courseFIle>
		<td>File:</td>
		<td id =courseFIlebox><input id="password" type="file" name="file" /></td>
		</tr>
		<tr id=button>
		<td><input id="register" type="reset" value="Reset" />
		</td>
		<td><input id="login" type="submit" value="Submit" />
		</td>
		</tr>
		</table>
		</form>
	</div>
</body>
</html>
