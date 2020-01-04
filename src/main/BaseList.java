package main;

public class BaseList<T extends Comparable<T>> {
    private Node<T> head;
    private int size;

    public BaseList(Node<T> head) {
        this.head = head;
        this.size = 0;
    }


    /**
     * adds a new value to the base list.
     * @param currentNode the intervals first node to work on
     * @param value the value of type T to be added to the list
     * @return whether or not adding of value was successful. If false, value has
     * probably already been added before.
     */
    public boolean add(Node<T> currentNode, T value) {
        // add first node if base list is empty
        if(this.size == 0) {
            this.head.next.reference = new Node<T>(null, value);
            return true;
        }

        // smallest value of list
        else if(value.compareTo(currentNode.value) < 0) {
            Node<T> tmp = currentNode;
            this.head.next.reference = new Node<T>(tmp, value);
            return true;
        }

        // value does already exist
        else if(value.compareTo(currentNode.value) == 0) {
            return false;
        }

        // value is bigger than current node -> recursion
        else {
            return addRecursive(currentNode, value);
        }
    }

    /**
     * recursive method to add the new value. Value is always bigger than current
     * node's value.
     * @param currentNode
     * @param value
     * @return
     */
    private boolean addRecursive(Node<T> currentNode, T value) {
        // highest value of entire list
        if(currentNode.next == null) {
            currentNode.next = new Node<T>(null, value);
            return true;
        }

        // value is smaller than next node's value
        else if(value.compareTo(currentNode.next.value) < 0) {
            Node<T> tmp = currentNode.next;
            currentNode.next = new Node<T>(tmp, value);
            return true;
        }

        // value equals next node's value
        else if(value.compareTo(currentNode.next.value) == 0) {
            return false;
        }

        // value is bigger than next node's value -> recursion
        else {
            return addRecursive(currentNode.next, value);
        }
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