<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ page session="true"%>
<script src="resources/js/follower.js" /></script> 
<div class="menu">

	<c:if test="${pageContext.request.userPrincipal.name != null}">
	Menu
	<ul>
			<li><a href="home">Home </a></li>
				
			<li><a href="userProfile">My Profile</a></li>
					
			<li><a href="#" onclick="openAddRecord()">Add record</a></li>
			
			<li><a href="my-statistic">My Statistic</a></li>
					
			<li><a href="settings">Settings</a></li>
			
			<li><a href="publicRecords">Public records</a></li>
				
			<li><a id="followedUsers" href="followedUsers">Users wich I follow</a></li>
			
			<li><a href="subscribers">My subsribers</a></li>	
		
		</ul>
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			Adminstrator menu
			<ul>
				<li><a href="users">Users</a></li>
				<li><a href="systemStatisticPage">SystemStatistic</a></li>
				<li><a href="activeUsers">Active Users</a></li>
				<li><a href="users-statistic">Users Statistic</a></li>
				<li><a href="userStatcGraphic">Users activity graphics</a></li>
			</ul>
		</sec:authorize>
	</c:if>
	<c:if test="${pageContext.request.userPrincipal.name == null}">
		<p></p>
	</c:if>
</div>


