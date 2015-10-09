<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="body">
			<c:forEach items="${usersList}" var="users">
				<a href='userProfile?nickName=${users.nickName}'>${users.nickName}</a>
				</br>
			</c:forEach>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>