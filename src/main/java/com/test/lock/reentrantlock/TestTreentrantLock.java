package com.test.lock.reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 2 * @Author: zhaoqi
 * 3 * @Date: 2019/7/26 0026 AM 9:53
 * 4

 */
    public class TestTreentrantLock {

        public static ReentrantLock lock = new ReentrantLock(false);

        static Condition condition1 = lock.newCondition();

        static Condition condition2 = lock.newCondition();

        public static void testMutiCondition(){
            new Thread(new Runnable() {
                public void run() {


                    try {
                        System.out.println("第一个线程执行加锁方法");
                        lock.lock();
                        TimeUnit.SECONDS.sleep(10);
                        System.out.println("第一个线程使用条件1await");
                        condition1.await();
                        System.out.println("第一个线程重新获得锁");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }finally {
                        lock.unlock();
                    }

                }
            }).start();


            new Thread(new Runnable() {
                public void run() {
                    System.out.println("第二个线程执行加锁方法");

                    try {
                        lock.lock();
                        System.out.println("线程2获得锁");
                        TimeUnit.SECONDS.sleep(10);
                        //执行完后通知条件1的方法

                        System.out.println("线程2开始通知条件1/2的线程");
                       condition1.signal();
                       condition2.signal();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }finally {
                        lock.unlock();
                    }

                }
            }).start();

        }


    public static void main(String[] args) {

            TestTreentrantLock.testMutiCondition();

    }
}



