package com.student.yazhoubang.controller;

import com.student.yazhoubang.dao.ChartDao;
import com.student.yazhoubang.damain.Chart;
import com.student.yazhoubang.utils.ChartWithDoctors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PatientTableController {
    @Autowired
    private ChartDao ChartDao;
    @RequestMapping("/PData-tables")
    public String PDataTables(Model model){
        List<String> CIdList=ChartDao.selectCIdById("111111111111111111");
        List<ChartWithDoctors>list=new ArrayList<>();
        for (int i=0;i<CIdList.size();i++){
            ChartWithDoctors chartWithDoctors=ChartDao.selectInformationByCId(CIdList.get(i));
            list.add(chartWithDoctors);
        }
        model.addAttribute("tableList",list);
        return "PData-tables";
    }

}
