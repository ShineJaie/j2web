package com.j2web.web.test.thread;

/**
 * 扩展 java.lang.Thread 类的线程对象
 * Created by wxj on 16-7-27.
 */
public class TestClient extends Thread {

    private SequenceNumber sn;

    public TestClient(SequenceNumber sn) {
        this.sn = sn;
    }

    @Override
    public void run() {
        // 每个线程打出3个序列号
        for (int i = 0; i < 3; ++i) {
//            for (long k = 0; k < 100000000; k++) ; // 模拟非常耗时的操作
            /*try {
                Thread.sleep(3); // 线程在每次执行过程中，总会睡眠3毫秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            System.out.println("thread[" + Thread.currentThread().getName() +
                    "] sn[" + sn.getNextNum() + "]");
        }
    }

}
