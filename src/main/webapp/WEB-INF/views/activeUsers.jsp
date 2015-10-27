<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
 
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
    <script src="resources/js/jquery-1.9.1.min.js"></script>

<style>
#activeUsers, #activeUsers th,#activeUsers td {
    border: 2px solid black;
}
</style>

<!-- <script src="resources/js/reloadActiveUsers.js" /></script> -->

<table id="activeUsers" class="hidden" style="
    font-family: arial;
    margin-left: 200px;">
    <caption>Who is online</caption>
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
	   
<script type="text/javascript">
    function reloadActiveUsers() {
        $.ajax({
        	type : 'GET',
            url : 'activeUsers1',
            dataType : 'json',
            
            success : function(data) {
            	
            	cap="<caption>Who is online</caption><thead><tr><th>Avatar</th><th>Nick Name</th><th>Session ID</th></tr></thead>";
            	txt="";
            	for (var  i=0; i<data.length;i++) {
            		   txt+=
   '<tr><td><div><img src="/images/tmpFiles/'+data[i].username+'/'+data[i].avatar+'" alt="user avatar" style="width:40px;height:40px;"/></div></td>'       			   
            			   
            		            +"<td>"+data[i].username+"</td>"
            		            +"<td>"+data[i].session+"</td></tr>";
            		   }
            	$("#activeUsers").empty();
            	$("#activeUsers").append(cap);
            	$("#activeUsers").append(txt); 	
                	
            }
        });
    }
</script>
 
<script type="text/javascript">
    var interval = 0;
    interval = setInterval(reloadActiveUsers, 10000);
</script>  
    
    
    </tiles:putAttribute>
</tiles:insertDefinition>