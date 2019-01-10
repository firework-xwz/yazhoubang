package com.student.yazhoubang.dao;

import com.student.yazhoubang.damain.Chart;
import com.student.yazhoubang.utils.ChartWithDoctors;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

@Mapper
public interface ChartDao {
    @Select("select * from yazhoubang.chart natural join yazhoubang.read where p_id=#{p_id}")
    public List<Chart> selectChartById(String p_id);
    @Select("select c_id from yazhoubang.chart natural join yazhoubang.read where p_id=#{p_id}")
    public List<String>selectCIdById(String p_id);
    @Select("select time,name,c_id from yazhoubang.chart natural join yazhoubang.write natural join yazhoubang.doctor where c_id=#{c_id}")
    public ChartWithDoctors selectInformationByCId(String c_id);
    @Select("select * from yazhoubang.chart where c_id=#{c_id}")
    public Chart selectChartByCId(String c_id);
    @Insert("insert into yazhoubang.read (p_id,c_id)values(#{p_id},#{c_id})")
    public Integer addRead(@Param("p_id")String p_id, @Param("c_id")String c_id);
    @Insert("insert into yazhoubang.chart (c_id,time,mobility,implant,FI,GI,BI_B,BI_L,PD_B,PD_L,CEJ_B,CEJ_L,PI_B,PI_L)values(#{c_id},#{time},#{mobility},#{implant},#{FI},#{GI},#{BI_B},#{BI_L},#{PD_B},#{PD_L},#{CEJ_B},#{CEJ_L},#{PI_B},#{PI_L}")
    public Integer addChart(@Param("c_id")String c_id, @Param("time") Date time, @Param("mobility")String mobility, @Param("implant")String implant, @Param("FI")String FI, @Param("GI")String GI,@Param("BI_B")String BI_B,@Param("BI_L")String BI_L,@Param("PD_B")String PD_B,@Param("PD_L")String PD_L,@Param("CEJ_B")String CEJ_B,@Param("CEJ_L")String CEJ_L,@Param("PI_B")String PI_B,@Param("PI_L")String PI_L);
}
