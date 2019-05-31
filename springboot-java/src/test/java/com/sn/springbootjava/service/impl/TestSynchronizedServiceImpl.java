package com.sn.springbootjava.service.impl;

import com.sn.springbootjava.entity.TestSynchronized;

/**
 * @author sn
 */
public class TestSynchronizedServiceImpl extends Thread {
    private TestSynchronized testSynchronized;

    public TestSynchronizedServiceImpl(TestSynchronized testSynchronized) {
        this.testSynchronized = testSynchronized;
    }

    @Override
    public void run() {
        testSynchronized.add("a");
    }
}
