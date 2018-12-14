package com.student.yazhoubang.controller;

import com.student.yazhoubang.dao.UserListDao;
import com.student.yazhoubang.utils.LoginCheck;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    UserListDao userListDao;

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public LoginCheck login(@RequestParam(value = "user_id") String user_id,@RequestParam(value = "user_password") String user_password,HttpSession httpSession) {

        LoginCheck loginCheck=new LoginCheck();
        if(user_id.equals("")){
            loginCheck.setStatus(1);
            loginCheck.setMsg("请输入您的身份证号！");
        }
        else if(user_password.equals("")){
            loginCheck.setStatus(2);
            loginCheck.setMsg("请输入您的密码！");
        }
        else if(userListDao.selectUserById(user_id)==null){
            loginCheck.setStatus(3);
            loginCheck.setMsg("该用户名不存在，请先确认用户名或注册！");
        }
        else if(!userListDao.selectUserById(user_id).getUser_password().equals(user_password)){
            loginCheck.setStatus(4);
            loginCheck.setMsg("您输入的密码不正确！");
        }
        else{
            loginCheck.setStatus(0);
            loginCheck.setMsg("登录成功！");
            httpSession.setMaxInactiveInterval(3600);
            httpSession.setAttribute("id",user_id);
            httpSession.setAttribute("role",userListDao.selectUserById(user_id).getUser_role());
        }
        System.out.println(user_id);
        System.out.println(user_password);
        return loginCheck;
    }


//    @RequestMapping("/main")
//    public String main(){
//        return "main";
//    }
}
