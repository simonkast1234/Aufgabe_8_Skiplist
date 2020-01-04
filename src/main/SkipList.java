package main;

import java.util.*;

public class SkipList<T extends Comparable<T>> implements Collection<T> {
    public static final int NUMBEROFINDEXNODES = 4;
	private Node<T> head;
	private int baseListSize;
	private int intervalSize;
	private int intervalModulo;

	public SkipList() {
		this.head = new Node<>();
		this.baseListSize = 0;
		addIndexNodes(this.head, SkipList.NUMBEROFINDEXNODES);
		System.out.println("index list created!");
	}

	private void addIndexNodes(Node<T> currentNode, int counter) {
		if(counter <= 0) return;
		currentNode.next = new Node<>();
		addIndexNodes(currentNode.next, (counter - 1));
	}

	//#####################################
	// add
	//#####################################
	public boolean add(T value) {
		if(addBaseList(detectAddingInterval(this.head.next, value), value) ) {
			this.baseListSize++;
			makeReferences();
			return true;
		}
		return false;
	}

	/**
	 * sets new references from index nodes to base nodes in the right interval length.
	 */
	private void makeReferences() {
		this.intervalSize = this.baseListSize / SkipList.NUMBEROFINDEXNODES;
		this.intervalModulo = this.baseListSize % SkipList.NUMBEROFINDEXNODES;
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
		if(indexCounter < SkipList.NUMBEROFINDEXNODES) {
			// if reached desired base node, refer index node to it and start method for next index
			if(baseCounter <= 0 || currentBaseNode.next == null) {
				currentIndexNode.reference = currentBaseNode;
				currentIndexNode.value = currentBaseNode.value;
				if(this.intervalModulo > 0) {
					this.intervalModulo--;
					makeReferences(currentIndexNode.next, (indexCounter + 1), currentBaseNode, intervalSize + 1);
				} else {
					makeReferences(currentIndexNode.next, (indexCounter + 1), currentBaseNode, intervalSize);
				}
			}

			// continue recursion until reached desired base node (base node != null)
			else {
				makeReferences(currentIndexNode, indexCounter, currentBaseNode.next, (baseCounter - 1) );
			}
		}
	}

	private Node<T> detectAddingInterval(Node<T> currentNode, T value) {
		// if list is empty
		if(this.baseListSize <= 0) return null;

		// value is in the last interval or value smaller than or equal to next index node's value
		if(currentNode.next == null || value.compareTo(currentNode.next.value) <= 0) {
			return currentNode.reference;
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
			this.head.next.reference = new Node<>(null, value);
			return true;
		}

		// smallest value of list
		else if(value.compareTo(currentNode.value) < 0) {
			this.head.next.reference = new Node<>(currentNode, value);
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
	 * @param currentNode the node working on
	 * @param value the value to be added
	 * @return whether or not adding was successful
	 */
	private boolean addBaseListRecursive(Node<T> currentNode, T value) {
		// biggest value of entire list
		if(currentNode.next == null) {
			currentNode.next = new Node<>(null, value);
			return true;
		}

		// value is smaller than next node's value
		else if(value.compareTo(currentNode.next.value) < 0) {
			Node<T> tmp = currentNode.next;
			currentNode.next = new Node<>(tmp, value);
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

	//#####################################
	// toString
	//#####################################
	public String toString() {
		return "head -> " + toStringIndex("", this.head.next, this.head.next.reference)
				+ "\n        " + toStringBase("", this.head.next.reference);
	}
	private String toStringIndex(String s, Node<T> currentIndexNode, Node<T> currentBaseNode) {
		// no base node to compare, just print index node
		if(currentBaseNode == null) {
			s += currentIndexNode.value;
			// end, when last index was printed out.
			if(currentIndexNode.next == null) return s + " ";
			else return toStringIndex(s + " ", currentIndexNode.next, null);
		}
		// compare base and index node
		else {
			// base note != index node -> print spaces
			if(currentIndexNode.value.compareTo(currentBaseNode.value) > 0) {
				for (int i = 0; i < String.valueOf(currentBaseNode.value).length(); i++) {
					s += " ";
				}
				return toStringIndex(s + " ", currentIndexNode, currentBaseNode.next);

			// base note == index note -> print index node
			} else {
				s += currentIndexNode.value;

				// end, when last index was printed out.
				if(currentIndexNode.next == null) return s + " ";
				else return toStringIndex(s + " ", currentIndexNode.next, currentBaseNode.next);
			}
		}
	}
	private String toStringBase(String s, Node<T> currentNode) {
		if(currentNode == null) return s;
		if(currentNode.next == null) return s + currentNode.value + " ";
		return toStringBase(s + currentNode.value + " ", currentNode.next);
	}

	//#####################################
	// get
	//#####################################
	public T get(int index) {
		if(index < 1 || index > this.baseListSize) return null;
		return get(this.head.next.reference, index);
	}
	private T get(Node<T> currentNode, int index) {
		if(index == 1) {
			return currentNode.value;
		}
		return get(currentNode.next, index - 1);
	}

	//#####################################
	// contains
	//#####################################
	public boolean contains(T value) {
		if(this.baseListSize == 0) {
			return false;
		}
		return contains(detectAddingInterval(this.head.next, value), value);
	}
	private boolean contains(Node<T> currentNode,  T value) {
		// if at the end of the list -> not contained
		if(currentNode == null) {
			return false;
		}

		// if same value as current node
		if(value.compareTo(currentNode.value) == 0) {
			return true;
		}
		// if value bigger than current node's value -> recursion
		if(value.compareTo(currentNode.value) > 0) {
			return contains(currentNode.next, value);
		}
		// if value smaller than current node's value -> not contained
		if(value.compareTo(currentNode.value) < 0) {
			return false;
		}
		System.out.println("something strange happened in contains method");
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