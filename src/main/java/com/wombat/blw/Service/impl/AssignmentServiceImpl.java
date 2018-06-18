package com.wombat.blw.Service.impl;

import com.wombat.blw.DO.Assignment;
import com.wombat.blw.DO.Item;
import com.wombat.blw.DO.Receipt;
import com.wombat.blw.DO.User;
import com.wombat.blw.DTO.DetailedAssignmentDTO;
import com.wombat.blw.DTO.MemberDTO;
import com.wombat.blw.DTO.ReceiptDTO;
import com.wombat.blw.DTO.SimpleAssignmentDTO;
import com.wombat.blw.Enum.AssignmentStatusEnum;
import com.wombat.blw.Form.ReceiptForm;
import com.wombat.blw.Mapper.*;
import com.wombat.blw.Service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssignmentServiceImpl implements AssignmentService {

    @Autowired
    private AssignmentMapper assignmentMapper;

    @Autowired
    private DetailMapper detailMapper;

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ReceiptMapper receiptMapper;

    @Override
    public List<SimpleAssignmentDTO> getListByPrjId(Integer prjId) {
        List<SimpleAssignmentDTO> res = new ArrayList<>();
        List<Integer> list = detailMapper.getItemIdByPrjId(prjId);
        for (Integer itemId : list) {
            SimpleAssignmentDTO s = new SimpleAssignmentDTO();
            s.setItemId(itemId);
            s.setItemName(itemMapper.getItemNameByItemId(itemId));
            List<Assignment> itemList = assignmentMapper.getListByItemId(itemId);
            for (Assignment assignment : itemList) {
                int userId = assignment.getUserId();
                s.setStatus(assignment.getStatus());
                s.setUserName(userMapper.findUserNameByUserId(userId));
                res.add(s);
            }
        }
        return res;
    }

    @Override
    public List<SimpleAssignmentDTO> getListByUserId(Integer userId) {
        List<SimpleAssignmentDTO> res = new ArrayList<>();
        String userName = userMapper.findUserNameByUserId(userId);
        List<Assignment> list = assignmentMapper.getListByUserId(userId);
        for (Assignment assignment : list) {
            int itemId = assignment.getItemId();
            int status = assignment.getStatus();
            String itemName = itemMapper.getItemNameByItemId(itemId);
            SimpleAssignmentDTO simpleAssignmentDTO = new SimpleAssignmentDTO();
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
        Item item = itemMapper.findOneById(itemId);
        Receipt receipt = receiptMapper.getReceiptByRcptId(item.getRcptId());
        ReceiptDTO receiptDTO = new ReceiptDTO();
        receiptDTO.setRcptId(receipt.getRcptId());
        receiptDTO.setAttachment(receipt.getAttachment());
        receiptDTO.setInvoice(receipt.getInvoice());
        receiptDTO.setReceipt(receipt.getReceipt());
        receiptDTO.setTransaction(receipt.getTransaction());
        DetailedAssignmentDTO detailedAssignmentDTO = new DetailedAssignmentDTO();
        detailedAssignmentDTO.setReceiptDTO(receiptDTO);
        detailedAssignmentDTO.setAmount(item.getAmount());
        detailedAssignmentDTO.setDescription(item.getDescription());
        detailedAssignmentDTO.setName(item.getName());
        detailedAssignmentDTO.setQuantity(item.getQuantity());
        detailedAssignmentDTO.setType(item.getType());
        return detailedAssignmentDTO;
    }

    @Override
    public void updateReceipt(Integer itemId, ReceiptForm receiptForm) {
        Item item = itemMapper.findOneById(itemId);
        receiptMapper.updateReceipt(item.getRcptId(), receiptForm);
    }

    @Override
    public void assign(Integer itemId, Integer userId) {
        Assignment assignment = new Assignment(userId, itemId);
        assignment.setStatus(AssignmentStatusEnum.IN_PROGRESS.getCode());
        assignmentMapper.create(assignment);
    }

    @Override
    public List<MemberDTO> findAssignmentReceiver(Integer itemId) {
        List<Assignment> assignmentList = assignmentMapper.getListByItemId(itemId);
        return assignmentList.stream().map(e -> new MemberDTO(e.getUserId(), userMapper.findRealNameByUserId(e.getUserId())))
                .collect(Collectors.toList());
    }

    @Override
    public List<MemberDTO> findMembersNotAssign(Integer orgId, Integer itemId) {
        List<User> userList = userMapper.findGeneralMembersInOrgNotAssigned(orgId, itemId);
        return userList.stream().map(e -> new MemberDTO(e.getUserId(), e.getRealName())).collect(Collectors.toList());
    }
}
