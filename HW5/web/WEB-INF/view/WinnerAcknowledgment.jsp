<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: alabdullahwi
  Date: 4/15/2015
  Time: 9:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Congratulations! You Won!</title>
</head>
<body>
<h1>Congratulations!!!</h1>
<p>You have won the raffle! Your prize is: two movie tickets</p>
<jsp:useBean id="dataBean" type="survey.domain.DataBean" scope="session"/>
<p>
    Mean: <%= dataBean.getMean() %><br/>
    Standard Deviation: <%= dataBean.getStdDev() %>
</p>
<h3>IDs of Students who have completed the survey so far: </h3>
<ul>
    <%
        List<String> ids = (List<String>) session.getAttribute("studentIDs");
        for (String id: ids) { %>
            <li> <a href="survey?reqStudentId=<%= id %>"><%= id %></a> </li>
                    <% } %>

</ul>
<a href="/index.jsp">BACK</a>
</body>
</html>
