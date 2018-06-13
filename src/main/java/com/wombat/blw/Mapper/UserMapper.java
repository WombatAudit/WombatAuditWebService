package com.wombat.blw.Mapper;

import com.wombat.blw.DO.User;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

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

<<<<<<< HEAD
=======
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
    @Select("SELECT * FROM user WHERE user_id = #{userId}")
    @Results({
            @Result(property = "userId", column = "user_id", javaType = Integer.class),
            @Result(property = "role", column = "role", javaType = String.class),
            @Result(property = "name", column = "name", javaType = String.class),
            @Result(property = "gender", column = "gender", javaType = String.class),
            @Result(property = "tel", column = "tel", javaType = BigDecimal.class),
            @Result(property = "email", column = "email", javaType = String.class),
            @Result(property = "companyId", column = "co_id", javaType = Integer.class)
    })
    User selectByUserId(Integer userId);

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
>>>>>>> df37c87ba64d7a4a4d212c0e07bef85d08f62a5d

}
