package com.student.yazhoubang.controller;

import com.student.yazhoubang.damain.Doctor;
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
public class ACheckController {
    @Autowired
    private DoctorDao doctorDao;
    @Autowired
    private WorkDao workDao;
    @Autowired
    private HospitalDao hospitalDao;

    @RequestMapping("/ACheck")
    public String ACheck(Model model){
        System.out.println("---checklist---");
        Map<String, Doctor> map = new HashMap<>();
        List<Doctor> doctorList = doctorDao.getAllForCheck();

        for(int i=0;i<doctorList.size();i++){
            String hos_name = hospitalDao.selectHospital(workDao.selectHByD(doctorList.get(i).getD_id())).getHospital_name();
            map.put(hos_name,doctorList.get(i));
        }

        model.addAttribute("doctorCheckList",map);
        return "ACheck";
    }
}
