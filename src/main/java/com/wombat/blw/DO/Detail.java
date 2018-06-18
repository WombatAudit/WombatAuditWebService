package com.wombat.blw.DO;

import lombok.Data;

@Data
public class Detail {

    private Integer versionId;
    private Integer itemId;

    public Detail(Integer versionId, Integer itemId) {
        this.versionId = versionId;
        this.itemId = itemId;
    }

    public Detail() {
    }
}
