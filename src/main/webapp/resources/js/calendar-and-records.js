YUI().use(
    'calendar',
    'datatype-date',
    'cssbutton',
    function(Y) {
        var calendar = new Y.Calendar({
            contentBox : "#mycalendar",
            width : '340px',
            showPrevMonth : true,
            showNextMonth : true,
            date : new Date()
        }).render();
        ;

        Date.prototype.yyyymmdd = function() {
            var yyyy = this.getFullYear().toString();
            var mm = (this.getMonth()+1).toString();
            var dd  = this.getDate().toString();
            return yyyy + (mm[1]?mm:"0"+mm[0]) + (dd[1]?dd:"0"+dd[0]);
        };

        var today = new Date();
        today.yyyymmdd();
        calendar.selectDates(today);

        var dtdate = Y.DataType.Date;
        Y.one("#selecteddate").setHTML(
            dtdate.format(today));

        var inputDate = $("#selecteddate").text();
        getRecordsByDate(inputDate);

        var startOfMonth = inputDate.substring(0,8)+ '01';
        highlightDaysWichHaveRecordsPerMonth(startOfMonth);

       calendar.on("selectionChange", function(ev) {
            var newDate = ev.newSelection[0];
            Y.one("#selecteddate").setHTML(
                dtdate.format(newDate));
            var inputDate = $("#selecteddate").text();
            getRecordsByDate(inputDate);
        });

        $(".yui3-calendarnav-nextmonth, .yui3-calendarnav-prevmonth").click(function(){
            var inputDate = getCurrentDayAndMonth();
            highlightDaysWichHaveRecordsPerMonth(inputDate);
        
        });

        function getRecordsByDate(inputDate)
        {
            $.ajax({
                url : 'getRecordsByDay',
                type : 'GET',
                dataType : 'json',
                data : ({
                    selected : inputDate
                }),
                success: function(data){
                    if(data){
                        var len = data.length;
                        var txt = "";
                        $("#table").empty().addClass("hidden");
                        if(len > 0){
                            txt += "<tr><th>ID</th><th>Title</th><th>Text"
                                +"</th><th>Visibility</th><th>Date</th></tr>";
                            for(var i=0;i<len;i++){
                                txt += "<tr><td>"+data[i].uuid+"</td><td>"
                                    +data[i].title+"</td></td>"
                                    + "</td><td>"+data[i].text+"</td></td>"
                                    + "</td><td>"+data[i].visibility+"</td></td>"
                                    + "<td><td>"+data[i].createdTime+"</td><tr>";
                            }
                            if(txt != ""){
                                $("#table").append(txt).removeClass("hidden");
                            }
                        }
                    }
                }
            });
        }

        function highlightDaysWichHaveRecordsPerMonth(startOfMonth)
        {
            $.ajax({
                url : 'getDaysWichHaveRecordsPerMonth',
                type : 'GET',
                dataType : 'json',
                data : ({
                    dateStart : startOfMonth
                }),
                success: function(data){
                    $('td[class~="yui3-calendar-day"]').each(function() {
                        $(this).css("text-shadow", "0px 0px red");
                    })
                    if(data){
                        var len = data.length;
                        if(len > 0){
                            for(var i=0;i<len;i++){
                                $('td[class~="yui3-calendar-day"]').each(function() {
                                    if ($(this).text() == data[i].substring(8,10)){
                                        $(this).css("text-shadow", "2px 2px red");
                                    }})
                            }
                        }
                    };
                }
            });
        }

        function getMonthNumber(month)
        {
            switch (month) {
                case 'January':
                    monthNumber = '01';
                    break;
                case 'February':
                    monthNumber = '02';
                    break;
                case 'March':
                    monthNumber = '03';
                    break;
                case 'May':
                    monthNumber = '04';
                    break;
                case 'April':
                    monthNumber = '05';
                    break;
                case 'June':
                    monthNumber = '06';
                    break;
                case 'July':
                    monthNumber = '07';
                    break;
                case 'August':
                    monthNumber = '08';
                    break;
                case 'September':
                    monthNumber = '09';
                    break;
                case 'October':
                    monthNumber = '10';
                    break;
                case 'November':
                    monthNumber = '11';
                    break;
                case 'December':
                    monthNumber = '12';
                    break;
            }
            return monthNumber;
        }

        function getCurrentDayAndMonth()
        {
            var curMonthYear = $(".yui3-calendar-header-label").text();
            year = curMonthYear.substring(curMonthYear.length-4, curMonthYear.length);
            month = curMonthYear.substring(0,curMonthYear.length-5);
            monthNumber = getMonthNumber(month);
            return year + '-' + monthNumber + '-01';
        }
    });

