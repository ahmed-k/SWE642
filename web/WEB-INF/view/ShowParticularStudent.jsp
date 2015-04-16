<%--
  Created by IntelliJ IDEA.
  User: alabdullahwi
  Date: 4/15/2015
  Time: 10:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student Survey Input: </title>
  <jsp:useBean  id="currentStudent" type="survey.domain.StudentBean" scope="request"></jsp:useBean>

  <%
      out.print(currentStudent.getFirstName());
      out.print(currentStudent.getLastName());
      out.print(currentStudent.getStudentID());

  %>
</head>
<body>

</body>
</html>
