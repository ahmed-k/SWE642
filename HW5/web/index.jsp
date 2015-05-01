<%@ taglib prefix="s" uri="/struts-tags" %>
<%-- Created by AHMED ALABDULLAH. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Ahmed Alabdullah's GMU CS Department Homepage </title>
    <link rel="stylesheet" type="text/css" href="../../web/stylesheets/style.css">

</head>

<body>
<div name="headerWrapperTable">
    <h1>Welcome To CS Department Homepage </h1>
    <div id="greeting"> </div>
    <p>This is the best department in the world.</p>
    <a href="http://www.cs.gmu.edu">Department Website</a>
    <h2>Offerings:</h2>
    <ol>
        <li>MSc Degree in Computer Science </li>
        <li>MSc Degree in Software Engineering </li>
    </ol>

</div>
<div id ="formWrapper" name="formWrapperTable" >
    <s:form action="survey" method="POST" autocomplete="on">
        <legend>Feedback Survey</legend>
        <label>First Name: <input  autofocus required name="studentBean.firstName" /><br/></label>
        <label>Last Name: <input required name="studentBean.lastName" /><br/></label>
        <label><h4>Address Information:</h4><br/></label>
        <label>Street:<input required name="studentBean.street"/><br/></label>
        <label>City:<input  readonly="readonly" name="studentBean.city"/><br/></label>
        <label>State:<input readonly="readonly" name="studentBean.state"/><br/></label>
        <label>ZIP:<input required name="studentBean.zip"/>
            <div id="zip_error"></div>
            <br/></label>
        <label>Telephone:<input name="studentBean.telephone"><br/></label>
        <label>Email:<input placeholder="e.g. john.smith@domain.com" name="studentBean.email"><br/></label>
        <label>Personal Website URL: <input placeholder="http://www.yourwebsiteurl.edu" name="studentBean.url"><br/></label>
        <label>What did you like about the campus?</label><br/>
        <input type="checkbox" name="studentBean.campus" value="students"><label>Students</label>
        <input type="checkbox" name="studentBean.campus" value="location"><label>Location</label><br/>
        <input type="checkbox" name="studentBean.campus" value="campus"><label>Campus</label>
        <input type="checkbox" name="studentBean.campus" value="atmosphere"><label>Atmosphere</label><br/>
        <input type="checkbox" name="studentBean.campus" value="dorm rooms"><label>Dorm Room</label>
        <input type="checkbox" name="studentBean.campus" value="sports"><label>Sports</label><br/>
        <label>How did you hear about the university?</label><br/>
        <input type="radio" name="studentBean.referralSource" value="friends"><label>Friends</label>
        <input type="radio" name="studentBean.referralSource" value="internet"><label>Internet</label><br/>
        <input type="radio" name="studentBean.referralSource" value="television"><label>Television</label>
        <input type="radio" name="studentBean.referralSource" value="other"><label>Other</label><br/>
        <label>High School Graduation Date</label>	<label>Month:</label>
        <input placeholder="click arrow to select Month" name="studentBean.graduationMonth" list="months" />
        Year:<input name="studentBean.graduationYear" /><br/>
        <label>How likely are you to recommend George Mason to other students?</label>
        <select name="studentBean.recommendationLikelihood">
            <option value="very likely">Very Likely</option>
            <option value="likely">Likely</option>
            <option value="unlikely">Unlikely</option>
        </select><br/>
        <label>Additional Comments:<br/></label>
        <textarea placeholder="enter additional comments here..." rows="10" cols="50" name="studentBean.additionalComments" ></textarea>
        <br/>

        <label>Data:<br/></label>
        <textarea placeholder="enter 10 comma separate numbers" rows ="10" cols="50" name="raffle"></textarea>
        <br/>
        <label>Student ID<input required  name="studentBean.studentID" /><br/></label>

        <input type="submit" />
    </s:form>
</div>

<datalist id="months">
    <option value="January">
    <option value="February">
    <option value="March">
    <option value="April">
    <option value="May">
    <option value="June">
    <option value="July">
    <option value="August">
    <option value="September">
    <option value="October">
    <option value="November">
    <option value="December">
</datalist>
<script type="text/javascript" src="/scripts/main.js"> </script >
</body>


</html>
