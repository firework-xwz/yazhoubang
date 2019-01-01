$(document).ready(function () {
    var postdata={"where":2,
    "c_id":-1};
    $.ajax(
        {
            type:"POST",
            url:"/PDetailTable",
            data:postdata,
            dataType: "json",
            async:false,
            success:function (result) {
                //这句要写成一行
                if (result!=null&&result.length>0) {
                    var PI_L = result.PI_L;
                    for (var i = 0; i < PI_L.length; i++) {
                        if (i < 48) {
                            var name = "u_lpi" + Math.floor(i / 3 + 1);
                            if (i % 3 == 0)
                                name = name + "_a";
                            else if (i % 3 == 1)
                                name = name + "_b";
                            else
                                name = name + "_c";
                        } else {
                            var name = "d_lpi" + Math.floor((i - 48) / 3 + 1);
                            if (i % 3 == 0)
                                name = name + "_a";
                            else if (i % 3 == 1)
                                name = name + "_b";
                            else
                                name = name + "_c";
                        }
                        document.getElementById(name).value = PI_L[i];
                    }

                    var CEJ_B = result.CEJ_B;
                    for (var i = 0; i < CEJ_B.length; i++) {
                        if (i < 48) {
                            var name = "u_bcej" + Math.floor(i / 3 + 1);
                            if (i % 3 == 0)
                                name = name + "_a";
                            else if (i % 3 == 1)
                                name = name + "_b";
                            else
                                name = name + "_c";
                        } else {
                            var name = "d_bcej" + Math.floor((i - 48) / 3 + 1);
                            if (i % 3 == 0)
                                name = name + "_a";
                            else if (i % 3 == 1)
                                name = name + "_b";
                            else
                                name = name + "_c";
                        }
                        document.getElementById(name).value = CEJ_B[i];
                    }

                    var CEJ_L = result.CEJ_L;
                    for (var i = 0; i < CEJ_L.length; i++) {
                        if (i < 48) {
                            var name = "u_lcej" + Math.floor(i / 3 + 1);
                            if (i % 3 == 0)
                                name = name + "_a";
                            else if (i % 3 == 1)
                                name = name + "_b";
                            else
                                name = name + "_c";
                        } else {
                            var name = "d_lcej" + Math.floor((i - 48) / 3 + 1);
                            if (i % 3 == 0)
                                name = name + "_a";
                            else if (i % 3 == 1)
                                name = name + "_b";
                            else
                                name = name + "_c";
                        }
                        document.getElementById(name).value = CEJ_L[i];
                    }

                    var BI_B = result.BI_B;
                    for (var i = 0; i < BI_B.length; i++) {
                        if (i < 48) {
                            var name = "u_bbi" + Math.floor(i / 3 + 1);
                            if (i % 3 == 0)
                                name = name + "_a";
                            else if (i % 3 == 1)
                                name = name + "_b";
                            else
                                name = name + "_c";
                        } else {
                            var name = "d_bbi" + Math.floor((i - 48) / 3 + 1);
                            if (i % 3 == 0)
                                name = name + "_a";
                            else if (i % 3 == 1)
                                name = name + "_b";
                            else
                                name = name + "_c";
                        }
                        document.getElementById(name).value = BI_B[i];
                    }

                    var BI_L = result.BI_L;
                    for (var i = 0; i < BI_L.length; i++) {
                        if (i < 48) {
                            var name = "u_lbi" + Math.floor(i / 3 + 1);
                            if (i % 3 == 0)
                                name = name + "_a";
                            else if (i % 3 == 1)
                                name = name + "_b";
                            else
                                name = name + "_c";
                        } else {
                            var name = "d_lbi" + Math.floor((i - 48) / 3 + 1);
                            if (i % 3 == 0)
                                name = name + "_a";
                            else if (i % 3 == 1)
                                name = name + "_b";
                            else
                                name = name + "_c";
                        }
                        document.getElementById(name).value = BI_L[i];
                    }

                    var PD_B = result.PD_B;
                    for (var i = 0; i < PD_B.length; i++) {
                        if (i < 48) {
                            var name = "u_bpd" + Math.floor(i / 3 + 1);
                            if (i % 3 == 0)
                                name = name + "_a";
                            else if (i % 3 == 1)
                                name = name + "_b";
                            else
                                name = name + "_c";
                        } else {
                            var name = "d_bpd" + Math.floor((i - 48) / 3 + 1);
                            if (i % 3 == 0)
                                name = name + "_a";
                            else if (i % 3 == 1)
                                name = name + "_b";
                            else
                                name = name + "_c";
                        }
                        document.getElementById(name).value = PD_B[i];
                    }

                    var PD_L = result.PD_L;
                    for (var i = 0; i < PD_L.length; i++) {
                        if (i < 48) {
                            var name = "u_lpd" + Math.floor(i / 3 + 1);
                            if (i % 3 == 0)
                                name = name + "_a";
                            else if (i % 3 == 1)
                                name = name + "_b";
                            else
                                name = name + "_c";
                        } else {
                            var name = "d_lpd" + Math.floor((i - 48) / 3 + 1);
                            if (i % 3 == 0)
                                name = name + "_a";
                            else if (i % 3 == 1)
                                name = name + "_b";
                            else
                                name = name + "_c";
                        }
                        document.getElementById(name).value = PD_L[i];
                    }
                }


            },
            error:function () {
                console.log("fail");
            }
        }
    )
})