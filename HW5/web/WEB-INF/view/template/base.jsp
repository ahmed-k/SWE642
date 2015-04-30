<%--
  Created by IntelliJ IDEA.
  User: alabdullahwi
  Date: 4/30/2015
  Time: 7:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" type="text/css" href="../../../resources/CSS/main.css">
  <link rel="stylesheet" type="text/css" href="../../../resources/CSS/jquery-ui.css" />
  <title><tiles:getAsString name="title"/></title>
</head>
<body>
<tiles:insertAttribute name="header" />
<tiles:insertAttribute name="body" />
<tiles:insertAttribute name="footer" />
</body>
</html>
