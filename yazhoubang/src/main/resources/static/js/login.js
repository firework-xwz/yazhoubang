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
                if(result.msg=="admin"){
                    window.location.href="/AIndex";
                }else if(result.msg=="doctor") {
                    window.location.href = "/DChart";
                }
                else if(result.msg=="patient") {
                    window.location.href = "/PatientHome"
                }
            }
        },
        error:function () {
            console.log("fail");
        }
    })
}