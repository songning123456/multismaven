package com.sn.springbootjava.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author sn
 * 需要配置的拦截器，静态资源, zookeper初始化操作...
 */
@Component
public class WebMVCConfig {

    /*@Bean(initMethod = "init")
    public ZKCustor zkCustor() {
        return new ZKCustor();
    }*/
}
