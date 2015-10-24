$(function(){
	$('#forgotAccount').click(function(){
	$('#firstPopUp').bPopup({
	modalClose: false,
	speed: 650,
    transition: 'slideIn',
    transitionClose: 'slideBack'
	});
	});
	});
	
	$('#closeEmailForm').click(function(){
	var bPopup = $('#firstPopUp').bPopup();
	bPopup.close();
	});
	
	
	function resultOfForgotAccount()
	{
		var mail = document.getElementById("email").value;
	    $.ajax({
	        url : 'forgotAccount',
	        type : 'GET',
	        dataType : 'text',
	        data : ({
	            email : mail
	        }),
	        error: function(data){
	        	var bPopup = $('#firstPopUp').bPopup();
	        	bPopup.close();
	        },
	   		 success: function(data){ 
	   	     	var bPopup = $('#firstPopUp').bPopup();
	   	    	bPopup.close();
	   	    	$("#responseEmail").text(data);
	   	    	$('#secondPopUp').bPopup({
	   	    	speed: 650,
	   	        transition: 'slideIn',
	   		    transitionClose: 'slideBack',
	   	     	autoClose: 3000
	   	 	});
	   		}
	    })
};