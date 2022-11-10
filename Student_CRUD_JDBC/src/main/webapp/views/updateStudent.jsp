<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: This MC
  Date: 10/11/2022
  Time: 4:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Student</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/StudentServlet" method="post">
    <table>
        <tr>
            <td>Student ID</td>
            <td><input type="text" name="studentId" value="${studentUpdate.studentId}" readonly/></td>
        </tr>
        <tr>
            <td>Student Name</td>
            <td><input type="text" name="studentName" value="${studentUpdate.studentName}"/></td>
        </tr>
        <tr>
            <td>Age</td>
            <td><input type="number" name="age" value="${studentUpdate.age}"/></td>
        </tr>
        <tr>
            <td>Birth Date</td>
            <td><input type="date" name="birthDate" value="${studentUpdate.birthDate}"/></td>
        </tr>
        <tr>
            <td>Status</td>
            <td>
                <c:choose>
                    <c:when test="${studentUpdate.studentStatus}">
                        <input type="radio" name="status" id="active" value="true" checked/><label for="active">Active</label>
                        <input type="radio" name="status" id="inactive" value="false"/><label for="inactive">Inactive</label>
                    </c:when>
                    <c:otherwise>
                        <input type="radio" name="status" id="active" value="true"/><label for="active">Active</label>
                        <input type="radio" name="status" id="inactive" value="false" checked/><label for="inactive">Inactive</label>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Update" name="action"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
