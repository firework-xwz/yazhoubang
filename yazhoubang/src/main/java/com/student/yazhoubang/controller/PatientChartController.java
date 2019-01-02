package com.student.yazhoubang.controller;

import com.student.yazhoubang.damain.Patient;
import com.student.yazhoubang.dao.ChartDao;
import com.student.yazhoubang.damain.Chart;
import com.student.yazhoubang.dao.PatientDao;
import com.student.yazhoubang.utils.ChartUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PatientChartController {

    @Autowired
    PatientDao patientDao;
    @Autowired
    private ChartDao ChartDao;
    @RequestMapping(value = "/PCharts")
    public String PCharts(Model model, HttpSession httpSession){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String p_id=(String)httpSession.getAttribute("id");
        List<String> doctorinformation=patientDao.selectDoctorBypId(p_id);
        String Dphone=patientDao.selectDphone(p_id);
        Patient patient =patientDao.selectPatientById(p_id);
        model.addAttribute("patientName",patient.getName());
        if(patient.getSex()==0){
            model.addAttribute("patientSex","男");
        }
        else
            model.addAttribute("patientSex","女");
        model.addAttribute("birthday", patientDao.selectBirthdaybyId(p_id));
        if(doctorinformation!=null&&doctorinformation.size()>0) {
            model.addAttribute("doctorName", doctorinformation.get(0));
            model.addAttribute("doctorPhone", Dphone);
        }
        return "PCharts";}
    @PostMapping(value = "/PCharts")
    @ResponseBody
    public List<Map> chartData(@RequestBody ChartUtils chartUtils, HttpSession httpSession){
        String p_id=(String)httpSession.getAttribute("id");
        //PatientChart data=patientChartDao.selectChartById(2);
        //return data;//待定
        String type=chartUtils.getName();
        String toothNumber=chartUtils.getTooth_number();
        int tooth_number=Integer.parseInt(toothNumber);
        List<Chart>chart=ChartDao.selectChartById(p_id);
        List<Map>result=new ArrayList<>();
        result.clear();
        if(chart.size()!=0||chart!=null) {
            if (type.equals("mobility and FI")) {
                for (int i = 0; i < chart.size(); i++) {
                    Map map = new HashMap();
                    map.put("time", chart.get(i).getTime());
                    ArrayList<String> name = new ArrayList<>();
                    ArrayList data = new ArrayList();
                    name.add("mobility");
                    if (tooth_number <= 12) {
                        String[] mobility = chart.get(i).getMobility().split("\\|");
                        if (mobility[tooth_number].equals("i")) {
                            data.add(1);
                        } else if (mobility[tooth_number].equals("ii")) {
                            data.add(2);
                        } else {
                            data.add(3);
                        }
                    } else
                        data.add(0);

                    name.add("FI");
                    if (tooth_number <= 12) {
                        String[] FI = chart.get(i).getFI().split("\\|");
                        if (FI[(tooth_number - 1) * 3 + 1].equals("i")) {
                            data.add(1);
                        } else if (FI[(tooth_number - 1) * 3 + 1].equals("ii")) {
                            data.add(2);
                        } else {
                            data.add(3);
                        }
                    } else
                        data.add(0);
                    map.put("name", name);
                    map.put("data", data);
                    result.add(map);
                }
            } else if (type.equals("implant")) {
                for (int i = 0; i < chart.size(); i++) {
                    Map map = new HashMap();
                    map.put("time", chart.get(i).getTime());
                    ArrayList<String> name = new ArrayList<>();
                    ArrayList data = new ArrayList();
                    name.add("implant");
                    String[] implant = chart.get(i).getImplant().split("\\|");
                    data.add(implant[tooth_number]);
                    map.put("name", name);
                    map.put("data", data);
                    map.put("status", "success");
                    result.add(map);
                }
            } else if (type.equals("GI,BI and PI")) {
                for (int i = 0; i < chart.size(); i++) {
                    Map map = new HashMap();
                    map.put("time", chart.get(i).getTime());
                    ArrayList<String> name = new ArrayList<>();
                    ArrayList data = new ArrayList();
                    name.add("GI");
                    String[] GI = chart.get(i).getGI().split("\\|");
                    data.add(GI[tooth_number]);
                    name.add("BI_B");
                    String[] BI_B = chart.get(i).getBI_B().split("\\|");
                    data.add(BI_B[(tooth_number - 1) * 3 + Integer.parseInt(chartUtils.getTooth_position())]);
                    name.add("BI_L");
                    String[] BI_L = chart.get(i).getBI_L().split("\\|");
                    data.add(BI_L[(tooth_number - 1) * 3 + Integer.parseInt(chartUtils.getTooth_position())]);
                    name.add("PI_B");
                    String[] PI_B = chart.get(i).getPI_B().split("\\|");
                    data.add(PI_B[(tooth_number - 1) * 3 + Integer.parseInt(chartUtils.getTooth_position())]);
                    name.add("PI_L");
                    String[] PI_L = chart.get(i).getPI_L().split("\\|");
                    data.add(PI_L[(tooth_number - 1) * 3 + Integer.parseInt(chartUtils.getTooth_position())]);
                    map.put("name", name);
                    map.put("data", data);
                    map.put("status", "success");
                    result.add(map);
                }
            } else if (type.equals("PD and CEJ")) {
                for (int i = 0; i < chart.size(); i++) {
                    Map map = new HashMap();
                    map.put("time", chart.get(i).getTime());
                    ArrayList<String> name = new ArrayList<>();
                    ArrayList data = new ArrayList();
                    name.add("PD_B");
                    String[] PD_B = chart.get(i).getPD_B().split("\\|");
                    data.add(PD_B[(tooth_number - 1) * 3 + Integer.parseInt(chartUtils.getTooth_position())]);
                    name.add("PD_L");
                    String[] PD_L = chart.get(i).getPD_L().split("\\|");
                    data.add(PD_L[(tooth_number - 1) * 3 + Integer.parseInt(chartUtils.getTooth_position())]);
                    name.add("CEJ_B");
                    String[] CEJ_B = chart.get(i).getCEJ_B().split("\\|");
                    data.add(CEJ_B[(tooth_number - 1) * 3 + Integer.parseInt(chartUtils.getTooth_position())]);
                    name.add("CEJ_L");
                    String[] CEJ_L = chart.get(i).getCEJ_L().split("\\|");
                    data.add(CEJ_L[(tooth_number - 1) * 3 + Integer.parseInt(chartUtils.getTooth_position())]);
                    map.put("name", name);
                    map.put("data", data);
                    map.put("status", "success");
                    result.add(map);
                }
            }
        }
        return result;
    }
}
