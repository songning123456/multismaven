package com.sn.springbootjava.service;

import com.sn.springbootjava.annotation.DataSourceAnnotation;

/**
 * @author sn
 */
public interface DataSourceAspectService {
    void doBefore(DataSourceAnnotation dataSourceAnnotation);

    void doAfter();

}
