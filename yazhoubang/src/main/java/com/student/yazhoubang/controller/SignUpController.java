package com.student.yazhoubang.controller;

import com.student.yazhoubang.damain.Doctor;
import com.student.yazhoubang.damain.Hospital;
import com.student.yazhoubang.damain.Patient;
import com.student.yazhoubang.damain.UserList;
import com.student.yazhoubang.dao.*;
import com.student.yazhoubang.utils.SignUpCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
    @Autowired
    private HospitalDao hospitalDao;
    @Autowired
    private WorkDao workDao;

    @RequestMapping("/signup")
    public String signup(Model model) {
        List<Hospital> hospitalList=hospitalDao.getAll();
        model.addAttribute("hospitalList",hospitalList);
        return "signup";
    }

    @PostMapping("/signup")
    @ResponseBody
    public SignUpCheck signup(@RequestParam(value = "id") String id, @RequestParam(value = "name") String name, @RequestParam(value = "sex") int sex,
                              @RequestParam(value = "phone_num") String phone_num, @RequestParam(value = "password") String password, @RequestParam(value = "role") int role, @RequestParam(value = "birthday") String date,@RequestParam(value = "hostipal")String hostipal_id, HttpSession httpSession) {
        System.out.println("-----signup-POST-----");
        System.out.println(hostipal_id);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = null;
        try {
            birthday = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        System.out.println(role);
        SignUpCheck signUpCheck = new SignUpCheck();
        if (role == 0) {
            System.out.println("patient");
            Patient patient = new Patient();
            patient.setP_id(id);
            patient.setName(name);
            patient.setBirthday(birthday);
            patient.setSex(sex);
            patient.setPhone_num(phone_num);
            patient.setPassword(password);
            patient.setRole(role);
            String idCard_regex = "^\\d{15}|^\\d{17}([0-9]|X|x)$";
            Pattern pattern = Pattern.compile(idCard_regex);
            Matcher matcher = pattern.matcher(patient.getP_id());
            if (patient.getP_id().equals("")) {
                signUpCheck.setStatus(1);
                signUpCheck.setMsg("请输入您的身份证号");
            } else if (!matcher.matches()) {
                signUpCheck.setStatus(1);
                signUpCheck.setMsg("请输入正确的身份证号码！");
            } else if (patient.getName().equals("")) {
                signUpCheck.setStatus(2);
                signUpCheck.setMsg("请输入您的姓名");
            } else if (patient.getBirthday() == null) {
                signUpCheck.setStatus(3);
                signUpCheck.setMsg("请输入您的出生日期");
            } else if (patient.getPhone_num().equals("")) {
                signUpCheck.setStatus(4);
                signUpCheck.setMsg("请输入您的电话号码");
            } else if (patient.getPassword().equals("")) {
                signUpCheck.setStatus(5);
                signUpCheck.setMsg("请输入您的密码");
            } else if (patientDao.selectPatientById(patient.getP_id()) != null) {
                signUpCheck.setStatus(6);
                signUpCheck.setMsg("该身份证号已被注册");
                System.out.println(patientDao.selectPatientById(patient.getP_id()).getName());
            } else {
                signUpCheck.setStatus(0);
                signUpCheck.setMsg("您已注册，请登录！");
                UserList userList = new UserList(patient.getP_id(), patient.getPassword(), patient.getRole());
                System.out.println(patientDao.addPatient(patient));
                System.out.println(userListDao.addUserList(userList));
                httpSession.setMaxInactiveInterval(3600);
                httpSession.setAttribute("userId", patient.getP_id());
            }
            System.out.println(patient.getBirthday());
        } else if (role == 1) {
//            System.out.println("doctor");
            Doctor doctor = new Doctor();
            doctor.setD_id(id);
            doctor.setName(name);
            doctor.setBirthday(birthday);
            doctor.setSex(sex);
            doctor.setPhone_num(phone_num);
            doctor.setPassword(password);
            doctor.setRole(role);
            doctor.setStatus(0);
            String idCard_regex = "^\\d{15}|^\\d{17}([0-9]|X|x)$";
            Pattern pattern = Pattern.compile(idCard_regex);
            Matcher matcher = pattern.matcher(doctor.getD_id());
            if (doctor.getD_id().equals("")) {
                signUpCheck.setStatus(1);
                signUpCheck.setMsg("请输入您的身份证号");
            } else if (!matcher.matches()) {
                signUpCheck.setStatus(1);
                signUpCheck.setMsg("请输入正确的身份证号码！");
            } else if (doctor.getName().equals("")) {
                signUpCheck.setStatus(2);
                signUpCheck.setMsg("请输入您的姓名");
            } else if (doctor.getBirthday() == null) {
                signUpCheck.setStatus(3);
                signUpCheck.setMsg("请输入您的出生日期");
            } else if (doctor.getPhone_num().equals("")) {
                signUpCheck.setStatus(4);
                signUpCheck.setMsg("请输入您的电话号码");
            } else if (doctor.getPassword().equals("")) {
                signUpCheck.setStatus(5);
                signUpCheck.setMsg("请输入您的密码");
            } else if (doctorDao.selectDoctorById(doctor.getD_id()) != null) {
                signUpCheck.setStatus(6);
                signUpCheck.setMsg("该身份证号已被注册");
                System.out.println(doctorDao.selectDoctorById(doctor.getD_id()).getName());
            } else {
                signUpCheck.setStatus(0);
                signUpCheck.setMsg("您已注册，请登录！");
                UserList userList = new UserList(doctor.getD_id(), doctor.getPassword(), doctor.getRole());
//                System.out.println(doctor.getD_id());
                try {
                    System.out.println(doctorDao.addDoctor(doctor));
                    System.out.println(userListDao.addUserList(userList));
                    System.out.println(workDao.addWork(doctor.getD_id(), hostipal_id));
                }catch (Exception e){
                    e.printStackTrace();
                }
                httpSession.setMaxInactiveInterval(3600);
                httpSession.setAttribute("userId", doctor.getD_id());
            }
//            System.out.println(doctor.getBirthday());
        }
        return signUpCheck;
    }

    @PostMapping("/main")
    public String main() {
        System.out.println("----main-------");
        return "main";
    }
}

