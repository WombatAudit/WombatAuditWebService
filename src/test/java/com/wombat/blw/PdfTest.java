package com.wombat.blw;

import com.wombat.blw.Service.CompanyService;
import com.wombat.blw.Service.impl.CompanyServiceImpl;
import org.junit.Test;

public class PdfTest {
    @Test
    public void test() throws Exception{
        CompanyServiceImpl companyService=new CompanyServiceImpl();
        companyService.javaToPdf();
    }
}
