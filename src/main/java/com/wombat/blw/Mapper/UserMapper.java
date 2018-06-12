package com.wombat.blw.Mapper;

import com.wombat.blw.DO.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public interface UserMapper {
    @Select("select username from user where user_id=#{user_id}")
    String getUserNameByUserId(int userId);

    @Select("select user_id,username,password,real_name,role,gender," +
            "tel,email,co_id,create_time " +
            "from user where username=#{userName}")
    User getUserByUserId(String userName);

    @Insert("insert into user " +
            "values null,#{username},#{password},#{realName}," +
            "#{role},#{gender},#{tel},#{email},#{companyId}")
    void createUser(String username, String password, String realName,
                    Integer role, Integer gender, BigDecimal tel, String email, Integer companyId);


}
