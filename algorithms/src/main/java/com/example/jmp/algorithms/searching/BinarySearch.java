package com.example.jmp.algorithms.searching;

public class BinarySearch {

    public static void main(String[] args) {
        int[] sortedArray = {0, 3, 5, 7, 8, 9, 12, 15, 16, 20, 28, 33};
        iterativeSearch(sortedArray, 99);
    }

    public static int iterativeSearch(int[] array, int number) {
        int left = 0;
        int right = array.length - 1;
        int middle;

        int counter = 0;
        while (left <= right) {
            counter++;
            middle = (left + right) / 2;
            if (number == array[middle]) {
//                System.out.printf("The number %d is found in the array at position %d in %d steps.",
//                    number, middle, counter);
                return middle;
            } else if (number < array[middle]) {
                right = middle - 1;
            } else if (number > array[middle]) {
                left = middle + 1;
            }
        }

//        System.out.printf("The number %d is not found in the array.", number);
        return -1;
    }
}
