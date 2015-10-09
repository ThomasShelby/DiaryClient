<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
 
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">

  <script type="text/javascript" src="https://www.google.com/jsapi"></script>
  <div id="chart_div"></div>

	<div class="body">
	<h1>System statistic per last month:</h1>
	<div class="text"><p>>General number of logins per month:${list}</p><br>	
	</div>
	
		<script>
	google.load('visualization', '1', {packages: ['corechart', 'bar']});
	google.setOnLoadCallback(drawTrendlines);

	function drawTrendlines() {
	      var data = new google.visualization.DataTable();
	      data.addColumn('timeofday', 'Time of Day');
	      data.addColumn('number', 'Logins per day');

	      data.addRows([
<c:forEach items="${map}" var="entry">
[{v: [${entry.key}+8, 0, 0], f: 'day ${entry.key}'}, ${entry.value}],
</c:forEach>]);
          
	      var options = {
	        title: 'System statistics throughout the last month (daily):',
	        trendlines: {
	          0: {type: 'exponential', lineWidth: 5, opacity: .5}  
	        },
	        hAxis: {
	          title: 'Days of month',
	          format: ' ',
	          viewWindow: {
	            min: [7, 30, 0],
	            max: [37, 30, 0]
	          }
	        },
	        vAxis: {
	          title: 'Amount per day',
	        	  type: "integer"
	        }
	      };

	      var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
	      chart.draw(data, options);
	    }
	</script>
	

	<button onclick="location.href=''">Back</button>
	<div>
    </tiles:putAttribute>
</tiles:insertDefinition>