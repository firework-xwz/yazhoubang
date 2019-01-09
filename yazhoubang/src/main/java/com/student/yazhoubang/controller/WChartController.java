package com.student.yazhoubang.controller;

import com.student.yazhoubang.damain.Chart;
import com.student.yazhoubang.damain.Msg;
import com.student.yazhoubang.dao.ChartDao;
import com.student.yazhoubang.dao.DoctorDao;
import com.student.yazhoubang.dao.PatientDao;
import com.student.yazhoubang.dao.WriteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private WriteDao writeDao;

    @RequestMapping("/WChart/{p_id}")
    public String WChart(@PathVariable(value = "p_id")String p_id, Model model, HttpSession httpSession){
        String d_id = (String)httpSession.getAttribute("id");
        String d_name = doctorDao.selectDoctorById(d_id).getName();
        int tsex = doctorDao.selectDoctorById(d_id).getSex();
        String sex = null;
        if(tsex==0){
            sex = "男";
        }
        else{
            sex = "女";
        }
        String p_name = patientDao.selectPatientById(p_id).getName();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dt = new Date();
        String time = df.format(dt);

        model.addAttribute("d_name",d_name);
        model.addAttribute("sex",sex);
        model.addAttribute("p_name",p_name);
        model.addAttribute("time",time);

        httpSession.setAttribute("tp_id",p_id);
        return "WChart";
    }

    @RequestMapping("/WChart/Confirm")
    @ResponseBody
    public Msg Confirm(@RequestBody Chart chart,HttpSession httpSession){
        Msg msg = new Msg();
        String p_id = (String)httpSession.getAttribute("p_id");
        String d_id = (String)httpSession.getAttribute("d_id");

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = df.format(chart.getTime());

        try{
            writeDao.addWrite(d_id,chart.getC_id(),time);
            chartDao.addRead(p_id,chart.getC_id());
            chartDao.addChart(chart.getC_id(),chart.getTime(),chart.getMobility(),chart.getImplant(),chart.getFI(),chart.getGI(),chart.getBI_B(),chart.getBI_L(),chart.getPD_B(),chart.getPD_L(),chart.getCEJ_B(),chart.getCEJ_L(),chart.getPI_B(),chart.getPI_L());
            msg.setMessage("SUCCESS!!!");
        }
        catch(Exception e){
            e.printStackTrace();
            msg.setMessage("FAIL!!!");
        }
        return msg;
    }
}
