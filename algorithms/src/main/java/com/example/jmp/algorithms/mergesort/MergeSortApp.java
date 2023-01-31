package com.example.jmp.algorithms.mergesort;

import java.util.Arrays;
import java.util.Random;

public class MergeSortApp {

    public static void main(String[] args) {
        int[] array = new int[13];
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(100);
        }

        System.out.println(Arrays.toString(array));
        int[] sortedArray = mergesort(array);
        System.out.println(Arrays.toString(sortedArray));
    }

    public static int[] mergesort(int[] array) {
        int arrayLength = array.length;
        if (arrayLength == 1) {
            return array;
        }

        int middle = arrayLength / 2;
        int[] left = new int[middle];
        int[] right = new int[arrayLength - middle];

        System.arraycopy(array, 0, left, 0, left.length);
        System.arraycopy(array, middle, right, 0, right.length);

        left = mergesort(left);
        right = mergesort(right);

        return merge(left, right);
    }

    public static int[] merge(int[] left, int[] right) {
        int[] sortedArray = new int[left.length + right.length];

        int i = 0; // sorted result array index
        int l = 0; // left array index
        int r = 0; // right array index
        while (l < left.length && r < right.length) {
            if (left[l] > right[r]) {
                sortedArray[i] = right[r];
                r++;
            } else {
                sortedArray[i] = left[l];
                l++;
            }
            i++;
        }

        /* Copy remaining elements of left[] if any */
        while (l < left.length) {
            sortedArray[i] = left[l];
            l++;
            i++;
        }

        /* Copy remaining elements of right[] if any */
        while (r < right.length) {
            sortedArray[i] = right[r];
            r++;
            i++;
        }

        return sortedArray;
    }
}
