package com.test.thread.wait_notify;

/**
 * 2 * @Author: zhaoqi
 * 3 * @Date: 2019/7/23 0023 PM 4:56
 * 4
 */
public class TestWaitNotify {


    /**
     * 必须要syncronized 修饰才可以用wait notify
     */
    public synchronized void test1() throws InterruptedException {
        long id = Thread.currentThread().getId();


        System.out.println(">>>>>>>>>线程[" + id + "]开始执行");
        System.out.println(">>>>>>>>>线程[" + id + "]触发wait方法,开始等待");

        wait();
        System.out.println(">>>>>>>>>线程[" + id + "]从wait阻塞中释放");
        //执行完

    }

    /**
     * 必须要syncronized 修饰才可以用wait notify
     */
    public synchronized void test2() throws InterruptedException {
        long id = Thread.currentThread().getId();
        Thread.sleep(10000);

        System.out.println(">>>>>>>>>线程[" + id + "]开始执行notify方法");
        notifyAll();


    }


    public static void main(String[] args) {

        //必须锁的是同一个对象 而且只有syncronzed修饰才会上锁 因为锁的是对象
        final TestWaitNotify t1 = new TestWaitNotify();

        new Thread(new Runnable() {
            public void run() {
                try {
                    t1.test1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

   new Thread(new Runnable() {
            public void run() {
                try {
                    t1.test2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }
}
