package com.student.yazhoubang.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CureDao {
    @Select("select p_id from cure where d_id=#{d_id}")
    public List<String> selectPByD(String d_id);
    @Select("select l_time from cure where d_id=#{d_id}")
    public String selectLByD(String d_id);
    @Select("select l_time from cure where p_id=#{p_id}")
    public String selectLByP(String p_id);
    @Insert("Insert into cure (d_id,p_id,s_time,l_time,status)values(#{d_id},#{p_id},#{s_time},#{l_time},#{status})")
    public Integer addCure(@Param("d_id") String d_id,@Param("p_id") String p_id,@Param("s_time") String s_time,@Param("l_time")String l_time,@Param("status")int Status);
}
