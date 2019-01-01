package com.student.yazhoubang.dao;

import com.student.yazhoubang.damain.PatientChart;
import com.student.yazhoubang.utils.ChartWithDoctors;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PatientChartDao {
    @Select("select * from yazhoubang.chart natural join yazhoubang.read where p_id=#{p_id}")
    public List<PatientChart> selectChartById(String p_id);
    @Select("select c_id from yazhoubang.chart natural join yazhoubang.read where p_id=#{p_id}")
    public List<String>selectCIdById(String p_id);
    @Select("select time,name,c_id from yazhoubang.chart natural join yazhoubang.write natural join yazhoubang.doctor where c_id=#{c_id}")
    public ChartWithDoctors selectInformationByCId(String c_id);
}
