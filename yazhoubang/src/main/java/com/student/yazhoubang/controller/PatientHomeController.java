package com.student.yazhoubang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class PatientHomeController {
    @RequestMapping("/PatientHome")
    public String PatientHome(HttpSession httpSession){
        httpSession.setAttribute("p_id","111111111111111111");
        return "PatientHome";}
}
