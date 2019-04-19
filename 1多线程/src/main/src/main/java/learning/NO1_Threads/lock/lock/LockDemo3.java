package learning.NO1_Threads.lock.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo3 {
    volatile int i = 0;

    Lock lock = new ReentrantLock();

    public void add() {
        lock.lock();
        //lock.tryLock();   尝试一次,立即返回
        //lock.tryLock(10000, TimeUnit.SECONDS);   限时时间内尝试获取

        try {
            // TODO  很多业务操作
            i++;
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        LockDemo3 ld = new LockDemo3();

        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    ld.add();
                }
            }).start();
        }
        Thread.sleep(2000L);
        System.out.println(ld.i);
    }
}
