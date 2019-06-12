package com.sn.springbootjava;

import com.sn.springbootjava.config.DataSourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;

/**
 * @author sn
 */
@Import(DataSourceConfig.class)
@SpringBootApplication(scanBasePackages = {"com.sn.springbootjava"}, exclude = {DataSourceAutoConfiguration.class})
public class SpringbootJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJavaApplication.class, args);
    }

}
