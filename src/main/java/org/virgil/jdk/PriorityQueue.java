package org.virgil.jdk;

/**
 * Created by Virgil on 2017/8/17.
 */
public class PriorityQueue {
    private int maxsize;
    private int[] queueArray;
    private int nItems;

    public PriorityQueue(int s) {
        this.maxsize = s;
        queueArray = new int[maxsize];
        nItems = 0;
    }

    public void insert(int item) {
        int j;
        if (nItems == 0) {
            queueArray[nItems++] = item;
        } else {
            for (j = nItems - 1; j >= 0; j--) {
                if (item > queueArray[j]) {
                    queueArray[j + 1] = queueArray[j];
                } else {
                    break;
                }
            }
            queueArray[j + 1] = item;
            nItems++;
        }
    }

    public int remove() {
        return queueArray[--nItems];
    }

    public boolean isEmpty() {
        return nItems == 0;
    }

    public static void main(String[] args) {
        PriorityQueue queue = new PriorityQueue(10);
        queue.insert(10);
        queue.insert(15);
        queue.insert(0);
        queue.insert(60);
        queue.insert(7);
        while (!queue.isEmpty()) {
            System.out.printf(String.valueOf(queue.remove()+" "));
        }


    }


}

