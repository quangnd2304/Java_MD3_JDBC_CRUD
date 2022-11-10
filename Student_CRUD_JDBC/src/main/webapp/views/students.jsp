<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: This MC
  Date: 10/11/2022
  Time: 3:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Students</title>
</head>
<body>
<table border="1">
    <thead>
    <tr>
        <th>Student ID</th>
        <th>Student Name</th>
        <th>Age</th>
        <th>Birth Date</th>
        <th>Status</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
        <c:forEach items="${listStudent}" var="st">
            <tr>
                <td>${st.studentId}</td>
                <td>${st.studentName}</td>
                <td>${st.age}</td>
                <td><fmt:formatDate value="${st.birthDate}" pattern="dd-MM-yyyy"/></td>
                <td>${st.studentStatus?"Active":"Inactive"}</td>
                <td>
                    <a href="<%=request.getContextPath()%>/StudentServlet?studentId=${st.studentId}&&action=Update">Update</a>
                    <a href="<%=request.getContextPath()%>/StudentServlet?studentId=${st.studentId}&&action=Delete">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<a href="views/newStudent.jsp">Create New Student</a>
</body>
</html>
