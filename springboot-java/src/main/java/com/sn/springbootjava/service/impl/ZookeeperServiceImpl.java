package com.sn.springbootjava.service.impl;

import com.sn.springbootjava.config.ZKCustor;
import com.sn.springbootjava.service.ZookeeperService;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.zookeeper.CreateMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sn
 */
@Service
@Slf4j
public class ZookeeperServiceImpl implements ZookeeperService {

    @Autowired
    ZKCustor zkCustor;

    /**
     * 查询节点数据
     *
     * @throws Exception
     */
    @Override
    public void selectNode() throws Exception {
        byte[] bytes = zkCustor.client.getData().forPath("/demo/first");
        log.info("查询节点数据： {}", bytes);
    }

    /**
     * 删除节点数据
     *
     * @throws Exception
     */
    @Override
    public void deleteNode() throws Exception {
        zkCustor.client.delete().forPath("/demo/first");
    }

    /**
     * 修改数据
     *
     * @throws Exception
     */
    @Override
    public void updateNode() throws Exception {
        zkCustor.client.setData().forPath("/demo/first", "修改后的数据".getBytes());
    }

    /**
     * 创建数据
     *
     * @throws Exception
     */
    @Override
    public void insertNode() throws Exception {
        zkCustor.client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/demo/first");
    }

    @Override
    public void watchNode() throws Exception {
        TreeCache treeCache = new TreeCache(zkCustor.client, "/");
        treeCache.getListenable().addListener(new TreeCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, TreeCacheEvent treeCacheEvent) throws Exception {
                ChildData childData = treeCacheEvent.getData();
                if (childData != null) {
                    switch (treeCacheEvent.getType()) {
                        case NODE_ADDED:
                            log.info("节点新增路径: {}, 节点新增数据: {}", childData.getPath(), new String(childData.getData()));
                            break;
                        case NODE_REMOVED:
                            log.info("节点删除路径: {}, 节点删除数据: {}", childData.getPath(), new String(childData.getData()));
                            break;
                        case NODE_UPDATED:
                            log.info("节点更新路径: {}, 节点更新数据: {}", childData.getPath(), new String(childData.getData()));
                            break;
                        default:
                            break;
                    }
                }
            }
        });
        // 开始监听
        treeCache.start();
        // 让程序不结束,一直监听
        Thread.sleep(900000000);
    }
}
