package org.virgil.jdk.sort;

import java.util.Arrays;

/**
 * Created by Virgil on 2017/9/4.
 */
public class MergeSort {
    public void merge(int[] a, int low, int mid, int high) {
        int[] temp = new int[high - low + 1];
        int i = low, j = mid + 1;
        int k = 0;
        while (i <= mid && j <= high) {
            if (a[i] < a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = a[i++];
        }
        while (j <= high) {
            temp[k++] = a[j++];
        }
        for (int k2 = 0; k2 < temp.length; k2++) {
            a[k2 + low] = temp[k2];
        }

    }

    public void mergeSort(int[] a, int low, int high) {
        int mid = (high + low) / 2;
        if (low < high) {
            mergeSort(a, low, mid);
            mergeSort(a, mid + 1, high);
            merge(a, low, mid, high);
        }
    }

    public static void main(String[] args) {
        MergeSort ms = new MergeSort();
        int a[] = {51, 46, 20, 18, 65, 97, 82, 30, 77, 50};
        ms.mergeSort(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
    }
}
