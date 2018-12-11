package com.student.yazhoubang.controller;

import com.student.yazhoubang.utils.LoginCheck;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public LoginCheck login(@RequestParam("user_id")String user_id,@RequestParam("user_password")String user_password) {
        System.out.println(user_id);
        System.out.println(user_password);
        return new LoginCheck();
    }
}
