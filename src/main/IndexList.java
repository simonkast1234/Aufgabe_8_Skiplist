package main;

public class IndexList<T extends Comparable<T>> {
    private Node<T> head;
    private int size;

    public IndexList(Node<T> head, int size) {
        this.head = head;
        this.size = size;
        initiallyAdd(this.head, size);
    }

    private boolean initiallyAdd(Node<T> currentNode, int counter) {
        if(counter <= 0) return false;
        currentNode.next = new Node<T>();
        initiallyAdd(currentNode.next, (counter - 1));
        return true;
    }

    public String toString() {
        if(this.head.next == null) return "";
        return "[" + toString("", this.head.next) + "]";
    }

    private String toString(String s, Node<T> currentNode) {
        if(currentNode.next == null) return s + currentNode.value;
        return toString(s + currentNode.value + ",", currentNode.next);
    }
}