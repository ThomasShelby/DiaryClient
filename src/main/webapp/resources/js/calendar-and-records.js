/**
 * Created by UserMaryana on 10/9/2015.
 */
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

			var dtdate = Y.DataType.Date;

			Date.prototype.yyyymmdd = function() {
				var yyyy = this.getFullYear().toString();
				var mm = (this.getMonth()+1).toString();
				var dd  = this.getDate().toString();
				return yyyy + (mm[1]?mm:"0"+mm[0]) + (dd[1]?dd:"0"+dd[0]); // padding
			};
			var today = new Date();
			today.yyyymmdd();
			calendar.selectDates(today);
			Y.one("#selecteddate").setHTML(
					dtdate.format(today));

			getDaysWichHaveRecordsPerMonth();
			getRecordsByDate();
			function getRecordsByDate()
			{

				var inputDate = $("#selecteddate").text();
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
			};

			calendar.on("selectionChange", function(ev) {
				var newDate = ev.newSelection[0];
				Y.one("#selecteddate").setHTML(
						dtdate.format(newDate));
				getRecordsByDate();

			});


			function getDaysWichHaveRecordsPerMonth()
			{
				var inputDate = $("#selecteddate").text();
				var startOfMonth = inputDate.substring(0,8)+ '01';
				$.ajax({
					url : 'getDaysWichHaveRecordsPerMonth',
					type : 'GET',
					dataType : 'json',
					data : ({
						dateStart : startOfMonth
					}),
					success: function(data){
						if(data){
							var len = data.length;
							if(len > 0){
								for(var i=0;i<len;i++){
									$('td[class~="yui3-calendar-day"]').each(function() {
										if ($(this).text() == data[i].substring(8,10)){
											$(this).css("background-color", "yellow");
										}})
								}};
						}
					}
				});
			}
		});