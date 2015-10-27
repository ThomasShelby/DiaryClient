<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%> 

<script src="resources/js/jquery.autocomplete.min.js" /></script>
<script src="resources/js/autocompleteHashTag.js" /></script>
<link rel="stylesheet" type="text/css" href="resources/css/filter-style.css"> 
<link rel="stylesheet" type="text/css" href="resources/css/autocomplete-style.css"> 
<div id="filter">
<center>
	<form action="hashTag" method="get">
		Search by hash Tag:
        <input id="filterField" class="autocomplete" type="text" name="hashTag" placeholder=" // #MyHashTag" 
        oninput="lookingForHashTag('filterField')" />
        <input  id="search" type="submit" value="Search!" /> 
	</form>
</center>
</div>
	    
