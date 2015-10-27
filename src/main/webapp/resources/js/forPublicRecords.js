 $(function(){
    $("#pubRecords").dataTable();
  
  });

	function substringFunction() {
    var c = document.getElementsByName('textMessage');
    var d = document.getElementsByName('createdTime');
        for (i = 0; i < c.length; i++) {
            var text = c[i].innerHTML;
            var date = d[i].innerHTML;
            if (text.length > 420) {
                text = text.substring(0,400) + "   ..............";
    }
     c[i].innerHTML = text;
     d[i].innerHTML = Date(date);
    }
}
