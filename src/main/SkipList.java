package main;

import java.util.*;

public class SkipList<T extends Comparable<T>> implements Collection<T> {
	private Node<T> head;
	private int size;
	IndexList<Integer> indexList;
	private final int indexListSize = 4;

	public SkipList() {
		this.head = new Node<>();
		this.size = 0;
		indexList = new IndexList<Integer>((Node<Integer>) head, this.indexListSize);
	}

	/**
	 * Returns the value at the specified position
	 * 
	 * @param index
	 * @return
	 */
	public T get(int index) {
		return null;
	}

	/**
	 * Inserts a value into the SkipList.
	 * 
	 * @param value
	 * @return 
	 */
	@Override
	public boolean add(T value) {


		return false;
	}

	/**
	 * Checks, whether a number is contained in the list.
	 * @param value
	 * @return boolean value whether number is contained in list
	 */
	public boolean contains(T value) {
		return false;
	}
	
	/**
	 * removes all elements form the list 
	 */
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * @return the size of the list
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		return super.toString();
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