package de.cristiano.marathon.miscellaneous;

import java.util.Scanner;


public class LinkedList {
    public static Node insert(Node head, int data) {
        Node nextNode = new Node(data);
        if (head == null) {
            return nextNode;
        }
        addNewNode(head, nextNode);
        return head;
    }

    private static void addNewNode(Node head, Node nextNode) {
        Node nextPoint = head;
        while (nextPoint.next != null) {
            nextPoint = nextPoint.next;
        }
        nextPoint.next = nextNode;
    }

    public static void display(Node head) {
        Node start = head;
        while (start != null) {
            System.out.print(start.data + " ");
            start = start.next;
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        Node head = null;
        int N = sc.nextInt();

        while (N-- > 0) {
            int ele = sc.nextInt();
            head = insert(head, ele);
        }
        display(head);
        sc.close();
    }


    static public class Node {
        public int data;
        public Node next;

        public Node(int d) {
            data = d;
            next = null;
        }
    }
}