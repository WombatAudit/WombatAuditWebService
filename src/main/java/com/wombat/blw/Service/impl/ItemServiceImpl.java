package com.wombat.blw.Service.impl;

import com.wombat.blw.DO.Item;
import com.wombat.blw.DO.Receipt;
import com.wombat.blw.DTO.DetailedItemDTO;
import com.wombat.blw.Mapper.ProjectMapper;
import com.wombat.blw.Service.ItemService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public DetailedItemDTO findDetailedItem(Integer prjId, Integer itemId) {
        DetailedItemDTO detailedItemDTO = new DetailedItemDTO();
        Item item = projectMapper.findItem(itemId);
        BeanUtils.copyProperties(item, detailedItemDTO);
        Receipt receipt = projectMapper.findReceipt(item.getRcptId());
        if (receipt == null) {
            receipt = new Receipt();
        }
        BeanUtils.copyProperties(receipt, detailedItemDTO);
        detailedItemDTO.setPrjName(projectMapper.findPrjName(prjId));
        return detailedItemDTO;
    }
}
