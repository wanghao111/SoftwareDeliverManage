package com.software.deliver.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author wanghao
 * @version 1.0
 * @date 2022/4/16 12:08
 */
@SpringBootApplication(scanBasePackages="com.software.deliver",exclude= DataSourceAutoConfiguration.class)
//@MapperScan("com.software.deliver.dal.mapper")
public class ApplicationStarter {


    public static void main(String[] args) {
        SpringApplication.run(ApplicationStarter.class, args);
    }

}
