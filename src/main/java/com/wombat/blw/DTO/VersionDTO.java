package com.wombat.blw.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class VersionDTO {

    private Integer versionId;
    private String tag;
    private Date date;

    public VersionDTO(Integer versionId, String tag, Date date) {
        this.versionId = versionId;
        this.tag = tag;
        this.date = date;
    }

    public VersionDTO() {
    }
}
