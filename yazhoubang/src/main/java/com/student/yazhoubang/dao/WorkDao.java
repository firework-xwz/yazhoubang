package com.student.yazhoubang.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface WorkDao {
    @Insert("insert into work (d_id,h_id)values(#{d_id},#{h_id})")
    public Integer addWork(String d_id, String h_id);
    @Delete("delete from work where d_id=#{d_id}")
    public Integer deleteWork(String d_id);
    @Select("select h_id from work where d_id=#{d_id}")
    public String selectHByD(String d_id);
}
