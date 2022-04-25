package main.datastructures;

/**
 * simple queue based on given List class
 * 
 * @author plmk
 *
 * @param <T> type of elements stored in the queue
 */
public class Queue<T> {

	private final List<T> queue;
	
	public Queue(List<T> queue) {
		this.queue = queue;
	}
	
	/**
	 * @param t element to be added to back of queue
	 */
	public void queue(T t) {
		
	}

	/**
	 * removes and returns element at front of queue
	 * 
	 * @return element at front of queue
	 */
	public T dequeue() {
		return null;
	}

	/**
	 * @return element at front of queue
	 */
	public T front() {
		return null;
	}
}
