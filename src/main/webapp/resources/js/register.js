$(function() {
	
	$.validator.addMethod("regx", function(value, element, regexpr) {          
	    return regexpr.test(value);
	}, "Please enter a valid username");
	
	$("#signupForm").validate({
		rules: {
			username: {
				required: true,
				minlength: 2,
				maxlength: 20,
				regx: /^[a-zA-Z0-9]+$/,
				remote: {
					url: "usernameExist",
					type: "POST",
					value: true
				}
			},
			password: {
				required: true,
				minlength: 5,
				maxlength: 20
			},
			confirm_password: {
				required: true,
				minlength: 5,
				maxlength: 20,
//				equalTo: "#password"				
			},
			email: {
				required: true,
				email: true,
			}
		},
		messages: {
			username: {
				required: "Please enter a username",
				minlength: "Please enter at least 2 characters",
				maxlength: "Please enter value less than or equal 20 characters",
				regx : "Please enter a valid username",
				remote: "This username is already taken"
			},
			password: {
				required: "Please provide a password",
				maxlength: "Please enter value less than or equal 20 characters",
				minlength: "Password must be at least 5 characters"
			},
			confirm_password: {
				required: "Please provide a password",
				maxlength: "Please enter value less than or equal 20 characters",
				minlength: "Password must be at least 5 characters"
//				equalsTo: "Please enter the same password as above"
			},
			email: {
				required: "We need your email to contact you",
				email: "Your email address must be in the format of name@domain.com"
			}
		},
		submitHandler : function(form) {
			sendAjax();
		}
	});

	function sendAjax() {
		var username = $('#username').val();

		$.post("usernameExist", {username: username},
		function(result)
		{
			if (result == null) {
				$('#username_availability_result').html(username + ' is Available');
			} else {
				$('#username_availability_result').html(username + ' is not Available');
			}
		});
	}
});