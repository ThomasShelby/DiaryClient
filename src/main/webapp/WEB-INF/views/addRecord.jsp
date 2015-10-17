<%@page session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script src="resources/js/jquery-1.9.1.min.js"></script>
<script src="resources/js/jquery.autocomplete.min.js" /></script>
<script src="resources/js/autocompleteHashTag.js" /></script>
<link rel="stylesheet" type="text/css" href="resources/css/autocomplete-style.css">
<link rel="stylesheet" type="text/css" href="resources/css/addRecord.css">
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="body">
<div class="addRecord">
    <form action="addRecord?${_csrf.parameterName}=${_csrf.token}" name="record" enctype="multipart/form-data" method="post">
    <!-- hidden values -->
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
    <input type="hidden" name="nick" value="${pageContext.request.userPrincipal.name}" />
    <input type="hidden" name="textarea" value="${record.text}" />
    
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
            <a class='close-dialog' href='javascript: closedialog()'></a>
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
	            value="${record.title}" />
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
						wrap="soft"> ${record.text}</textarea>
        </div>
    </div>

     <!-- four block -->
     <div class="file_upload">
        <div class="leftV">
            Select a file:
        </div>
        <div class="rightV">
            <input id="fileInput" type="file" name="file" value=" ${record.supplement}">
        </div>
    </div>
    <!-- Submit -->
	<center>
        <button id="butS">Submit</button>
	</center>
	</form>
</div>
</div>
</tiles:putAttribute>
</tiles:insertDefinition>