<%@page session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">

		<div class="body">
<script src="resources/js/jquery.1.10.2.min.js"></script>
<script src="resources/js/jquery.autocomplete.min.js" /></script>
<script src="resources/js/autocompleteHashTag.js" /></script>
<link rel="stylesheet" type="text/css" href="resources/css/autocomplete-style.css">
			<h1>Dear ${pageContext.request.userPrincipal.name} please add your
				record</h1>
			<form action="addRecord" name="record" method="post">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" /> 
				<input type="hidden" name="nick"
					value="${pageContext.request.userPrincipal.name}" />
				<div>
					Title: <input id="title" class="autocomplete" size=110 type="text" name="title"
						value="${record.title}" />
				</div>
				<div>
					Choose visibility for record: <input id="status" type="radio"
						name="status" value="PRIVATE" checked>Private</input> <input
						id="status" type="radio" name="status" value="PUBLIC">Public</input>

				</div>
				<div>
					Message:
					<textarea id="text" type="text" class="autocomplete" name="text" cols="100" rows="30"
						wrap="hard" value="${record.text}">
        			</textarea>
				</div>
				<center>
					<button>Submit</button>
				</center>
			</form>

		</div>
	</tiles:putAttribute>

</tiles:insertDefinition>