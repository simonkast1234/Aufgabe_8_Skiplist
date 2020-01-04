package main;

public class IndexList<T extends Comparable<T>> {
    private Node<T> head;
    private int numberOfIndexNodes;

    public IndexList(Node<T> head, int numberOfIndexNodes) {
        this.head = head;
        this.numberOfIndexNodes = numberOfIndexNodes;
        initiallyAdd(this.head, numberOfIndexNodes);
    }



    public boolean add(T value) {




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