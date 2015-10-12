<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<header>
	<div class="alignleft">
		<div class="logo">
			<a href='/DiaryClient/'><img
				src="resources/images/logo.png" height="100" alt="image" /></a>
		</div>
		<div class="name">The Diary</div>
	</div>
	<div class="alignright">
		<!-- <div class="msg">${msg}</div> -->
		<div class="username">
			<sec:authorize access="hasRole('ROLE_USER')">
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
				<c:if test="${pageContext.request.userPrincipal.name != null}">
					<h2>
						Hello, <a
							href='/DiaryClient/userProfile?nickName=${pageContext.request.userPrincipal.name}'>${pageContext.request.userPrincipal.name}</a>
					</h2>
		</div>
		<div class="btn">
			<button onclick="javascript:formSubmit()">Logout</button>
		</div>
		</c:if>
		<c:if test="${pageContext.request.userPrincipal.name == null}">
			<div class="btn2">
				<button onclick="location.href='/DiaryClient/login'">Log in</button>
			</div>
			<div class="btn2">
			<button onclick="location.href='/DiaryClient/signup'">Sign up</button>
			</div>
		</c:if>
		</sec:authorize>
</header>
<link rel="stylesheet" type="text/css" href="resources/css/header.css">
