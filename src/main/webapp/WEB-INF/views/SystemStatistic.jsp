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
<div id="chart">
   	<svg></svg>
</div>



<script> 

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
	        		$.get("/DiaryClient/systemStatistic/logins", loginsHandler),
	        		$.get("/DiaryClient/systemStatistic/records", recordsHandler)
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