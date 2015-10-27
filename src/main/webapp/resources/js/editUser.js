function checkPassword()
{
	var passwordOld = document.getElementById('passwordOld');
	var passwordNew = document.getElementById('passwordNew');
	var passwordNewRepeat = document.getElementById('passwordNewRepeat');
	var message = document.getElementById('confirmMessage');
	var buttonSubmit = document.getElementById('buttonSubmit');
	var goodColor = "#66cc66";
	var badColor = "#ff6666";
	var submit = true; 
	if (passwordOld.value == document.getElementById('password').value){
		if(passwordNew.value == passwordNewRepeat.value){
			passwordNewRepeat.style.backgroundColor = goodColor;
			message.style.color = goodColor;
			message.innerHTML = "Passwords Match!"
		}else{
			passwordNewRepeat.style.backgroundColor = badColor;
			message.style.color = badColor;
			message.innerHTML = "Passwords Do Not Match!"
				submit = false;	
		}
	}
	return submit;
} 