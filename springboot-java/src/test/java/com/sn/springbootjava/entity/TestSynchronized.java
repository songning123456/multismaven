package com.sn.springbootjava.entity;

/**
 * @author sn
 */
public class TestSynchronized {
    private int num;

    synchronized public void add(String username) {
        try {
            if (username.equals("a")) {
                num = 100;
                System.out.println("add a end");
                Thread.sleep(2000);
            } else {
                num = 200;
                System.out.println("add b end");
            }
            System.out.println(username + " name " + num);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
