$(document).ready(function () {
    setInterval(fun,2000);
});

var fun = function check() {
		
		$.ajax({
			type : 'GET',
			url : 'checkNewRecords',
			success : function (data){
				if(data == 'true') {
					$("#followedUsers").css({
					      "background-color": "yellow",
					      "font-weight": "bolder"
					    });
				}
			}
			
		});
};