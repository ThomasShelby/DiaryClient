<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="body">

			<script src="http://yui.yahooapis.com/3.18.1/build/yui/yui-min.js"></script>
			<script src="resources/js/jquery-1.9.1.min.js"></script>
			<script src="resources/js/calendar-and-records.js"></script>
			<div id="demo" class="yui3-skin-sam yui3-g">
				<div id="leftcolumn" class="yui3-u">
					<div id="mycalendar"></div>
				</div>
				<div id="rightcolumn" class="yui3-u">
					<div id="links" style="padding-left: 20px;">
						Selected date: <span id="selecteddate"></span>
					</div>
				</div>
			</div>
			<h2 style="color: blue; text-align: center">Records</h2>
			<table id="table" class="hidden"></table>
		</div>

	</tiles:putAttribute>
</tiles:insertDefinition>