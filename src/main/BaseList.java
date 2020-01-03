package main;

public class BaseList<T> {
    private Node<T> head;
    private int size;

    public BaseList(Node<T> head) {
        this.head = head;
        this.size = 0;
    }

    public boolean add(T value) {

        // add first node if base list is empty
        this.head.next.reference = new Node<T>(null, value);

        return false;
    }

    public String toString() {
        if(this.head.next == null) return "";
        return "[" + toString("", this.head.next) + "]";
    }
    private String toString(String s, Node<T> currentNode) {
        if(currentNode.next == null) return s+"x";
        return toString(s+"x,", currentNode.next);
    }

}