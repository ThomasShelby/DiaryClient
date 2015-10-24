<%@ page session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script src="resources/js/jquery-1.9.1.min.js"></script>
<script src="resources/js/jquery.autocomplete.min.js" /></script>
<script src="resources/js/autocompleteHashTag.js" /></script>
<script src="resources/js/modalWindow.js" /></script>
<script src="resources/js/addCanvas.js" /></script>

<script type="text/javascript">
$(document).ready(function() {
	$('input:radio[name="canvas"]').change(
		    function(){
		        if (this.checked && this.value == 'canvas') {
		        	$(document.getElementById("canvasDiv")).show();
		        	$(document.getElementById("messageDiv")).hide();
		        }else {
		        	$(document.getElementById("canvasDiv")).hide();
		        	$(document.getElementById("messageDiv")).show();
		        }
		    });
	$("#butS").mousedown(function(){
		var canvas = document.getElementById("canvas");
		var context = canvas.getContext("2d");                    
		var imageDataURL = canvas.toDataURL('image/png');
		//console.log(imageDataURL);
		document.getElementById('canvasData').value = imageDataURL;
	});
	
});</script>
<link rel="stylesheet" type="text/css" href="resources/css/autocomplete-style.css">
<link rel="stylesheet" type="text/css" href="resources/css/addRecord.css">
<style type = "text/css"> 
#canvasDiv {
	display:none;
}
</style>
<div class="addRecord">
	<!-- set action for this form (update or create record) -->
	
	
		<c:set var="action" value="addRecord?${_csrf.parameterName}=${_csrf.token}"/>
	
	<c:if test="${(record != null) && (label == 'edit')}">
		<c:set var="action" value="editRecord?${_csrf.parameterName}=${_csrf.token}"/>
	</c:if>

    <form id="record" action="${action}" name="record" enctype="multipart/form-data" method="POST">
    <!-- hidden values -->
    <input id="nick" type="hidden" name="nick" value="${pageContext.request.userPrincipal.name}" />
    
    
		<input id="uuid" type="hidden" name="uuid" value="${record.uuid}" />
	
	
    <input type="hidden" name="userId" value="${user.uuid}" />
    <input type="hidden" name="csrfToken" id="csrfToken" value="${_csrf.token}"/>
	<input type="hidden" name="csrfHeader" id="csrfHeader" value="${_csrf.headerName}"/>
    
    <!-- to first line -->
        <div class="bigger">
            <div id="UserAvatar">
           <img src="/images/tmpFiles/${user.nickName}/${user.avatar}" width="100" height="100" class="Avatar"  alt="userAvatar"/>
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
	        Message:<input type="radio" name="canvas" value="noCanvas" checked>
	        <br>
	        Canvas:<input type="radio" name="canvas" value="canvas">
        </div>
        <div class="rightV">
					 <textarea id="text" type="text" class="autocomplete" name="text"
						wrap="soft" oninput="lookingForHashTag('text')">${record.text}</textarea>
        </div>
        <div id = "canvasDiv" class="rightV">   
	        <canvas id="canvas" height="300px" width="595px"></canvas>
	        <input type="hidden" name="canvasData" id="canvasData" value="" />
	    </div>
    </div>
    
    
    <!-- four block  -->
     <div class="file_upload_block">
        <div class="leftV">
           Select a file:
        </div>
        <div class="rightV">
          <div class="file_upload">+<input id="file" type="file" name="file" onchange="setValue()" /></div>
			<input  id="url" type="text" name="supplement" value="${record.supplement}" readonly /> 
        </div>
    </div>  

    <!-- Submit -->
	<center>
        <button id="butS">Submit</button>
	</center>
	</form>
</div>
