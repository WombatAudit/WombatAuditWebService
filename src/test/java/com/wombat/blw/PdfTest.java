package com.wombat.blw;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.wombat.blw.DTO.SimpleCompanyDTO;
import com.wombat.blw.Service.CompanyService;
import com.wombat.blw.Service.impl.CompanyServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileOutputStream;
import java.util.List;

@RunWith(SpringRunner.class)
//@WebMvcTest
@SpringBootTest
public class PdfTest {

    private static final String DEST = "target/HelloWorld.pdf";

    @Autowired
    private CompanyService companyService;

    @Test
    public void test() throws Exception {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(DEST));
        document.open();
        List<SimpleCompanyDTO> simpleCompanyDTOList = companyService.getList();
        for (SimpleCompanyDTO simpleCompanyDTO : simpleCompanyDTOList) {
            document.add(new Paragraph(simpleCompanyDTO.toString()));
        }
        document.close();
        writer.close();
    }
}
