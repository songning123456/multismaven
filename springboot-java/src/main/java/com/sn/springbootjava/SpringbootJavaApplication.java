package com.sn.springbootjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author sn
 */
@SpringBootApplication(scanBasePackages = {"com.sn.springbootjava"})
public class SpringbootJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJavaApplication.class, args);
    }

}
