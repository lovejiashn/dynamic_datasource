package com.jiashn.dynamic_datasource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class DynamicDatasourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DynamicDatasourceApplication.class, args);
    }

}
