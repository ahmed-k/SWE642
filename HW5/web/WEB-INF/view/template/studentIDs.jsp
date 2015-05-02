<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<h3>IDs of Students who have completed the survey so far: </h3>
<ul>
  <c:forEach var ="_student" items="${studentIDs}" >
    <a href="
    <s:url action="student">
      <s:param name="studentID">${_student}</s:param>
    </s:url>">${_student}</a><br/>
  </c:forEach>
</ul>
<a href="/index.jsp">BACK</a>
