package com.wombat.blw.Service;

import com.wombat.blw.DTO.DetailedAssignmentDTO;
import com.wombat.blw.DTO.MemberDTO;
import com.wombat.blw.DTO.SimpleAssignmentDTO;
import com.wombat.blw.Form.ReceiptForm;

import java.util.List;

public interface AssignmentService {

    List<SimpleAssignmentDTO> getListByPrjId(Integer prjId);

    List<SimpleAssignmentDTO> getListByUserId(Integer userId);

    DetailedAssignmentDTO getDetail(Integer itemId);

    void updateReceipt(Integer itemId, ReceiptForm receiptForm);

    void assign(Integer itemId, Integer userId);

    List<MemberDTO> findAssignmentReceiver(Integer itemId);

    List<MemberDTO> findMembersNotAssign(Integer orgId, Integer itemId);
}
