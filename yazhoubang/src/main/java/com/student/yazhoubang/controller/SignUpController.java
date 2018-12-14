package com.student.yazhoubang.controller;

import com.student.yazhoubang.damain.Doctor;
import com.student.yazhoubang.damain.Patient;
import com.student.yazhoubang.damain.UserList;
import com.student.yazhoubang.dao.UserListDao;
import com.student.yazhoubang.utils.User;
import com.student.yazhoubang.dao.DoctorDao;
import com.student.yazhoubang.dao.PatientDao;
import com.student.yazhoubang.utils.SignUpCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Controller
public class SignUpController {
    @Autowired
    private PatientDao patientDao;
    @Autowired
    private DoctorDao doctorDao;
    @Autowired
    private UserListDao userListDao;

    @RequestMapping("/signup")
    public String signup(){
        return "signup";
    }

    @PostMapping("/signup")
    @ResponseBody
    public SignUpCheck signup(@RequestBody User user,HttpSession httpSession){
        System.out.println(user.getRole());
        System.out.println("-----signup-POST-----");
        SignUpCheck signUpCheck=new SignUpCheck();
        if(user.getRole()==0){
            System.out.println("patient");
            Patient patient=new Patient();
            patient.setP_id(user.getId());
            patient.setName(user.getName());
            patient.setBirthday(user.getBirthday());
            patient.setSex(user.getSex());
            patient.setPhone_num(user.getPhone_num());
            patient.setPassword(user.getPassword());
            patient.setRole(user.getRole());
            String idCard_regex="^\\d{15}|^\\d{17}([0-9]|X|x)$";
            Pattern pattern=Pattern.compile(idCard_regex);
            Matcher matcher=pattern.matcher(patient.getP_id());
            if(patient.getP_id().equals("")){
                signUpCheck.setStatus(1);
                signUpCheck.setMsg("请输入您的身份证号");
            }
            else if(!matcher.matches()){
                signUpCheck.setStatus(1);
                signUpCheck.setMsg("请输入正确的身份证号码！");
            }
            else if(patient.getName().equals("")){
                signUpCheck.setStatus(2);
                signUpCheck.setMsg("请输入您的姓名");
            }
            else if(patient.getBirthday()==null){
                signUpCheck.setStatus(3);
                signUpCheck.setMsg("请输入您的出生日期");
            }
            else if(patient.getPhone_num().equals("")){
                signUpCheck.setStatus(4);
                signUpCheck.setMsg("请输入您的电话号码");
            }
            else if(patient.getPassword().equals("")){
                signUpCheck.setStatus(5);
                signUpCheck.setMsg("请输入您的密码");
            }
            else if(patientDao.selectPatientById(patient.getP_id())!=null){
                signUpCheck.setStatus(6);
                signUpCheck.setMsg("该身份证号已被注册");
                System.out.println(patientDao.selectPatientById(patient.getP_id()).getName());
            }
            else {
                signUpCheck.setStatus(0);
                signUpCheck.setMsg("您已注册，请登录！");
                UserList userList=new UserList(patient.getP_id(),patient.getPassword(),patient.getRole());
                System.out.println(patientDao.addPatient(patient));
                System.out.println(userListDao.addUserList(userList));
                httpSession.setMaxInactiveInterval(3600);
                httpSession.setAttribute("userId",patient.getP_id());
            }
            System.out.println(patient.getBirthday());
        }
        else if(user.getRole()==1){
            System.out.println("doctor");
            Doctor doctor=new Doctor();
            doctor.setD_id(user.getId());
            doctor.setName(user.getName());
            doctor.setBirthday(user.getBirthday());
            doctor.setSex(user.getSex());
            doctor.setPhone_num(user.getPhone_num());
            doctor.setPassword(user.getPassword());
            doctor.setRole(user.getRole());
            doctor.setStatus(0);
            String idCard_regex="^\\d{15}|^\\d{17}([0-9]|X|x)$";
            Pattern pattern=Pattern.compile(idCard_regex);
            Matcher matcher=pattern.matcher(doctor.getD_id());
            if(doctor.getD_id().equals("")){
                signUpCheck.setStatus(1);
                signUpCheck.setMsg("请输入您的身份证号");
            }
            else if(!matcher.matches()){
                signUpCheck.setStatus(1);
                signUpCheck.setMsg("请输入正确的身份证号码！");
            }
            else if(doctor.getName().equals("")){
                signUpCheck.setStatus(2);
                signUpCheck.setMsg("请输入您的姓名");
            }
            else if(doctor.getBirthday()==null){
                signUpCheck.setStatus(3);
                signUpCheck.setMsg("请输入您的出生日期");
            }
            else if(doctor.getPhone_num().equals("")){
                signUpCheck.setStatus(4);
                signUpCheck.setMsg("请输入您的电话号码");
            }
            else if(doctor.getPassword().equals("")){
                signUpCheck.setStatus(5);
                signUpCheck.setMsg("请输入您的密码");
            }
            else if(doctorDao.selectDoctorById(doctor.getD_id())!=null){
                signUpCheck.setStatus(6);
                signUpCheck.setMsg("该身份证号已被注册");
                System.out.println(doctorDao.selectDoctorById(doctor.getD_id()).getName());
            }
            else {
                signUpCheck.setStatus(0);
                signUpCheck.setMsg("您已注册，请登录！");
                UserList userList=new UserList(doctor.getD_id(),doctor.getPassword(),doctor.getRole());
                System.out.println(doctorDao.addDoctor(doctor));
                System.out.println(userListDao.addUserList(userList));
                httpSession.setMaxInactiveInterval(3600);
                httpSession.setAttribute("userId",doctor.getD_id());
            }
            System.out.println(doctor.getBirthday());
        }
        return signUpCheck;
    }

//    @PostMapping("/main")
//    public String main(){
//        System.out.println("----main-------");
//        return "main";
//    }

}
