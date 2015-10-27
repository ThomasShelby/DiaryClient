$(function() {
    $( "#selectedDate" ).datepicker();
  });

google.load("visualization", "1", {packages:["corechart"]});
function getActiveUsers() {
	var selectedDate = document.getElementById("selectedDate").value;
    var dateRange = document.getElementById("dateRange").value;

	$.ajax({
		url : 'getUsersActivity',
        type : 'GET',
        dataType : 'json',
        data : ({
        	dateRange : dateRange,
        	selectedDate : selectedDate
        		}),
        success: function(data){
        	drawChart(data);      	 
        }   
})};

function drawChart(data) {
  var dateRange = document.getElementById("dateRange").value;
  var values = new google.visualization.DataTable();
  values.addColumn('string', 'key');
  values.addColumn('number', 'count');
  for (var key in data) {
	values.addRow([getFormatedValues(key), data[key]]); 
   }

  var options = {
    title: 'Users Activity',
    hAxis:{
    	  title: getTitleForX(dateRange),
    	  },
    	  lineWidth: 5,
    	  pointShape: 'circle' ,
    	  pointSize: 8,
    	  vAxis:  {
    	  title: 'Count Users',
    	  },
    	  legend: { position: 'none' }
	};

  var chart = new google.visualization.LineChart(document.getElementById('chart_div2'));
  chart.draw(values, options);
};

function getFormatedValues(value) {
	 var dateRange = document.getElementById("dateRange").value;
	 if (dateRange == "hourly") {
		return value; 
	 }
	 if (dateRange == "weekly") {
		 switch(value) {
		 case '0':
			 return 'Sunday';
		 case '1':
			 return 'Monday';
		 case '2':
			 return 'Tuesday';
		 case '3':
			 return 'Wednesday';
		 case '4':
			 return 'Thursday';
		 case '5':
			 return 'Friday';
		 case '6':
			 return 'Suturday';
		 }
	 }
	 if (dateRange == "monthly") {
		 return value;
	 }
}
function getTitleForX(dateRange) {
	if (dateRange == 'hourly') {
		return 'hours';
	}
	if (dateRange == 'weekly') {
		return 'days of week';
	}
	if (dateRange == 'monthly') {
		return 'days of month';
	}
}