<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Show All Users</title>
</head>
<body>
<jsp:include page="header.html" />
<jsp:include page="link.html" />
<jsp:include page="rightMenu.jsp" />
    <table border=1>
        <thead>
            <tr>
                <th bgcolor ="yellow">USER ID</th>
                <th>NAME</th>
                <th>EMAIL</th>
                <th>PASSWORD</th>
                <th>GENDER</th>
                <th>ADDRESS LINE</th>
                <th>CITY</th>
                <th>STATE</th>
                <th>DOB</th>
<!--                 <th>USER ROLE</th>
 -->                <th>CONTACT NO</th><!-- 
                <th>REGISTER DATE</th>
                <th>USER ACCESS</th> -->
                <th colspan=2>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td><c:out value="${user.userid}" /></td>
                    <td><c:out value="${user.username}" /></td>
                    <td><c:out value="${user.userEmail}" /></td>
                    <td><c:out value="${user.password}" /></td>
                    <td><c:out value="${user.gender}" /></td>
                    <td><c:out value="${user.addressLine}" /></td>
                    <td><c:out value="${user.city}" /></td>
                    <td><c:out value="${user.state}" /></td>
                    <td><c:out value="${user.dob}" /></td>
<%--                     <td><c:out value="${user.userRole}" /></td>
 --%>                    <td><c:out value="${user.contactNumber}" /></td>
<%--                     <td><c:out value="${user.registerDate}" /></td>
                    <td><c:out value="${user.userAccess}" /></td>
 --%>                   <%--  <td><a href="UserController?action=editUser&userId=<c:out value="${user.userid}"/>">Update</a></td> --%>
                     <td><a href="UserController?action=deleteUser&userId=<c:out value="${user.userid}"/>">Delete</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <jsp:include page="footer.html" />
    </body>
</html>
