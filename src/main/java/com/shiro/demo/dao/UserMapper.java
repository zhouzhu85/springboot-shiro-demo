package com.shiro.demo.dao;

import com.shiro.demo.domain.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author zhouzhu
 * @Description
 * @create 2020-04-29 15:19
 */
public interface UserMapper {

    @Select("select * from user where username=#{username}")
    User findByUsername(@Param("username")String username);

    @Select("select * from user where id=#{userId}")
    User findById(@Param("userId") Integer userId);

    @Select("select * from user where username=#{username} and password=#{pwd}")
    User findByUsernameAndPwd(@Param("username")String username,@Param("pwd")String pwd);
}
