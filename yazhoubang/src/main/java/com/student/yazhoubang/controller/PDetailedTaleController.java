package com.student.yazhoubang.controller;

import com.student.yazhoubang.damain.Doctor;
import com.student.yazhoubang.damain.Patient;
import com.student.yazhoubang.damain.Chart;
import com.student.yazhoubang.dao.*;
import com.student.yazhoubang.utils.GetYearAge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PDetailedTaleController {
    @Autowired
    PatientDao patientDao;
    @Autowired
    ChartDao patientChartDao;
    @Autowired
    ReadDao readDao;
    @Autowired
    WriteDao writeDao;
    @Autowired
    DoctorDao doctorDao;

    @RequestMapping(value = "/PDetailTable/{c_id}")
    public String PDetailTable(@PathVariable String c_id, Model model){
        System.out.println("---Chart---");
        Chart patientChart=patientChartDao.selectChartByCId(c_id);
        String p_id=readDao.selectP_idByC_id(c_id);
        Patient patient=patientDao.selectPatientById(p_id);
        String d_id=writeDao.selectP_idByC_id(c_id);
        Doctor doctor=doctorDao.selectDoctorById(d_id);
        String d_name=doctor.getName();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(patient.getBirthday()));
        int age=GetYearAge.getYearAge(sdf.format(patient.getBirthday()));
        model.addAttribute("years",age);
        model.addAttribute("d_name",d_name);
        if(patientChart!=null&&patient!=null){
        //此处填入病人个人信息
           model.addAttribute("name",patient.getName());
          if (patient.getSex()==0)
            model.addAttribute("sex","男");
          else
            model.addAttribute("sex","女");

          model.addAttribute("time",patientChart.getTime());
        //此处填入FI所有数据
        String[] FI=patientChart.getFI().split("\\|");
        for(int i=0;i<FI.length;i++){
            String FI_name="FI"+i;
            model.addAttribute(FI_name,FI[i]);
        }
        //此处填入implant的上部分数据
        String[] IB=patientChart.getImplant().split("\\|");
        for(int i=0;i<IB.length;i++){
            String IB_name="IB"+i;
            model.addAttribute(IB_name,IB[i]);
        }
        //此处填入mobility
        String[] M=patientChart.getMobility().split("\\|");
        for(int i=0;i<M.length;i++){
            String M_name="M"+i;
            model.addAttribute(M_name,M[i]);
        }
        //此处填入GI数据
        String[] GI=patientChart.getGI().split("\\|");
        for (int i=0;i<GI.length;i++){
            String GI_name="GI"+i;
            model.addAttribute(GI_name,GI[i]);
        }
        //此处填入PI_B
        String[] bPI=patientChart.getPI_B().split("\\|");
        for (int i=0;i<bPI.length;i++){
            String bPIname="bPI"+i;
            model.addAttribute(bPIname,bPI[i]);
        }}
        String[]lPI=patientChart.getPI_L().split("\\|");
        for(int i=0;i<lPI.length;i++){
            String lPI_name="lPI"+i;
            model.addAttribute(lPI_name,lPI[i]);
        }
        String[]bCEJ=patientChart.getCEJ_B().split("\\|");
        for(int i=0;i<bCEJ.length;i++){
            String bCEJ_name="bCEJ"+i;
            model.addAttribute(bCEJ_name,bCEJ[i]);
        }
        String[]lCEJ=patientChart.getCEJ_L().split("\\|");
        for(int i=0;i<lCEJ.length;i++){
            String lCEJ_name="lCEJ"+i;
            model.addAttribute(lCEJ_name,lCEJ[i]);
        }
        String[]bBI=patientChart.getBI_B().split("\\|");
        for(int i=0;i<bBI.length;i++){
            String bBI_name="bBI"+i;
            model.addAttribute(bBI_name,bBI[i]);
        }
        String[]lBI=patientChart.getBI_L().split("\\|");
        for(int i=0;i<lBI.length;i++){
            String lBI_name="lBI"+i;
            model.addAttribute(lBI_name,lBI[i]);
        }
        String[]bPD=patientChart.getPD_B().split("\\|");
        for(int i=0;i<bPD.length;i++){
            String bPD_name="bPD"+i;
            model.addAttribute(bPD_name,bPD[i]);
        }
        String[]lPD=patientChart.getPD_L().split("\\|");
        for(int i=0;i<lPD.length;i++){
            String lPD_name="lPD"+i;
            model.addAttribute(lPD_name,lPD[i]);
        }
        return "PDetailTable";
    }

//    @PostMapping("/PDetailTable")
//    @ResponseBody
//    public Map CopyData(@RequestParam("where")String where, @RequestParam("c_id")String c_id){
//        Map map=new HashMap();
//        if (where.equals("1")) {
//            this.c_id = c_id;
//        }
//        else {
//            Chart patientChart=patientChartDao.selectChartByCId(this.c_id);
//            if (patientChart!=null){
//            String[] PI_L=patientChart.getPI_L().split("\\|");
//            map.put("PI_L",PI_L);
//            String[]CEJ_B=patientChart.getCEJ_B().split("\\|");
//            map.put("CEJ_B",CEJ_B);
//            String[]CEJ_L=patientChart.getCEJ_L().split("\\|");
//            map.put("CEJ_L",CEJ_L);
//            String[] BI_B=patientChart.getBI_B().split("\\|");
//            map.put("BI_B",BI_B);
//            String[]BI_L=patientChart.getBI_L().split("\\|");
//            map.put("BI_L",BI_L);
//            String[]PD_B=patientChart.getPD_B().split("\\|");
//            map.put("PD_B",PD_B);
//            String[]PD_L=patientChart.getPD_L().split("\\|");
//            map.put("PD_L",PD_L);}
//        }
//        return map;
//    }
}
