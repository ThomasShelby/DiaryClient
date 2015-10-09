<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Diary</title>
<style type="text/css">

.Wmenu {
	font-size:25px;
	list-style:none;
	float:left;
	width:300px;
	margin-left:50px;
	margin-top:50px;
}
.Wmenu ul{
	list-style:none;
}

.Wmenu a{
	text-decoration:none;
}

.Wcontent{
	font-size:30px;
	float:left;
	margin-top:50px;
}

/* unvisited link */
a {
    color: #000;
}

/* visited link */
a:visited {
    color: #000;
}

/* mouse over link */
a:hover {
    color: #040875;
}

/* selected link */
a:active {
    color: #040875;
}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/tiles/header.jsp" />
	<div class="Wmenu">
	<ul>
		<li><a href="users">Users</a></li>
		<li><a href="systemStatistic">SystemStatistic</a></li>
		<li><a href="users-statistic">Users Statistic</a></li>
		<li><a href="home">Home</a></li>
		<li><a href="publicRecords">Public Records</a></li>
	</ul>
	</div>
	<div class="Wcontent">
	<p>Hi everyone, this is The Diary!</br>
	Here You can note something for yourself , express your opinion ,</br> and simply enjoy good company of people.</br>
	On the left menu You can check some information about us and our users,</br> go to you page , add record or even look on all public records You want.</br>
	</p>
	</div>
	<br>
</body>
</html>

