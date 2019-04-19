package com.study.test;

import java.util.concurrent.locks.LockSupport;

/**
 * @Author by xiongYiMing on 2019/4/19 16:42
 */
public class unpark {
    public static void main(String[] args) {
        LockSupport.unpark(Thread.currentThread());
        LockSupport.park();
        System.out.println("hello");
        LockSupport.unpark(Thread.currentThread());
        LockSupport.unpark(Thread.currentThread());
        LockSupport.park();
        LockSupport.park();
        System.out.println("hello");
    }
}
