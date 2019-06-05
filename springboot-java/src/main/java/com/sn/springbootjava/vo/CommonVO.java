package com.sn.springbootjava.vo;

import lombok.Data;

/**
 * @author sn
 */
@Data
public class CommonVO<T> {
    /**
     * 指定第一页开始记录号
     */
    private String recordStartNo;
    /**
     * 每页记录数
     */
    private String pageRecordNum;
    /**
     * 查询条件
     */
    private T condition;
}
