<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<title>Error - ${exception.message}</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link
	href='http://fonts.googleapis.com/css?family=Love+Ya+Like+A+Sister'
	rel='stylesheet' type='text/css'>
<link href='../resources/css/error.css' rel='stylesheet' type='text/css'>
<script src="../resources/js/jquery-1.9.1.min.js"></script>
<script>
	$(document).ready(function() {
		$("#stack").hide();
		$("#hideshow").click(function() {
			$("#stack").toggle();
		});
	});
</script>
</head>
<body>
	<div class="wrap">
		<div class="logo">
			<p>OOPS... ${msg}</p>
			<div class="errortext" style="font-size: 100pt;">${exception.message}</div>
			<div class="sub">
				<p>
					<a id="hideshow" href="#">Show Stacktrace </a>&nbsp;&nbsp;<a
						href="javascript:window.history.back()">Back</a>
				</p>
			</div>
			<div id="stack" class="stack" style="align: left;">
				<strong>Exception Stack Trace</strong><br>
				<c:forEach items="${exception.stackTrace}" var="ste">
                    ${ste}
                </c:forEach>
			</div>
		</div>
	</div>
</body>
</html>