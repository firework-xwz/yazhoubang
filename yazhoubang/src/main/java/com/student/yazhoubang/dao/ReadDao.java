package com.student.yazhoubang.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ReadDao {
    @Select("select p_id from yazhoubang.read where c_id=#{c_id}")
    public String selectP_idByC_id(String c_id);
}
