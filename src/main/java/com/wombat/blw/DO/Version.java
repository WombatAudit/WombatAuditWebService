package com.wombat.blw.DO;

import lombok.Data;
import java.util.Date;

@Data
public class Version {
    private int prjId;
    private int version;
    private Date createDate;
}
