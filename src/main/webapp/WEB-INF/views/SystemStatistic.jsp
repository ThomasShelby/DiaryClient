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
            float: left;
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
<button class="chart-button logins-per-day" onclick="showLoginPerDayStatistics()" style="display:none;">Logins per day</button>
<button class="chart-button duration-per-day" onclick="showDurationPerDayStatistics()">Duration per day</button>
<button class="chart-button session-duration-per-day" onclick="showSessionDurationPerDayStatistics()" style="display:none;">Session duration per day</button>
<div id="chart">
   	<svg></svg>
</div>



<script> 

function showLoginPerDayStatistics(){
	$(".logins-per-day").hide();
	$(".session-duration-per-day").hide();
	$(".duration-per-day").show();
	renderChart(test_data);
};
function showDurationPerDayStatistics(){
	$(".logins-per-day").hide();
	$(".duration-per-day").hide();
	$(".session-duration-per-day").show();
	renderChart(loginDurationData);
};
function showSessionDurationPerDayStatistics(){
	$(".duration-per-day").hide();
	$(".session-duration-per-day").hide();
	$(".logins-per-day").show();
    renderChart(sessionDurationData);
};

var test_data = [{
    "key": "Logins per day",
    "values": [
       <c:forEach items="${map}" var="entry">
        {
            "x": "${entry.key}",
            "y": "${entry.value}"
        },
        </c:forEach>
    ]
}, {
    "key": "Records per day",
    "values": [

	<c:forEach var = "valueArray" items = "${recordsPerDay}">
	{
		"x": "${valueArray[0]}",
		"y": "${valueArray[1]}"
	},
	</c:forEach>
]       
}];
var loginDurationData = [{
    "key": "Durations per day",
    "values": [
       <c:forEach items="${loginDuration}" var="entry">
        {
            "x": "${entry.key}",
            "y": "${entry.value}"
        },
        </c:forEach>
    ]
}];
var sessionDurationData = [{
    "key": "Session durations per day",
    "values": [
       <c:forEach items="${sessionDuration}" var="entry">
        {
            "x": "${entry.key}",
            "y": "${entry.value}"
        },
        </c:forEach>
    ]
}];
function renderChart(data){
nv.addGraph({
    generate: function () {
        var width = nv.utils.windowSize().width,
                height = nv.utils.windowSize().height;
        var chart = nv.models.multiBarChart() 
        .width(width)
        .height(height) 
        .margin({top: 0, right: 40, bottom: 130, left: 160})
        .showControls(true) 
        .reduceXTicks(false) 
        .color( [d3.rgb("#08306b"), d3.rgb("#f81111")])
		chart.yAxis.tickFormat(d3.format(',d'));
        chart.xAxis.rotateLabels(-45);
        chart.xAxis.tickFormat(function (d) {
            return moment(d, "YYYY/MM/DD") 
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
renderChart(test_data);
</script>

    </tiles:putAttribute>
</tiles:insertDefinition>