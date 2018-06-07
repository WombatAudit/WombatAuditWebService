package com.wombat.blw.Mapper;

import com.wombat.blw.DO.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public interface UserMapper {

//    @Select("SELECT * FROM user")
//    @Results({
//            @Result(property = "userId", column = "user_id", javaType = Integer.class),
//            @Result(property = "role", column = "role", javaType = String.class),
//            @Result(property = "name", column = "name", javaType = String.class),
//            @Result(property = "gender", column = "gender", javaType = String.class),
//            @Result(property = "tel", column = "tel", javaType = BigDecimal.class),
//            @Result(property = "email", column = "email", javaType = String.class),
//            @Result(property = "companyId", column = "co_id", javaType = Integer.class)
//    })
//    List<User> getAll();
//
//    @Select("SELECT * FROM user WHERE user_id = #{userId}")
//    @Results({
//            @Result(property = "userId", column = "user_id", javaType = Integer.class),
//            @Result(property = "role", column = "role", javaType = String.class),
//            @Result(property = "name", column = "name", javaType = String.class),
//            @Result(property = "gender", column = "gender", javaType = String.class),
//            @Result(property = "tel", column = "tel", javaType = BigDecimal.class),
//            @Result(property = "email", column = "email", javaType = String.class),
//            @Result(property = "companyId", column = "co_id", javaType = Integer.class)
//    })
//    User selectByUserId(Integer userId);
//
//    @Insert("INSERT INTO user(user_id,role,name,gender,tel,email,co_id) VALUES(#{userId},#{role},#{name},#{gender},#{tel}," +
//            "#{email},#{companyId})")
//    void insert(User user);
//
//    @Update("UPDATE user SET role=#{role},name=#{name},gender=#{gender},tel=#{tel},email=#{email}," +
//            "co_id=#{companyId} WHERE user_id=#{userId}")
//    void update(User user);
//
//    @Delete("DELETE FROM user WHERE user_id=#{userId}")
//    void delete(Integer userId);

    //TODO
}
