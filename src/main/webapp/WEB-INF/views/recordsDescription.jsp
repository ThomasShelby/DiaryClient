<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="body">
<link rel="stylesheet" type="text/css" href="resources/css/recordDescription.css">
		<c:if test="${result == true}">
			<h1> Record successfully added</h1>
		</c:if>
			<c:if test="${result == false}">
		<h1> Adding of record failed</h1>
		</c:if>
		<c:if test="${record != null}">
			<center>
			<h1><b> Record Description</b></h1>
			<hr>
			</center>
<div id="headRD">
<div class="inLineClass">
Author: <img src="/images/tmpFiles/${user.nickName}/${user.avatar}" width="50" height="40" alt="userAvatar"/>
<span id="upper"><a href='userProfile?nickName=${user.nickName}'>${user.nickName}</a></span>
</div>
<div class="inLineClass">
Record created: ${record.createdTime}
</div>
<div class="inLineClass">
  Record status: ${record.visibility}
</div>
<c:if test="${record.supplement != null}">
<div class="inLineClass">
Open file: <a href="/images/tmpFiles/${user.nickName}/${record.supplement}" target="_blank">  ${record.supplement} </a>
</div>
</c:if>
<hr>
<center>
<div class="title">
 <span id="upper"> ${record.title}</span>
 </div>
 </center>
 			<hr>
 <div class="textRecord"> ${record.text}   </div>
 <center>
<hr>
 <button onclick="location.href='publicRecords'" class="buttonRD">Back</button>

<!--<c:if test="${user.nickName} == ${pageContext.request.userPrincipal.name}"> 

 </c:if>-->

<c:if test="${(user.nickName == pageContext.request.userPrincipal.name)}">
  <button onclick="openEditRecord('${record.uuid}')" class="buttonRD">Edit</button>
 </c:if> 
 </center>

</div>
		</c:if>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>







