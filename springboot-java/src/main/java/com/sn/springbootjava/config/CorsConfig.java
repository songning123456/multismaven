package com.sn.springbootjava.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author sn
 * 解決跨域
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    /**
     * 添加跨域设置
     *
     * @param corsRegistry
     */
    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        corsRegistry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "PUT", "POST", "DELETE", "OPTIONS")
                .maxAge(3600);
    }

    @Bean
    public UrlInterceptor getUrlInterceptor() {
        return new UrlInterceptor();
    }

    /**
     * 自定义拦截器，并指定拦截路径
     * /** 代表所有路径，也可以指定路径 /xxx
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.getUrlInterceptor()).addPathPatterns("/**");
    }
}
