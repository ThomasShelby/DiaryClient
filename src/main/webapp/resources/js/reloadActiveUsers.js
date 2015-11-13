

 $(document).ready(function(){
	 
	 var tabl1=$("#listActiveUsers").DataTable();
		
		var columnCount=$('.userProperty option').length;
		
		for ( var i=2 ; i<columnCount ; i++ ) {
				 tabl1.column( i ).visible( false )};
				 
				 
		$('.userProperty').chosen().change(function(){
		
		
			for ( var i=0 ; i<columnCount ; i++ ) {
					tabl1.column( i ).visible( false )};
				 
			$('.userProperty option:selected').each(function(){
				var numberOfVisibleColumn=$( this ).index();
				tabl1.column (numberOfVisibleColumn).visible( true );
			});
		
		});  
 		 	 
	 setInterval(function(){reloadActiveUsers(tabl1)},5000);
 });
 
var reloadActiveUsers=function(tabl1){
	 
	$.ajax({
		
		type : 'GET',
		url : 'activeUsers1',
		dataType : 'json',
		 
		success : function(data) {
			
			tabl1.clear().draw();
			for (var  i=0; i<data.length;i++) {
	var avatarImage='<div><img src="/images/tmpFiles/'+data[i].username+'/'+data[i].avatar+'" alt="user avatar" style="width:40px;height:40px;"/></div>'
	tabl1.row.add( [avatarImage,data[i].username,data[i].firstName,data[i].secondName,data[i].eMail,data[i].session,data[i].ipAddress] ).draw( false );
			}
		},
		error: function(data){
			alert("An error occurred during retrieving data about active users!");
		}
	});
};




