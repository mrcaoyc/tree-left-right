package com;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author CaoYongCheng
 */
@SpringBootApplication
@EnableSwagger2Doc
@EnableJpaRepositories
public class TreeApplication {
    public static void main(String[] args) {
        SpringApplication.run(TreeApplication.class, args);
    }
}
