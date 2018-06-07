package com.wombat.blw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wombat.blw.Mapper")
public class BlwApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlwApplication.class, args);
    }
}
