<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
 
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
    <script src="resources/js/jquery-1.9.1.min.js"></script>



	<link href="https://cdnjs.cloudflare.com/ajax/libs/nvd3/1.8.1/nv.d3.css" rel="stylesheet" type="text/css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/d3/3.5.2/d3.min.js" charset="utf-8"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/nvd3/1.8.1/nv.d3.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.6/moment-with-locales.min.js"></script>    
    <style>
        .chart-button {
            width: auto;
            float: right;
        }
		   #nav {
	float: right;
	width: 100%;
	list-style: none;
	font-weight: bold;
	margin-bottom: 10px;
    }

	#nav li {
		float: right;
		margin-right: 10px;
		position: relative;
		display: block;
	}
	
	#nav li a {
		display: block;
		padding: 5px;
		color: #fff;
		background: #333;
		text-decoration: none;
		text-shadow: 1px 1px 1px rgba(0, 0, 0, 0.75);
		-moz-border-radius: 2px;
		-webkit-border-radius: 2px;
		border-radius: 2px;
	}
	
	#nav li a:hover {
		color: #fff;
		background: #6b0c36;
		background: rgba(107, 12, 54, 0.75); 
		text-decoration: underline;
	}
	
	#nav ul {
		list-style: none;
		position: absolute;
		left: -9999px;
		opacity: 0; 
		-webkit-transition: 0.25s linear opacity;
	}
	
	#nav ul li {
		padding-top: 1px;
		float: none;
	/* 	background: url(dot.gif); */
	}
	
	#nav ul a {
		white-space: nowrap;
		display: block;
	}
	
	#nav li:hover ul { 
		left: -30; 
		opacity: 1; 
	}
	
	#nav li:hover a {
		background: #6b0c36;
		background: rgba(107, 12, 54, 0.75); 
		text-decoration: underline;
	}
	
	#nav li:hover ul a {
		text-decoration: none;
		-webkit-transition: -webkit-transform 0.075s linear;
	}
	
	#nav li:hover ul li a:hover {
		background: #333;
		background: rgba(51, 51, 51, 0.75);
		text-decoration: underline;
		-moz-transform: scale(1.05);
		-webkit-transform: scale(1.05);
	}
        text {
            font: 12px sans-serif;
        }
        svg {
            display: block;
        }
        html, body, #chart, svg {
            margin: 0px;
            padding: 0px;
           /* height: 100%;*/ 
            width: 100%;
        }
        #chart .nv-bar{
            fill-opacity: .8;
        }
    </style>
<button class="chart-button logins-per-day" onclick="showLoginAndRecordPerDayStatistics()" style="display:none;">Show logins and records per day</button>
<button class="chart-button duration-per-day" onclick="showLoginDurationPerDayStatistics()">Show login duration per day</button>
<button class="chart-button session-duration-per-day" onclick="showSessionDurationPerDayStatistics()" style="display:none;">Show session duration per day</button>

<ul id="nav">
	<li>
		<a href="#" title="Graph month">Select month: </a>
		<ul id="monthSelector">
			<li><a href="#">September</a></li>
			<li><a href="#">October</a></li>
			<li><a href="#">November</a></li>
			<li><a href="#">December</a></li>
		</ul>
	</li>
</ul>


<div id="chart">
   	<svg></svg>
</div>



<script> 
var selectedMonth = moment().month();
$("#monthSelector li").click(function(){
		var nameOfMonth = $(this).text();
		var date = moment().month(nameOfMonth);
		selectedMonth = date.month() + 1; //returns number
		loginsAndRecordsStartHandler();
})

function showLoginAndRecordPerDayStatistics(){
	$(".logins-per-day").hide();
	$(".session-duration-per-day").hide();
	$(".duration-per-day").show();
	loginsAndRecordsStartHandler();
};

