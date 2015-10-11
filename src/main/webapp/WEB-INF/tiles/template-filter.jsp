<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
    <head>
<script src="resources/js/jquery.1.10.2.min.js"></script>
<script src="resources/js/jquery.autocomplete.min.js" /></script>
<script src="resources/js/autocompleteHashTag.js" /></script>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>The Diary</title>

<link rel="stylesheet" type="text/css" href="resources/css/style.css">
<link rel="stylesheet" type="text/css" href="resources/css/filter-style.css"> 
<link rel="stylesheet" type="text/css" href="resources/css/autocomplete-style.css"> 

</head>
<body>
    <div class="page">
        <tiles:insertAttribute name="header" />
        <div class="content">
            <tiles:insertAttribute name="menu" />
             <tiles:insertAttribute name="filter" />
            <tiles:insertAttribute name="body" />
        </div>
       <tiles:insertAttribute name="footer" />
    </div>
</body>
</html>