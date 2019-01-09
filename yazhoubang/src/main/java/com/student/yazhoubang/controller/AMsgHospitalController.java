package com.student.yazhoubang.controller;

import com.student.yazhoubang.damain.Msg;
import com.student.yazhoubang.dao.HospitalDao;
import com.student.yazhoubang.damain.Hospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AMsgHospitalController {
    @Autowired
    private HospitalDao hospitalDao;

    @RequestMapping("/AMsgHospital")
    public String showHospitalList(Model model) {
        System.out.println("---Hospitallist---");
        List<Hospital> hospitalList = hospitalDao.getAll();
        model.addAttribute("hospitalList", hospitalList);
        return "AMsgHospital";
    }

    @RequestMapping("/AMsgHospital/addHospital")
    @ResponseBody
    public Msg addHospital(@RequestParam(value="h_id")String h_id, @RequestParam(value="position")String position, @RequestParam(value="phone_num")String phone_num, @RequestParam(value="hospital_name")String hospital_name){
        System.out.println(h_id);
        System.out.println("---addHospital---");
        Msg msg = new Msg();
        try{
            Hospital h = new Hospital();
            h.setH_id(h_id);
            h.setHospital_name(hospital_name);
            h.setPhone_num(phone_num);
            h.setPosition(position);
            hospitalDao.addHospital(h);
            msg.setMessage("SUCCESS!!!");
        }
        catch (Exception e){
            e.printStackTrace();
            msg.setMessage("FAIL!!!");
        }
        return msg;
    }
}
