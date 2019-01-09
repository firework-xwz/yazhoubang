package com.student.yazhoubang.controller;

import com.student.yazhoubang.damain.Doctor;
import com.student.yazhoubang.damain.Hospital;
import com.student.yazhoubang.damain.Msg;
import com.student.yazhoubang.dao.DoctorDao;
import com.student.yazhoubang.dao.HospitalDao;
import com.student.yazhoubang.dao.WorkDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AMsgDoctorController {
    @Autowired
    private DoctorDao doctorDao;
    @Autowired
    private WorkDao workDao;
    @Autowired
    private HospitalDao hospitalDao;

    @RequestMapping("AMsgDoctor")
    public String showDoctorList(Model model){
        System.out.println("---doctorlist---");

        List<Map> ans = new ArrayList<>();
        List<Doctor> doctorList = doctorDao.getAllTrue();

        for(int i=0;i<doctorList.size();i++){
            Map map = new HashMap();
            String d_id = doctorList.get(i).getD_id();
            Hospital hos = hospitalDao.selectHospital(workDao.selectHByD(d_id));
            String hos_name = hos.getHospital_name();

            map.put("hos_name",hos_name);
            map.put("doc",doctorList.get(i));
            ans.add(map);
        }

        model.addAttribute("doctorList",ans);
        return "AMsgDoctor";
    }

    @RequestMapping("/AMsgDoctor/deleteDoctor")
    @ResponseBody
    public Msg deleteDocctor(@RequestParam(value="d_id")String d_id){
        System.out.println(d_id);
        System.out.println("---deleteDoctor---");
        Msg msg = new Msg();
        try{
            doctorDao.setDoctorFalse(d_id);
            msg.setMessage("SUCCESS!!!");
        }
        catch (Exception e){
            e.printStackTrace();
            msg.setMessage("FAIL!!!");
        }
        return msg;
    }
}
