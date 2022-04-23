package datastructures;

/**
 * simple singularly linked LinkedList
 * 
 * @author plmil
 *
 * @param <T> type of elements stored in list
 */
public class List<T> {
	
	private Node<T> head;
	private int size;
	
	/**
	 * add the specified element at position 0
	 * 
	 * @param t element to prepend
	 */
	public void prepend(final T t) {
		this.head = new Node<T>(t, this.head);
		this.size++;
	}
	
	/**
	 * add the specified element at position \old(this.size)
	 * 
	 * @param t element to append
	 */
	public void append(final T t) {
		if(this.size == 0) {
			this.head = new Node<T>(t, null);
		} else {
			this.getNodeAt(this.size - 1).setNextNode(new Node<T>(t, null));
		}
		this.size++;
	}
	
	/**
	 * @param position of the element to return
	 * @return the element specified by the given position
	 */
	public T getElementAt(final int position) {
		return this.getNodeAt(position).getElement();
	}
	
	/**
	 * @param position of the node to return
	 * @return the node specified by the given position
	 */
	private Node<T> getNodeAt(final int position){
		if(position < 0 || position >= this.size) {
			throw new IllegalArgumentException("Position not valid!");
		}
		
		Node<T> currentNode = this.head;
		for(int i = 0; i < position; i++) {
			currentNode = currentNode.getNextNode();
		}
		return currentNode;
	}
	
	/**
	 * removes element at specified position at returns the corresponding element
	 *
	 * @param position position of the node to delete
	 * @return value stored in the deleted node
	 */
	public T removeElementAt(int position){
		Node<T> node2Delete = this.getNodeAt(position);
		if(position == 0) {
			this.head = node2Delete.getNextNode();
		} else {
			this.getNodeAt(position - 1).setNextNode(node2Delete.getNextNode());
		}
		this.size--;
		return node2Delete.getElement();
	}
	
	/**
	 * @return number of elements stored in the list
	 */
	public int getSize() {
		return this.size;
	}
	
	/**
	 * nodes to be used in the list
	 * 
	 * @author plmil
	 *
	 * @param <G> type of element stored in node
	 */
	private final class Node<G>{
		private final G element;
		private Node<G> nextNode;
		
		public Node(final G element, final Node<G> nextNode) {
			this.element = element;
			this.nextNode = nextNode;
		}

		public Node<G> getNextNode() {
			return nextNode;
		}

		public void setNextNode(final Node<G> nextNode) {
			this.nextNode = nextNode;
		}

		public G getElement() {
			return element;
		}
	}
}
