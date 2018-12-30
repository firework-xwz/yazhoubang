$(document).ready(function () {
    document.getElementById("zx").style.display="inline-block";
    document.getElementById("ld").style.display="none";
    document.getElementById("order").style.display="none";
    var index=document.getElementById("type").selectedIndex;
    var name=document.getElementById("type").options[index].text;
    linechart(name);
    $("#type").change(function() {
        var type=$("#type").val();
        if(type=="maf"){
            var index=document.getElementById("type").selectedIndex;
            var name=document.getElementById("type").options[index].text;
            var index=document.getElementById("chart-type").selectedIndex;
            var chart=document.getElementById("chart-type").options[index].text;
            document.getElementById("zx").style.display="inline-block";
            document.getElementById("ld").style.display="none";
            document.getElementById("order").style.display="none";
                linechart(name);
        }
        else if(type=="im"){
            var index=document.getElementById("type").selectedIndex;
            var name=document.getElementById("type").options[index].text;
            var index=document.getElementById("chart-type").selectedIndex;
            var chart=document.getElementById("chart-type").options[index].text;
            document.getElementById("zx").style.display="inline-block";
            document.getElementById("ld").style.display="none";
            document.getElementById("order").style.display="none";
                linechart(name);
        }
        else if(type=="GBP"){
            var index=document.getElementById("type").selectedIndex;
            var name=document.getElementById("type").options[index].text;
            var index=document.getElementById("chart-type").selectedIndex;
            var chart=document.getElementById("chart-type").options[index].text;
            document.getElementById("zx").style.display="inline-block";
            document.getElementById("ld").style.display="inline-block";
            document.getElementById("order").style.display="inline-block";
            if(chart=="折线图"){
                linechart(name);
            }
            else if(chart=="雷达图"){
                radarchart(name);
            }
            else if(chart=="柱状图"){
                orderedchart(name);
            }
        }
        else if(type=="PaC"){
            var index=document.getElementById("type").selectedIndex;
            var name=document.getElementById("type").options[index].text;
            var index=document.getElementById("chart-type").selectedIndex;
            var chart=document.getElementById("chart-type").options[index].text;
            document.getElementById("zx").style.display="inline-block";
            document.getElementById("ld").style.display="inline-block";
            document.getElementById("order").style.display="inline-block";
            if(chart=="折线图"){
                linechart(name);
            }
            else if(chart=="雷达图"){
                radarchart(name);
            }
            else if(chart=="柱状图"){
                orderedchart(name);
            }
        }
    })
    $("#chart-type").change(function () {
        var chart=$("#chart-type").val();
    if(chart=="zx"){
            var index=document.getElementById("type").selectedIndex;
            var name=document.getElementById("type").options[index].text;
            linechart(name);
        }
        else if(chart=="ld"){
            var index=document.getElementById("type").selectedIndex;
            var name=document.getElementById("type").options[index].text;
            radarchart(name);
        }
        else if(chart=="order"){
            var index=document.getElementById("type").selectedIndex;
            var name=document.getElementById("type").options[index].text;
            orderedchart(name);
        }
    })
})

