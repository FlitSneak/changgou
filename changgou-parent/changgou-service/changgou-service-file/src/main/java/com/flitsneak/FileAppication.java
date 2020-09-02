package com.flitsneak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})//继承的common依赖有启动数据库配置的嫌疑
@EnableEurekaClient
public class FileAppication {
    public static void main(String[] args) {
        SpringApplication.run(FileAppication.class,args);
    }
}
