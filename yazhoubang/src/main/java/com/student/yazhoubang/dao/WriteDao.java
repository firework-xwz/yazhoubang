package com.student.yazhoubang.dao;

import org.apache.ibatis.annotations.*;

@Mapper
public interface WriteDao {
    @Insert("insert into write (d_id,c_id,w_time)values(#{d_id},#{c_id}),#{w_time}")
    public Integer addWrite(@Param("d_id") String d_id, @Param("c_id") String c_id, @Param("w_time") String w_time);
    @Delete("delete from write where d_id=#{d_id}")
    public Integer deleteWrite(String d_id);
    @Select("select c_id from write where d_id=#{d_id}")
    public String selectCByD(String d_id);
}
