package com.wombat.blw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan("com.wombat.blw.Mapper")
public class WombatAuditApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(WombatAuditApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(WombatAuditApplication.class);
    }
}
