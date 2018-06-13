package com.wombat.blw.Mapper;

import com.wombat.blw.DO.Receipt;
import com.wombat.blw.Form.ReceiptForm;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Component
public interface ReceiptMapper {

    @Select("select rcpt_id,receipt,invoice,transaction,attachment from receipt " +
            "where rcpt_id=#{rcptId}")
    Receipt getReceiptByRcptId(int RcptId);

    @Update("update receipt set receipt=#{receiptForm.receipt} where rcpt_id=#{rcptId}")
    void updateReceipt(@Param("rcptId") int rcpt_id, ReceiptForm receiptForm);
}
