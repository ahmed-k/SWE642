<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: alabdullahwi
  Date: 4/16/2015
  Time: 3:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error Page</title>
</head>
<body>

<h1>ERROR</h1>
<p><s:actionerror/></p>
<a href="${backLink}">BACK</a>
</body>
</html>
