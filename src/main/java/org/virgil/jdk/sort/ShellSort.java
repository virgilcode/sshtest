package org.virgil.jdk.sort;

import java.util.Arrays;

/**
 * Created by Virgil on 2017/9/4.
 */
public class ShellSort {
    public void sort(int[] data) {

        for (int increment = data.length / 2; increment > 0; increment /= 2) {
            for (int i = 0; i < data.length; i++) {
                for (int j = i; j < data.length - increment; j += increment) {
                    if (data[j] > data[j + increment]) {
                        int temp = data[j];
                        data[j] = data[j + increment];
                        data[j + increment] = temp;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int a[] = {51, 46, 20, 18, 65, 97, 82, 30, 77, 50};
        ShellSort ss = new ShellSort();
        ss.sort(a);
        System.out.println(Arrays.toString(a));
    }
}
