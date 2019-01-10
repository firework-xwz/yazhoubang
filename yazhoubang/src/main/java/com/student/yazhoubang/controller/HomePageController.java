package com.student.yazhoubang.controller;

import com.student.yazhoubang.damain.Doctor;
import com.student.yazhoubang.damain.Patient;
import com.student.yazhoubang.damain.UserList;
import com.student.yazhoubang.dao.DoctorDao;
import com.student.yazhoubang.dao.PatientDao;
import com.student.yazhoubang.dao.UserListDao;
import com.student.yazhoubang.utils.LoginCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class HomePageController {
    @Autowired
    UserListDao userListDao;
    @Autowired
    PatientDao patientDao;
    @Autowired
    DoctorDao doctorDao;

    @RequestMapping("/home")
    public String home(HttpSession httpSession, Model model){
        if(httpSession.getAttribute("id")==null){
            System.out.println("Unlogined");
            model.addAttribute("name","登录");
            model.addAttribute("url","/login");
        }
        else {
            System.out.println("logined");
            String id= (String)httpSession.getAttribute("id");
            UserList userList=userListDao.selectUserById(id);
            if(userList.getUser_role()==0){
                Patient patient=patientDao.selectPatientById(id);
                model.addAttribute("name",patient.getName());
                model.addAttribute("url","/PatientHome");
            }
            else if(userList.getUser_role()==1){
                Doctor doctor=doctorDao.selectDoctorById(id);
                model.addAttribute("name",doctor.getName());
                model.addAttribute("url","/DChart");
            }
            else{
                model.addAttribute("name","管理员");
                model.addAttribute("url","/AIndex");
            }
        }
        return "Homepage";
    }



    @RequestMapping("/logout")
    public String logout(Model model,HttpSession httpSession){
        httpSession.setAttribute("id",null);
        model.addAttribute("name","登录");
        model.addAttribute("url","/login");
        return "HomePage";
    }
}
