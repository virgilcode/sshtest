package org.virgil.jdk.thread;

/**
 * Created by Virgil on 2017/9/8.
 */
public class ThreadTest extends Thread {
    @Override
    public void run() {
        System.out.println("thread run ");
    }

    public static void main(String[] args){
        Thread th=new Thread(new ThreadTest());
        th.start();
        System.out.println("ppppppppppppp");
    }
}
