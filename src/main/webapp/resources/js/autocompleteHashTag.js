function lookingForHashTag(type) {

	var c = document.getElementById(type).value;


	if (c.charAt(c.length-1) == "#" && (c.charAt(0) =="#" || c.charAt(c.length-2) == " ")) {
		$(document).ready(function() {
			$('.autocomplete').autocomplete({
				paramName: "tag",
				serviceUrl: 'getTags',
				delimiter: " ",
				minChars: 2,
				deferRequestBy: 200,
				transformResult: function(response) {
					return {

						suggestions: $.map($.parseJSON(response), function(item) {

							return { value: item.tag, data: item.uuid };
						})

					};

				}

			});


		});
	}
}