<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<script src="resources/js/jquery.autocomplete.min.js" /></script>
<script src="resources/js/autocompleteHashTag.js" /></script>
<link rel="stylesheet" type="text/css" href="resources/css/autocomplete-style.css">
<link rel="stylesheet" type="text/css" href="resources/css/addRecord.css">

<div class="addRecord">
	<!-- set action for this form (update or create record) -->
	<c:if test="${record == null}">
	<c:set var="action" value="addRecord?${_csrf.parameterName}=${_csrf.token}"/>	
	</c:if>
	<c:if test="${record != null}">
	<c:set var="action" value="editRecord?${_csrf.parameterName}=${_csrf.token}"/>	
	</c:if>

    <form action="${action}" name="record" enctype="multipart/form-data" method="post">
    <!-- hidden values -->
    <input type="hidden" name="nick" value="${pageContext.request.userPrincipal.name}" />
    <input type="hidden" name="id_rec" value="${record.uuid}" />
    <input type="hidden" name="id_user" value="${user.uuid}" />
    
    <!-- to first line -->
        <div class="bigger">
            <div id="UserAvatar">
           <img src="/images/tmpFiles/${user.avatar}" width="100" height="100" class="Avatar"  alt="userAvatar"/>
           </div>
           <div id="nickName">
             ${user.nickName}
            </div>
            <div id="fullName">
              Dear ${user.firstName} ${user.secondName} please add your record
            </div>
            <a id="btnClose" class='close-dialog' onclick="CloseAddRecord()"></a>
        </div>

    <!-- second block -->
    <div class="bigger">
	    <div class="middle">
	        Choose visibility for record:
            <input class="status" type="radio" name="status" value="PRIVATE" checked>Private</input>
            <input class="status" type="radio" name="status" value="PUBLIC">Public</input>
        </div>
        <div class="middle">
            <div class="leftV">
        	    Title:
            </div>
            <div class="rightV">
                <input id="title" class="autocomplete" type="text" name="title"
	            value="${record.title}" oninput="lookingForHashTag('title')" />
            </div>
        </div>
    </div>

    <!-- third block -->
    <div id="thirdBlock">
        <div class="leftV">
	        Message:
        </div>
        <div class="rightV">
	        <textarea id="text" type="text" class="autocomplete" name="text"
						wrap="soft" oninput="lookingForHashTag('text')"> ${record.text}</textarea>
        </div>
    </div>
    
    
    <!-- four block  -->
     <div class="file_upload_block">
        <div class="leftV">
           Select a file:
        </div>
        <div class="rightV">
          <div class="file_upload">+<input id="file" type="file" name="file" onchange="setValue()" /></div>
   		<input id="url" type="text" name="url" value="${record.supplement}" readonly />  
        </div>
    </div>  

    <!-- Submit -->
	<center>
        <button id="butS">Submit</button>
	</center>
	</form>
</div>

