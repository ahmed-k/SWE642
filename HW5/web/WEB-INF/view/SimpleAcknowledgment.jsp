<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Thank you</title>
</head>
<body>
<h1>Thank You</h1>
<p>Thanks for completing this survey.</p>
<p>
  Mean: ${dataBean.mean} <br/>
  Standard Deviation: ${dataBean.stdDev}
</p>
<tiles:useAttribute name="studentIDs" />
</body>
</html>
