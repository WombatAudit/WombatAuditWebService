package com.wombat.blw.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class ProjectOverviewDTO {

    private String name;
    private String orgName;
    private Date startTime;
    private Date endTime;
}
