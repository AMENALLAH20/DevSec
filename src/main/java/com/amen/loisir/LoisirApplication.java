package com.amen.loisir;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ws.config.annotation.EnableWs;

@SpringBootApplication
@EnableWs
public class LoisirApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoisirApplication.class, args);
    }

}
