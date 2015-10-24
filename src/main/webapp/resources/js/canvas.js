$(document.getElementById("canvasDiv")).hide();
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
	
});