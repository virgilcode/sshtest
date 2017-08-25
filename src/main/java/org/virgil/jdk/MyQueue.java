package org.virgil.jdk;

/**
 * Created by Virgil on 2017/8/17.
 */
public class MyQueue {
    private int maxSize;
    private long[] queArray;
    private int front;
    private int rear;
    private int nItems;

    public MyQueue(int a) {
        this.maxSize = a;
        this.queArray = new long[maxSize];
        front = 0;
        rear = -1;
        nItems = 0;
    }

    public void insert(long j) {
        if (rear == maxSize - 1) {
            rear = -1;
        }
        queArray[++rear] = j;
        nItems++;
    }

    public long remove() {
        long temp = queArray[front++];
        if (front == maxSize) {
            front = 0;
        }
        nItems--;
        return temp;
    }

    public long peekFront() {
        return queArray[front];
    }

    public boolean isEmpty() {
        return nItems == 0;
    }

    public int size() {
        return nItems;
    }

    public static void main(String[] args) {
        MyQueue queue = new MyQueue(10);
        queue.insert(1);
        queue.insert(2);
        queue.insert(3);
        queue.insert(4);
        queue.remove();
        queue.remove();
        queue.insert(5);
        System.out.println(queue.size());
    }
}
