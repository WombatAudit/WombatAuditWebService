package com.wombat.blw.DO;

import lombok.Data;

import java.util.Date;

@Data
public class Project {
    private int prjId;
    private int orgId;
    private String name;
    private String description;
    private Date startTime;
    private int status;
    private Date endTime;
    private Integer versionId;
}
