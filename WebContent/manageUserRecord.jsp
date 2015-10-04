<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Show All Users</title>
<style type="text/css">
th {
	background-color:#0099CC;
}
</style>
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
                 <th>USER ROLE</th>
                <th>REGISTER DATE</th>
                <th>USER ACCESS</th> 
                <th colspan=2>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td><c:out value="${user.userid}" /></td>
                    <td><c:out value="${user.username}" /></td>
                    <td><c:out value="${user.userEmail}" /></td>
                    <td><c:out value="${user.userRole}" /></td>
                   <td><c:out value="${user.registerDate}" /></td>
                   <td><c:out value="${user.userAccess}" /></td>
				  <td><a href="UserController?action=manageUsersRecords&userId=<c:out value="${user.userid}"/>">Update</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <jsp:include page="footer.html" />
    </body>
</html>
