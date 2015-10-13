<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<tiles:insertDefinition name="filter">
	<tiles:putAttribute name="body">
		<div class="body">
<script type="text/javascript" charset="utf8" src="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/jquery.dataTables.min.js"></script>
<link rel="stylesheet" type="text/css" href="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/css/jquery.dataTables.css">
<table id="pubRecords" >
    <thead>
      <tr><th width=20%>Title</th><th width=20%>Created Time</th><th width=60%>Text</th></tr>
    </thead>
    <tbody>
    <c:forEach items="${recordsList}" var="records">
      <tr><td><a href='recordsDescription?id_rec=${records.uuid}'>${records.title}</a></td>
      <td>${records.createdTime}</td>
      <td>${records.text}</td></tr>
	</c:forEach>
    </tbody>
  </table>
  <!--  <script type="text/javascript" charset="utf8" src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.2.min.js"></script>-->
  <script>
  $(function(){
    $("#pubRecords").dataTable();
  })
  </script>
  </div>
</tiles:putAttribute>
</tiles:insertDefinition>


<!--  	<h1>Public Records:</h1>
			<c:forEach items="${recordsList}" var="records">
				<dl>
					<dt>
						<a href='recordsDiscription?id_rec=${records.uuid}'>${records.title}</a>
					</dt>
					<dd>
						<form name="result">
						  <input size=150 TYPE="text" NAME="message" value="${records.text}">	
							</input>
						</form>
					</dd>
				</dl>
			</c:forEach>
		</div>-->

