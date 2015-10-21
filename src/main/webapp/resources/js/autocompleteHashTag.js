	$(document).ready(function() {
		$('.autocomplete').autocomplete({
			paramName: "tag",
			serviceUrl: 'getTags',
			delimiter: " ",
			minChars: 1,
			deferRequestBy: 200,
		    transformResult: function(response) {
		        return {
		        	
		            suggestions: $.map($.parseJSON(response), function(item) {
		            	
		                return { value: item };
		            })
		            
		        };
		        
		    }
		    
		});
		
		
	});