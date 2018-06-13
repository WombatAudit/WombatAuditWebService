package com.wombat.blw.Mapper;

import com.wombat.blw.DO.Company;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public interface CompanyMapper {

    @Select("SELECT * FROM company")
    @Results({
            @Result(property = "companyId", column = "co_id", javaType = Integer.class),
            @Result(property = "name", column = "name", javaType = String.class),
            @Result(property = "description", column = "description", javaType = String.class),
            @Result(property = "taxId", column = "tax_id", javaType = String.class),
            @Result(property = "accountBank", column = "account_bank", javaType = String.class),
            @Result(property = "account", column = "account", javaType = BigDecimal.class)
    })
    List<Company> getAll();
<<<<<<< HEAD
//
//    @Select("SELECT * FROM company WHERE co_id = #{companyId}")
//    @Results({
//            @Result(property = "companyId", column = "co_id", javaType = Integer.class),
//            @Result(property = "name", column = "name", javaType = String.class),
//            @Result(property = "description", column = "description", javaType = String.class),
//            @Result(property = "taxId", column = "tax_id", javaType = String.class),
//            @Result(property = "accountBank", column = "account_bank", javaType = String.class),
//            @Result(property = "account", column = "account", javaType = BigDecimal.class)
//    })
//    Company selectByCompanyId(Integer companyId);
//
//    @Insert("INSERT INTO company(co_id,name,description,tax_id,account_bank,account) VALUES(#{companyId}," +
//            "#{name},#{description},#{taxId},#{accountBank},#{account})")
//    void insert(Company company);
//
//    @Insert("UPDATE company SET name=#{name},description=#{description},tax_id=#{taxId}," +
//            "account_bank=#{accountBank},account=#{account} WHERE co_id=#{companyId}")
//    void update(Company company);
//
//    @Delete("DELETE FROM company WHERE co_id = #{companyId}")
//    void delete(Integer companyId);

    //TODO
=======



>>>>>>> df37c87ba64d7a4a4d212c0e07bef85d08f62a5d
}
