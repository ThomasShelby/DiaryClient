	$(document).ready(function() {
		$('.autocomplete').autocomplete({
			paramName: "tag",
			serviceUrl: 'getTags',
			delimiter: " ",
			minChars: 3,
			deferRequestBy: 300,
		    transformResult: function(response) {
		        return {
		        	
		            suggestions: $.map($.parseJSON(response), function(item) {
		            	
		                return { value: item.tag, data: item.uuid };
		            })
		            
		        };
		        
		    }
		    
		});
		
		
	});