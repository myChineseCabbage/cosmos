package com.zxb.cosmos;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zxb.cosmos.mapper")
public class CosmosApplication {
    private static Logger logger = LoggerFactory.getLogger(CosmosApplication.class);
    public static void main(String[] args) {
        logger.debug("start......");
        SpringApplication.run(CosmosApplication.class, args);
    }

}
