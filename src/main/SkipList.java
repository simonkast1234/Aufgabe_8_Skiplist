package main;

import java.util.*;

public class SkipList<T extends Comparable<T>> implements Collection<T> {
    public static final int NUMBEROFINDEXNODES = 4;
	private Node<T> head;
	private int baseListSize;
	private int intervalSize;

	public SkipList() {
		this.head = new Node<T>();
		this.baseListSize = 0;
		addIndexNodes(this.head, SkipList.NUMBEROFINDEXNODES);
	}

	private void addIndexNodes(Node<T> currentNode, int counter) {
		if(counter <= 0) return;
		currentNode.next = new Node<T>();
		addIndexNodes(currentNode.next, (counter - 1));
		System.out.println("index list created!");
	}


	public boolean add(T value) {
		if( addBaseList(detectAddingInterval(this.head.next, value), value) ) {
			this.baseListSize++;
			makeReferences();
			System.out.println(value + " has been added!");
			return true;
		}
		return false;
	}

	/**
	 * sets new references from index nodes to base nodes in the right interval length.
	 */
	private void makeReferences() {
		this.intervalSize = this.baseListSize / SkipList.NUMBEROFINDEXNODES; // todo: improve algorithm
		makeReferences(this.head.next, 0, this.head.next.reference, 0);
	}
	/**
	 * recursive, goes for each index node through an intervals length amount of base nodes and refers each index
	 * node to the desired base node. Stops after going through all index nodes.
	 * @param currentIndexNode the index node working on
	 * @param indexCounter the counter of which index is being worked on
	 * @param currentBaseNode the base node working on
	 * @param baseCounter the counter of how many base nodes left to reach the desired one
	 */
	private void makeReferences(Node<T> currentIndexNode, int indexCounter, Node<T> currentBaseNode, int baseCounter) {
		// if reached desired base node, refer index node to it and start method for next index
		if(indexCounter < SkipList.NUMBEROFINDEXNODES && baseCounter <= 0) {
			currentIndexNode.reference = currentBaseNode;
			currentIndexNode.value = currentBaseNode.value;
			makeReferences(currentIndexNode.next, (indexCounter + 1), currentBaseNode, intervalSize);
		}

		// continue recursion until reached desired base node
		else if(indexCounter < SkipList.NUMBEROFINDEXNODES) {
			makeReferences(currentIndexNode, indexCounter, currentBaseNode.next, (baseCounter - 1) );
		}
	}

	private Node<T> detectAddingInterval(Node<T> currentNode, T value) {
		// value is in the last interval OR value smaller than or equal to next index node's value
		if(currentNode.next == null || value.compareTo(currentNode.next.value) <= 0) {
			return currentNode;
		}

		// value bigger than next index node's value
		else {
			return detectAddingInterval(currentNode.next, value);
		}
	}

	/**
	 * adds a new value to the base list.
	 * @param currentNode the intervals first node to work on
	 * @param value the value of type T to be added to the list
	 * @return whether or not adding of value was successful. If false, value has
	 * probably already been added before.
	 */
	public boolean addBaseList(Node<T> currentNode, T value) {
		// add first node if base list is empty
		if(this.baseListSize == 0) {
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
			return addBaseListRecursive(currentNode, value);
		}
	}
	/**
	 * recursive method to add the new value. Value is always bigger than current
	 * node's value.
	 * @param currentNode
	 * @param value
	 * @return
	 */
	private boolean addBaseListRecursive(Node<T> currentNode, T value) {
		// biggest value of entire list
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
			return addBaseListRecursive(currentNode.next, value);
		}
	}


	public String toString() {
		return "head -> " + toStringIndex("", this.head.next)
				+ "\n        " + toStringBase("", this.head.next.reference);
	}

	private String toStringIndex(String s, Node<T> currentNode) {
		if(currentNode.next == null) return s + currentNode.value;
		s += currentNode.value;
		for (int i = 0; i < this.intervalSize; i++) {
			s += "  ";
		}
		return toStringIndex(s + " ", currentNode.next);
	}
	private String toStringBase(String s, Node<T> currentNode) {
		if(currentNode.next == null) return s + currentNode.value;
		return toStringBase(s + currentNode.value + " ", currentNode.next);
	}





	public T get(int index) {
		return null;
	}

	public boolean contains(T value) {
		return false;
	}

	public void clear() {
		// TODO Auto-generated method stub
		
	}

	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}














	//#####################################
	//do not change the following methods!!
	//#####################################
	
	@Override
	public boolean isEmpty() {
		return size() <= 0;
	}
	
	@Override
	public boolean contains(Object o) {
		try {
			T casted = (T) o;
			return contains(casted) ;
		} catch (ClassCastException e) {
			return false;
		}
	}
	
	
	@Override
	@Deprecated
	public boolean remove(Object o) {
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		for(Object o : c) {
			if(!contains(o)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		for(T t:c) {
			add(t);
		}
		return true;
	}

	@Override
	@Deprecated
	public boolean removeAll(Collection<?> c) {
		return false;
	}

	@Override
	@Deprecated
	public boolean retainAll(Collection<?> c) {
		return false;
	}

	@Override
	public Iterator<T> iterator() {
		//really slow way to implement this but should be some what understandable
		return new Iterator<T>() {
			int index = 0;
			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return index < size();
			}

			@Override
			public T next() {
				return get(index++);
			}
		};
	}

	@Override
	public Object[] toArray() {
		Object[] obj = new Object[size()];
		for (int i = 0; i < size(); i++) {
			obj[i] = get(i);
		}
		return obj;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <X> X[] toArray(X[] a) {
		for (int i = 0; i < size(); i++) {
			a[i] = (X) get(i);
		}
		return a;
	}

}