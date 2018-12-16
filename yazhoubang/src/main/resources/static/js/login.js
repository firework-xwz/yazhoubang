function login() {
    var data={
        "user_id":$("#user_id").val(),
        "user_password":$("#user_password").val()
    };
    console.log(data);
    $.ajax({
        url:"/login",
        type:"POST",
        data: data,
        dataType:"json",
        success:function (result) {
            alert(result.msg);
            if(result.status==0){
                window.location.href="./main"
            }
        },
        error:function () {
            console.log("fail");
        }
    })
}