package com.wombat.blw.Service;

import com.wombat.blw.DTO.MemberDTO;
import com.wombat.blw.DTO.SimpleAssignmentDTO;
import com.wombat.blw.Form.ReceiptForm;

import java.util.List;

public interface AssignmentService {

    void create(Integer itemId, Integer assigneeId, Integer assignerId);

    List<MemberDTO> findAssigneeList(Integer itemId);

    List<MemberDTO> findMembersNotAssign(Integer orgId, Integer itemId);

    List<SimpleAssignmentDTO> findSimpleAssigned(Integer assignerId);

    List<SimpleAssignmentDTO> findSimpleAssignedInStatus(Integer assignerId, Integer status);

    List<SimpleAssignmentDTO> findSimpleReceivedInStatus(Integer assignerId, Integer status);

    void assignmentSubmit(ReceiptForm receiptForm, Integer itemId, Integer userId);
}
