package com.student.yazhoubang.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
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
}
