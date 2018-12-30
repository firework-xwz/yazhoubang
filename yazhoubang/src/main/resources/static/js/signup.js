function signup() {   //注册模块函数
    // alert($("#role").val());
    if($("#re-password").val()!=$("#password").val()){
        alert("确认密码与原密码不符！");
        return;
    }
    var data={
        "id":$("#id").val(),
        "name":$("#name").val(),
        "birthday":$("#birthday").val(),
        "sex":$("#sex").val(),
        "phone_num":$("#phone_num").val(),
        "password":$("#password").val(),
        "role":$("#role").val()
    };
    console.log(data);
    $.ajax({
        url:"/signup",
        type:"POST",
        data: data,
        dataType:"json",
        success:function(result){
            alert(result.msg);
            $("#errorInfo").val(result.msg).show();
            if(result.status==0){
                // result=result.serializeArray();
                console.log(result);
                window.location.href="/login";
            }
        },
        error:function () {
            console.log("fail");
        }
    })
}


