<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<tiles:insertDefinition name="filter">
	<tiles:putAttribute name="body">
		<div class="body">
		<script type="text/javascript" charset="utf8" src="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/jquery.dataTables.min.js"></script>
<script src="resources/js/forPublicRecords.js"></script>
<link rel="stylesheet" type="text/css" href="resources/css/dataTable-style.css">
			<img src="/images/tmpFiles/${user.nickName}/${user.avatar}"
				alt="user avatar" style="width: 304px; height: 228px;" />
			<h2>profile</h2>
			<ul>
				<li><b>Nick Name: </b>${user.nickName}</li>
				<li><b>Full name: </b>${user.firstName} ${user.secondName}</li>
				<li><b>Sex: </b>${user.sex}</li>
				<li><b>Address: </b>${user.address}</li>
				<li><b>E-mail: </b>${user.eMail}</li>
				<li><b>Date of birth: </b>${user.dateOfBirth}</li>
			</ul>
			<c:if test="${pageContext.request.userPrincipal.name == user.nickName}">
				<button onclick="location.href='edit'">Edit</button>
			</c:if>
			<c:if test="${pageContext.request.userPrincipal.name != user.nickName}">
				<c:choose>
					<c:when test="${followed != 'true'}">
						<button onclick="location.href='follow?nickName=${user.nickName}'">Follow</button>
					</c:when>
					<c:otherwise>
						<button
							onclick="location.href='unfollow?nickName=${user.nickName}'">Unfollow</button>
					</c:otherwise>
				</c:choose>
			</c:if>
			<button onclick="location.href='users'">Back</button>
			<table id="pubRecords">
				<thead>
					<tr>
						<th width=20%>Title</th>
						<th width=20%>Created Time</th>
						<th width=60%>Text</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${recordList}" var="record">
						<tr>
							<td><a href='recordsDescription?id_rec=${record.uuid}'>${record.title}</a></td>
							<td>${record.createdTime}</td>
							<td name="textMessage">${record.text}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<script>window.onload =  substringFunction();</script>
	</tiles:putAttribute>
</tiles:insertDefinition>