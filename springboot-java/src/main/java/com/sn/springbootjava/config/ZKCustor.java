package com.sn.springbootjava.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.springframework.stereotype.Component;

/**
 * @author sn
 */
@Component
@Slf4j
public class ZKCustor {

    public CuratorFramework client = null;

    private static final String ZOOKEEPER_SERVER = "127.0.0.1:2181";

    public void init() {
        if (client != null) {
            return;
        }

        // 创建重试策略
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 5);

        // 创建zookeeper客户端
        client = CuratorFrameworkFactory.builder().connectString(ZOOKEEPER_SERVER)
                .sessionTimeoutMs(10000)
                .retryPolicy(retryPolicy)
                .namespace("admin")
                .build();
        client.start();

        // 持久化节点
        try {
            if (client.checkExists().forPath("/head") == null) {
                client.create().creatingParentContainersIfNeeded()
                        .withMode(CreateMode.PERSISTENT)
                        .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                        .forPath("/head");
                log.info("zookeeper初始化成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("zookeeper初始化失败");
        }
    }
}
