package com.example.jmp.algorithms.searching;

public class BinarySearch {

    public static void main(String[] args) {
        int[] sortedArray = {0, 3, 5, 7, 8, 9, 12, 15, 16, 20, 28, 33};
        iterativeSearch(sortedArray, 99);
        System.out.println(recursiveSearch(sortedArray, 0, sortedArray.length - 1, 33));
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

    public static int recursiveSearch(int[] array, int first, int last, int number) {
        int middle = (first + last) / 2;

        if (first > last) {
            return -1;
        }

        if (number == array[middle]) {
            return middle;
        } else if (number < array[middle]) {
            return recursiveSearch(array, first, middle - 1, number);
        } else {
            return recursiveSearch(array, middle + 1, last, number);
        }
    }
}
