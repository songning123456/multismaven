package com.sn.springbootjava.service;

/**
 * @author sn
 */
public interface ZookeeperService {
    void selectNode() throws Exception;

    void deleteNode() throws Exception;

    void updateNode() throws Exception;

    void insertNode() throws Exception;

    void watchNode() throws Exception;
}
