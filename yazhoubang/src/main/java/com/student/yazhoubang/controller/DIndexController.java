package com.student.yazhoubang.controller;

import com.student.yazhoubang.damain.Chart;
import com.student.yazhoubang.damain.Doctor;
import com.student.yazhoubang.damain.Msg;
import com.student.yazhoubang.damain.Patient;
import com.student.yazhoubang.dao.*;
import org.apache.ibatis.annotations.Param;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.sql.Types.NULL;

@Controller
public class DIndexController {
    @Autowired
    private ChartDao chartDao;
    @Autowired
    private PatientDao patientDao;
    @Autowired
    private DoctorDao doctorDao;
    @Autowired
    private CureDao cureDao;
    @Autowired
    private WriteDao writeDao;


    @RequestMapping("/DIndex")
    public String DIndex(Model model, HttpSession httpSession){
        System.out.println("---DIndex---");

        String d_id = (String)httpSession.getAttribute("id");
        System.out.println(d_id);

        List<Map> ans = new ArrayList<>();
        //要显示的病人id
        List<String> p_idList = cureDao.selectPByD(d_id);
        //要显示的病人列表
        List<Patient> patientList = new LinkedList<>();
        //要显示的最后就诊时间
        List<String> last_checkList = new LinkedList<>();

        for(int i=0;i<p_idList.size();i++){
            Map map  = new HashMap();
            String tp_id = p_idList.get(i);

            Patient patient = patientDao.selectPatientById(tp_id);
            String last_check = cureDao.selectLByP(tp_id);
            map.put("patient",patient);
            map.put("last_check",last_check);
            ans.add(map);
        }

        String dname = doctorDao.selectDoctorById(d_id).getName();
        model.addAttribute("ans",ans);
        model.addAttribute("dname",dname);
        return "DIndex";
    }

    @RequestMapping("/DIndex/addPatient/{id}")
    public String addPatient(@PathVariable("id") String p_id,Model model){
        System.out.println("---addPatient---");
        System.out.println(p_id);
        Patient p = new Patient();
        try{
            p = patientDao.selectPatientById(p_id);
            model.addAttribute("p",p);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return "AddPatientFirst";
    }

    @RequestMapping("DIndex/addPatient/confirm")
    @ResponseBody
    public Msg addPatientConfirm(@RequestParam(value="p_id")String p_id, HttpSession httpSession){
        System.out.println("---addPatientConfirm---");
        System.out.println(p_id);
        String d_id = (String)httpSession.getAttribute("id");
        System.out.println(d_id);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s_time = df.format(new Date());
        Msg msg = new Msg();
        try{
            cureDao.addCure(d_id,p_id,s_time);
            msg.setMessage("SUCCESS!");
        }
        catch(Exception e){
            e.printStackTrace();
            msg.setMessage("FAIL!");
        }
        return msg;
    }

    @RequestMapping("/DIndex/ViewChartByP_id/{id}")
    public String viewChartByP_id(@PathVariable("id") String p_id, Model model){
        System.out.println("---viewChartByP_id---");
        System.out.println(p_id);
        List<Chart>ChartList=chartDao.selectChartById(p_id);
        model.addAttribute("ChartList",ChartList);
        return "ViewChartByP_id";
    }
}
