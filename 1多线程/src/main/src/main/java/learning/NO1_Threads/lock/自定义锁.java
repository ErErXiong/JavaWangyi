package learning.NO1_Threads.lock;

import java.util.Iterator;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * @Author by xiongYiMing on 2019/3/19 15:02
 */
public class 自定义锁 implements Lock {
    // 1. 定义一个owner
    volatile AtomicReference<Thread> owner = new AtomicReference<>();
    // 2. 定义一个线程等待队列
    volatile LinkedBlockingQueue<Thread> waiters = new LinkedBlockingQueue();

    @Override
    public boolean tryLock() {


        // 当前值为null,就把当前线程放入owner中
        return owner.compareAndSet(null,Thread.currentThread());
    }

    @Override
    public void lock() {
        // 没有抢到锁的进入等待队列
        while (!tryLock()) {
            // 项队列尾部插入一条数据, 若控件满,则返回false
            waiters.offer(Thread.currentThread());
            // 其他所有线程 进入挂起状态 (抢到锁的是不会进入此循环的)
            LockSupport.park();
            // 当线程被唤醒,则从waiters里移除
            waiters.remove(Thread.currentThread());
        }
    }

    @Override
    public void unlock() {
        // 解锁,若成功,则唤醒其他线程
        if (owner.compareAndSet(Thread.currentThread(), null)) {
            // 唤醒其他线程
            Iterator<Thread> iterable = waiters.iterator();
            if (iterable.hasNext()) {
                LockSupport.unpark(iterable.next());
            }
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
