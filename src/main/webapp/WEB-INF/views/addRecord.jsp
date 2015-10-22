<%@ page session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script src="resources/js/jquery-1.9.1.min.js"></script>
<script src="resources/js/jquery.autocomplete.min.js" /></script>
<script src="resources/js/autocompleteHashTag.js" /></script>
<script src="resources/js/addCanvas.js" /></script>

<script type="text/javascript">
$(document).ready(function() {
	$('#record').change(function(e) {
		var frm = $('#record');
		var token = $('#csrfToken').val();
		var header = $('#csrfHeader').val();

		var nick = $("#nick").val();
		var title = $("#title").val();
		var text = $("#text").val();
		var data = {
			'nick' : nick,
			'title' : title,
			'text' : text
		}
		$.ajax({
			contentType : 'application/json',
			type : frm.attr('method'),
			url : "${pageContext.request.contextPath}/addRecord/save",
			dataType : 'json',
			data : JSON.stringify(data),
			beforeSend : function(xhr) {
				xhr.setRequestHeader(header, token);
			}
		});
	});
	
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
	$('input:button[name="saveCanvas"]').click(function(){
		var canvas = document.getElementById("canvas");
		var context = canvas.getContext("2d");                    
		var imageDataURL = canvas.toDataURL('image/png');
		console.log(imageDataURL);
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
	<c:if test="${record == null}">
		<c:set var="action" value="addRecord?${_csrf.parameterName}=${_csrf.token}"/>
	</c:if>
	<c:if test="${record != null}">
		<c:set var="action" value="editRecord?${_csrf.parameterName}=${_csrf.token}"/>	
	</c:if>

    <form id="record" action="${action}" name="record" enctype="multipart/form-data" method="POST">
    <!-- hidden values -->
    <input id="nick" type="hidden" name="nick" value="${pageContext.request.userPrincipal.name}" />
    
    <c:if test="${record == null}">
		<input type="hidden" name="id_rec" value="${temporaryRecord.uuid}" />
	</c:if>
	<c:if test="${record != null}">
		<input type="hidden" name="id_rec" value="${record.uuid}" />
	</c:if>
	
    <input type="hidden" name="id_user" value="${user.uuid}" />
    <input type="hidden" id="csrfToken" value="${_csrf.token}"/>
	<input type="hidden" id="csrfHeader" value="${_csrf.headerName}"/>
    
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
            	<c:if test="${record == null}">
					<input id="title" class="autocomplete" type="text" name="title"
	           			 value="${temporaryRecord.title}" oninput="lookingForHashTag('title')" />
	           	</c:if>
				<c:if test="${record != null}">
					<input id="title" class="autocomplete" type="text" name="title"
	            		value="${record.title}" oninput="lookingForHashTag('title')" />	
	            </c:if>
            </div>
        </div>
    </div>

    <!-- third block -->
    <div id="thirdBlock">
        <div class="leftV">
	        Message:<input type="radio" name="canvas" value="noCanvas" checked>
	        <br>
	        Canvas:<input type="radio" name="canvas" value="canvas">
	        <br>
	        <input type="button" name="saveCanvas" value ="Save canvas">
        </div>
        <div id="messageDiv"class="rightV">
        	<c:if test="${record == null}">
					 <textarea id="text" type="text" class="autocomplete" name="text"
						wrap="soft" oninput="lookingForHashTag('text')">${temporaryRecord.text}</textarea>
	        </c:if>
			<c:if test="${record != null}">
					 <textarea id="text" type="text" class="autocomplete" name="text"
						wrap="soft" oninput="lookingForHashTag('text')">${record.text}</textarea>
	        </c:if>
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
          	<c:if test="${record == null}">
					<input id="url" type="text" name="url" value="${temporaryRecord.supplement}" readonly /> 
	        </c:if>
			<c:if test="${record != null}">
					 <input id="url" type="text" name="url" value="${record.supplement}" readonly /> 
	        </c:if>
        </div>
    </div>  

    <!-- Submit -->
	<center>
        <button id="butS">Submit</button>
	</center>
	</form>
</div>
