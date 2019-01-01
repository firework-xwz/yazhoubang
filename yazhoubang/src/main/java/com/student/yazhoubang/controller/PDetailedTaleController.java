package com.student.yazhoubang.controller;

import com.student.yazhoubang.damain.Patient;
import com.student.yazhoubang.damain.PatientChart;
import com.student.yazhoubang.dao.PatientChartDao;
import com.student.yazhoubang.dao.PatientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PDetailedTaleController {
    private String c_id;
    @Autowired
    PatientDao patientDao;
    @Autowired
    PatientChartDao patientChartDao;

    @RequestMapping("/PDetailTable")
    public String PDetailTable(Model model,HttpSession httpSession){
        PatientChart patientChart=patientChartDao.selectChartByCId(c_id);
        Patient patient=patientDao.selectPatientById((String)httpSession.getAttribute("p_id"));
        if(patientChart!=null&&patient!=null){
        //此处填入病人个人信息
       model.addAttribute("name",patient.getName());
        if (patient.getSex()==0)
            model.addAttribute("sex","男");
        else
            model.addAttribute("sex","女");
        model.addAttribute("years",18);
        model.addAttribute("time",patientChart.getTime());
        //此处填入FI所有数据
        String[] FI=patientChart.getFI().split("\\|");
        for(int i=0;i<FI.length;i++){
            if (i<18) {
                String FI_name = "u_fi_" +i;
                model.addAttribute(FI_name,FI[i]);
            }
            else {
                String FI_name="d_fi_"+(i-18);
                model.addAttribute(FI_name,FI[i]);
            }
        }
        //此处填入implant的上部分数据
        String[] Implant=patientChart.getImplant().split("\\|");
        for(int i=0;i<Implant.length;i++){
            String IB_name="ib"+i;
            model.addAttribute(IB_name,Implant[i]);
        }
        //此处填入mobility
        String[] mobility=patientChart.getMobility().split("\\|");
        model.addAttribute("arrays",mobility);
        for(int i=0;i<mobility.length;i++){
            String M_name="m"+i;
            model.addAttribute(M_name,mobility[i]);
        }
        //此处填入GI数据
        String[] GI=patientChart.getGI().split("\\|");
        for (int i=0;i<GI.length;i++){
            String GI_name="gi"+i;
            model.addAttribute(GI_name,GI[i]);
        }
        //此处填入PI_B
        String[] PI_B=patientChart.getPI_B().split("\\|");
        for (int i=0;i<PI_B.length;i++){
            String PI_Bname="bPI"+i;
            model.addAttribute(PI_Bname,PI_B[i]);
        }}
        return "PDetailTable";}

    @PostMapping("/PDetailTable")
    @ResponseBody
    public Map CopyData(@RequestParam("where")String where, @RequestParam("c_id")String c_id){
        Map map=new HashMap();
        if (where.equals("1")) {
            this.c_id = c_id;
        }
        else {
            PatientChart patientChart=patientChartDao.selectChartByCId(this.c_id);
            if (patientChart!=null){
            String[] PI_L=patientChart.getPI_L().split("\\|");
            map.put("PI_L",PI_L);
            String[]CEJ_B=patientChart.getCEJ_B().split("\\|");
            map.put("CEJ_B",CEJ_B);
            String[]CEJ_L=patientChart.getCEJ_L().split("\\|");
            map.put("CEJ_L",CEJ_L);
            String[] BI_B=patientChart.getBI_B().split("\\|");
            map.put("BI_B",BI_B);
            String[]BI_L=patientChart.getBI_L().split("\\|");
            map.put("BI_L",BI_L);
            String[]PD_B=patientChart.getPD_B().split("\\|");
            map.put("PD_B",PD_B);
            String[]PD_L=patientChart.getPD_L().split("\\|");
            map.put("PD_L",PD_L);}
        }
        return map;
    }
}
