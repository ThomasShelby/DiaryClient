
function getAllAddresses() {
	var country = document.getElementById("country").value;
	 $.ajax({
		 url : 'getAllAddresses',
         type : 'GET',
         dataType : 'json',
         data : ({
        	 country : country
         }),
         success: function(data){
        	 drawMap(data);
         }   
         });
};
google.load('visualization', '1', {'packages': ['geochart']});
function drawMap(data) {
	var country = document.getElementById("country").value;
  var values = new google.visualization.DataTable();
  values.addColumn('string', 'City');
  values.addColumn('number', 'count Active Users');
  values.addColumn('number', 'count All Users'); 
 
for (var i=0;i<data[0].length;i++) {
	values.addRow([data[0][i], data[1][i], data[2][i]]); 	
}
var countryCode = getCountryCode(country);
var options = {
		region: countryCode,
		displayMode: 'markers',
		colorAxis: {colors: ['green', 'blue']}};
	
	    var chart  = new google.visualization.GeoChart(document.getElementById('chart_div'));

	    chart .draw(values, options);
};

