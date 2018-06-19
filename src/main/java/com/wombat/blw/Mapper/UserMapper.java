package com.wombat.blw.Mapper;

import com.wombat.blw.DO.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Component
public interface UserMapper {

    @Select("select username from user where user_id=#{user_id}")
    String findUserNameByUserId(int userId);

    @Select("select real_name from user where user_id=#{user_id}")
    String findRealNameByUserId(int userId);

    @Insert("insert into user(username, password, real_name, role, gender, tel, email, co_id) " +
            "values (#{user.username}, #{user.password}, #{user.realName}, #{user.role}, #{user.gender}, " +
            "#{user.tel}, #{user.email}, #{user.companyId})")
    @Options(useGeneratedKeys = true, keyColumn = "user_id")
    void create(@Param("user") User user);

    @Select("SELECT * FROM user WHERE user_id = #{userId}")
    @Results({
            @Result(property = "userId", column = "user_id", javaType = Integer.class),
            @Result(property = "username", column = "username", javaType = String.class),
            @Result(property = "password", column = "password", javaType = String.class),
            @Result(property = "realName", column = "real_name", javaType = String.class),
            @Result(property = "role", column = "role", javaType = Integer.class),
            @Result(property = "gender", column = "gender", javaType = Integer.class),
            @Result(property = "tel", column = "tel", javaType = BigDecimal.class),
            @Result(property = "email", column = "email", javaType = String.class),
            @Result(property = "companyId", column = "co_id", javaType = Integer.class),
            @Result(property = "createTime", column = "create_time", javaType = Date.class)
    })
    User findUserByUserId(Integer userId);

    @Select("select * from user where username = #{username}")
    @Results({
            @Result(property = "userId", column = "user_id", javaType = Integer.class),
            @Result(property = "username", column = "username", javaType = String.class),
            @Result(property = "password", column = "password", javaType = String.class),
            @Result(property = "realName", column = "real_name", javaType = String.class),
            @Result(property = "role", column = "role", javaType = Integer.class),
            @Result(property = "gender", column = "gender", javaType = Integer.class),
            @Result(property = "tel", column = "tel", javaType = BigDecimal.class),
            @Result(property = "email", column = "email", javaType = String.class),
            @Result(property = "companyId", column = "co_id", javaType = Integer.class),
            @Result(property = "createTime", column = "create_time", javaType = Date.class)
    })
    User findUserByUsername(String username);

    @Select("SELECT user_id, real_name FROM user WHERE co_id = #{coId} AND role = 1 AND user_id NOT IN " +
            "( SELECT user_id FROM participate WHERE org_id = #{orgId} )")
    @Results({
            @Result(property = "userId", column = "user_id", javaType = Integer.class),
            @Result(property = "realName", column = "real_name", javaType = String.class)
    })
    List<User> findGeneralMembersOfCompanyNotIn(@Param("coId") Integer coId, @Param("orgId") Integer orgId);

    @Select("select * from user join participate on user.user_id = participate.user_id where org_id = #{orgId} and user.user_id not in " +
            "(select assignee_id from assignment where item_id = #{itemId})")
    @Results({
            @Result(property = "userId", column = "user_id", javaType = Integer.class),
            @Result(property = "username", column = "username", javaType = String.class),
            @Result(property = "password", column = "password", javaType = String.class),
            @Result(property = "realName", column = "real_name", javaType = String.class),
            @Result(property = "role", column = "role", javaType = Integer.class),
            @Result(property = "gender", column = "gender", javaType = Integer.class),
            @Result(property = "tel", column = "tel", javaType = BigDecimal.class),
            @Result(property = "email", column = "email", javaType = String.class),
            @Result(property = "companyId", column = "co_id", javaType = Integer.class),
            @Result(property = "createTime", column = "create_time", javaType = Date.class)
    })
    List<User> findGeneralMembersInOrgNotAssigned(@Param("orgId") Integer orgId, @Param("itemId") Integer itemId);
}

