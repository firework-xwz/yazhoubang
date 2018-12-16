$(document).ready(function () {
    document.getElementById("line-chart-dots").style.display="none";
    document.getElementById("autoupdate-chart").style.display="inline-block";
    document.getElementById("ordered-bars-chart").style.display="none";
    $('.dropdown-menu a').on('click',function(e){
        var id=e.target.id;
        if(id=="line_chart"){
            document.getElementById("line-chart-dots").style.display="inline-block";
            document.getElementById("autoupdate-chart").style.display="none";
            document.getElementById("ordered-bars-chart").style.display="none";
        }
        else if(id=="O_Bars_chart"){
            document.getElementById("ordered-bars-chart").style.display="inline-block";
            document.getElementById("autoupdate-chart").style.display="none";
            document.getElementById("line-chart-dots").style.display="none";
        }
        else if(id=="auto_updating_chart"){
            document.getElementById("ordered-bars-chart").style.display="none";
            document.getElementById("autoupdate-chart").style.display="inline-block";
            document.getElementById("line-chart-dots").style.display="none";
        }
    })
})