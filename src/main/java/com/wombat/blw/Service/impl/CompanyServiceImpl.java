package com.wombat.blw.Service.impl;

<<<<<<< HEAD
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.wombat.blw.DO.Company;
=======
import com.wombat.blw.DO.Company;
import com.wombat.blw.DO.User;
>>>>>>> df37c87ba64d7a4a4d212c0e07bef85d08f62a5d
import com.wombat.blw.DTO.DetailedCompanyDTO;
import com.wombat.blw.DTO.SimpleCompanyDTO;
import com.wombat.blw.Form.CompanyForm;
import com.wombat.blw.Mapper.CompanyMapper;
import com.wombat.blw.Mapper.UserMapper;
import com.wombat.blw.Service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.itextpdf.text.pdf.PdfName.DEST;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyMapper companyMapper;
<<<<<<< HEAD

//    @Autowired
//    private WebSocket webSocket;
=======
    private UserMapper userMapper;
    @Autowired
    private WebSocket webSocket;
>>>>>>> df37c87ba64d7a4a4d212c0e07bef85d08f62a5d

    @Override
    public List<SimpleCompanyDTO> getList() {
        List<Company> list = companyMapper.getAll();
        List<SimpleCompanyDTO> newList = new ArrayList<>();
        for(Company c:list){
            SimpleCompanyDTO s=new SimpleCompanyDTO();
            s.setCompanyId(c.getCompanyId());
            s.setName(c.getName());
            newList.add(s);
        }
        return newList;
    }

    @Override
    public DetailedCompanyDTO getDetail(Integer companyId) {
    }

    @Override
    public void create(CompanyForm companyForm) {
    }

    @Override
    public void delete(Integer companyId) {
    }

    @Override
    public Integer getCoId(Integer userId) {
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
