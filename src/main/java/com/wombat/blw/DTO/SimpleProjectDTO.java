package com.wombat.blw.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class SimpleProjectDTO {

    private Integer prjId;
    private Integer orgId;
    private String name;
    private Integer status;
    private Date createTime;
    private Date endTime;

    public SimpleProjectDTO(Integer prjId, Integer orgId, String name, Integer status, Date createTime, Date endTime) {
        this.prjId = prjId;
        this.orgId = orgId;
        this.name = name;
        this.status = status;
        this.createTime = createTime;
        this.endTime = endTime;
    }

    public SimpleProjectDTO() {
    }
}
