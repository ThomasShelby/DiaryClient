<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>The Diary - Register</title>
<link href="resources/css/login.css" rel="stylesheet">
<script src="resources/js/jquery-1.9.1.min.js"></script>
<script src="resources/js/jquery.validate.js"></script>
<script src="resources/js/register.js"></script>
<script src="resources/js/passwordStrength.js"></script>
</head>
<body onload='document.registerForm.username.focus();'>
    <div id="login">
        <h1><a href="/DiaryClient/">The Diary Registration</a></h1>
        <form:form method="post" commandName="signupForm" id="signupForm">
            <form:input path="username" id="username" type='text' name='username' placeholder='username' />
            <form:errors path="username" class="error"/>
            <form:input path="email" id="email" type='text' name='email' placeholder='email' />
            <form:errors path="email" class="error"/>
            <form:input path="password" id="password" type='password' name='password' placeholder="password" />
            <form:errors path="password" class="error"/>
            <form:input path="confirmPassword" id="confirmPassword" type='password' name='confirmPassword' placeholder="confirm password" />
            <form:errors path="confirmPassword" class="error"/>
            <div class="" id="passwordStrength"></div>
            <input type="submit" value="Sign Up" id="submitBtn"/>
            <div class="register">Already registered? <a href="/DiaryClient/login">Login here</a></div>
        </form:form>
    </div>
</body>
</html>