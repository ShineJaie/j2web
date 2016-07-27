package com.j2web.web.test.thread;

/**
 * 序列号
 * Created by wxj on 16-7-27.
 */
public class SequenceNumber {

    // 普通调用
    private int num = 0;

    public int getNextNumPlain() {
        num += 1;
        return num;
    }

    // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /**
     * 通过匿名内部类覆盖ThreadLocal的initialValue()方法，指定初始值
     */
    private static final ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    /**
     * 获取下一个序列值
     */
    public int getNextNum() {
        seqNum.set(seqNum.get() + 1);
        return seqNum.get();
    }

    public static void main(String[] args) {
        SequenceNumber sn = new SequenceNumber();

        // 3个线程共享sn，各自产生序列号
        TestClient t1 = new TestClient(sn);
        TestClient t2 = new TestClient(sn);
        TestClient t3 = new TestClient(sn);

        t1.start();
        t2.start();
        t3.start();


        // 3个线程共享sn，各自产生序列号
        TestClientImp tp4 = new TestClientImp(sn);
//        TestClientImp tp5 = new TestClientImp(sn);
//        TestClientImp tp6 = new TestClientImp(sn);

        Thread t4 = new Thread(tp4);
//        Thread t5 = new Thread(tp5);
//        Thread t6 = new Thread(tp6);

        t4.start();
//        t5.start();
//        t6.start();

        System.out.println("线程运行完毕");
    }

}
