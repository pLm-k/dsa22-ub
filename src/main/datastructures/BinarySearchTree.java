package main.datastructures;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class BinarySearchTree<T extends Comparable<T>>{
	enum TreeTraversalType{INORDER,POSTORDER,PREORDER,LEVELORDER}
	
	private volatile BinaryTreeNode<T> root;

	public BinarySearchTree() {
		this.root = null;
	}

	public void insert(T t) {
		this.root = this.insert(this.root, t);
	}

	private BinaryTreeNode<T> insert(BinaryTreeNode<T> node, T t) {
		if (node == null) {
			BinaryTreeNode<T> newNode = new BinaryTreeNode<>();
			newNode.setValue(t);
			return newNode;
		}
		if (t.compareTo(node.getValue()) < 0) {
			node.setLeftChild(this.insert(node.getLeftChild(), t));
		} else if (t.compareTo(node.getValue()) > 0) {
			node.setRightChild(this.insert(node.getRightChild(), t));
		}
		return node;
	}

	public BinaryTreeNode<T> getRootNode() {
		return this.root;
	}

	public boolean isFull() {
		return this.root == null ? true: isFull(this.root);
	}
	
	private boolean isFull(BinaryTreeNode<T> node) {
		if(node.getLeftChild() == null && node.getRightChild() == null) {
			return true;
		}else if(node.getLeftChild() != null && node.getRightChild() != null) {
			return isFull(node.getLeftChild())&&isFull(node.getRightChild());
		}
		return false;
	}
	
	
	public Iterator<T> iterator(TreeTraversalType traversalType) {
		return new SimpleTreeIterator<T>(traversalType);
	}
	
	private class SimpleTreeIterator<G extends Comparable<T>> implements Iterator<T>{
		private final LinkedList<T> sortedList = new LinkedList<T>();
		
		public SimpleTreeIterator(TreeTraversalType traversalType) {
			if(BinarySearchTree.this.root == null) {
				return;
			}
			switch(traversalType) {
				case INORDER: fillListInorder(BinarySearchTree.this.root);
							break;
				case PREORDER: fillListPreorder(BinarySearchTree.this.root);
							break;
				case POSTORDER: fillListPostorder(BinarySearchTree.this.root);
							break;
				case LEVELORDER: fillListLevelorder(BinarySearchTree.this.root);
			}
			
		}
		
		@Override
		public boolean hasNext() {
			return !this.sortedList.isEmpty();
		}
		
		@Override
		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			return this.sortedList.poll();
		}		
		
		@Override
		public void remove(){
			throw new UnsupportedOperationException();
		}	
		
		private void fillListInorder(BinaryTreeNode<T> node) {
			if(node == null) {
				return;
			}
			fillListInorder(node.getLeftChild());
			this.sortedList.offer(node.getValue());
			fillListInorder(node.getRightChild());
		}
		
		private void fillListPreorder(BinaryTreeNode<T> node) {
			if(node == null) {
				return;
			}
			this.sortedList.offer(node.getValue());
			fillListPreorder(node.getLeftChild());
			fillListPreorder(node.getRightChild());
		}
		
		private void fillListPostorder(BinaryTreeNode<T> node) {
			if(node == null) {
				return;
			}
			fillListPostorder(node.getLeftChild());
			fillListPostorder(node.getRightChild());
			this.sortedList.offer(node.getValue());
		}
		
		private void fillListLevelorder(BinaryTreeNode<T> node) {
			LinkedList<BinaryTreeNode<T>>  nodeQueue = new LinkedList<BinaryTreeNode<T>>();
			nodeQueue.offer(node);
			while(!nodeQueue.isEmpty()) {
				BinaryTreeNode<T> currentNode = nodeQueue.poll();
				this.sortedList.offer(currentNode.getValue());
				if(currentNode.getLeftChild() != null) {
					nodeQueue.offer(currentNode.getLeftChild());
				}
				if(currentNode.getRightChild() != null) {
					nodeQueue.offer(currentNode.getRightChild());
				}
			}
		}
	}
	private class BinaryTreeNode<F extends Comparable<F>>{
		private F val;
		private BinaryTreeNode<F> leftChild;
		private BinaryTreeNode<F> rightChild;
		
		public F getValue() {
			return val;
		}
		public void setValue(F val) {
			this.val = val;
		}
		public BinaryTreeNode<F> getLeftChild() {
			return leftChild;
		}
		public void setLeftChild(BinaryTreeNode<F> leftChild) {
			this.leftChild = leftChild;
		}
		public BinaryTreeNode<F> getRightChild() {
			return rightChild;
		}
		public void setRightChild(BinaryTreeNode<F> rightChild) {
			this.rightChild = rightChild;
		}
		
	}
}
