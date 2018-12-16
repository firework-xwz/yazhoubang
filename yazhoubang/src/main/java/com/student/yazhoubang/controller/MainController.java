package com.student.yazhoubang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {


    @GetMapping("/main")
    public String main(Model model, HttpSession httpSession){
        System.out.println("----main------");
//        System.out.println("用户id："+id);
        model.addAttribute("id",httpSession.getAttribute("id"));
        model.addAttribute("role",httpSession.getAttribute("role"));
        return "main";
    }
}
