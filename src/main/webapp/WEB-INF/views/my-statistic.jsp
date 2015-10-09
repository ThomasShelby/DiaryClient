<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="body">
			<h2>My Statistic</h2>
			<ul>
				<li><b>Nick Name: </b>${user.nickName}</li>
				<li><b>Full name: </b>${user.firstName} ${user.secondName}</li>
				<li><b>Number Of records: </b>${numberOfRecords}</li>
				<li><b>Number Of logins: </b>${userStatistic.numberOfLogins}</li>
				<li><b>Last records: </b>${userStatistic.lastRecords}</li>
			</ul>
			<button onclick="location.href='/DiaryClient'">Back</button>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>

</body>
</html>