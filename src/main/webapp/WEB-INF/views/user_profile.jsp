<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="body">
		<img src="/images/tmpFiles/${user.nickName}/${user.avatar}" alt="user avatar" style="width:304px;height:228px;"/>
			<h2>profile</h2>
			<ul>
				<li><b>Nick Name: </b>${user.nickName}</li>
				<li><b>Full name: </b>${user.firstName} ${user.secondName}</li>
				<li><b>Sex: </b>${user.sex}</li>
				<li><b>Address: </b>${user.address}</li>
				<li><b>E-mail: </b>${user.eMail}</li>
				<li><b>Date of birth: </b>${user.dateOfBirth}</li>
			</ul>
			<c:if test="${pageContext.request.userPrincipal.name==user.nickName}">
				<button onclick="location.href='edit?nickName=${user.nickName}'">Edit</button>
			</c:if>
			<button onclick="location.href='users'">Back</button>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>