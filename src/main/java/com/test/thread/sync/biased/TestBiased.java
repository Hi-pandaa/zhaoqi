package com.test.thread.sync.biased;

import org.openjdk.jol.info.ClassLayout;

import java.util.HashMap;

/**
 * 2 * @Author: zhaoqi
 * 3 * @Date: 2019/7/24 0024 AM 11:17
 * 4 偏向锁
 */
public class TestBiased {

    /**
     * Returns a hash code value for the object. This method is
     * supported for the benefit of hash tables such as those provided by
     * {@link HashMap}.
     * <p>
     * The general contract of {@code hashCode} is:
     * <ul>
     * <li>Whenever it is invoked on the same object more than once during
     * an execution of a Java application, the {@code hashCode} method
     * must consistently return the same integer, provided no information
     * used in {@code equals} comparisons on the object is modified.
     * This integer need not remain consistent from one execution of an
     * application to another execution of the same application.
     * <li>If two objects are equal according to the {@code equals(Object)}
     * method, then calling the {@code hashCode} method on each of
     * the two objects must produce the same integer result.
     * <li>It is <em>not</em> required that if two objects are unequal
     * according to the {@link Object#equals(Object)}
     * method, then calling the {@code hashCode} method on each of the
     * two objects must produce distinct integer results.  However, the
     * programmer should be aware that producing distinct integer results
     * for unequal objects may improve the performance of hash tables.
     * </ul>
     * <p>
     * As much as is reasonably practical, the hashCode method defined by
     * class {@code Object} does return distinct integers for distinct
     * objects. (This is typically implemented by converting the internal
     * address of the object into an integer, but this implementation
     * technique is not required by the
     * Java&trade; programming language.)
     *
     * @return a hash code value for this object.
     * @see Object#equals(Object)
     * @see System#identityHashCode
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public synchronized void test1() {

        long id = Thread.currentThread().getId();
        System.out.println("线程[" + id + "]获取到锁,打印此时对象头的信息,应该是偏向标志位1");
        System.out.println(ClassLayout.parseInstance(this).toPrintable());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程[" + id + "]方法执行完成,释放锁");
    }


    public static void main(String[] args) throws InterruptedException {
        final TestBiased t1 = new TestBiased();
        System.out.println("对象新建完成,初始化对象头的锁信息和关键信息,打印初始化的对象的头信息");
        System.out.println(ClassLayout.parseInstance(t1).toPrintable());



        new Thread(new Runnable() {
            public void run() {
                t1.test1();
                System.out.println("释放锁后打印对象头的信息,观察偏向标志位的信息");
                System.out.println(ClassLayout.parseInstance(this).toPrintable());
            }
        }).start();

        Thread.sleep(20000);

        int i = t1.hashCode();


        System.out.println(ClassLayout.parseInstance(t1).toPrintable());

/*        new Thread(new Runnable() {
            public void run() {
                System.out.println("准备观察第二个线程抢夺锁的时候的变化");
                t1.test1();
                System.out.println(ClassLayout.parseInstance(this).toPrintable());

            }
        }).start();*/



    }
}