var dom = document.getElementById("chart");
var myChart = echarts.init(dom);
/*此处定义折线图*/
function linechart(name) {

    var app = {};
    option = null;
    option = {
        grid:{
            top:"50px",
            left:"50px",
            right:"15px",
            bottom:"50px",
            containLabel: true
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:[]
        },
        toolbox: {
            feature: {
                saveAsImage: {}
            }
        },
        xAxis: {
            data: []
        },
        yAxis: {
            type: 'value'
        },
        series: []
    };
    ;
    myChart.showLoading();
   fetchLinedata(myChart,name);
    $('#panel').resize(function() {
        //重置容器高宽
        myChart.resize();
    });
}
function fetchLinedata(myChart,name){
    var postdata={"name":name};
    var optionJson=[];
    $.ajax(
        {
            type:"POST",
            url:"/PCharts",
            data:postdata,
            async: false,
            success:function (result) {
                optionJson=result;
            },
            error:function () {
                console.log("fail");
            }
        }
    )
    var series=[];
        for(var j=0;j<optionJson[0].name.length;j++) {
            var data=[];
            for(var k=0;k<optionJson.length;k++){
                data.push(optionJson[k].data[j])
            }
            var item = {
                name: optionJson[0].name[j],
                type: 'line',
                data: data
            }
        series.push(item);
    }
    var time=[];
    for(var i=0;i<optionJson.length;i++){
        time.push(optionJson[i].time);
    }
    var NAME=[];
    for(var i=0;i<optionJson[0].name.length;i++){
        NAME.push(optionJson[0].name[i]);
    }
    myChart.hideLoading();
    myChart.setOption(
        {
            grid:{
                top:"50px",
                left:"50px",
                right:"15px",
                bottom:"50px",
                containLabel: true
            },
            tooltip: {
                trigger: 'axis'
            },
            legend:{
              data:NAME
            },
            toolbox: {
                feature: {
                    saveAsImage: {}
                }
            },
            xAxis:{
                type: 'category',
                data:time,
            },
            yAxis: {
                type: 'value'
            },
            series:series
        },true
    )
    $('#panel').resize(function() {
        //重置容器高宽
        myChart.resize();
    });
}
/*此处定义柱状图*/
function orderedchart(name){

    //var dom=document.getElementById("chart");
    //var myChart = echarts.init(dom);
    var app = {};
    option = null;
    option = {
        grid:{
            top:"50px",
            left:"50px",
            right:"15px",
            bottom:"50px"
        },
        legend: {},
        tooltip: {},
        dataset: {
            source: []
        },
        xAxis: {type: 'category'},
        yAxis: {},
        // Declare several bar series, each will be mapped
        // to a column of dataset.source by default.
        series: []
    };
    ;
    myChart.showLoading();
    fetchorderedchart(myChart,name);
    $('#panel').resize(function() {
        //重置容器高宽
        myChart.resize();
    });
}
function fetchorderedchart(myChart,name){
    var postdata={"name":name};
    var optionJson=[];
    $.ajax(
        {
            type:"POST",
            url:"/PCharts",
            data:postdata,
            async: false,
            success:function (result) {
                optionJson=result;
            },
            error:function () {
                console.log("fail");
            }
        }
    )
    var series=[];
    var source=[];
    var name=['name'];
    for(var i=0;i<optionJson[0].name.length;i++){
        var item={type: 'bar'};
        name.push(optionJson[0].name[i]);
        series.push(item);
    }
    source.push(name);

    for(var i=0;i<optionJson.length;i++){
        var item=[];
       item.push(optionJson[i].time);
          for (var k=0;k<optionJson[i].data.length;k++){
              item.push(optionJson[i].data[k]);
          }
        source.push(item);
    }
    myChart.hideLoading();
    myChart.setOption({
        grid:{
            top:"50px",
            left:"50px",
            right:"15px",
            bottom:"50px"
        },
        legend: {},
        tooltip: {},
        dataset:{
            source:source
    },
        xAxis: {type: 'category'},
        yAxis: {},
        series:series
    },true)
    $('#panel').resize(function() {
        //重置容器高宽
        myChart.resize();
    });
}
/*此处定义雷达图*/
function  radarchart(name) {
    //var dom = document.getElementById("chart");
    //var myChart = echarts.init(dom);
    var app = {};
    option = null;

    option = {
        grid:{
            top:"50px",
            left:"50px",
            right:"15px",
            bottom:"50px"
        },
        tooltip: {},
        legend: {
            data: ['牙周帮']
        },
        radar: {
            // shape: 'circle',
            name: {
                textStyle: {
                    color: '#fff',
                    backgroundColor: '#999',
                    borderRadius: 3,
                    padding: [3, 5]
                }
            },
            indicator: []
        },
        series: [{
            name: '预算 vs 开销（Budget vs spending）',
            type: 'radar',
            // areaStyle: {normal: {}},
            data : []
        }]
    };;
    myChart.showLoading();
    fetchradardata(myChart,name);
    $('#panel').resize(function() {
        //重置容器高宽
        myChart.resize();
    });
}
function fetchradardata(myChart,name) {
    var postdata={"name":name};
    var optionJson=[];
    $.ajax(
        {
            type:"POST",
            url:"/PCharts",
            data:postdata,
            async: false,
            success:function (result) {
                optionJson=result;
            },
            error:function () {
                console.log("fail");
            }
        }
    )
    var indicator;
    if(name=="GI,BI and PI"){
        indicator=[
            { name: 'GI',min: 0, max: 3},
            { name: 'BI_B',min: 0, max: 5},
            { name: 'BI_L',min: 0, max: 5},
            { name: 'PI_B',min:0, max: 3},
            { name: 'PI_L',min: 0, max: 3}
        ];
    }
    else{
        indicator=[
            { name: 'PD_B',min: -1000, max: 1000},
            { name: 'PD_L',min: -1000, max: 1000},
            { name: 'CEJ_B',min: -1000, max: 1000},
            { name: 'CEJ_L',min: -1000, max: 1000}
        ]
    }
    var data=[];
for(var i=0;i<optionJson.length;i++){
    var value=[];
    for(var k=0;k<optionJson[i].data.length;k++){
        value.push(optionJson[i].data[k]);
    }
    var item={value:value};
    data.push(item);
}
myChart.hideLoading();
myChart.setOption({
        tooltip: {},
        legend: {
            data: ['牙周帮']
        },
        // shape: 'circle',
    radar: {
        name: {
            textStyle: {
                color: '#fff',
                backgroundColor: '#999',
                borderRadius: 3,
                padding: [3, 5]
            }
        },
        indicator: indicator
    },
    series: [{
        name: '牙周帮',
        type: 'radar',
        // areaStyle: {normal: {}},
        data : data
    }]
},true)
    $('#panel').resize(function() {
        //重置容器高宽
        myChart.resize();
    });
}