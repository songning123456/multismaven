package com.sn.springbootjava.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author sn
 */
@Configuration
@EnableConfigurationProperties({ServerProperties.class})
public class ApplicationConfig {

}
