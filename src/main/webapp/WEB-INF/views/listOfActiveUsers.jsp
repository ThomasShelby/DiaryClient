<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
 
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
    
    <div class="body">
    
  	<script type="text/javascript" charset="utf8" src="resources/js/jquery.dataTables.min.js"></script>
 	<link rel="stylesheet" type="text/css" href="resources/css/dataTableForListOfActiveUsers.css">
 	  
  	<script src="resources/js/chosen.jquery.js" type="text/javascript"></script>
  	<link rel="stylesheet" href="resources/css/chosen.css" charset="utf-8" />
  	
 	<script type="text/javascript" src="resources/js/reloadActiveUsers.js" /></script>
  
  
	<form name="form">
		<select name="listProperty" data-placeholder="Choose visible columns for a table..." 
			style="width:520px;" multiple class="userProperty">
			<option value='Avatar' selected>Avatar</option>
			<option value='NickName' selected>NickName</option>
			<option value='FirstName'>FirstName</option>
			<option value='LastName'>LastName</option>
			<option value='Email'>Email</option>
			<option value='SessionID'>SessionID</option>
		</select>
	</form>
	
  <table id="listActiveUsers">
    
    <thead>
      <tr>
      <th>Avatar</th>
      <th>Nick Name</th>
      <th>First Name</th>
      <th>Last Name</th>
      <th>Email</th>
      <th>Session ID</th>
      </tr>
    </thead>
    <tbody>
    <c:forEach items="${activeUsers}" var="aU">
      <tr>
      <td><div><img src="/images/tmpFiles/${aU.username}/${aU.avatar}" alt="user avatar" style="width:40px;height:40px;"/></div></td>
      <td>${aU.username}</td>
      <td>${aU.firstName}</td>
      <td>${aU.secondName}</td>
      <td>${aU.eMail}</td>
      <td>${aU.session}</td> 
      </tr>
	</c:forEach>
    </tbody>
  </table>
   
</div>
  
  </tiles:putAttribute>
</tiles:insertDefinition>