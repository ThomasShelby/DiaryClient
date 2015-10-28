 $(function(){
    $("#pubRecords").dataTable();
  
  });

	function substringFunction() {
    var c = document.getElementsByName('textMessage');

        for (i = 0; i < c.length; i++) {
            var text = c[i].innerHTML;

            if (text.length > 420) {
                text = text.substring(0,400) + "   ..............";
    }
     c[i].innerHTML = text;

    }
}
