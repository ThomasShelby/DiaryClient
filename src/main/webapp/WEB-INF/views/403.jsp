<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HTTP Status 403 - Access is denied</title>
<style>
@import url(http://fonts.googleapis.com/css?family=Open+Sans:300,400,700);

*{margin:0;padding:0;}

body{
  background:#567;
  font-family:'Open Sans',sans-serif;
}

.button{
  width:100px;
  background:#3399cc;
  display:block;
  margin:0 auto;
  margin-top:1%;
  padding:10px;
  text-align:center;
  text-decoration:none;
  color:#fff;
  cursor:pointer;
  transition:background .3s;
  -webkit-transition:background .3s;
}

.button:hover{
  background:#2288bb;
}

#login{
  width:400px;
  margin:0 auto;
  margin-top:250px;
  margin-bottom:2%;
  transition:opacity 1s;
  -webkit-transition:opacity 1s;
}

#login h1{
  background:#3399cc;
  padding:20px 0;
  font-size:140%;
  font-weight:300;
  text-align:center;
  color:#fff;
}

.form{
  background:#f0f0f0;
  padding:6% 4%;
  font-size:140%;
  font-weight:300;
  text-align:center;
  color:#000000;
}

input[type="text"],input[type="password"]{
  width:92%;
  background:#fff;
  margin-bottom:4%;
  border:1px solid #ccc;
  padding:4%;
  font-family:'Open Sans',sans-serif;
  font-size:95%;
  color:#555;
}

input[type="submit"]{
  width:100%;
  background:#3399cc;
  border:0;
  padding:4%;
  font-family:'Open Sans',sans-serif;
  font-size:100%;
  color:#fff;
  cursor:pointer;
  transition:background .3s;
  -webkit-transition:background .3s;
}

input[type="submit"]:hover{
  background:#2288bb;
}

.error {
    padding: 15px;
    margin-bottom: 20px;
    border: 1px solid transparent;
    border-radius: 4px;
    color: #a94442;
    background-color: #f2dede;
    border-color: #ebccd1;
}

.msg {
    padding: 15px;
    margin-bottom: 20px;
    border: 1px solid transparent;
    border-radius: 4px;
    color: #31708f;
    background-color: #d9edf7;
    border-color: #bce8f1;
}
</style>
</head>
<body>
    <div id="login">
        <h1>Access denied!</h1>
        <c:choose>
        <c:when test="${empty username}">
            <h2 class="form">You do not have permission to access this page! Go away!</h2>
        </c:when>
        <c:otherwise>
            <h2 class="form">Hey, ${username} <br/>You do not have permission to access this page! Go away!</h2>
        </c:otherwise>
    </c:choose>
    </div>
</body>
</html>