package org.virgil.jdk.thread;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Virgil on 2017/9/8.
 */
public class EggTest {
    private BlockingQueue<Object> eggs = new ArrayBlockingQueue<Object>(5);

    public void putEgg(Object egg) {
        try {
            eggs.put(egg);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("put egg ...");
    }

    public void takeEgg() {
        try {
            eggs.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("take egg ...");
    }

    static class TakeThread extends Thread {
        private EggTest eggTest;

        public TakeThread(EggTest eggTest) {
            this.eggTest = eggTest;
        }

        @Override
        public void run() {
            eggTest.takeEgg();
        }
    }

    static class AddThread extends Thread {
        private EggTest egg;

        private Object o = new Object();

        public AddThread(EggTest egg) {
            this.egg = egg;
        }

        @Override
        public void run() {
            egg.putEgg(o);
        }
    }

    public static void main(String[] args) {
        EggTest eggTest = new EggTest();
        for (int i = 0; i < 10; i++) {
            new Thread(new AddThread(eggTest)).start();
        }
        for (int i = 0; i < 10; i++) {
            new Thread(new TakeThread(eggTest)).start();
        }
    }
}
