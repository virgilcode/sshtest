package org.virgil.jdk.sort;

import java.util.Arrays;

/**
 * Created by Virgil on 2017/8/28.
 */
public class BubbleSort {
    public void sort(int[] a) {
        if (a.length == 1) {
            return;
        }
        for (int i = 0; i <= a.length - 1; i++) {
            for (int j = i; j <= a.length - 1; j++) {
                int temp = a[i];
                if (a[i] > a[j]) {
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        BubbleSort sort = new BubbleSort();
        int[] a = {12, 3, 2, 5, 7, 0};
        System.out.println(Arrays.toString(a));
        sort.sort(a);
        System.out.println(Arrays.toString(a));

    }
}
