package com.wombat.blw.DTO;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class SimpleAssignmentDTO {

    private String prjName;
    private Integer prjId;
    private Integer itemId;
    private String itemName;
    private String assigneeRealName;
    private String assignerRealName;
    private Integer status;
    private Integer quantity;
    private BigDecimal amount;
    private Date startTime;

    public SimpleAssignmentDTO() {
    }
}
