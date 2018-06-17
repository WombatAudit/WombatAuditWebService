package com.wombat.blw.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class SimpleProjectDTO {

    private Integer prjId;
    private String name;
    private Integer status;
    private Date createTime;
    private Date endTime;

    public SimpleProjectDTO(Integer prjId, String name, Integer status, Date createTime, Date endTime) {
        this.prjId = prjId;
        this.name = name;
        this.status = status;
        this.createTime = createTime;
        this.endTime = endTime;
    }

    public SimpleProjectDTO() {
    }
}
