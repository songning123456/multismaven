package com.sn.springbootjava.service.impl;

import com.sn.springbootjava.annotation.DataSourceAnnotation;
import com.sn.springbootjava.config.DynamicDataSource;
import com.sn.springbootjava.constant.DataSourceTypeConstant;
import com.sn.springbootjava.service.DataSourceAspectService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

/**
 * @author sn
 */
@Service
@Aspect
@Slf4j
public class DataSourceAspectServiceImpl implements DataSourceAspectService {

    @Pointcut("@annotation(com.sn.springbootjava.annotation.DataSourceAnnotation)")
    public void pointcut() {
    }

    @Before(value = "pointcut() && @annotation(dataSourceAnnotation)", argNames = "dataSourceAnnotation")
    @Override
    public void doBefore(DataSourceAnnotation dataSourceAnnotation) {
        String dataSource = dataSourceAnnotation.value();
        if (!"".equals(dataSource)) {
            log.info("从主数据源->切换到->从数据源（{}）", dataSource);
            DynamicDataSource.setDataSourceKey(dataSource);
        }
    }

    @After("pointcut()")
    @Override
    public void doAfter() {
        DynamicDataSource.cleanDataSourceKey();
        log.info("恢复主数据源");
        DynamicDataSource.setDataSourceKey(DataSourceTypeConstant.MASTER);
    }
}
