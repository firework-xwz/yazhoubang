//------------- calendar.js -------------//
$(document).ready(function() {

	//declare chart colours ( be sure are same with custom-variables.less or main.css file)
	var colours = {
		white: '#ffffff',
		dark: '#79859b',
		red : '#f68484',
		blue: '#75b9e6',
		green : '#71d398',
		yellow: '#ffcc66',
		brown: '#f78db8',
		orange : '#f4b162',
		purple : '#af91e1',
		pink : '#f78db8',
		lime : '#a8db43',
		magenta: '#eb45a7',
		teal: '#97d3c5',
		textcolor: '#5a5e63',
		gray: '#f3f5f6'
	}
	$('#patient').click(function(){
		$('#example-modal').modal('show');
	})
	$('#close').click(function () {
		$('#example-modal').modal('hide')
	})


	/* initialize the external events
		-----------------------------------------------------------------*/
	
	$('#external-events div.external-event').each(function() {
	
		// create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
		// it doesn't need to have a start or end
		var eventObject = {
			title: $.trim($(this).text()) // use the element's text as the event title
		};
		
		// store the Event Object in the DOM element so we can get to it later
		$(this).data('eventObject', eventObject);
		
		// make the event draggable using jQuery UI
		$(this).draggable({
			zIndex: 999,
			revert: true,      // will cause the event to go back to its
			revertDuration: 0  //  original position after the drag
		});
		
	});
var events=[];
var haveEvents=0;
	/*get  appointment data from database*/
	$(function () {
		var optionJson=[];
		var data={
			id:"1",
			s_time:"2018-10-10 22:00:00",
			type:"select",
			};
		data=JSON.stringify(data);
		$.ajax(
			{
			type:"POST",
			url:"/PCalendar",
			contentType: "application/json;charset=utf-8",
			data:data,
			success:function (result) {
				optionJson=result;
				if(optionJson.status=="empty")
				{
					events=[];
				}
				else {
					if (optionJson.appointmentStatus=="0")
					var item={
						title: "等待回复",
						start: optionJson.start,
						//end: new Date(y, m, d, 14, 0),
						allDay: false,
						id:optionJson.start
					}
					else if(optionJson.appointmentStatus=="1")
					{
						var item={
							title: "预约成功",
							start: optionJson.start,
							//end: new Date(y, m, d, 14, 0),
							allDay: false,
							id:optionJson.start
						}
					}

					events.push(item);
					haveEvents=1;
				}
			},
			error:function () {
				console.log("fail");
			}
		});
	})


	/* initialize the calendar
	-----------------------------------------------------------------*/
	var date = new Date();
	var d = date.getDate();
	var m = date.getMonth();
	var y = date.getFullYear();

	var calendar = $('#calendar').fullCalendar({
		header: {
			left: 'prev,next today',
			center: 'title',
			right: 'month,agendaWeek,agendaDay'
		},
		buttonText: {
        	prev: '<i class="en-arrow-left8 s16"></i>',
        	next: '<i class="en-arrow-right8 s16"></i>',
        	today:'Today'
    	},
		editable: true,
		droppable: true, // this allows things to be dropped onto the calendar !!!
		drop: function(date) { // this function is called when something is dropped
		
			// retrieve the dropped element's stored Event Object
			var originalEventObject = $(this).data('eventObject');
			
			// we need to copy it, so that multiple events don't have a reference to the same object
			var copiedEventObject = $.extend({}, originalEventObject);
			
			// assign it the date that was reported
			copiedEventObject.start = date;
			
			// render the event on the calendar
			// the last `true` argument determines if the event "sticks" (http://arshaw.com/fullcalendar/docs/event_rendering/renderEvent/)
			if(date<(new Date())){
				alert("请在合适时间进行预约")
			}
			else if(haveEvents==1){
				alert("请先完成已有预约");
			}
			else{
			var data={
				"id":1,
				"s_time":$.fullCalendar.formatDate(date,"yyyy-MM-dd HH:mm:ss"),
				"type":"insert"
			}
			data=JSON.stringify(data);
				$.ajax({
					type:"POST",
					url:"/PCalendar",
					contentType: "application/json;charset=utf-8",
					data:data,
					success:function (result) {
						if(result.status=="noD_id")
						{
							alert("您还没有医师");
						}
						else {
							haveEvents=1;
							copiedEventObject.title = "等待回复";
							copiedEventObject.id = $.fullCalendar.formatDate(date, "yyyy-MM-dd HH:mm:ss");
							haveEvents = 1;
							$('#calendar').fullCalendar('renderEvent', copiedEventObject, true);
							alert("提交成功");
						}

					},
					error:function () {
						console.log("fail");
					}
				})
			}
			
			// is the "remove after drop" checkbox checked?
			if ($('#drop-remove').is(':checked')) {
				// if so, remove the element from the "Draggable Events" list
				$(this).remove();
			}	
		},
        eventClick : function(event, jsEvent, view){
			var time;
			//执行一个laydate实例
			laydate.render({
				elem: '#test2',
				format: 'yyyy-MM-dd HH:mm:ss',
				value:$.fullCalendar.formatDate(event.start,"yyyy-MM-dd HH:mm:ss"),
				type: 'datetime',
				min:$.fullCalendar.formatDate(new Date(),"yyyy-MM-dd HH:mm:ss"),
				done: function(value, date, endDate){
					time=value;
				}
			});
			document.getElementById("btn_delete").style.display="inline-block";

			$('#myModal').modal('show');

			$('#btn_submit').click(function () {
				if(time==null)
					time=$.fullCalendar.formatDate(event.start,"yyyy-MM-dd HH:mm:ss")
				if(time!=$.fullCalendar.formatDate(event.start,"yyyy-MM-dd HH:mm:ss")){
					var data={
						id:1,
						s_time:time,
						type:"update"
					};
					data=JSON.stringify(data);
					$.ajax({
						type:"POST",
						url:"/PCalendar",
						contentType: "application/json;charset=utf-8",
						data:data,
						success:function (result) {
							var title=event.title;
							$("#calendar").fullCalendar("removeEvents",event.id);
							calendar.fullCalendar('renderEvent',
								{
									title: title,
									start: time,
									allDay: false,
									id:time
								}
							);
							haveEvents=1;
							alert("提交成功");
							$('#myModal').modal('hide');
						},
						error:function () {
							console.log("fail");
						}
					})
				}
			});

			$('#btn_delete').click(function () {
				data={
					id:"1",
					s_time:"2018-12-29 00:00:00",
					type:"delete",
				};
				data=JSON.stringify(data);
				$.ajax({
					type:"POST",
					url:"/PCalendar",
					contentType: "application/json;charset=utf-8",
					data:data,
					success:function (result) {
						haveEvents=0;
						$("#calendar").fullCalendar("removeEvents",event.id);
						$('#myModal').modal('hide');
					},
					error:function () {
						console.log("fail");
					}
				})
			})
        },
		eventDrop : function(event, dayDelta, minuteDelta, allDay, revertFunc, jsEvent, ui, view){
			if(event.start<(new Date())){
				revertFunc()
			}
			else{
				event.id=$.fullCalendar.formatDate(event.start,"yyyy-MM-dd HH:mm:ss")
				var data={
					id:"1",
					s_time:$.fullCalendar.formatDate(event.start,"yyyy-MM-dd HH:mm:ss"),
					type:"update"
					};
				data=JSON.stringify(data);
				$.ajax({
					type:"POST",
					url:"/PCalendar",
					contentType: "application/json;charset=utf-8",
					data:data,
					success:function (result) {
						alert("修改时间成功")
					},
					error:function () {
						console.log("fail");
						revertFunc()
					}
				})
			}
		},


		events:events

	});
	
	//force to reajust size on page load because full calendar some time not get right size.
	$(window).load(function(){
		$('#calendar').fullCalendar('render');
	});
	
	//------------- Sparklines -------------//
	$('#usage-sparkline').sparkline([35,46,24,56,68, 35,46,24,56,68], {
		width: '180px',
		height: '30px',
		lineColor: colours.dark,
		fillColor: false,
		spotColor: false,
		minSpotColor: false,
		maxSpotColor: false,
		lineWidth: 2
	});

	$('#cpu-sparkline').sparkline([22,78,43,32,55, 67,83,35,44,56], {
		width: '180px',
		height: '30px',
		lineColor: colours.dark,
		fillColor: false,
		spotColor: false,
		minSpotColor: false,
		maxSpotColor: false,
		lineWidth: 2
	});

	$('#ram-sparkline').sparkline([12,24,32,22,15, 17,8,23,17,14], {
		width: '180px',
		height: '30px',
		lineColor: colours.dark,
		fillColor: false,
		spotColor: false,
		minSpotColor: false,
		maxSpotColor: false,
		lineWidth: 2
	});

    //------------- Init pie charts -------------//
    //pass the variables to pie chart init function
    //first is line width, size for pie, animated time , and colours object for theming.
	initPieChart(10,40, 1500, colours);


});

