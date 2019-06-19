package com.sn.springbootjava.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;

import java.nio.charset.Charset;

/**
 * 转换默认值(序列化时使用fastJson)
 *
 * @author sn
 */
//@Configuration
public class HttpMessageConverterConfig {

    /**
     * 引入fast-json解析json，不使用默认的jackson
     * 必须在pom.xml引入fast-json的jar包，并且版必须大于1.2.10
     *
     * @return
     */
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverts() {
        // 定义一个convert转换消息的对象
        FastJsonHttpMessageConverter fastJsonHttpMessageConvert = new FastJsonHttpMessageConverter();

        // 添加fast-json的配置信息
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        SerializerFeature[] serializerFeatures = new SerializerFeature[]{
                // 输出key是包含双引号
                SerializerFeature.QuoteFieldNames,
                // 是否输出为null的字段,若为null 则显示该字段
                SerializerFeature.WriteMapNullValue,
                // 数值字段如果为null，则输出为0
                SerializerFeature.WriteNullNumberAsZero,
                // List字段如果为null,输出为[],而非null
                SerializerFeature.WriteNullListAsEmpty,
                // 字符类型字段如果为null,输出为"",而非null
                SerializerFeature.WriteNullStringAsEmpty,
                // Boolean字段如果为null,输出为false,而非null
                SerializerFeature.WriteNullBooleanAsFalse,
                // Date的日期转换器
                SerializerFeature.WriteDateUseDateFormat,
                // 循环引用
                SerializerFeature.DisableCircularReferenceDetect,
        };

        fastJsonConfig.setSerializerFeatures(serializerFeatures);
        fastJsonConfig.setCharset(Charset.forName("UTF-8"));

        //3、在convert中添加配置信息
        fastJsonHttpMessageConvert.setFastJsonConfig(fastJsonConfig);

        //4、将convert添加到converters中
        HttpMessageConverter<?> httpMessageConverter = fastJsonHttpMessageConvert;

        return new HttpMessageConverters(httpMessageConverter);
    }
}
