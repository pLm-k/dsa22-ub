package main.datastructures.iteratorDSA;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class SimpleList<T extends Comparable<T>>{

	private final List<T> list;

	public SimpleList() {
		list = new ArrayList<T>();
	}

	public void append(T element) {
		list.add(element);
	}

	public int getSize() {
		return list.size();
	}

	public T getElement(int index) {
		return list.get(index);
	}

	public Iterator<T> iterator() {
		return skippingIterator(1);
	}

	public Iterator<T> skippingIterator(int numberSkippedElements) {
		return new Iterator<T>() {
			int pointer;
			int skippingVal = numberSkippedElements;
			
			public boolean hasNext() {
				return this.pointer < getSize();
			}
			
			public T next() {
				if(!hasNext()) {
					throw new NoSuchElementException("there are no more elements to iterate over!");
				}
				
				T returnElement = getElement(this.pointer);
				this.pointer+=this.skippingVal;
				return returnElement;
			}
			
			public void remove() {
				throw new UnsupportedOperationException("remove is not supported by this iterator implementation!");
			}
		};
	}

}
