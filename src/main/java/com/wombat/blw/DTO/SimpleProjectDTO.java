package com.wombat.blw.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class SimpleProjectDTO {

    private Integer projectId;
    private String name;
    private Integer status;
    private Date createTime;
    private Date endTime;

    public SimpleProjectDTO(Integer projectId, String name, Integer status, Date createTime, Date endTime) {
        this.projectId = projectId;
        this.name = name;
        this.status = status;
        this.createTime = createTime;
        this.endTime = endTime;
    }

    public SimpleProjectDTO() {
    }
}
