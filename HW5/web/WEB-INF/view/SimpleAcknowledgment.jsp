<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%><%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: alabdullahwi
  Date: 4/15/2015
  Time: 9:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Thank you</title>
</head>
<body>
<jsp:useBean id="dataBean" type="survey.domain.DataBean" scope="session"/>
<h1>Thank You</h1>
<p>Thanks for completing this survey.</p>
<p>
  Mean: ${dataBean.mean} <br/>
  Standard Deviation: ${dataBean.stdDev}
</p>
<h3>IDs of Students who have completed the survey so far: </h3>
<ul>
  <c:forEach var ="student" >
    
  </c:forEach>
  <%
    List<String> ids = (List<String>) session.getAttribute("studentIDs");
    for (String id: ids) { %>
  <li> <a href="survey?reqStudentId=<%= id %>"><%= id %></a> </li>
  <% } %>

</ul>
<a href="/index.jsp">BACK</a>
</body>
</html>
