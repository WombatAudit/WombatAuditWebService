package com.wombat.blw.DO;

import lombok.Data;

import java.util.Date;

@Data
public class Version {

    private int versionId;
    private String tag;
    private Date createTime;
}
