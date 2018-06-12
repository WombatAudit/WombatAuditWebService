package com.wombat.blw.Mapper;

import com.wombat.blw.DO.Item;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DetailMapper {
    @Select("select item_id from detail where prj_id=#{prj_id}")
    List<Integer> getItemIdByPrjId(int prj_id);
    //TODO
}
