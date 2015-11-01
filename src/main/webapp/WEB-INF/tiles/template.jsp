<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>The Diary</title>

<script src="resources/js/jquery-2.1.4.min.js"></script>    
<script src="resources/js/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css" href="resources/css/jquery-ui.css">
<script src="resources/js/modalWindow.js"></script>	

<link rel="stylesheet" type="text/css" href="resources/css/style.css">
<link rel="stylesheet" type="text/css" href="resources/css/weather-style.css">
</head>
<body>
    <div class="page">
        <tiles:insertAttribute name="header" />
        <div class="content">
        	<div id="element_to_pop_up"></div>
			<div id="overlay" class="maskForModalWindow"></div>
            <tiles:insertAttribute name="menu" />
            <tiles:insertAttribute name="body" />
        </div>
            <tiles:insertAttribute name="footer" />
    </div>
</body>
</html>