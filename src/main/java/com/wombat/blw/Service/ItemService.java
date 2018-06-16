package com.wombat.blw.Service;

import com.wombat.blw.DTO.DetailedItemDTO;

public interface ItemService {

    DetailedItemDTO findDetailedItem(Integer prjId, Integer itemId);
}
