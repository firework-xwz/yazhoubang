package com.student.yazhoubang.controller;

import com.student.yazhoubang.dao.PatientChartDao;
import com.student.yazhoubang.damain.PatientChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PatientChartController {
    @Autowired
    private PatientChartDao patientChartDao;
    @RequestMapping(value = "/PCharts")
    public String PCharts(){return "PCharts";}
    @PostMapping(value = "/PCharts")
    @ResponseBody
    public List<Map> chartData(@RequestParam(value = "name")String type){
        //PatientChart data=patientChartDao.selectChartById(2);
        //return data;//待定
        List<PatientChart>chart=patientChartDao.selectChartById("111111111111111111");
        List<Map>result=new ArrayList<>();
        result.clear();
        if(type.equals("mobility and FI")){
            for(int i=0;i<chart.size();i++){
                Map map=new HashMap();
                map.put("time",chart.get(i).getTime());
                ArrayList<String>name =new ArrayList<>();
                ArrayList data=new ArrayList();
                name.add("mobility");
                if(chart.get(i).getMobility().equals("i")){
                data.add(1);}
                else if(chart.get(i).getMobility().equals("ii")){
                    data.add(2);
                }
                else{
                    data.add(3);
                }
                name.add("FI");
                if(chart.get(i).getFI().equals("i")){
                    data.add(1);}
                else if(chart.get(i).getFI().equals("ii")){
                    data.add(2);
                }
                else{
                    data.add(3);
                }
                map.put("name",name);
                map.put("data",data);
                result.add(map);
            }
        }
        else if(type.equals("implant")){
            for (int i=0;i<chart.size();i++){
                Map map=new HashMap();
                map.put("time",chart.get(i).getTime());
                ArrayList<String>name=new ArrayList<>();
                ArrayList data=new ArrayList();
                name.add("implant");
                data.add(chart.get(i).getImplant());
                map.put("name",name);
                map.put("data",data);
                result.add(map);
            }
        }
        else if(type.equals("GI,BI and PI")){
            for(int i=0;i<chart.size();i++){
                Map map=new HashMap();
                map.put("time",chart.get(i).getTime());
                ArrayList<String>name=new ArrayList<>();
                ArrayList data=new ArrayList();
                name.add("GI");
                data.add(chart.get(i).getGI());
                name.add("BI_B");
                data.add(chart.get(i).getBI_B());
                name.add("BI_L");
                data.add(chart.get(i).getBI_L());
                name.add("PI_B");
                data.add(chart.get(i).getPI_B());
                name.add("PI_L");
                data.add(chart.get(i).getPI_L());
                map.put("name",name);
                map.put("data",data);
                result.add(map);
            }
        }
        else if(type.equals("PD and CEJ")) {
            for (int i=0;i<chart.size();i++){
                Map map=new HashMap();
                map.put("time",chart.get(i).getTime());
                ArrayList<String>name=new ArrayList<>();
                ArrayList data=new ArrayList();
                name.add("PD_B");
                data.add(chart.get(i).getPD_B());
                name.add("PD_L");
                data.add(chart.get(i).getPD_L());
                name.add("CEJ_B");
                data.add(chart.get(i).getCEJ_B());
                name.add("CEJ_L");
                data.add(chart.get(i).getCEJ_L());
                map.put("name",name);
                map.put("data",data);
                result.add(map);
            }
        }
        return result;
    }
}
