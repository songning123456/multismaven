package com.sn.springbootjava.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sn
 */
@Data
@PropertySource("classpath:application-dev.yml")
@ConfigurationProperties("info")
public class ServerProperties {

    private final App app = new App();

    @Data
    public static class App {
        private String name;
        private String threadCount;
        private List<String> users = new ArrayList<>();
    }
}
