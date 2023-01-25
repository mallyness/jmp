package com.example.jmp.algorithms.data.structure.list;

public class LinkedList {

    private Node head;
    private int size;

    public LinkedList(Node node) {
        this.head = node;
        this.size = 0;
    }

    public LinkedList() {
        this.head = null;
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    public Node getHead() {
        return head;
    }

    public void insert(String data) {
        Node newNode = new Node(data);
        if (head == null) { // first insertion
            head = newNode;
        } else {
            Node currentNode = head;
            while (currentNode.getNext() != null) {
                currentNode = currentNode.getNext();
            }
            currentNode.setNext(newNode);
        }
        size++;
    }

    public void insertHead(String data) {
        Node newNode = new Node(data);
        newNode.setNext(head);
        head = newNode;
        size++;
    }

    public Node find(String data) {
        Node currentNode = head;
        while (currentNode != null) {
            if (currentNode.getData().equals(data)) {
                return currentNode;
            } else {
                currentNode = currentNode.getNext();
            }
        }
        return null;
    }

    public boolean deleteAt(int index) {
        if (index < 0 || index > size - 1) {
            return false;
        } else {
            if (index == 0) {
                head = head.getNext();
            } else {
                Node node = head;
                for (int i = 0; i < index - 1; i++) {
                    node = node.getNext();
                }
                node.setNext(node.getNext().getNext());
            }
            size--;
            return true;
        }
    }

    public void print() {
        System.out.println("Print LinkedList with size = " + size);
        Node currentNode = head;
        while (currentNode != null) {
            System.out.println(currentNode.getData());
            currentNode = currentNode.getNext();
        }
    }

    private static class Node {

        private String data;
        private Node next;

        public Node(String data) {
            this.data = data;
            this.next = null;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                "data='" + data + '\'' +
                ", next=" + next +
                '}';
        }
    }

}
