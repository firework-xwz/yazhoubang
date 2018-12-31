package com.student.yazhoubang.controller;

import com.student.yazhoubang.damain.Doctor;
import com.student.yazhoubang.damain.Hospital;
import com.student.yazhoubang.dao.DoctorDao;
import com.student.yazhoubang.dao.HospitalDao;
import com.student.yazhoubang.dao.WorkDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
        Map<String, Doctor> map = new HashMap<>();
        List<Doctor> doctorList = doctorDao.getAll();

        for(int i=0;i<doctorList.size();i++){
            String hos_name = hospitalDao.selectHospital(workDao.selectHByD(doctorList.get(i).getD_id())).getHospital_name();
            map.put(hos_name,doctorList.get(i));
        }

        model.addAttribute("doctorList",map);
        return "AMsgDoctor";
    }
}
