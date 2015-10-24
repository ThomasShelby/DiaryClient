$(document).ready(function () {
    setInterval(fun,10000);
});

var fun = function autosave() {
		var uuid = $("#uuid").val();
		var nick = $("#nick").val();
		var title = $("#title").val();
		var text = $("#text").val();
		
		var token = $("#csrfToken").val();
		var header = $("#csrfHeader").val();
		
		var data = {
			'uuid' : uuid,
			'nick' : nick,
			'title' : title,
			'text' : text
		}
		
		$.ajax({
			contentType : 'application/json',
			type : 'POST',
			url : "addRecord/save",
			dataType : 'json',
			data : JSON.stringify(data),
			beforeSend : function(xhr) {
				xhr.setRequestHeader(header, token);
			}
		});
};



function openEditRecord(idRecord) 
{
		$("#element_to_pop_up").load('editRecord?id_rec=' + idRecord);
		ShowDialog();
};

function openAddRecord() 
{
		$("#element_to_pop_up").load("addRecord");
		ShowDialog();
};

function ShowDialog()
{
		$("#overlay").show();
		$("#element_to_pop_up").fadeIn(300);
		$("#overlay").unbind("click");
};
					
function CloseAddRecord() 
{
		$("#overlay").hide();
		$("#element_to_pop_up").fadeOut(300);
};


/*This script for addRecord form. It inserts url value in box input type="text" */
function setValue() {
	    var x = document.getElementById("file").value;
	    document.getElementById("url").value = x;
	};