package com.sn.springbootjava.constant;

/**
 * @author an
 */
public interface DataSourceTypeConstant {
    /**
     * 主数据源
     */
    String MASTER = "MASTER";
    /**
     * 第1从数据源
     */
    String FIRST_FOLLOWER = "FIRST_FOLLOWER";
    /**
     * 第2从数据源
     */
    String SECOND_FOLLOWER = "SECOND_FOLLOWER";
}
