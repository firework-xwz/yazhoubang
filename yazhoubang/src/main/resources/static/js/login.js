function login() {
    var loginData=$("#loginForm").serializeArray();
    console.log(loginData);
    var s={};
    $.each(loginData,function (i,field) {
        s[field.name]=field.value;
    });
    console.log(s);
    var data=s.stringify();
    console.log(data);
    $.ajax({
        url:"/login",
        type:"POST",
        contentType: "application/json;charset=utf-8",
        data: data,
        success:function (result) {
            console.log("success");
            alert(result);
        },
        error:function () {
            console.log("fail");
        }
    })
}