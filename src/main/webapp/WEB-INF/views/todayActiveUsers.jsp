<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
 
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
    <script src="resources/js/jquery-1.9.1.min.js"></script>

<!-- //<script> 
//var activeUsers=[]; -->
<style>
#activeUsers, #activeUsers th,#activeUsers td {
    border: 2px solid black;
}
</style>
<table id="activeUsers" style="
    font-family: arial;
    margin-left: 200px;">
    <caption>Who is active</caption>
    <thead>
      <tr>
      <th>Avatar</th>
      <th>Nick Name</th>
      <th>Session ID</th>
      </tr>
    </thead>
    <tbody>
    <c:forEach items="${activeUsers}" var="aU">
      <tr>
      <td><div><img src="/images/tmpFiles/${aU.username}/${aU.avatar}" alt="user avatar" style="width:40px;height:40px;"/></div></td>
      <td>${aU.username}</td> 
      <td>${aU.session}</td> 
      </tr>
	</c:forEach>
    </tbody>
  </table>
	   
<!-- //</script> -->   
    
    
    </tiles:putAttribute>
</tiles:insertDefinition>