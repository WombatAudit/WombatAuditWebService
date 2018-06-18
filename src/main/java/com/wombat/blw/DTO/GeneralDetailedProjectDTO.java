package com.wombat.blw.DTO;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class GeneralDetailedProjectDTO {

    private Integer prjId;
    private String name;
    private String description;
    private Date startTime;
    private Date endTime;
    private Integer status;
    private Date versionTime;
    private String versionTag;
    private Integer versionId;
    private List<ItemDTO> itemDTOList;
    private String orgName;
}
