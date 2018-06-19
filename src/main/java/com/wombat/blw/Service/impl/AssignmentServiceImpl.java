package com.wombat.blw.Service.impl;

import com.wombat.blw.DO.Assignment;
import com.wombat.blw.DO.Item;
import com.wombat.blw.DO.Receipt;
import com.wombat.blw.DO.User;
import com.wombat.blw.DTO.MemberDTO;
import com.wombat.blw.DTO.SimpleAssignmentDTO;
import com.wombat.blw.Enum.AssignmentStatusEnum;
import com.wombat.blw.Form.ReceiptForm;
import com.wombat.blw.Mapper.*;
import com.wombat.blw.Service.AssignmentService;
import org.springframework.beans.BeanUtils;
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
    private ItemMapper itemMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ReceiptMapper receiptMapper;

    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public void create(Integer itemId, Integer assigneeId, Integer assignerId) {
        Assignment assignment = new Assignment();
        assignment.setItemId(itemId);
        assignment.setAssigneeId(assigneeId);
        assignment.setAssignerId(assignerId);
        assignment.setStatus(AssignmentStatusEnum.IN_PROGRESS.getCode());
        assignmentMapper.create(assignment);
    }

    @Override
    public List<MemberDTO> findAssigneeList(Integer itemId) {
        List<Assignment> assignmentList = assignmentMapper.findListByItemId(itemId);
        return assignmentList.stream().map(e -> new MemberDTO(e.getAssigneeId(), userMapper.findRealNameByUserId(e.getAssigneeId())))
                .collect(Collectors.toList());
    }

    @Override
    public List<MemberDTO> findMembersNotAssign(Integer orgId, Integer itemId) {
        List<User> userList = userMapper.findGeneralMembersInOrgNotAssigned(orgId, itemId);
        return userList.stream().map(e -> new MemberDTO(e.getUserId(), e.getRealName())).collect(Collectors.toList());
    }

    @Override
    public List<SimpleAssignmentDTO> findSimpleAssigned(Integer assignerId) {
        List<Assignment> assignmentList = assignmentMapper.findListByAssignerId(assignerId);
        return toSimpleDTOList(assignmentList);
    }

    @Override
    public List<SimpleAssignmentDTO> findSimpleAssignedInStatus(Integer assignerId, Integer status) {
        List<Assignment> assignmentList = assignmentMapper.findListByAssignerIdAndStatus(assignerId, status);
        return toSimpleDTOList(assignmentList);
    }

    @Override
    public List<SimpleAssignmentDTO> findSimpleReceivedInStatus(Integer assigneeId, Integer status) {
        List<Assignment> assignmentList = assignmentMapper.findListByAssigneeIdAndStatus(assigneeId, status);
        return toSimpleDTOList(assignmentList);
    }

    private SimpleAssignmentDTO toSimpleDTO(Assignment assignment) {
        if (assignment == null) {
            return null;
        }
        SimpleAssignmentDTO simpleAssignmentDTO = new SimpleAssignmentDTO();
        Item item = itemMapper.findOneById(assignment.getItemId());
        BeanUtils.copyProperties(item, simpleAssignmentDTO);
        simpleAssignmentDTO.setItemName(item.getName());
        Integer prjId = itemMapper.findItemPrjId(assignment.getItemId());
        simpleAssignmentDTO.setPrjId(prjId);
        simpleAssignmentDTO.setPrjName(projectMapper.findPrjName(prjId));
        simpleAssignmentDTO.setAssigneeRealName(userMapper.findUserNameByUserId(assignment.getAssigneeId()));
        simpleAssignmentDTO.setAssignerRealName(userMapper.findUserNameByUserId(assignment.getAssignerId()));
        simpleAssignmentDTO.setStartTime(assignment.getStartTime());
        return simpleAssignmentDTO;
    }

    private List<SimpleAssignmentDTO> toSimpleDTOList(List<Assignment> assignmentList) {
        List<SimpleAssignmentDTO> simpleAssignmentDTOList = new ArrayList<>();
        for (Assignment assignment : assignmentList) {
            SimpleAssignmentDTO simpleAssignmentDTO = toSimpleDTO(assignment);
            simpleAssignmentDTOList.add(simpleAssignmentDTO);
        }
        return simpleAssignmentDTOList;
    }

    @Override
    public void assignmentSubmit(ReceiptForm receiptForm, Integer itemId, Integer userId) {
        Receipt receipt = new Receipt();
        BeanUtils.copyProperties(receiptForm, receipt);
        receiptMapper.createReceipt(receipt);
        receiptMapper.addReceipt(itemId, userId, receipt.getRcptId());
        itemMapper.updateItemReceipt(receipt.getRcptId(), itemId);
        assignmentMapper.updateStatus(itemId, userId, AssignmentStatusEnum.SUBMITTED.getCode());

    }
}
