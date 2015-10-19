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
<jsp:include page="header.jsp" />
<jsp:include page="link.html" />
<jsp:include page="rightMenu.jsp" />
    <table border=1>
        <thead>
            <tr bgcolor ="#0099CC">
                <th >USER ID</th>
                <th>NAME</th>
                <th>Att Id</th>
                <th>PASSWORD</th>
                <th>User Role</th>
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
                    <td><c:out value="${user.userRole}" /></td>
                     <td><a href="UserController?action=deleteUser&userId=<c:out value="${user.userid}"/>">Delete</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <jsp:include page="footer.html" />
    </body>
</html>
