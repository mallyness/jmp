package com.example.jmp.algorithms.data.structure.deque;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class DequeApp {

    public static void main(String[] args) {
        Deque<Integer> deque = new ArrayDeque<>(List.of(1, 2, 3, 4, 5));

        System.out.println(deque);
        deque.pop();
        System.out.println(deque);
        Integer peek = deque.peek();
        System.out.println(peek);
        System.out.println(deque);
    }
}
