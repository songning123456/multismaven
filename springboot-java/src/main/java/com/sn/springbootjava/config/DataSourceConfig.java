package com.sn.springbootjava.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.google.common.collect.Maps;
import com.sn.springbootjava.constant.DataSourceTypeConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @author sn
 */
@Slf4j
@Configuration
public class DataSourceConfig {

    @Bean(initMethod = "init", destroyMethod = "close")
    @ConfigurationProperties("spring.datasource.druid.primary-data")
    public DruidDataSource primaryDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(initMethod = "init", destroyMethod = "close")
    @ConfigurationProperties("spring.datasource.druid.second-data")
    public DruidDataSource secondDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public DynamicDataSource dataSource(@Qualifier("primaryDataSource") DataSource primaryDataSource, @Qualifier("secondDataSource") DataSource secondDataSource) {
        Map<String, DataSource> pool = Maps.newHashMapWithExpectedSize(2);
        pool.put(DataSourceTypeConstant.MASTER, primaryDataSource);
        pool.put(DataSourceTypeConstant.FIRST_FOLLOWER, secondDataSource);
        // 可以在这里修改默认的数据源配置
        return new DynamicDataSource(pool, primaryDataSource);
    }
}
