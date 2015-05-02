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
    <ul>Student Survey Feedback
    <li><strong>Student ID: </strong>${student.studentID}</li>
    <li><strong>First Name: </strong>${student.firstName}</li>
    <li><strong>Last Name: </strong>${student.lastName}</li>
    <li><strong>Graduation Date: </strong>${student.graduationDate}</li>
    </ul>
  <ul>Address Information:
    <li><strong>Street: </strong>${student.street}</li>
    <li><strong>City: </strong>${student.city}</li>
    <li><strong>ZIP: </strong>${student.zip}</li>
  </ul>
  <ul>Contact Information:
    <li><strong>Telephone: </strong>${student.telephone}</li>
    <li><strong>Email: </strong>${student.email}</li>
    <li><strong>URL: </strong>${student.url}</li>
  </ul>
  <ul>
    <li><strong>Liked about campus:</strong>${student.printAttribute(student.campus)}</li>
    <li><strong>Heard about university through:</strong>${student.referralSource}</li>
    <li><strong>Recommendation Likelihood:</strong>${student.recommendationLikelihood}</li>
    <li><strong>Additional Comments:</strong>${student.additionalComments}</li>
  </ul>
  <br/>
  <br/>
  <br/>
  <a href="${backLink}">BACK</a>

</head>
<body>

</body>
</html>