//Setup easy pie charts in sidebar
var initPieChart = function(lineWidth, size, animateTime, colours) {
	$(".pie-chart").easyPieChart({
        barColor: colours.dark,
        borderColor: colours.dark,
        trackColor: '#d9dde2',
        scaleColor: false,
        lineCap: 'butt',
        lineWidth: lineWidth,
        size: size,
        animate: animateTime
    });
}
function add0(m){return m<10?'0'+m:m }
function formate(time)
{
	var y = time.getFullYear();
	var m = time.getMonth()+1;
	var d = time.getDate();
	var h = time.getHours();
	var mm = time.getMinutes();
	var s = time.getSeconds();
	return y+'-'+add0(m)+'-'+add0(d)+' '+add0(h)+':'+add0(mm)+':'+add0(s);
}
Date.prototype.Format = function(fmt)
{ //author: meizz
	var o = {
		"M+" : this.getMonth()+1,                 //月份
		"d+" : this.getDate(),                    //日
		"h+" : this.getHours(),                   //小时
		"m+" : this.getMinutes(),                 //分
		"s+" : this.getSeconds(),                 //秒
		"q+" : Math.floor((this.getMonth()+3)/3), //季度
		"S"  : this.getMilliseconds()             //毫秒
	};
	if(/(y+)/.test(fmt))
		fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
	for(var k in o)
		if(new RegExp("("+ k +")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
	return fmt;
}
