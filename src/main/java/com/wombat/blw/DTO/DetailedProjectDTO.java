package com.wombat.blw.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class DetailedProjectDTO {

    private String name;
    private String description;
    private Date startTime;
    private String statues;
}
