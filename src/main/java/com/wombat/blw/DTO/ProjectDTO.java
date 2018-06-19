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
    private Integer versionId;

    public ProjectDTO() {
    }
}
