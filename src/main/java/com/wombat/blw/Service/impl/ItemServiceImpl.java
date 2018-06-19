package com.wombat.blw.Service.impl;

import com.wombat.blw.DO.*;
import com.wombat.blw.DTO.DetailedItemDTO;
import com.wombat.blw.DTO.ItemDTO;
import com.wombat.blw.Form.ItemForm;
import com.wombat.blw.Form.ReceiptForm;
import com.wombat.blw.Mapper.ItemMapper;
import com.wombat.blw.Mapper.OrganizationMapper;
import com.wombat.blw.Mapper.ProjectMapper;
import com.wombat.blw.Mapper.ReceiptMapper;
import com.wombat.blw.Service.ItemService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private OrganizationMapper organizationMapper;

    @Autowired
    private ReceiptMapper receiptMapper;

    @Override
    public DetailedItemDTO findDetailedItem(Integer prjId, Integer itemId) {
        DetailedItemDTO detailedItemDTO = new DetailedItemDTO();
        Item item = projectMapper.findItem(itemId);
        BeanUtils.copyProperties(item, detailedItemDTO);
        Receipt receipt = receiptMapper.findReceipt(item.getRcptId());
        if (receipt == null) {
            receipt = new Receipt();
        }
        BeanUtils.copyProperties(receipt, detailedItemDTO);
        Project project = projectMapper.findById(prjId);
        detailedItemDTO.setPrjId(prjId);
        detailedItemDTO.setPrjName(project.getName());
        detailedItemDTO.setOrgName(organizationMapper.findOrgNameById(project.getOrgId()));
        return detailedItemDTO;
    }

    @Override
    public Item createItem(Integer versionId, ItemForm itemForm) {
        Item item = new Item();
        BeanUtils.copyProperties(itemForm, item);
        itemMapper.create(item);
        Detail detail = new Detail(versionId, item.getItemId());
        itemMapper.addDetail(detail);
        return itemMapper.findOneById(item.getItemId());
    }

    @Override
    public void deleteItemFromDetail(Integer versionId, Integer itemId) {
        Detail detail = new Detail(versionId, itemId);
        itemMapper.deleteDetail(detail);
    }

    @Override
    public ItemDTO findOne(Integer prjId, Integer itemId) {
        Item item = itemMapper.findOneById(itemId);
        ItemDTO itemDTO = new ItemDTO();
        BeanUtils.copyProperties(item, itemDTO);
        itemDTO.setPrjId(prjId);
        itemDTO.setPrjName(projectMapper.findPrjName(prjId));
        return itemDTO;
    }

    @Override
    public Receipt createReceipt(ReceiptForm receiptForm) {
        Receipt receipt = new Receipt();
        BeanUtils.copyProperties(receiptForm, receipt);
        receiptMapper.createReceipt(receipt);
        return receiptMapper.findReceipt(receipt.getRcptId());
    }

}
