<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page session="true"%>
<header>
	<div class="alignleft">
		<div class="logo">
			<a href='/DiaryClient/'><img src="resources/images/logo.png"
				height="100" alt="image" /></a>
		</div>
		<div class="name">The Diary</div>
	</div>
	<div class="alignright">
		<!-- <div class="msg">${msg}</div> -->
		<div class="username">
			<sec:authorize access="isAuthenticated()">
				<c:url value="/j_spring_security_logout" var="logoutUrl" />
				<form action="${logoutUrl}" method="post" id="logoutForm">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</form>
				<script>
					function formSubmit() {
						document.getElementById("logoutForm").submit();
					}
				</script>

				<h2>
					Hello, <a
						href='/DiaryClient/userProfile?nickName=${pageContext.request.userPrincipal.name}'>${pageContext.request.userPrincipal.name}</a>
				</h2>
		</div>
		<div class="btn">
			<button onclick="javascript:formSubmit()">Logout</button>
		</div>
		</sec:authorize>
		<sec:authorize access="!isAuthenticated()">

			<div class="btn2">
				<button onclick="location.href='/DiaryClient/login'">Log in</button>
			</div>
			<div class="btn2">
			<button onclick="location.href='/DiaryClient/signup'">Sign up</button>
			</div>
		</sec:authorize>
</header>
<link rel="stylesheet" type="text/css" href="resources/css/header.css">
