package com.wombat.blw.Mapper;

import com.wombat.blw.DO.Detail;
import com.wombat.blw.DO.Item;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public interface ItemMapper {

    @Select("select name from item where item_id=#{id}")
    String getItemNameByItemId(int id);

    @Select("select * from item where item_id = #{itemId}")
    @Results({
            @Result(property = "itemId", column = "item_id", javaType = Integer.class),
            @Result(property = "type", column = "type", javaType = String.class),
            @Result(property = "name", column = "name", javaType = String.class),
            @Result(property = "description", column = "description", javaType = String.class),
            @Result(property = "quantity", column = "quantity", javaType = Integer.class),
            @Result(property = "amount", column = "amount", javaType = BigDecimal.class),
            @Result(property = "rcptId", column = "rcpt_id", javaType = Integer.class),
    })
    Item findOneById(Integer itemId);

    @Insert("insert into item(type, name, description, quantity, amount) values (#{type}, #{name}, #{description}, #{quantity}, " +
            "#{amount})")
    @Options(useGeneratedKeys = true, keyColumn = "item_id", keyProperty = "itemId")
    void create(Item item);

    @Insert("insert into detail(version_id, item_id) values (#{versionId}, #{itemId})")
    void addDetail(Detail detail);

    @Delete("delete from detail where version_id = #{versionId} and item_id = #{itemId}")
    void deleteDetail(Detail detail);
}
