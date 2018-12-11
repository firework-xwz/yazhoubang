package com.student.yazhoubang.dao;

import com.student.yazhoubang.damain.UserList;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserListDao {

    @Insert("insert into user_list(user_id,user_password,user_role)values(#{user_id},#{user_password},#{user_role})")
    public Integer addUserList(UserList userList);
    @Select("select * from user_list where user_id=#{user_id}")
    public UserList selectUserById(String user_id);
}
