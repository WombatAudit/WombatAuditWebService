package com.wombat.blw.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class ProjectOverviewDTO {

    private Integer prjId;
    private String name;
    private String orgName;
    private Date startTime;
    private Date endTime;

    public ProjectOverviewDTO(Integer prjId, String name, String orgName, Date startTime, Date endTime) {
        this.prjId = prjId;
        this.name = name;
        this.orgName = orgName;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public ProjectOverviewDTO() {
    }
}
