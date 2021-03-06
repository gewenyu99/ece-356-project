package com.ece356.demographics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemographicsApplication {

    public static final int PAGE_SIZE = 30;

    public static void main(String[] args) {
        SpringApplication.run(DemographicsApplication.class, args);
    }


}
