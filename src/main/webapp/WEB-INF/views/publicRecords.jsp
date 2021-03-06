<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<tiles:insertDefinition name="filter">
	<tiles:putAttribute name="body">
		<div class="body">
<script type="text/javascript" charset="utf8" src="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/jquery.dataTables.min.js"></script>
<script src="resources/js/forPublicRecords.js"></script>
<!--  <link rel="stylesheet" type="text/css" href="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/css/jquery.dataTables.css">-->
<link rel="stylesheet" type="text/css" href="resources/css/dataTable-style.css">
<table id="pubRecords" >
    <thead>
      <tr><th width=20%>Title</th><th width=20%>Created Time</th><th width=60%>Text</th></tr>
    </thead>
    <tbody>
    <c:forEach items="${recordsList}" var="records">
      <tr><td><a href='recordsDescription?id_rec=${records.uuid}'>${records.title}</a></td>
      <td name="createdTime">${records.createdTime}</td>
      <td name="textMessage" > ${records.text}</td></tr>
	</c:forEach>
    </tbody>
  </table>
  </div>
  <script>
  window.onload =  substringFunction();
  </script>
</tiles:putAttribute>
</tiles:insertDefinition>
