package com.wombat.blw.Mapper;

import com.wombat.blw.DO.Assignment;
import com.wombat.blw.DTO.DetailedAssignmentDTO;
import com.wombat.blw.DTO.ReceiptDTO;
import com.wombat.blw.DTO.SimpleAssignmentDTO;
import com.wombat.blw.Form.ReceiptForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Mapper
@Component
public interface AssignmentMapper {
    @Select("select user_id,item_id,status,max(start_date) as max_date " +
            "from assignment " +
            "where item_id=#{itemId}")
    @Results({
            @Result(property = "userId",column = "user_id",javaType = Integer.class),
            @Result(property = "itemId",column = "item_id",javaType = Integer.class),
            @Result(property = "startDate",column = "max_date",javaType = Date.class),
    })
    List<Assignment> getListByItemId(Integer itemId) ;

    @Select("select user_id,item_id,status,max(start_date) as max_date " +
            "from assignment " +
            "where user_id=#{userId}")
    @Results({
            @Result(property = "userId",column = "user_id",javaType = Integer.class),
            @Result(property = "itemId",column = "item_id",javaType = Integer.class),
            @Result(property = "startDate",column = "max_date",javaType = Date.class),
    })
    List<Assignment> getListByUserId(Integer userId) ;

    @Select("select status from assignment where user_id=#{userId} and item_id=#{itemId}")
    String getStatusByKey(Integer userId,Integer itemId);

}