function showLoginDurationPerDayStatistics(){
	$(".logins-per-day").hide();
	$(".duration-per-day").hide();
	$(".session-duration-per-day").show();
	$.get("/DiaryClient/systemStatistic/login_duration", loginDurationHandler);
};

function showSessionDurationPerDayStatistics(){
	$(".duration-per-day").hide();
	$(".session-duration-per-day").hide();
	$(".logins-per-day").show();
	$.get("/DiaryClient/systemStatistic/session_duration", sessionDurationHandler);
};


var loginsAndRecordsData = [{"key": "Logins per day"} , {"key": "Records per day"}];
var loginDurationData = [{"key": "Login durations per day"}];
var sessionDurationData = [{"key": "Session durations per day"}];


var loginsAndRecordsStartHandler = function(){
	var promises = [
	        		$.get("/DiaryClient/systemStatistic/logins", {month: selectedMonth}, loginsHandler),
	        		$.get("/DiaryClient/systemStatistic/records", {month: selectedMonth}, recordsHandler)
	        	];
	        	
	        	$.when.apply($, promises)
	        	.then(function(){
	        		renderChart(loginsAndRecordsData);
	        	});
}

var sessionDurationHandler = function(data){
	var durations = data;

	var sessionDuration = [];
	
	console.log(data);
	$.each(durations, function(key, value){
		sessionDuration.push(
			{
			"x" : key,
			"y" : value
			}
		);
	});
	sessionDurationData[0].values = sessionDuration;
	renderChart(sessionDurationData);
};

var loginDurationHandler = function(data){
	var durations = data;

	var loginDuration = [];
	
	console.log(data);
	$.each(durations, function(key, value){
		loginDuration.push(
			{
			"x" : key,
			"y" : value
			}
		);
	});
	loginDurationData[0].values = loginDuration;
	renderChart(loginDurationData);
};


var loginsHandler = function(data){
	var logins = data;
	var loginsValues = [];

	$.each(logins, function(key, value){
		loginsValues.push(
			{
			"x" : key,
			"y" : value
			}
		);
	});
    console.log(loginsValues);
	loginsAndRecordsData[0].values = loginsValues;
}


var recordsHandler = function(data){
	var records = data;
	var recordsValues = [];
	
	$.each(records, function(key, value){
		recordsValues.push(
			{
			"x" : key,
			"y" : value
			}
		);
	});
	loginsAndRecordsData[1].values = recordsValues;
}

function renderChart(data){
nv.addGraph({
    generate: function () {
    	d3.selectAll("svg > *").remove();
    	
        var width = nv.utils.windowSize().width,
                height = nv.utils.windowSize().height;
        var chart = nv.models.multiBarChart() 
        .width(width)
        .height(height) 
        .margin({top: 0, right: 40, bottom: 130, left: 200})
        .showControls(true) 
        .reduceXTicks(false) 
        .color( [d3.rgb("#08306b"), d3.rgb("#f81111")])
		chart.yAxis.tickFormat(d3.format(',d'));
        chart.xAxis.rotateLabels(-45);
        chart.xAxis.tickFormat(function (d) {
            return moment(d, "YYYY-MM-DDTHH:mm:ss.SSSZ") 
                    .format("DD/MM/YY"); 
        });
        chart.dispatch.on('renderEnd', function () {
            console.log('Render Complete');
        });
        var svg = d3.select('#chart svg').datum(data);
        console.log('calling chart');
        svg.transition().duration(0).call(chart);
        return chart;
    },
    callback: function (graph) {
        nv.utils.windowResize(function () {
            var width = nv.utils.windowSize().width;
            var height = nv.utils.windowSize().height;
            graph.width(width).height(height);
            d3.select('#chart svg')
                    .attr('width', width)
                    .attr('height', height)
                    .transition().duration(0)
                    .call(graph);
        });
    }
});
};


loginsAndRecordsStartHandler();

</script>

    </tiles:putAttribute>
</tiles:insertDefinition>