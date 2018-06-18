package com.wombat.blw.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class ProjectDTO {

    private Integer prjId;
    private String name;
    private Integer status;
    private Date createTime;
    private Date endTime;
    private String description;

    public ProjectDTO(Integer prjId, String name, Integer status, Date createTime, Date endTime, String description) {
        this.prjId = prjId;
        this.name = name;
        this.status = status;
        this.createTime = createTime;
        this.endTime = endTime;
        this.description = description;
    }

    public ProjectDTO() {
    }
}
