package com.test.thread.sync.join;

/**
 * 2 * @Author: zhaoqi
 * 3 * @Date: 2019/7/23 0023 PM 4:14
 * 4
 */
public class TestJoin implements  Runnable {




    public static void main(String[] args) {

        final Thread t1 = new Thread(new Runnable() {
            public void run() {
                System.out.println("线程1开始执行");
                try {
                    Thread.sleep(10000);
                    System.out.println("线程1执行完成");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        new Thread(new Runnable() {
            public void run() {
                try {
                    System.out.println("线程2开始执行,线程1join 执行完以后才能继续");
                    t1.join();
                    System.out.println("线程1执行完成,线程2开始执行继续的逻辑");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    public void run() {

        long id = Thread.currentThread().getId();
        System.out.println("线程["+id+"]正在运行.....................................................");

    }
}
