package com.yzq.springclouddemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;



@EnableDiscoveryClient
@SpringBootApplication
public class AuthorityApplication {
    public static void main(String[] args) {
     SpringApplication.run(AuthorityApplication.class,args);
    }
}
