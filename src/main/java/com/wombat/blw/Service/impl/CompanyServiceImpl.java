package com.wombat.blw.Service.impl;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.wombat.blw.DO.Company;
import com.wombat.blw.DTO.DetailedCompanyDTO;
import com.wombat.blw.DTO.SimpleCompanyDTO;
import com.wombat.blw.Form.CompanyForm;
import com.wombat.blw.Mapper.CompanyMapper;
import com.wombat.blw.Service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.itextpdf.text.pdf.PdfName.DEST;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyMapper companyMapper;

//    @Autowired
//    private WebSocket webSocket;

    @Override
    public List<SimpleCompanyDTO> getList() {
        //TODO
        return null;
    }

    @Override
    public DetailedCompanyDTO getDetail(Integer companyId) {
        //TODO
        return null;
    }

    @Override
    public void create(CompanyForm companyForm) {
        //TODO
    }

    @Override
    public void delete(Integer companyId) {
        //TODO
    }

    @Override
    public Integer getCoId(Integer userId) {
        //TODO
        return null;
    }

    private static final String DEST = "target/HelloWorld.pdf";
    public void javaToPdf() throws FileNotFoundException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(DEST));
        document.open();
        document.add(new Paragraph("ssss"));
        document.add(new Paragraph("tttt"));
        document.close();
        writer.close();
    }
}
