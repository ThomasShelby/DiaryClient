<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Users</title>
<style type="text/css">
    <%@include file="../css/style.css" %>
    </style>
</head>
<body>

<c:forEach items="${usersList}" var="users">
	
		<a href='userProfile?nickName=${users.nickName}'>${users.nickName}</a></br>
	
</c:forEach>

</body>
</html>