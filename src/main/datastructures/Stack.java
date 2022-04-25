package main.datastructures;

/**
 * simple stack based on given List class
 * 
 * @author plmk
 *
 * @param <T> type of elements stored in ths stack
 */
public class Stack<T>{

	private final List<T> stack;
	
	public Stack(List<T> stack) {
		this.stack = stack;
	}
	
	/**
	 * @param t element to be added to top of stack
	 */
	public void push(T t) {
		
	}

	/**
	 * removes and returns element on top of stack
	 * 
	 * @return element on top of stack
	 */
	public T pop() {
		return null;
	}

	/**
	 * @return element on top of stack
	 */
	public T peek() {
		return null;
	}
}
