package com.example.jmp.algorithms.recursion;

public class RecursionApp {

    public static void main(String[] args) {
        countdown(5);

        System.out.printf("%d to the power of %d is %d \n", 5, 3, power(5, 3));
        System.out.printf("%d to the power of %d is %d \n", 1, 5, power(1, 5));
        System.out.printf("%d to the power of %d is %d \n", 8, 0, power(8, 0));
        System.out.printf("%d to the power of %d is %d \n", 2, 7, power(2, 7));

        System.out.printf("%d! is %d \n", 4, factorial(4));
        System.out.printf("%d! is %d \n", 5, factorial(5));
        System.out.printf("%d! is %d \n", 1, factorial(1));
        System.out.printf("%d! is %d \n", 0, factorial(0));
    }

    public static void countdown(int x) {
        if (x == 0) {
            System.out.println("Done.");
        } else {
            System.out.println("x = " + x);
            countdown(x - 1);
            System.out.println("foo " + x);
        }
    }

    public static int power(int number, int pwr) {
        if (pwr == 0) {
            return 1;
        } else {
            int result = number * power(number, pwr - 1);
//            System.out.println(result);
            return result;

//            int x = pwr;
//            int result = 1;
//            while (x != 0) {
//                result *= number;
//                x--;
//            }
//            return result;
        }
    }

    public static int factorial(int number) {
        if (number == 0) {
            return 1;
        } else {
            return number * factorial(number - 1);
        }
    }
}
