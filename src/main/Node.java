package main;

public class Node<T> {
    public T value;
    public Node<T> next;
    public Node<T> reference;

    public Node() {
        this(null, null, null);
    }

    public Node(Node<T> next, T value) {
        this(null, next, value);
    }

    public Node(T value) {
        this(null, null, value);
    }

    public Node(Node<T> base, Node<T> next) {
        this(base, next, null);
    }

    public Node(Node<T> reference, Node<T> next, T value) {
        this.reference = reference;
        this.next = next;
        this.value = value;
    }
}
