package zhaoqi.sync;

/**
 * 2 * @Author: zhaoqi
 * 3 * @Date: 2019/7/19 0019 PM 3:45
 * 4
 */
public class SyncTest {


    public  void test1(){

        synchronized (this) {
            System.out.println(String.format("@in>>>>>>>>>>>>>线程[%s]获取到锁对象", Thread.currentThread().getId()));
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {

        new Thread(() -> {
            SyncTest t1 = new SyncTest();

            t1.test1();
        }).start();
        new Thread(() -> {
            SyncTest t1 = new SyncTest();

            t1.test1();
        }).start();
        new Thread(() -> {
            SyncTest t1 = new SyncTest();


            t1.test1();
        }).start();

    }
}
