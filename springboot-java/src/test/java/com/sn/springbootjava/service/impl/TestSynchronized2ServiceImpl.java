package com.sn.springbootjava.service.impl;

import com.sn.springbootjava.entity.TestSynchronized;

/**
 * @author sn
 */
public class TestSynchronized2ServiceImpl extends Thread {
    private TestSynchronized testSynchronized;

    public TestSynchronized2ServiceImpl(TestSynchronized testSynchronized) {
        this.testSynchronized = testSynchronized;
    }

    @Override
    public void run() {
        testSynchronized.add("b");
    }
}
