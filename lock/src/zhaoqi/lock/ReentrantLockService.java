package zhaoqi.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 2 * @Author: zhaoqi
 * 3 * @Date: 2019/7/19 0019 PM 2:18
 * 4
 */
public class ReentrantLockService {

    ReentrantLock reentrantLock = new ReentrantLock();


    public void test1() {
        System.out.println(String.format("@in>>>>>>>>>>>>>线程[%s]开始获取锁对象", Thread.currentThread().getId()));
        reentrantLock.lock();
        System.out.println(String.format("@out>>>>>>>>>>>>>线程[%s]成功获取锁对象", Thread.currentThread().getId()));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(String.format("@in>>>>>>>>>>>>>线程[%s]放弃锁资源", Thread.currentThread().getId()));
        reentrantLock.unlock();
    }

    public void test2() throws InterruptedException {
        System.out.println(String.format("@in>>>>>>>>>>>>>线程[%s]开始获取锁对象", Thread.currentThread().getId()));
        reentrantLock.lock();
        System.out.println(String.format("@out>>>>>>>>>>>>>线程[%s]成功获取锁对象", Thread.currentThread().getId()));
        System.out.println(String.format("@out>>>>>>>>>>>>>线程[%s]调用await方法等待通知", Thread.currentThread().getId()));

        reentrantLock.newCondition().await();//阻塞 等待notify通知
        System.out.println(String.format("@out>>>>>>>>>>>>>线程[%s]调用await方法结束，重新获得所资源", Thread.currentThread().getId()));

    }

    public static void main(String[] args) {
        ReentrantLockService reentrantLockService = new ReentrantLockService();

        new Thread(() -> reentrantLockService.test1()).start();
        new Thread(() -> reentrantLockService.test1()).start();
        new Thread(() -> reentrantLockService.test1()).start();
    }

}
