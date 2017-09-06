package org.virgil.jdk.sort;

import java.util.Arrays;

/**
 * Created by Virgil on 2017/9/4.
 */
public class QuickSort {
    public void sort(int[] a, int low, int high) {
        if (low >= high) {
            return;
        }
        int i = low, j = high;
        int index = a[i];
        while (i < j) {
            while (i < j && a[j] >= index) {
                j--;
            }
            if (i < j) {
                a[i++] = a[j];
            }
            while (i < j && a[i] <= index) {
                i++;
            }git add .
            if (i < j) {
                a[j--] = a[i];
            }

        }
        a[i] = index;
        sort(a, 0, i - 1);
        sort(a, i + 1, high);
    }

    public void quicksort(int[] a) {

        sort(a, 0, a.length-1);
    }

    public static void main(String[] args) {
        QuickSort qs = new QuickSort();
        int a[] = {51, 46, 20, 18, 65, 97, 82, 30, 77, 50};
        qs.quicksort(a);
        System.out.println(Arrays.toString(a));
    }
}
