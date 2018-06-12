package com.wombat.blw;

import com.wombat.blw.Mapper.CompanyMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@AutoConfigureTestDatabase
@SpringBootTest
public class BlwApplicationTests {
    @Autowired
    private CompanyMapper companyMapper;
    @Test
    public void contextLoads() {


        companyMapper.getAll();
    }

}
