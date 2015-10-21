
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="body">
		<canvas id="canvas" width="500px" height="500px">
			<div class="text"><p>The most active user: ${mostActiveUser.nickName}
				-${mostActiveUser.firstName} ${mostActiveUser.secondName} with
				${usersAmountOfRecords} records.</p><br>
			<p>The most popular tag(one by record) :
				${mostPopularTag.getTagMessage()}</p>
			</div>
			</br>
			<div id= "drawCharts">
				<h3>Select chart to display :</h3>
				<a href="#" id = "drawCol" class ="block">Show number of logins </a>
				<a href="#" id = "drawLine" class = "block">Show number of records </a>
				<a href="#" id = "drawPie" class = "block">Show sex statistics </a>
			</div>
			</br>
			<div id="chart"></div></br>
			<button onclick="location.href='/DiaryClient'">Back</button>
		</div>
		
	</tiles:putAttribute>
</tiles:insertDefinition>
<style type="text/css">
	.text{
	font-size:15px;
	}
	.block{
	display:block;
	}
</style>
<script type ="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript"
	src="http://underscorejs.org/underscore-min.js"></script>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript" >
function getDataForLogins() {
	var json= ${usersList};
	for (var i = 0; i < json.length; i++) {
		delete json[i].uuid;
		delete json[i].numberOfRecords;
		delete json[i].lastLogin;
		delete json[i].lastRecords;
	}

	var header = [];
	for ( var key in json[0]) {
		header.push(key);
	}
	header = ['User','Logins'];
	//console.log(header);

	var rows = [];
	for (var i = 0; i < json.length; i++) {
		rows.push([ json[i].nickName, json[i].numberOfLogins ]);
	}
	//console.log(rows);
	var jsonData = [ header ].concat(rows);
	return jsonData;
}
function drawChartForLogins() {
	var dataForLogins = getDataForLogins();
	var dataForLogins = google.visualization
			.arrayToDataTable(dataForLogins);
	var optionsForLogins = {
		isStacked : false,
		title : 'Statistic by number of Logins',
		height : 300,
		width : 500,
		colors : [ 'blue' ]
	};
	var chartForLogins = new google.visualization.ColumnChart(document
			.getElementById('chart'));
	chartForLogins.draw(dataForLogins, optionsForLogins);
}



function getDataForRecords() {
	var json = ${usersList};
	for (var i = 0; i < json.length; i++) {
		delete json[i].uuid;
		delete json[i].numberOfLogins;
		delete json[i].lastLogin;
		delete json[i].lastRecords;
	}

	var header = [];
	for ( var key in json[0]) {
		header.push(key);
	}
	//console.log(header);

	header = ['User','Records'];
	
	var rows = [];
	for (var i = 0; i < json.length; i++) {
		rows.push([ json[i].nickName, json[i].numberOfRecords ]);
	}
	//console.log(rows);
	var jsonData = [ header ].concat(rows);
	return jsonData;
}
function drawChartForRecords() {
	var dataForRecords = getDataForRecords();
	var dataForRecords = google.visualization
			.arrayToDataTable(dataForRecords);
	var optionsForRecords = {
		isStacked : false,
		title : 'Statistic by number of Records',
		height : 300,
		width : 500,
		colors : [ 'red' ]
	};
	var chartForRecords = new google.visualization.LineChart(document
			.getElementById('chart'));
	chartForRecords.draw(dataForRecords, optionsForRecords);
	
}


function getDataForSexes() {
	var male = ${male};
	var female = ${female};
	var header = ['Sexes','Percentage'];
	
	var rows = [['Male',male],['Female',female]];
	
	var data = [ header ].concat(rows);
	return data;
}

function drawChartForSexes() {
	var dataForSexes = getDataForSexes();
	var dataForSexes = google.visualization
			.arrayToDataTable(dataForSexes);
	var optionsForSexes = {
		title : 'Statistic by Sex',
		height : 400,
		width : 400,
		is3D:true,
	};

	var chartForSexes = new google.visualization.PieChart(document
			.getElementById('chart'));
	chartForSexes.draw(dataForSexes, optionsForSexes);
}

function initialize () {
    $('#drawCol').click(function() {
        drawChartForLogins();
    });
    $('#drawLine').click(function() {
        drawChartForRecords();
    });
    $('#drawPie').click(function() {
        drawChartForSexes();
    });
}
google.setOnLoadCallback(initialize);
google.load("visualization", "1", {packages:["corechart"]});

</script>