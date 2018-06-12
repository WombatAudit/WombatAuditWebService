package com.wombat.blw.Mapper;

import com.wombat.blw.DO.Receipt;
import com.wombat.blw.Form.ReceiptForm;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface ReceiptMapper {

    //TODO
    @Select("select rcpt_id,receipt,invoice,transaction,attachment from receipt " +
            "where rcpt_id=#{rcptId}")
    Receipt getReceiptByRcptId(int RcptId);

    @Update("update receipt set receipt=#{receiptForm.receipt} where rcpt_id=#{rcptId}")
    void updateReceipt(int rcpt_id, ReceiptForm receiptForm);
}
