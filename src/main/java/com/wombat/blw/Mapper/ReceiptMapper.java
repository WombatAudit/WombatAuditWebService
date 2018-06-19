package com.wombat.blw.Mapper;

import com.wombat.blw.DO.Receipt;
import com.wombat.blw.Form.ReceiptForm;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Component
public interface ReceiptMapper {

    @Select("select user_id from item_receipt where rcpt_id = #{rcptId}")
    @ResultType(Integer.class)
    Integer findUserIdByRcptId(Integer rcptId);

    @Insert("insert into receipt (invoice, receipt, transaction, attachment) values (#{invoice}, #{receipt}, #{transaction}, " +
            "#{attachment})")
    @Options(useGeneratedKeys = true, keyProperty = "rcptId", keyColumn = "rcpt_id")
    void createReceipt(Receipt receipt);

    @Select("select * from receipt where rcpt_id = #{rcptId}")
    @Results({
            @Result(property = "rcptId", column = "rcpt_id", javaType = Integer.class),
            @Result(property = "receipt", column = "receipt", javaType = String.class),
            @Result(property = "invoice", column = "invoice", javaType = String.class),
            @Result(property = "transaction", column = "transaction", javaType = String.class),
            @Result(property = "attachment", column = "attachment", javaType = String.class)
    })
    Receipt findReceipt(Integer rcptId);

    @Insert("insert into item_receipt(item_id, user_id, rcpt_id) values (#{itemId}, #{userId}, #{rcptId})")
    void addReceipt(@Param("itemId") Integer itemId, @Param("userId") Integer userId, @Param("rcptId") Integer rcptId);
}
