package com.student.yazhoubang.dao;

import com.student.yazhoubang.damain.PatientChart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PatientChartDao {
    @Select("select * from yazhoubang.chart natural join yazhoubang.read where p_id=#{p_id}")
    public List<PatientChart> selectChartById(String p_id);
}
