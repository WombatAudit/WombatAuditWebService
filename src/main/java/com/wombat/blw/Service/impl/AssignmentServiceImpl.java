package com.wombat.blw.Service.impl;

import com.wombat.blw.DTO.DetailedAssignmentDTO;
import com.wombat.blw.DTO.SimpleAssignmentDTO;
import com.wombat.blw.Form.ReceiptForm;
import com.wombat.blw.Service.AssignmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentServiceImpl implements AssignmentService {

    @Override
    public List<SimpleAssignmentDTO> getListByPrjId(Integer prjId) {
        //TODO
        return null;
    }

    @Override
    public List<SimpleAssignmentDTO> getListByUserId(Integer userId) {
        //TODO
        return null;
    }

    @Override
    public DetailedAssignmentDTO getDetail(Integer itemId) {
        //TODO
        return null;
    }

    @Override
    public void updateReceipt(Integer itemId, ReceiptForm receiptForm) {
        //TODO
    }
}
