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

    <ul>Student Survey Feedback
    <li><strong>Student ID: </strong><%= currentStudent.printAttribute(currentStudent.getStudentID())%></li>
    <li><strong>First Name: </strong><%= currentStudent.printAttribute(currentStudent.getFirstName())%></li>
    <li><strong>Last Name: </strong><%= currentStudent.printAttribute(currentStudent.getLastName())%></li>
    <li><strong>Graduation Date: </strong><%= currentStudent.printGraduationDate()%></li>
    </ul>
  <ul>Address Information:
    <li><strong>Street: </strong><%= currentStudent.printAttribute(currentStudent.getStreet())%></li>
    <li><strong>City: </strong><%= currentStudent.printAttribute(currentStudent.getCity())%></li>
    <li><strong>ZIP: </strong><%= currentStudent.printAttribute(currentStudent.getZip())%></li>
  </ul>
  <ul>Contact Information:
    <li><strong>Telephone: </strong><%= currentStudent.printAttribute(currentStudent.getTelephone())%></li>
    <li><strong>Email: </strong><%= currentStudent.printAttribute(currentStudent.getEmail())%></li>
    <li><strong>URL: </strong><%= currentStudent.printAttribute(currentStudent.getUrl())%></li>
  </ul>
  <ul>
    <li><strong>Liked about campus:</strong><%= currentStudent.printAttribute(currentStudent.getCampus())%></li>
    <li><strong>Heard about university through:</strong><%= currentStudent.printAttribute(currentStudent.getReferralSource())%></li>
    <li><strong>Recommendation Likelihood:</strong><%= currentStudent.printAttribute(currentStudent.getRecommendationLikelihood())%></li>
    <li><strong>Additional Comments:</strong><%= currentStudent.printAttribute(currentStudent.getAdditionalComments())%></li>
  </ul>
  <br/>
  <br/>
  <br/>
  <a href="<%= request.getAttribute("backLink")%>">BACK</a>

</head>
<body>

</body>
</html>
