package com.wombat.blw.DTO;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class AdminDetailedProjectDTO {

    private Integer prjId;
    private String name;
    private String description;
    private Date startTime;
    private Date endTime;
    private Integer status;
    private Date versionTime;
    private List<SimpleItemDTO> simpleItemDTOList;
    private String orgName;
    private BigDecimal totalCost;
}
