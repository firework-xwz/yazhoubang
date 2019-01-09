package com.student.yazhoubang.controller;

import com.student.yazhoubang.dao.ChartDao;
import com.student.yazhoubang.dao.DoctorDao;
import com.student.yazhoubang.dao.PatientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class WChartController {
    @Autowired
    private ChartDao chartDao;
    @Autowired
    private PatientDao patientDao;
    @Autowired
    private DoctorDao doctorDao;

    @RequestMapping("/WChart/{p_id}")
    public String WChart(@PathVariable(value = "p_id")String p_id, Model model, HttpSession httpSession){
        String d_id = (String)httpSession.getAttribute("id");
        String d_name = doctorDao.selectDoctorById(d_id).getName();
        String p_name = patientDao.selectPatientById(p_id).getName();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dt = new Date();
        String time = df.format(dt);

        model.addAttribute("d_name",d_name);
        model.addAttribute("p_name",p_name);
        model.addAttribute("time",time);

        return "WChart";
    }
}
