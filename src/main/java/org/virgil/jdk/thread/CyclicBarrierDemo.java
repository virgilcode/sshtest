package org.virgil.jdk.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by Virgil on 2017/9/12.
 */
class MyThread extends Thread {
    private CyclicBarrier cyclicBarrier;
    private String name;
    private int ID;

    public MyThread(CyclicBarrier cyclicBarrier, String name1, int ID) {
        this.cyclicBarrier = cyclicBarrier;
        this.name = name1;
        this.ID = ID;
    }

    @Override
    public void run() {
        System.out.println(name + "开始准备");
        try {
            Thread.sleep(ID * 1000);
            System.out.println(name + "准备完毕！等待发令枪");
            try {
                try {
                    cyclicBarrier.await(ID * 1000, TimeUnit.MILLISECONDS);
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
                System.out.println(name + "跑完了路程！");
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
                System.out.println(name + "看不见起跑线了");
            }
            System.out.println(name + "退场！");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class CyclicBarrierDemo {
    public static void main(String[] args) throws InterruptedException {
        Map<Integer, Thread> threads = new HashMap<>();
        CyclicBarrier barrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {

                System.out.println("发令枪响了，跑！");
            }
        });
        for (int i = 0; i < 5; i++) {
            MyThread myThread = new MyThread(barrier, "运动员" + i + "号", i);
            threads.put(i, myThread);
            myThread.start();
        }
//        Thread.currentThread().sleep(3000);
//        threads.get(1).interrupt();

    }
}
