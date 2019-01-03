package com.student.yazhoubang.controller;

import com.student.yazhoubang.damain.Chart;
import com.student.yazhoubang.damain.Doctor;
import com.student.yazhoubang.damain.Patient;
import com.student.yazhoubang.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
        Map<String, Patient> map = new HashMap<>();
        String d_id = (String)httpSession.getAttribute("id");
        System.out.println(d_id);
        //要显示的病人id
        List<String> p_idList = cureDao.selectPByD(d_id);
        //要显示的病人列表
        List<Patient> patientList = new LinkedList<>();
        //要显示的最后就诊时间
        List<String> last_checkList = new LinkedList<>();

        for(int i=0;i<p_idList.size();i++){
            String tp_id = p_idList.get(i);
            patientList.add(patientDao.selectPatientById(tp_id));
            last_checkList.add(cureDao.selectLByP(tp_id));
            map.put(((LinkedList<String>) last_checkList).getLast(),((LinkedList<Patient>) patientList).getLast());
        }
        model.addAttribute("patientList",map);
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

    @RequestMapping("/DIndex/ViewChartByP_id/{id}")
    public String viewChartByP_id(@PathVariable("id") String p_id, Model model){
        System.out.println("---viewChartByP_id---");
        System.out.println(p_id);
        List<Chart>ChartList=chartDao.selectChartById(p_id);
        model.addAttribute("ChartList",ChartList);
        return "ViewChartByP_id";
    }
}
