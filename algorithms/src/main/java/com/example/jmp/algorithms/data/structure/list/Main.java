package com.example.jmp.algorithms.data.structure.list;

public class Main {

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.insert("1");
        list.insert("2");
        list.insert("3");
        list.insert("4");
        list.insert("5");
        list.print();

        System.out.println(list.find("5"));

        System.out.println(list.getSize());
        list.deleteAt(5);
        list.print();
        list.deleteAt(0);
        list.print();
    }
}
