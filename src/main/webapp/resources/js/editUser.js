function checkForm(form) {

	var passwordOld = document.getElementById('passwordOld');
	var passwordNew = document.getElementById('passwordNew');
	
	if (!(passwordOld.value == undefined)) {
		var message = document.getElementById('confirmMessage');
		var buttonSubmit = document.getElementById('buttonSubmit');
		var goodColor = "#66cc66";
		var badColor = "#ff6666";

		var isCorrectOldPassword = checkPassword(passwordOld.value);
		if (!(passwordOld.value=='undefined')){
			if (isCorrectOldPassword) {
				matches();
				if (passwordNew.value.length < 5){
					message.style.color = badColor;
					message.innerHTML = "New password must be at least 5 characters";
					return false;
				}
			} else {
				alert("Not correct old password");
				return false
			}
		}
	}
	return true;
}

function matches(){
	
	var passwordNew = document.getElementById('passwordNew');
	var passwordNewRepeat = document.getElementById('passwordNewRepeat');
	
	if (passwordNew.value == passwordNewRepeat.value) {
		passwordNewRepeat.style.backgroundColor = goodColor;
		message.style.color = goodColor;
		message.innerHTML = "Passwords Match!"
			document.getElementById('passwordNew').value = passwordNew.value;
	} else {
		passwordNewRepeat.style.backgroundColor = badColor;
		message.style.color = badColor;
		message.innerHTML = "Passwords Do Not Match!"
			return false;
	}
}

function checkPassword(passwordOld) {

	var isCorrect = false;

	$.ajax({
		url : 'isOldPasswordCorrect',
		type : 'GET',
		dataType : 'json',
		data : ({
			password : passwordOld
		}),
		async : false,
		success : function(data) {
			if (data) {
				if (data == 'true')
					isCorrect = true
			}
		}
	});

	return isCorrect;
}