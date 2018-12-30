package com.student.yazhoubang.controller;

import com.student.yazhoubang.dao.PatientChartDao;
import com.student.yazhoubang.damain.PatientChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class PatientTableController {
    @Autowired
    private PatientChartDao patientChartDao;
    @RequestMapping("/PData-tables")
    public String PDataTables(Model model){
        List<PatientChart> chart=patientChartDao.selectChartById("1600");
        model.addAttribute("tableList",chart);
        return "PData-tables";
    }

}
