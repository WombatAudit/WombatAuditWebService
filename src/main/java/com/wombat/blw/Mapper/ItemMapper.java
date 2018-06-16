package com.wombat.blw.Mapper;

import com.wombat.blw.DO.Item;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface ItemMapper {

    @Select("select name from item where item_id=#{id}")
    String getItemNameByItemId(int id);

    @Select("select item_id,type,name,description,quantity,amount,rcpt_id " +
            "from item where item_id=#{id}")
    @Results({
            @Result(property = "itemId", column = "item_id", javaType = Integer.class),
            @Result(property = "rcptId", column = "rcpt_id", javaType = Integer.class),
    })
    Item getItemByItemId(int id);

}
