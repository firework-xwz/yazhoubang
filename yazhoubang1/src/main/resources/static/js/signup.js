function signup() {   //注册模块函数
    // alert($("#role").val());
    if($("#re-password").val()!=$("#password").val()){
        alert("确认密码与原密码不符！");
        return;
    }
    var signupData=$("#signupForm").serializeArray();
    console.log(signupData);
    var s={};
    $.each(signupData,function (i,field) {
        s[field.name]=field.value;
    });
    console.log(s);
    var data= JSON.stringify(s);
    console.log(data);
    $.ajax({
        url:"/signup",
        type:"POST",
        contentType: "application/json;charset=utf-8",
        data: data,
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


