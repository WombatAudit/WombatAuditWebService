package com.wombat.blw.Service.impl;

import com.sun.org.apache.xerces.internal.xs.ShortList;
import com.wombat.blw.DO.Assignment;
import com.wombat.blw.DO.Item;
import com.wombat.blw.DO.Receipt;
import com.wombat.blw.DO.User;
import com.wombat.blw.DTO.DetailedAssignmentDTO;
import com.wombat.blw.DTO.ReceiptDTO;
import com.wombat.blw.DTO.SimpleAssignmentDTO;
import com.wombat.blw.Form.ReceiptForm;
import com.wombat.blw.Mapper.*;
import com.wombat.blw.Service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.java2d.pipe.SpanShapeRenderer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class AssignmentServiceImpl implements AssignmentService {

    @Autowired
    private AssignmentMapper assignmentMapper;
    private DetailMapper detailMapper;
    private ItemMapper itemMapper;
    private UserMapper userMapper;
    private ReceiptMapper receiptMapper;

    @Override
    public List<SimpleAssignmentDTO> getListByPrjId(Integer prjId) {
        List<SimpleAssignmentDTO> res=new ArrayList<>();
        List<Integer> list=detailMapper.getItemIdByPrjId(prjId);
        for(Integer itemId:list){
            SimpleAssignmentDTO s=new SimpleAssignmentDTO();
            s.setItemId(itemId);
            s.setItemName(itemMapper.getItemNameByItemId(itemId));
            List<Assignment> itemList=assignmentMapper.getListByItemId(itemId);
            for(Assignment assignment:itemList){
                int userId=assignment.getUserId();
                s.setStatus(assignment.getStatus());
                s.setUserName(userMapper.getUserNameByUserId(userId));
                res.add(s);
            }
        }
//        private String itemId;
//        private String itemName;
//        private String userName;
//        private String status;
        return res;
    }

    @Override
    public List<SimpleAssignmentDTO> getListByUserId(Integer userId) {
        //TODO
        List<SimpleAssignmentDTO> res=new ArrayList<>();
        String userName=userMapper.getUserNameByUserId(userId);
        List<Assignment> list=assignmentMapper.getListByUserId(userId);
        for(Assignment assignment:list){
            int itemId=assignment.getItemId();
            int status=assignment.getStatus();
            String itemName=itemMapper.getItemNameByItemId(itemId);
            SimpleAssignmentDTO simpleAssignmentDTO=new SimpleAssignmentDTO();
            simpleAssignmentDTO.setStatus(status);
            simpleAssignmentDTO.setUserName(userName);
            simpleAssignmentDTO.setItemName(itemName);
            simpleAssignmentDTO.setItemId(itemId);
            res.add(simpleAssignmentDTO);
        }
        return res;
    }

    @Override
    public DetailedAssignmentDTO getDetail(Integer itemId) {
        //TODO
        Item item=itemMapper.getItemByItemId(itemId);
        Receipt receipt=receiptMapper.getReceiptByRcptId(item.getRcptId());
        ReceiptDTO receiptDTO=new ReceiptDTO();
        receiptDTO.setRcptId(receipt.getRcptId());
        receiptDTO.setAttachment(receipt.getAttachment());
        receiptDTO.setInvoice(receipt.getInvoice());
        receiptDTO.setReceipt(receipt.getReceipt());
        receiptDTO.setTransaction(receipt.getTransaction());
        DetailedAssignmentDTO detailedAssignmentDTO=new DetailedAssignmentDTO();
        detailedAssignmentDTO.setReceiptDTO(receiptDTO);
        detailedAssignmentDTO.setAmount(item.getAmount());
        detailedAssignmentDTO.setDescription(item.getDescription());
        detailedAssignmentDTO.setName(item.getName());
        detailedAssignmentDTO.setQuantity(item.getQuantity());
        detailedAssignmentDTO.setType(item.getType());
        return detailedAssignmentDTO;

//        private String type;
//        private String name;
//        private String description;
//        private Integer quantity;
//        private BigDecimal amount;
//        private ReceiptDTO receiptDTO;
    }

    @Override
    public void updateReceipt(Integer itemId, ReceiptForm receiptForm) {
        //TODO
        Item item=itemMapper.getItemByItemId(itemId);
        receiptMapper.updateReceipt(item.getRcptId(),receiptForm);
    }
}
