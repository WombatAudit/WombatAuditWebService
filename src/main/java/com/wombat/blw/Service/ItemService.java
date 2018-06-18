package com.wombat.blw.Service;

import com.wombat.blw.DO.Item;
import com.wombat.blw.DTO.DetailedItemDTO;
import com.wombat.blw.DTO.ItemDTO;
import com.wombat.blw.Form.ItemForm;

public interface ItemService {

    DetailedItemDTO findDetailedItem(Integer prjId, Integer itemId);

    Item createItem(Integer versionId, ItemForm itemForm);

    void deleteItemFromDetail(Integer versionId, Integer itemId);

    ItemDTO findOne(Integer prjId, Integer itemId);
}
