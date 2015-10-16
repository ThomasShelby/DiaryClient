<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
 
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">




    <link href="https://cdnjs.cloudflare.com/ajax/libs/nvd3/1.8.1/nv.d3.css" rel="stylesheet" type="text/css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/d3/3.5.2/d3.min.js" charset="utf-8"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/nvd3/1.8.1/nv.d3.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.6/moment-with-locales.min.js"></script>
    <style>
        text {
            font: 12px sans-serif;
        }
        svg {
            display: block;
        }
        html, body, #test1, svg {
            margin: 0px;
            padding: 0px;
            height: 100%;
            width: 100%;
        }
        #test1 .nv-bar{
            fill-opacity: .8;
        }
    </style>
<div id="test1">
    <svg></svg>
</div>

<script>
    var test_data = [{
        "key": "Logins per day",
        "values": [
       <c:forEach items="${map}" var="entry">
        {
            "x": "${entry.key}",
            "y": ${entry.value}
        },
        </c:forEach>
        ]
    }];
    nv.addGraph({
        generate: function () {
            var width = nv.utils.windowSize().width,
                    height = nv.utils.windowSize().height;

            var chart = nv.models.multiBarChart() 
            .width(width) 
            .height(height) 
            .showControls(true) 
            .reduceXTicks(false) 
            .color( [d3.rgb("#08306b"), d3.rgb("#4292c6")])
			chart.yAxis.tickFormat(d3.format(',d'));
            chart.xAxis.rotateLabels(-45);
            chart.xAxis.tickFormat(function (d) {
                return moment(d, "DD") 
                        .format("DD/10"); 
            });
            chart.dispatch.on('renderEnd', function () {
                console.log('Render Complete');
            });
            var svg = d3.select('#test1 svg').datum(test_data);
            console.log('calling chart');
            svg.transition().duration(0).call(chart);
            return chart;
        },

        callback: function (graph) {
            nv.utils.windowResize(function () {
                var width = nv.utils.windowSize().width;
                var height = nv.utils.windowSize().height;
                graph.width(width).height(height);

                d3.select('#test1 svg')
                        .attr('width', width)
                        .attr('height', height)
                        .transition().duration(0)
                        .call(graph);
            });
        }

    });
</script>



    </tiles:putAttribute>
</tiles:insertDefinition>