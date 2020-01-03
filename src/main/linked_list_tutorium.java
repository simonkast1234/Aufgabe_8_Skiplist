package main;
/*
import Prog1Tools.IOTools;

public class linked_list_tutorium<T extends Comparable<? super T>> {

    static linked_list_tutorium<String> liste = new linked_list_tutorium<>();
    Node<T> head;
    Node<T> tail;
    int size;

    public linked_list_tutorium() {
        this.size = 0;
    }

    @Override
    public String toString() {
        if(head == null) return "";
        else if(size == 1) return "[" + head.value + "]";
        else {
            return "[" + toString("", head) + "]";
        }
    }
    private String toString(String s, Node<T> currentNode) {
        if(head.value.compareTo(currentNode.next.value) == 0) {
            s += currentNode.value;
            return s;
        } else {
            return toString((s+currentNode.value.toString()+","), currentNode.next);
        }
    }

    public boolean add() {
        String s = IOTools.readString("Hinzufügen: ([Q]uit) ");
        if(s.equals("Q")) {
            return false;
        }
        add((T) s);
        return true;
    }

    private void add(T value) {
        // initially set head
        if(head == null) {
            head = new Node<T>(value);
            head.next = head;
            tail = head;

            // Set as new head
        } else if(value.compareTo(head.value) < 0) {
            Node<T> tmp = head;
            head = new Node<T>(value);
            head.next = tmp;
            tail.next = head;

            // set as new tail
        } else if(value.compareTo(tail.value) > 0) {
            tail.next = new Node<T>(value);
            tail.next.next = head;
            tail = tail.next;

            // start recursion
        } else {
            add(value, head);
        }

        size++;

        // print out where we are
        System.out.println(liste.head.value + "=head " + liste + " " + liste.tail.value + "=tail");
    }


    private void add(T value, Node<T> currentNode) {
        // stop if value has already added before
        if(value.compareTo(currentNode.value) == 0) {
            return;
        }

        // place in between
        if( value.compareTo(currentNode.value) > 0 && value.compareTo(currentNode.next.value) < 0) {
            Node<T> tmp = currentNode.next;
            currentNode.next = new Node<T>(value);
            currentNode.next.next = tmp;
        } else {
            add(value, currentNode.next);
        }
    }

    public static void main(String[] args) {

        try {
            while(liste.add()) {
            }
            System.out.println("Good bye");

        } catch (java.lang.StackOverflowError e) {
            System.out.println("StackOverflowError");
        }
    }
}

 */
